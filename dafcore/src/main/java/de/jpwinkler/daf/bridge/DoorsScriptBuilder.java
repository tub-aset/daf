/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.bridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DoorsScriptBuilder {

    private final List<DXLScript> preamble = new ArrayList<>();
    private final Set<DXLScript> libraries = new LinkedHashSet<>();
    private final List<DXLScript> scripts = new ArrayList<>();
    private final Map<String, String> variables = new HashMap<>();

    private DoorsScriptBuilder parentBuilder;

    public DoorsScriptBuilder() {
        this(null);
    }

    public DoorsScriptBuilder(DoorsScriptBuilder parentBuilder) {
        this.parentBuilder = parentBuilder;
    }

    public DoorsScriptBuilder beginScope() {
        DoorsScriptBuilder scopeBuilder = new DoorsScriptBuilder(this);
        scripts.add(DXLScript.fromBuilder(scopeBuilder));
        return scopeBuilder;
    }

    public DoorsScriptBuilder endScope() {
        return parentBuilder;
    }

    public DoorsScriptBuilder addPreamble(final DXLScript script) {
        preamble.add(script);
        return this;
    }

    public DoorsScriptBuilder addLibrary(final DXLScript library) {
        libraries.add(library);
        return this;
    }

    public DoorsScriptBuilder addScript(final DXLScript script) {
        scripts.add(script);
        return this;
    }

    public DoorsScriptBuilder setVariable(final String variable, final String value) {
        variables.put(variable, value);
        return this;
    }

    public String build() {
        final StringBuilder builder = new StringBuilder();

        for (final DXLScript script : preamble) {
            builder.append(script.getDXL());
            builder.append("\n");
        }

        for (final DXLScript library : libraries) {
            builder.append(library.getDXL());
            builder.append("\n");
        }

        for (final DXLScript script : scripts) {
            // Curly brackets are used to keep variables local within their
            // script and avoid name clashes. That would happen if the same
            // script is used multiple times.
            builder.append("{");
            builder.append(script.getDXL());
            builder.append("}");
            builder.append("\n");
        }

        // replace variables with variables from parent builders or our own variables,
        // the ones closer to this builder replacing the ones farther away.
        String script = builder.toString();
        DoorsScriptBuilder currentBuilder = this;
        List<DoorsScriptBuilder> builders = new ArrayList<>();
        do {
            builders.add(0, currentBuilder);
            currentBuilder = currentBuilder.parentBuilder;
        } while (currentBuilder != null);
        
        HashMap<String, String> variables = new HashMap<>();
        for(DoorsScriptBuilder bd : builders) {
            variables.putAll(bd.variables);
        }

        for (final Entry<String, String> e : variables.entrySet()) {
            final String variable = "$$" + e.getKey() + "$$";

            script = script.replace(variable, escapeString(e.getValue()));
        }

        // Replace any unknown variables.
        // TODO: log unknown variables as warnings.
        script = script.replaceAll("\\$\\$(.*?)\\$\\$", "");

        return script;
    }

    private String escapeString(final String value) {
        return value == null ? "" : value.replace("\\", "\\\\");
    }

}
