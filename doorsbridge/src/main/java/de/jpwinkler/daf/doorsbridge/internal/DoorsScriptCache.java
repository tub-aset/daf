package de.jpwinkler.daf.doorsbridge.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class DoorsScriptCache {

    private final Map<String, String> scripts = new HashMap<>();

    public String getScript(final String name) throws ScriptNotFoundException {
        if (scripts.containsKey(name)) {
            return scripts.get(name);
        } else {
            String fileContents;
            try {
                fileContents = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("dxl/" + name));
            } catch (final IOException e) {
                throw new ScriptNotFoundException(e);
            }
            scripts.put(name, fileContents);
            return fileContents;
        }
    }

}
