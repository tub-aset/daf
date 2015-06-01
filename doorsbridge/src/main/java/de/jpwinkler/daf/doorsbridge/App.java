package de.jpwinkler.daf.doorsbridge;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( final String[] args )
    {
        try {
            DoorsApplicationFactory.getDoorsApplication().ack("Hello World!");
        } catch (DoorsException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
