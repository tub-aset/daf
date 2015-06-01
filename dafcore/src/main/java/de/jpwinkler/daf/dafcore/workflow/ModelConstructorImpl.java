package de.jpwinkler.daf.dafcore.workflow;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public interface ModelConstructorImpl extends StepImpl {

    void setSource(DoorsModule module);

    ModelObject execute();
}
