package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;
import java.io.InputStream;
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
                final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dxl/" + name);
                if (resourceAsStream == null) {
                    throw new ScriptNotFoundException("Could not read script " + name + " (stream was null)");
                }
                fileContents = IOUtils.toString(resourceAsStream);
            } catch (final IOException e) {
                throw new ScriptNotFoundException(e);
            }
            scripts.put(name, fileContents);
            return fileContents;
        }
    }

}
