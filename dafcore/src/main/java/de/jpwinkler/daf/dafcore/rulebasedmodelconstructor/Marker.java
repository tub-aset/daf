package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import java.util.HashMap;
import java.util.Map;

public class Marker {

    public static final String MODEL_OBJECT = "model_object";

    private final String type;
    private final Map<String, Object> attributes = new HashMap<>();

    public Marker(final String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        final Marker other = (Marker) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    public Object getAttribute(final String key) {
        return attributes.get(key);
    }

    public void setAttribute(final String key, final Object attribute) {
        attributes.put(key, attribute);
    }

    @Override
    public String toString() {
        return type;
    }

}
