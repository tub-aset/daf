package de.jpwinkler.daf.maxent.util;

import java.util.HashMap;
import java.util.Map;

public class TriMap<K1, K2, K3, V> {

    private static class Key<K1, K2, K3> {
        public K1 k1;
        public K2 k2;
        public K3 k3;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((k1 == null) ? 0 : k1.hashCode());
            result = prime * result + ((k2 == null) ? 0 : k2.hashCode());
            result = prime * result + ((k3 == null) ? 0 : k3.hashCode());
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
            if (k3 == null) {
                if (other.k3 != null) {
                    return false;
                }
            } else if (!k3.equals(other.k3)) {
                return false;
            }
            return true;
        }

    }

    private final Map<Key<K1, K2, K3>, V> internalMap = new HashMap<>();
    private final Key<K1, K2, K3> theKey = new Key<>();

    public void put(final K1 k1, final K2 k2, final K3 k3, final V v) {
        theKey.k1 = k1;
        theKey.k2 = k2;
        theKey.k3 = k3;

        internalMap.put(theKey, v);
    }

    public V get(final K1 k1, final K2 k2, final K3 k3) {
        theKey.k1 = k1;
        theKey.k2 = k2;
        theKey.k3 = k3;

        return internalMap.get(theKey);
    }
}
