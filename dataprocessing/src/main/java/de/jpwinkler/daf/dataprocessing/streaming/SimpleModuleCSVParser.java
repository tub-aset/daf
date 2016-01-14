package de.jpwinkler.daf.dataprocessing.streaming;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

public class SimpleModuleCSVParser {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private final CSVParser parser;

    public SimpleModuleCSVParser(final String fileName) throws IOException {
        parser = new CSVParser(new InputStreamReader(new BOMInputStream(new FileInputStream(fileName))), FORMAT);
    }

    public Iterator<SimpleDoorsObject> iterator() {
        return new Iterator<SimpleDoorsObject>() {

            private final Iterator<CSVRecord> csvIterator = parser.iterator();

            @Override
            public SimpleDoorsObject next() {
                return createSimpleDoorsObject(csvIterator.next());
            }

            @Override
            public boolean hasNext() {
                return csvIterator.hasNext();
            }
        };
    }

    private SimpleDoorsObject createSimpleDoorsObject(final CSVRecord record) {
        final SimpleDoorsObject object = new SimpleDoorsObject();

        for (final Entry<String, String> e : record.toMap().entrySet()) {
            object.setAttribute(e.getKey(), e.getValue());
        }

        return object;
    }

    public List<SimpleDoorsObject> getObjects() throws IOException {
        return parser.getRecords().stream().map(record -> createSimpleDoorsObject(record)).collect(Collectors.toList());
    }
}
