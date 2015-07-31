package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class Step extends WorkflowElement {

    private String name;

    private final List<OperationFeature> features = new ArrayList<>();

    public List<OperationFeature> getFeatures() {
        return features;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
