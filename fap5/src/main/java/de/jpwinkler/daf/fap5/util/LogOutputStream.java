package de.jpwinkler.daf.fap5.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogOutputStream extends OutputStream {

    private final Logger logger;
    private final Level level;

    StringBuffer buffer = new StringBuffer();

    public LogOutputStream(final Logger logger, final Level level) {
        super();
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void write(final int b) throws IOException {
        if (b == 13) {
            return;
        }
        if (b == 10) {
            logger.log(level, buffer.toString());
            buffer = new StringBuffer();
        } else {
            buffer.append((char) b);
        }

    }

    @Override
    public void close() throws IOException {
        super.close();
        if (buffer.length() > 0) {
            logger.log(level, buffer.toString());
        }
    }

}
