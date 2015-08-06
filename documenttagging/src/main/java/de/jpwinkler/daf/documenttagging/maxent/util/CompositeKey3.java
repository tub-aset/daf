package de.jpwinkler.daf.documenttagging.maxent.util;

public class CompositeKey3<K1, K2, K3> {

    private K1 k1;
    private K2 k2;
    private K3 k3;

    public CompositeKey3() {
    }

    public CompositeKey3(final K1 k1, final K2 k2, final K3 k3) {
        super();
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
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

    public K3 getK3() {
        return k3;
    }

    public void setK3(final K3 k3) {
        this.k3 = k3;
    }

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
        final CompositeKey3 other = (CompositeKey3) obj;
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