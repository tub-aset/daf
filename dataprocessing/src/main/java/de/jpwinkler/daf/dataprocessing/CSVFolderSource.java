package de.jpwinkler.daf.dataprocessing;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;

public class CSVFolderSource implements DoorsModuleSource {

    private static final Logger LOGGER = Logger.getLogger(CSVFolderSource.class.getName());

    private final List<String> moduleFileNames;

    private int position;

    private final ModuleCSVParser parser = new ModuleCSVParser();

    private final Predicate<String> filenameFilter;

    public CSVFolderSource(final String folder, final boolean recursiveModuleSearch) {
        this(folder, recursiveModuleSearch, (fn) -> true);
    }

    public CSVFolderSource(final String folder, final boolean recursiveModuleSearch, final Predicate<String> filenameFilter) {
        super();
        this.filenameFilter = filenameFilter;
        moduleFileNames = new LinkedList<>();
        addModules(new File(folder), recursiveModuleSearch);
        position = 0;
    }

    private void addModules(final File file, final boolean recursiveModuleSearch) {
        for (final File child : file.listFiles()) {
            if (child.isFile() && child.getName().endsWith(".csv") && filenameFilter.test(child.getName())) {
                moduleFileNames.add(child.getAbsolutePath());
            } else if (recursiveModuleSearch && child.isDirectory()) {
                addModules(child, recursiveModuleSearch);
            }
        }
    }

    @Override
    public DoorsModule next() throws IOException {
        if (available() == 0) {
            return null;
        }

        final String moduleName = moduleFileNames.get(position);

        LOGGER.info(String.format("Parsing CSV file %s...", moduleName));

        try {
            final DoorsModule module = parser.parseCSV(new File(moduleName));
            position++;
            return module;
        } catch (final CSVParseException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int available() {
        return moduleFileNames.size() - position;
    }

    @Override
    public void reset() {
        position = 0;
    }

}
