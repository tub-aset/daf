package de.jpwinkler.libs.doorsbridge.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DoorsScriptBuilder {

    private final List<DXLScript> scripts = new ArrayList<>();
    private final Map<String, String> variables = new HashMap<>();

    public DoorsScriptBuilder addScript(final DXLScript name) {
        scripts.add(name);
        return this;
    }

    public DoorsScriptBuilder setVariable(final String variable, final String value) {
        if (value != null) {
            variables.put(variable, value);
        }
        return this;
    }

    public String build() throws ScriptNotFoundException, IOException {
        final StringBuilder builder = new StringBuilder();

        for (final DXLScript script : scripts) {
            builder.append(script.getDXL());
            builder.append("\n");
        }

        String script = builder.toString();

        for (final Entry<String, String> e : variables.entrySet()) {
            final String variable = "$$" + e.getKey() + "$$";

            script = script.replace(variable, escapeString(e.getValue()));

        }

        script = script.replaceAll("\\$\\$(.*?)\\$\\$", "");

        return script;
    }

    private String escapeString(final String value) {
        return value.replace("\\", "\\\\");
    }

}
