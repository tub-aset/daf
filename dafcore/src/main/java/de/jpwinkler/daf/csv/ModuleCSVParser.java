package de.jpwinkler.daf.csv;

import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class ModuleCSVParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private DoorsModule buildModuleModel(final CSVParser csvParser) throws CSVParseException, NumberFormatException, IOException {
        final DoorsFactory factory = DoorsPackage.eINSTANCE.getDoorsFactory();

        final DoorsModule module = factory.createDoorsModule();

        DoorsTreeNode current = module;
        int currentLevel = 0;

        for (final CSVRecord record : csvParser.getRecords()) {
            final int objectLevel = Integer.parseInt(record.get("Object Level"));

            while (objectLevel <= currentLevel) {
                currentLevel--;
                current = current.getParent();
            }

            while (objectLevel > currentLevel + 1) {
                if (current.getChildren().isEmpty()) {
                    final DoorsObject createDoorsObject = factory.createDoorsObject();
                    createDoorsObject.setObjectHeading("");
                    createDoorsObject.setObjectText("");
                    createDoorsObject.setObjectLevel(currentLevel + 1);
                    current.getChildren().add(createDoorsObject);
                }
                current = current.getChildren().get(current.getChildren().size() - 1);
                currentLevel++;
            }

            final DoorsObject newObject = factory.createDoorsObject();

            newObject.setModule(module);

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
            final DoorsModule parseCSV = parseCSV(is);
            parseCSV.setName(FilenameUtils.getBaseName(file.getAbsolutePath()));
            return parseCSV;
        }
    }

    public DoorsModule parseCSV(final InputStream is) throws IOException, CSVParseException {
        final String csvString = IOUtils.toString(new BOMInputStream(is), Charset.forName("UTF-8"));
        final DoorsModule parseCSV = buildModuleModel(CSVParser.parse(csvString, FORMAT));
        return parseCSV;
    }

}
