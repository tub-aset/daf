package de.jpwinkler.daf.dafcore.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class ModuleMetaDataParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    public void parseModuleMetaData(final File metaDataFile, final DoorsModule module) throws IOException {

        final CSVParser parser = CSVParser.parse(metaDataFile, Charset.forName("Cp1252"), FORMAT);

        for (final CSVRecord record : parser.getRecords()) {
            module.getAttributes().put(record.get(0), record.get(1));
            if (record.get(0).equals("Name")) {
                module.setName(record.get(1));
            }
            if (record.get(0).equals("url")) {
                module.setUrl(record.get(1));
            }
            if (record.get(0).equals("path")) {
                module.setPath(record.get(1));
            }
        }

    }

}
