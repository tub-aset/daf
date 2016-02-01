package de.jpwinkler.daf.dataprocessing.streaming;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ModuleIterator implements Iterator<DoorsObject> {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private final CSVParser parser;

    private final Iterator<CSVRecord> csvIterator;

    public ModuleIterator(final String fileName) throws IOException {
        parser = new CSVParser(new InputStreamReader(new BOMInputStream(new FileInputStream(fileName))), FORMAT);
        csvIterator = parser.iterator();
    }

    private DoorsObject createDoorsObject(final CSVRecord record) {
        final DoorsObject object = CSVFactory.eINSTANCE.createDoorsObject();

        for (final Entry<String, String> e : record.toMap().entrySet()) {
            object.getAttributes().put(e.getKey(), e.getValue());
        }

        return object;
    }

    @Override
    public DoorsObject next() {
        return createDoorsObject(csvIterator.next());
    }

    @Override
    public boolean hasNext() {
        return csvIterator.hasNext();
    }
}
