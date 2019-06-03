/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.model;

import static de.jpwinkler.daf.model.DoorsModelUtil.IDENTITY;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_PARSER;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_WRITER;
import static de.jpwinkler.daf.model.DoorsModelUtil.LIST_PARSER;
import static de.jpwinkler.daf.model.DoorsModelUtil.LIST_WRITER;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author fwiesweg
 */
public enum DoorsSystemAttributes {
    TAGS(List.class, LIST_PARSER, LIST_WRITER),
    MODULE_OBJECT_ATTRIBUTES(List.class, LIST_PARSER, LIST_WRITER, DoorsModule.class),
    MODULE_DESCRIPTION("Description", DoorsModule.class),
    OBJECT_LEVEL("Object Level", Integer.class, INT_PARSER, INT_WRITER, DoorsObject.class),
    OBJECT_IDENTIFIER("Object Identifier", DoorsObject.class),
    OBJECT_TEXT("Object Text", DoorsObject.class),
    OBJECT_SHORT_TEXT("Object Short Text", DoorsObject.class),
    OBJECT_HEADING("Object Heading", DoorsObject.class),
    OBJECT_NUMBER("Object Number", DoorsObject.class),
    ABSOLUTE_NUMBER("Absolute Number", Integer.class, INT_PARSER, INT_WRITER, DoorsObject.class);

    <T> DoorsSystemAttributes(Class<T> type, Function<String, T> parser, Function<T, String> writer, Class<? extends DoorsTreeNode>... appliesTo) {
        this(null, type, parser, writer);
    }

    <T> DoorsSystemAttributes(String key, Class<? extends DoorsTreeNode>... appliesTo) {
        this(key, String.class, IDENTITY, IDENTITY, appliesTo);
    }

    <T> DoorsSystemAttributes(String key, Class<T> type, Function<String, T> parser, Function<T, String> writer, Class<? extends DoorsTreeNode>... appliesTo) {
        this.key = key;
        this.type = type;
        this.parser = parser;
        this.writer = (Function<Object, String>) writer;
        this.appliesTo = appliesTo;
    }

    private static final Map<String, DoorsSystemAttributes> inverseMap = Stream.of(DoorsSystemAttributes.values())
            .collect(Collectors.toMap(v -> v.getKey(), v -> v));

    public static Optional<DoorsSystemAttributes> getForKey(String key) {
        return inverseMap.containsKey(key) ? Optional.of(inverseMap.get(key)) : Optional.empty();
    }

    private final String key;
    private final Class<?> type;
    private final Function<String, ?> parser;
    private final Function<Object, String> writer;
    private final Class<? extends DoorsTreeNode>[] appliesTo;

    public String getKey() {
        return key == null ? "__SYSTEM__" + this.name() : key;
    }

    public boolean isSystemKey() {
        return key == null;
    }

    public <T> T getValue(Class<T> expectedType, DoorsTreeNode node) {
        if (!expectedType.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }

        return (T) this.parser.apply(node.getAttributes().get(this.getKey()));
    }

    public <T> void setValue(Class<T> expectedType, DoorsTreeNode node, T value) {
        if (!type.isAssignableFrom(expectedType)) {
            throw new IllegalArgumentException();
        }

        node.getAttributes().put(this.getKey(), writer.apply(value));
    }

    public static Stream<DoorsSystemAttributes> valuesFor(Class<? extends DoorsTreeNode> cls) {
        return Stream.of(DoorsSystemAttributes.values())
                .filter(v -> v.appliesTo.length == 0 || Stream.of(v.appliesTo).anyMatch(c -> c.isAssignableFrom(cls)));
    }
}
