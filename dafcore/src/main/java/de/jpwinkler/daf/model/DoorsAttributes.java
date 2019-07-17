package de.jpwinkler.daf.model;

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
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import static de.jpwinkler.daf.model.DoorsModelUtil.IDENTITY;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_PARSER;
import static de.jpwinkler.daf.model.DoorsModelUtil.INT_WRITER;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DoorsAttributes {
    MODULE_DESCRIPTION("Description", DoorsModule.class),
    MODULE_VIEW("__view__", String.class, IDENTITY, IDENTITY, DoorsModule.class),
    OBJECT_LEVEL("Object Level", Integer.class, INT_PARSER, INT_WRITER, DoorsObject.class),
    OBJECT_IDENTIFIER("Object Identifier", DoorsObject.class),
    OBJECT_TEXT("Object Text", DoorsObject.class),
    OBJECT_SHORT_TEXT("Object Short Text", DoorsObject.class),
    OBJECT_HEADING("Object Heading", DoorsObject.class),
    OBJECT_NUMBER("Object Number", DoorsObject.class),
    ABSOLUTE_NUMBER("Absolute Number", Integer.class, INT_PARSER, INT_WRITER, DoorsObject.class),
    DATABASE_COPIED_FROM("Copied From", DoorsFolder.class),
    DATABASE_COPIED_AT("Copied At", DoorsFolder.class);

    <T> DoorsAttributes(Class<T> type, Class<? extends DoorsTreeNode>... appliesTo) {
        this(null, IDENTITY, IDENTITY, appliesTo);
    }

    <T> DoorsAttributes(Class<T> type, Function<String, T> parser, Function<T, String> writer, Class<? extends DoorsTreeNode>... appliesTo) {
        this(null, type, parser, writer, appliesTo);
    }

    <T> DoorsAttributes(String key, Class<? extends DoorsTreeNode>... appliesTo) {
        this(key, String.class, IDENTITY, IDENTITY, appliesTo);
    }

    <T> DoorsAttributes(String key, Class<T> type, Function<String, T> parser, Function<T, String> writer, Class<? extends DoorsTreeNode>... appliesTo) {
        this.key = key;
        this.type = type;
        this.parser = parser;
        this.writer = (Function<Object, String>) writer;
        this.appliesTo = appliesTo;
    }

    private static final Map<String, DoorsAttributes> inverseMap = Stream.of(DoorsAttributes.values())
            .collect(Collectors.toMap(v -> v.getKey(), v -> v));

    public static Optional<DoorsAttributes> getForKey(String key) {
        return inverseMap.containsKey(key) ? Optional.of(inverseMap.get(key)) : Optional.empty();
    }

    private final String key;
    private final Class<?> type;
    private final Function<String, ?> parser;
    private final Function<Object, String> writer;
    private final Class<? extends DoorsTreeNode>[] appliesTo;

    public String getKey() {
        return key;
    }

    public <T> T getValue(Class<T> expectedType, DoorsTreeNode node) {
        if (!expectedType.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }

        return (T) this.parser.apply(node.getAttributes().get(this.getKey()));
    }

    public <T> CompletableFuture<T> getValueAsync(BackgroundTaskExecutor executor, Class<T> expectedType, DoorsTreeNode node) {
        if (!expectedType.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }

        return node.getAttributesAsync(executor)
                .thenApply(m -> m.get(this.getKey()))
                .thenApply(v -> (T) this.parser.apply(v));
    }

    public <T> void setValue(Class<T> expectedType, DoorsTreeNode node, T value) {
        if (!type.isAssignableFrom(expectedType)) {
            throw new IllegalArgumentException();
        }

        node.getAttributes().put(this.getKey(), writer.apply(value));
    }

    public static Stream<DoorsAttributes> valuesFor(Class<? extends DoorsTreeNode> cls) {
        return Stream.of(DoorsAttributes.values())
                .filter(v -> v.appliesTo.length == 0 || Stream.of(v.appliesTo).anyMatch(c -> c.isAssignableFrom(cls)));
    }
}
