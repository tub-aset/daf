/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.model;

import static de.jpwinkler.daf.model.DoorsModuleUtil.listParser;
import static de.jpwinkler.daf.model.DoorsModuleUtil.listWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author fwiesweg
 */
public enum DoorsSystemAttributes {
    TAGS(List.class, listParser, listWriter),
    OBJECT_ATTRIBUTES(List.class, listParser, listWriter);

    <T> DoorsSystemAttributes(Class<T> type, Function<String, T> parser, Function<T, String> writer) {
        this.type = type;
        this.parser = parser;
        this.writer = (Function<Object, String>) writer;
    }

    private final Class<?> type;
    private final Function<String, ?> parser;
    private final Function<Object, String> writer;

    private String getKey() {
        return "__SYSTEM__" + this.name();
    }

    public <T> T getValue(Class<T> expectedType, Map<String, String> attributesMap) {
        if (!expectedType.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }

        return (T) this.parser.apply(attributesMap.get(this.getKey()));
    }

    public <T> void setValue(Class<T> expectedType, Map<String, String> attributesMap, T value) {
        if (!type.isAssignableFrom(expectedType)) {
            throw new IllegalArgumentException();
        }

        attributesMap.put(this.getKey(), writer.apply(value));
    }
}
