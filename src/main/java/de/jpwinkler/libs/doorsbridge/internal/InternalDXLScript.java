package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class InternalDXLScript extends DXLScript {

    private final String scriptName;

    public InternalDXLScript(final String scriptName) {
        super();
        this.scriptName = scriptName;
    }

    @Override
    public String getDXL() throws IOException {
        final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dxl/" + scriptName);
        if (resourceAsStream == null) {
            throw new ScriptNotFoundException("Could not read script " + scriptName + " (stream was null)");
        }
        return IOUtils.toString(resourceAsStream);
    }

    public String getScriptName() {
        return scriptName;
    }

}
