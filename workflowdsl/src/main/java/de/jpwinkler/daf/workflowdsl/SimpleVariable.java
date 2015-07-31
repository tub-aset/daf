package de.jpwinkler.daf.workflowdsl;

public class SimpleVariable extends Variable {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

}
