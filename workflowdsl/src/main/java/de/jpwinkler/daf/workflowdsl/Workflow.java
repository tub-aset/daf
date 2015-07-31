package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class Workflow {

    private final List<WorkflowElement> elements = new ArrayList<>();

    public List<WorkflowElement> getElements() {
        return elements;
    }

}
