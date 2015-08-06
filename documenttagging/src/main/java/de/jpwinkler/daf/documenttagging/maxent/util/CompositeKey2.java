package de.jpwinkler.daf.documenttagging.maxent.util;

public class CompositeKey2<K1, K2> {

    private K1 k1;
    private K2 k2;

    public CompositeKey2() {
    }

    public CompositeKey2(final K1 k1, final K2 k2) {
        super();
        this.k1 = k1;
        this.k2 = k2;
    }

    public K1 getK1() {
        return k1;
    }

    public void setK1(final K1 k1) {
        this.k1 = k1;
    }

    public K2 getK2() {
        return k2;
    }

    public void setK2(final K2 k2) {
        this.k2 = k2;
    }

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
        final CompositeKey2 other = (CompositeKey2) obj;
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