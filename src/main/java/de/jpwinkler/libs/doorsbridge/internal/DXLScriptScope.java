package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;

public class DXLScriptScope implements DXLScript {

    private final DoorsScriptBuilder builder = new DoorsScriptBuilder();

    public DoorsScriptBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getDXL() throws IOException {
        return builder.build();
    }

    @Override
    public String toString() {
        return "DXLScriptScope";
    }

}
