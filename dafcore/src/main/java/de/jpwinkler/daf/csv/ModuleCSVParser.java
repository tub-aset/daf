package de.jpwinkler.daf.csv;

import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsSystemAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

public class ModuleCSVParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private DoorsModule buildModuleModel(final CSVParser csvParser, String moduleName) throws CSVParseException, NumberFormatException, IOException {
        final DoorsFactory factory = DoorsPackage.eINSTANCE.getDoorsFactory();

        final DoorsModule module = DoorsModelUtil.createModule(moduleName);

        DoorsTreeNode current = module;
        int currentLevel = 0;
        if (!csvParser.getHeaderMap().containsKey(DoorsSystemAttributes.OBJECT_LEVEL.getKey())) {
            throw new CSVParseException("This is no DOORS CSV file: Object Level missing");
        }

        for (final CSVRecord record : csvParser.getRecords()) {
            final int objectLevel = Integer.parseInt(record.get(DoorsSystemAttributes.OBJECT_LEVEL.getKey()));

            while (objectLevel <= currentLevel) {
                currentLevel--;
                current = current.getParent();
            }

            while (objectLevel > currentLevel + 1) {
                if (current.getChildren().isEmpty()) {
                    final DoorsObject createDoorsObject = DoorsModelUtil.createObject(current, "");
                    current.getChildren().add(createDoorsObject);
                }
                current = current.getChildren().get(current.getChildren().size() - 1);
                currentLevel++;
            }

            final DoorsObject newObject = DoorsModelUtil.createObject(module, "");

            newObject.setParent(module);

            for (final Entry<String, String> e : record.toMap().entrySet()) {
                newObject.getAttributes().put(e.getKey(), e.getValue());
            }

            current.getChildren().add(newObject);
        }

        module.setObjectAttributes(new ArrayList<>(csvParser.getHeaderMap().keySet()));

        return module;
    }

    public DoorsModule parseCSV(final File file) throws IOException {
        try ( InputStream is = new FileInputStream(file)) {
            return parseCSV(is, FilenameUtils.getBaseName(file.getAbsolutePath()));
        }
    }

    public DoorsModule parseCSV(final InputStream is, String moduleName) throws IOException, CSVParseException {
        return buildModuleModel(new CSVParser(new InputStreamReader(is, Charset.forName("UTF-8")), FORMAT), moduleName);
    }

}
