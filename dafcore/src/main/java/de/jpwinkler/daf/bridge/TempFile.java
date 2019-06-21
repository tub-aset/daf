/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public final class TempFile extends File implements AutoCloseable {

    private static final String PREFIX = "doorsbridge";

    public TempFile(File file) {
        super(file.getAbsolutePath());
        this.deleteOnExit();
    }

    public TempFile() throws IOException {
        this(File.createTempFile(PREFIX, null));
    }

    public TempFile(String prefix, String suffix) throws IOException {
        this(File.createTempFile(PREFIX, suffix));
    }

    public TempFile(String prefix, String suffix, File directory) throws IOException {
        this(File.createTempFile(PREFIX, suffix, directory));
    }

    @Override
    public void close() {
        this.delete();
    }

}
