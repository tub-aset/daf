package de.jpwinkler.daf.documenttagging.maxent.util;

import java.util.HashMap;
import java.util.Map;

public class BiMap<K1, K2, V> {

    private static class Key<K1, K2> {
        public K1 k1;
        public K2 k2;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((k1 == null) ? 0 : k1.hashCode());
            result = prime * result + ((k2 == null) ? 0 : k2.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Key other = (Key) obj;
            if (k1 == null) {
                if (other.k1 != null) {
                    return false;
                }
            } else if (!k1.equals(other.k1)) {
                return false;
            }
            if (k2 == null) {
                if (other.k2 != null) {
                    return false;
                }
            } else if (!k2.equals(other.k2)) {
                return false;
            }
            return true;
        }

    }

    private final Map<Key<K1, K2>, V> internalMap = new HashMap<>();
    private final Key<K1, K2> theKey = new Key<>();

    public void put(final K1 k1, final K2 k2, final V v) {
        theKey.k1 = k1;
        theKey.k2 = k2;

        internalMap.put(theKey, v);
    }

    public V get(final K1 k1, final K2 k2) {
        theKey.k1 = k1;
        theKey.k2 = k2;

        return internalMap.get(theKey);
    }

    public boolean containsKey(final K1 k1, final K2 k2) {
        theKey.k1 = k1;
        theKey.k2 = k2;

        return internalMap.containsKey(theKey);
    }
}
