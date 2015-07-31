package de.jpwinkler.daf.workflowdsl.factory;

import de.jpwinkler.daf.workflowdsl.ModuleSet;

public class ModuleSetProxy extends ModuleSet {

    private final String moduleSetRef;

    public ModuleSetProxy(final String moduleSetRef) {
        this.moduleSetRef = moduleSetRef;
    }

    public String getModuleSetRef() {
        return moduleSetRef;
    }

}
