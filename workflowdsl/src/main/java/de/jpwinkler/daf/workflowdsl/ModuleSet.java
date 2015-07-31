package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class ModuleSet extends WorkflowElement {

    private String name;

    private final List<ModuleSetEntry> moduleSetEntries = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<ModuleSetEntry> getModuleSetEntries() {
        return moduleSetEntries;
    }

}
