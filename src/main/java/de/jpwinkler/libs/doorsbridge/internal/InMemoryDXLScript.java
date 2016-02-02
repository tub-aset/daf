package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;

public class InMemoryDXLScript extends DXLScript {

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

}
