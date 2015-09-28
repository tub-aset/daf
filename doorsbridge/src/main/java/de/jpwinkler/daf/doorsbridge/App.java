package de.jpwinkler.daf.doorsbridge;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( final String[] args )
    {
        try {
            DoorsApplicationFactory.getDoorsApplication().ack("Hello World!");
        } catch (DoorsException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
