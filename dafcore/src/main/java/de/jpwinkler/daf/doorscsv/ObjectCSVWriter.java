package de.jpwinkler.daf.doorscsv;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class ObjectCSVWriter extends OutputStreamWriter {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withQuoteMode(QuoteMode.ALL)
            .withRecordSeparator("\r\n");

    private final CSVPrinter csvPrinter;
    private final String[] header;

    public ObjectCSVWriter(final String[] header, final OutputStream out, final Charset cs) throws IOException {
        super(out, cs);
        csvPrinter = new CSVPrinter(this, FORMAT.withHeader(header));
        this.header = header;
    }

    public ObjectCSVWriter(final String[] header, final OutputStream out, final CharsetEncoder enc) throws IOException {
        super(out, enc);
        csvPrinter = new CSVPrinter(this, FORMAT.withHeader(header));
        this.header = header;
    }

    public ObjectCSVWriter(final String[] header, final OutputStream out, final String charsetName) throws IOException {
        super(out, charsetName);
        csvPrinter = new CSVPrinter(this, FORMAT.withHeader(header));
        this.header = header;
    }

    public ObjectCSVWriter(final String[] header, final OutputStream out) throws IOException {
        super(out);
        csvPrinter = new CSVPrinter(this, FORMAT.withHeader(header));
        this.header = header;
    }

    public void writeObject(final DoorsObject object) throws IOException {
        final Object[] values = new Object[header.length];
        for (int i = 0; i < header.length; i++) {
            final String attribute = header[i];
            values[i] = object.getAttributes().get(attribute);
        }
        csvPrinter.printRecord(values);
    }

}
