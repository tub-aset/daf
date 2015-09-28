package de.jpwinkler.daf.dafcore.csv;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ModuleCSVWriter extends ModuleWriter {

    private static final Logger LOGGER = Logger.getLogger(ModuleCSVWriter.class.getName());

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withHeader()
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withQuoteMode(QuoteMode.ALL)
            .withRecordSeparator("\r\n");

    public ModuleCSVWriter(final OutputStream out, final Charset cs) {
        super(out, cs);
    }

    public ModuleCSVWriter(final OutputStream out, final CharsetEncoder enc) {
        super(out, enc);
    }

    public ModuleCSVWriter(final OutputStream out, final String charsetName) throws UnsupportedEncodingException {
        super(out, charsetName);
    }

    public ModuleCSVWriter(final OutputStream out) {
        super(out);
    }

    @Override
    public void writeModule(final DoorsModule module) throws IOException {
        final String[] header = module.getAttributeDefinitions().stream().map(ad -> ad.getName()).toArray(size -> new String[size]);
        final CSVPrinter printer = new CSVPrinter(this, FORMAT.withHeader(header));
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                final Object[] values = new Object[header.length];
                for (int i = 0; i < header.length; i++) {
                    final String attribute = header[i];
                    values[i] = object.getAttributes().get(attribute);
                }
                try {
                    printer.printRecord(values);
                } catch (final IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
                return true;
            }
        });
        printer.flush();
        printer.close();
    }

}
