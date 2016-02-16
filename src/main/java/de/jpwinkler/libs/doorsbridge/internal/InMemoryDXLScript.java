package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;

public class InMemoryDXLScript implements DXLScript {

    private final String dxl;

    public InMemoryDXLScript(final String dxl) {
        super();
        this.dxl = dxl;
    }

    @Override
    public String getDXL() throws IOException {
        return dxl;
    }

    public String getDxl() {
        return dxl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dxl == null) ? 0 : dxl.hashCode());
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
        final InMemoryDXLScript other = (InMemoryDXLScript) obj;
        if (dxl == null) {
            if (other.dxl != null) {
                return false;
            }
        } else if (!dxl.equals(other.dxl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InMemoryDXLScript [dxl=" + dxl + "]";
    }

}
