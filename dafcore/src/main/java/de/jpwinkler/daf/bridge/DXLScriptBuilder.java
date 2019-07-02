package de.jpwinkler.daf.bridge;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class DXLScriptBuilder extends DXLScript {

    private final List<DXLScript> preambles = new ArrayList<>();
    private final List<DXLScript> scripts = new ArrayList<>();
    private final Map<String, String> variables = new HashMap<>();

    private DXLScriptBuilder parentBuilder;

    public DXLScriptBuilder() {
        this(null);
    }

    public DXLScriptBuilder(DXLScriptBuilder parentBuilder) {
        this.parentBuilder = parentBuilder;
    }

    @Override
    public URI getSource() {
        return this.scripts.isEmpty() ? null : this.scripts.get(0).getSource();
    }

    public DXLScriptBuilder beginScope() {
        DXLScriptBuilder builder = new DXLScriptBuilder(this);
        scripts.add(builder);
        return builder;
    }

    public DXLScriptBuilder endScope() {
        return parentBuilder;
    }

    public DXLScriptBuilder addPreamble(DXLScript library) {
        preambles.add(library);
        return this;
    }

    public DXLScriptBuilder addScript(DXLScript script) {
        scripts.add(script);
        return this;
    }

    public DXLScriptBuilder setVariable(String variable, String value) {
        variables.put(variable, value);
        return this;
    }

    Stream<Pair<DXLScriptBuilder, String>> getVariable(String variable) {
        return Stream.concat(
                Stream.concat(this.preambles.stream(), this.scripts.stream())
                        .filter(scr -> scr instanceof DXLScriptBuilder)
                        .map(scr -> (DXLScriptBuilder) scr)
                        .flatMap(scr -> scr.getVariable(variable)),
                variables.containsKey(variable) ? Stream.of(Pair.of(this, variables.get(variable))) : Stream.empty());
    }

    List<DXLScript> getPreambles() {
        return preambles;
    }

    List<DXLScript> getScripts() {
        return scripts;
    }

    Map<String, String> getVariables() {
        DXLScriptBuilder currentBuilder = this;
        List<DXLScriptBuilder> builders = new ArrayList<>();
        do {
            builders.add(0, currentBuilder);
            currentBuilder = currentBuilder.parentBuilder;
        } while (currentBuilder != null);

        HashMap<String, String> variables = new HashMap<>();
        for (DXLScriptBuilder bd : builders) {
            variables.putAll(bd.variables);
        }

        return variables;
    }

    @Override
    public String build() {
        final StringBuilder builder = new StringBuilder();

        for (final DXLScript preamble : preambles) {
            builder.append(preamble.build());
            builder.append("\n");
        }

        for (final DXLScript script : scripts) {
            // Curly brackets are used to keep variables local within their
            // script and avoid name clashes. That would happen if the same
            // script is used multiple times.
            builder.append("{");
            builder.append(script.build());
            builder.append("}");
            builder.append("\n");
        }

        // replace variables with variables from parent builders or our own variables,
        // the ones closer to this builder replacing the ones farther away.
        String script = builder.toString();

        for (final Entry<String, String> e : getVariables().entrySet()) {
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
