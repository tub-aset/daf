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
        variables.put(variable, value);
        return this;
    }

    public String build() throws ScriptNotFoundException, IOException {
        final StringBuilder builder = new StringBuilder();

        for (final DXLScript script : scripts) {
            builder.append(script.getDXL());
            builder.append("\n");
        }

        for (final Entry<String, String> e : variables.entrySet()) {
            final String variable = "$$" + e.getKey() + "$$";
            int index = builder.indexOf(variable);
            while (index != -1) {
                final String newValue = escapeString(e.getValue());
                builder.replace(index, index + variable.length(), newValue);
                index += newValue.length();
                index = builder.indexOf(variable, index);
            }
        }

        return builder.toString();
    }

    private String escapeString(final String value) {
        return value.replace("\\", "\\\\");
    }

}
