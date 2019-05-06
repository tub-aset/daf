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
package de.jpwinkler.daf.bridge.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class DoorsScriptBuilder {

    private final List<DXLScript> preamble = new ArrayList<>();
    private final Set<DXLScript> libraries = new LinkedHashSet<>();
    private final List<DXLScript> scripts = new ArrayList<>();
    private final Map<String, String> variables = new HashMap<>();

    private DXLScriptScope currentScope;

    public DoorsScriptBuilder beginScope() {
        if (currentScope == null) {
            currentScope = new DXLScriptScope();
            scripts.add(currentScope);
        } else {
            throw new RuntimeException("Already in a scope.");
        }
        return this;
    }

    public DoorsScriptBuilder endScope() {
        if (currentScope == null) {
            throw new RuntimeException("Not in a scope.");
        }
        currentScope = null;
        return this;
    }

    public DoorsScriptBuilder addPreamble(final DXLScript script) {
        preamble.add(script);
        return this;
    }

    public DoorsScriptBuilder addLibrary(final DXLScript library) {
        libraries.add(library);
        return this;
    }

    public DoorsScriptBuilder addScript(final DXLScript scritp) {
        if (currentScope != null) {
            currentScope.getBuilder().addScript(scritp);
        } else {
            scripts.add(scritp);
        }
        return this;
    }

    public DoorsScriptBuilder setVariable(final String variable, final String value) {
        if (currentScope != null) {
            currentScope.getBuilder().setVariable(variable, value);
        } else if (value != null) {
            variables.put(variable, value);
        }
        return this;
    }

    public String build() throws IOException {
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

        String script = builder.toString();

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
        return value.replace("\\", "\\\\");
    }

}
