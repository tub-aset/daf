package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyTailer implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(MyTailer.class.getName());

    private final OutputStream out;

    private final File file;

    private boolean run = true;

    public MyTailer(final File file, final OutputStream out) {
        this.file = file;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            final RandomAccessFile raf = new RandomAccessFile(file, "r");

            long position = raf.length();
            raf.seek(position);

            long length = raf.length();
            while (position < length || run) {

                while (position < length) {

                    final byte[] b = new byte[1000];
                    final int numRead = raf.read(b);

                    out.write(b, 0, numRead);
                    out.flush();
                    position = raf.getFilePointer();

                }

                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                }
                length = raf.length();

            }

            raf.close();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void stop() {
        run = false;
    }

    public static MyTailer create(final File file, final OutputStream os) {
        final MyTailer myTailer = new MyTailer(file, os);

        final Thread t = new Thread(myTailer);
        t.setDaemon(true);
        t.start();

        return myTailer;
    }

}
