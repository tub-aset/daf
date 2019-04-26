package de.jpwinkler.daf.doorscsv.util;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ConsoleLogFormatter extends Formatter {

    private static final int MAX_SOURCE_LENGTH = 50;

    @Override
    public String format(final LogRecord record) {
        String source = record.getSourceClassName() + "." + record.getSourceMethodName();
        if (source.length() > MAX_SOURCE_LENGTH) {
            source = source.substring(source.length() - MAX_SOURCE_LENGTH);
        }

        return String.format("%tT | %7s | %" + MAX_SOURCE_LENGTH + "s | %s%n", new Date(record.getMillis()), record.getLevel(), source, record.getMessage());
    }

}
