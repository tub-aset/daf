package de.jpwinkler.daf.documenttagging.maxent.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class CompositeKey2Test {

    @Test
    public void testEquals() {
        final CompositeKey2<String, String> k1 = new CompositeKey2<>("a", "b");
        final CompositeKey2<String, String> k2 = new CompositeKey2<>("a", "b");
        final CompositeKey2<String, String> k3 = new CompositeKey2<>("a", "c");
        final CompositeKey2<String, String> k4 = new CompositeKey2<>("c", "b");
        final CompositeKey2<String, String> k5 = new CompositeKey2<>("d", "d");
        final CompositeKey2<String, String> k6 = new CompositeKey2<>("a", null);
        final CompositeKey2<String, String> k7 = new CompositeKey2<>(null, "b");

        assertEquals(k1, k2);
        assertNotEquals(k1, k3);
        assertNotEquals(k1, k4);
        assertNotEquals(k1, k5);
        assertNotEquals(k1, k6);
        assertNotEquals(k1, k7);

    }

    @Test
    public void testWithHashMap() {
        final Map<CompositeKey2<String, String>, String> map = new HashMap<>();

        assertFalse(map.containsKey(new CompositeKey2<String, String>("a", "b")));
        map.put(new CompositeKey2<String, String>("a", "b"), "x");
        assertEquals(map.get(new CompositeKey2<String, String>("a", "b")), "x");
        map.put(new CompositeKey2<String, String>("a", "b"), "y");
        assertEquals(map.get(new CompositeKey2<String, String>("a", "b")), "y");
        assertEquals(map.get(new CompositeKey2<String, String>("a", "c")), null);
        map.put(new CompositeKey2<String, String>("a", "c"), "z");
        assertEquals(map.get(new CompositeKey2<String, String>("a", "c")), "z");
        assertEquals(map.get(new CompositeKey2<String, String>("a", "b")), "y");

    }

    @Test
    public void testEntrySet() {
        final Map<CompositeKey2<String, String>, String> map = new LinkedHashMap<>();
        map.put(new CompositeKey2<String, String>("a", "c"), "1");
        map.put(new CompositeKey2<String, String>("a", "d"), "2");
        map.put(new CompositeKey2<String, String>("b", "c"), "3");
        map.put(new CompositeKey2<String, String>("b", "d"), "4");

        final Iterator<Entry<CompositeKey2<String, String>, String>> i = map.entrySet().iterator();

        assertEquals("1", map.get(i.next().getKey()));
        assertEquals("2", map.get(i.next().getKey()));
        assertEquals("3", map.get(i.next().getKey()));
        assertEquals("4", map.get(i.next().getKey()));
    }

}
