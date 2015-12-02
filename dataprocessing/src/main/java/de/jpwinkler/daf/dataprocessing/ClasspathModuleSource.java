package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;

public class ClasspathModuleSource implements DoorsModuleSource {

    private final Class<?> theClass;
    private final List<String> resourceNames;
    private int position;

    private final ModuleCSVParser parser = new ModuleCSVParser();

    public ClasspathModuleSource(final Class<?> theClass, final List<String> resourceNames) {
        super();
        this.theClass = theClass;
        this.resourceNames = resourceNames;
        position = 0;
    }

    @Override
    public DoorsModule next() throws IOException {
        if (available() > 0) {
            try {
                final DoorsModule module = parser.parseCSV(theClass.getResourceAsStream(resourceNames.get(position)));
                position++;
                return module;
            } catch (final CSVParseException e) {
                throw new IOException(e);
            }
        }
        return null;
    }

    @Override
    public int available() {
        return resourceNames.size() - position;
    }

    @Override
    public void reset() {
        position = 0;
    }

}
