package de.jpwinkler.daf.dafcore.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;

public class ModuleCSVParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private DoorsModule buildModuleModel(final List<CSVRecord> records) throws CSVParseException {
        final CSVFactory factory = CSVPackage.eINSTANCE.getCSVFactory();

        final DoorsModule module = factory.createDoorsModule();

        DoorsTreeNode current = module;
        int currentLevel = 0;

        for (final CSVRecord record : records) {
            final int objectLevel = Integer.parseInt(record.get("Object Level"));

            while (objectLevel <= currentLevel) {
                currentLevel--;
                current = current.getParent();
            }

            if (objectLevel > currentLevel + 1) {
                current = current.getObjects().get(current.getObjects().size() - 1);
                currentLevel++;
            }

            final DoorsObject newObject = factory.createDoorsObject();

            newObject.setModule(module);

            for (final Entry<String, String> e : record.toMap().entrySet()) {
                switch (e.getKey()) {
                case "Object Identifier":
                    newObject.setObjectIdentifier(e.getValue());
                    break;
                case "Object Level":
                    newObject.setObjectLevel(Integer.parseInt(e.getValue()));
                    break;
                case "Object Number":
                    newObject.setObjectNumber(e.getValue());
                    break;
                case "Created By":
                    newObject.setCreatedBy(e.getValue());
                    break;
                case "Created Thru":
                    newObject.setCreatedThru(e.getValue());
                    break;
                case "Created On":
                    try {
                        newObject.setCreatedOn(new SimpleDateFormat("dd MMMM yyyy", java.util.Locale.ENGLISH).parse(e.getValue()));
                    } catch (final ParseException e1) {
                        throw new CSVParseException("Could not parse date string \"" + e.getValue() + "\"", e1);
                    }
                    break;
                case "Absolute Number":
                    newObject.setAbsoluteNumber(Integer.parseInt(e.getValue()));
                    break;
                case "Last Modified On":
                    try {
                        newObject.setLastModifiedOn(new SimpleDateFormat("dd MMMM yyyy", java.util.Locale.ENGLISH).parse(e.getValue()));
                    } catch (final ParseException e1) {
                        throw new CSVParseException("Could not parse date string \"" + e.getValue() + "\"", e1);
                    }
                    break;
                case "Last Modified By":
                    newObject.setLastModifiedBy(e.getValue());
                    break;
                case "Object Text":
                    newObject.setObjectText(e.getValue());
                    break;
                case "Object Short Text":
                    newObject.setObjectShortText(e.getValue());
                    break;
                case "Object Heading":
                    newObject.setObjectHeading(e.getValue());
                    break;
                }
                newObject.getAttributes().put(e.getKey(), e.getValue());
            }

            current.getObjects().add(newObject);
        }

        return module;
    }

    public DoorsModule parseCSV(final File file) throws IOException, CSVParseException {
        try (InputStream is = new FileInputStream(file)) {
            final DoorsModule parseCSV = parseCSV(is);
            parseCSV.setName(FilenameUtils.getBaseName(file.getAbsolutePath()));
            return parseCSV;
        }
    }

    public DoorsModule parseCSV(final InputStream is) throws IOException, CSVParseException {
        final String csvString = IOUtils.toString(new BOMInputStream(is), Charset.defaultCharset());
        final List<CSVRecord> records = CSVParser.parse(csvString, FORMAT).getRecords();
        final DoorsModule parseCSV = buildModuleModel(records);
        return parseCSV;
    }

}
