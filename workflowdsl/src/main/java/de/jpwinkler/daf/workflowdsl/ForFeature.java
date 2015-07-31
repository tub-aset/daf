package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class ForFeature extends OperationFeature {

    private final List<OperationFeature> features = new ArrayList<>();
    private String loopVar;
    private String arrayVar;

    public String getLoopVar() {
        return loopVar;
    }

    public void setLoopVar(final String loopVar) {
        this.loopVar = loopVar;
    }

    public List<OperationFeature> getFeatures() {
        return features;
    }


    public String getArrayVar() {
        return arrayVar;
    }

    public void setArrayVar(final String arrayVar) {
        this.arrayVar = arrayVar;
    }

}
