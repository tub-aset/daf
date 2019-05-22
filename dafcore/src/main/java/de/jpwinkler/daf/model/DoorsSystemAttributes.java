/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.model;

import static de.jpwinkler.daf.model.DoorsModelUtil.IDENTITY;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static de.jpwinkler.daf.model.DoorsModelUtil.LIST_PARSER;
import static de.jpwinkler.daf.model.DoorsModelUtil.LIST_WRITER;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_PARSER;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_WRITER;

/**
 *
 * @author fwiesweg
 */
public enum DoorsSystemAttributes {
    TAGS(List.class, LIST_PARSER, LIST_WRITER),
    OBJECT_ATTRIBUTES(List.class, LIST_PARSER, LIST_WRITER),
    OBJECT_LEVEL("Object Level", Integer.class, INT_PARSER, INT_WRITER),
    OBJECT_IDENTIFIER("Object Identifier", String.class, IDENTITY, IDENTITY),
    OBJECT_TEXT("Object Text", String.class, IDENTITY, IDENTITY),
    OBJECT_SHORT_TEXT("Object Short Text", String.class, IDENTITY, IDENTITY),
    OBJECT_HEADING("Object Heading", String.class, IDENTITY, IDENTITY),
    OBJECT_NUMBER("Object Number", String.class, IDENTITY, IDENTITY),
    ABSOLUTE_NUMBER("Absolute Number", Integer.class, INT_PARSER, INT_WRITER);
    

    <T> DoorsSystemAttributes(Class<T> type, Function<String, T> parser, Function<T, String> writer) {
        this(name -> "__SYSTEM__" + name, type, parser, writer);
    }
    
    <T> DoorsSystemAttributes(String key, Class<T> type, Function<String, T> parser, Function<T, String> writer) {
        this(name -> key, type, parser, writer);
    }

    <T> DoorsSystemAttributes(Function<String, String> key, Class<T> type, Function<String, T> parser, Function<T, String> writer) {
        this.key = key.apply(this.name());
        this.type = type;
        this.parser = parser;
        this.writer = (Function<Object, String>) writer;
    }

    private final String key;
    private final Class<?> type;
    private final Function<String, ?> parser;
    private final Function<Object, String> writer;

    public String getKey() {
        return key;
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
