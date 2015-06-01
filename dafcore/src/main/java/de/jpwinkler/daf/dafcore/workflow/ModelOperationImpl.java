package de.jpwinkler.daf.dafcore.workflow;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

public interface ModelOperationImpl extends StepImpl {

    ModelObject execute();

}
