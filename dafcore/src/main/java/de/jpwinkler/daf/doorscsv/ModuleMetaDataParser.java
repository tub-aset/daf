package de.jpwinkler.daf.doorscsv;

import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ModuleMetaDataParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    public Map<String, String> parseModuleMetaData(final File metaDataFile) throws IOException {

        final CSVParser parser = CSVParser.parse(metaDataFile, Charset.forName("UTF-8"), FORMAT);

        final Map<String, String> metadata = new HashMap<>();

        for (final CSVRecord record : parser.getRecords()) {
            metadata.put(record.get(0), record.get(1));
        }
        parser.close();

        return metadata;
    }

    public void updateModuleMetaData(final File metaDataFile, final DoorsModule module) throws IOException {
        final Map<String, String> metadata = parseModuleMetaData(metaDataFile);

        for (final Entry<String, String> e : metadata.entrySet()) {
            module.getAttributes().put(e.getKey(), e.getValue());
            if (e.getKey().equals("Name")) {
                module.setName(e.getValue());
            }
            if (e.getKey().equals("__url__")) {
                module.setUrl(e.getValue());
            }
            if (e.getKey().equals("__path__")) {
                module.setPath(e.getValue());
            }
            if (e.getKey().equals("__view__")) {
                module.setView(e.getValue());
            }

        }
    }


}
