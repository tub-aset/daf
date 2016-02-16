package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ExternalDXLScript implements DXLScript {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dxlFile == null) ? 0 : dxlFile.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExternalDXLScript other = (ExternalDXLScript) obj;
        if (dxlFile == null) {
            if (other.dxlFile != null) {
                return false;
            }
        } else if (!dxlFile.equals(other.dxlFile)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalDXLScript [dxlFile=" + dxlFile + "]";
    }

}
