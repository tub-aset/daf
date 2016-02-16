package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class InternalDXLScript implements DXLScript {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scriptName == null) ? 0 : scriptName.hashCode());
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
        final InternalDXLScript other = (InternalDXLScript) obj;
        if (scriptName == null) {
            if (other.scriptName != null) {
                return false;
            }
        } else if (!scriptName.equals(other.scriptName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InternalDXLScript [scriptName=" + scriptName + "]";
    }

}
