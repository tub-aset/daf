package de.jpwinkler.daf.csv;

import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class ModuleMetaDataParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    public static Map<String, String> readModuleMetaData(final File metaDataFile) throws IOException {

        final Map<String, String> metadata;
        try ( CSVParser parser = CSVParser.parse(metaDataFile, Charset.forName("UTF-8"), FORMAT)) {
            metadata = new HashMap<>();
            for (final CSVRecord record : parser.getRecords()) {
                metadata.put(record.get(0), record.get(1));
            }
        }

        return metadata;
    }

    public static void writeModuleMetaData(final File metaDataFile, Map<String, String> metaData) throws IOException {
        try ( CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(metaDataFile), FORMAT)) {
            for (Entry<String, String> e : metaData.entrySet()) {
                csvPrinter.printRecord(e.getKey(), e.getValue());
            }
        }
    }
}
