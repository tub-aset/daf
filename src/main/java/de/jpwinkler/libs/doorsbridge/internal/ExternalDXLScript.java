package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ExternalDXLScript extends DXLScript {

    private final File dxlFile;

    public ExternalDXLScript(final File dxlFile) {
        super();
        this.dxlFile = dxlFile;
    }

    @Override
    public String getDXL() throws IOException {
        return FileUtils.readFileToString(dxlFile);
    }

    public File getDxlFile() {
        return dxlFile;
    }

}
