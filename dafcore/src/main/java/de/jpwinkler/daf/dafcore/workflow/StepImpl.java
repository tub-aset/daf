package de.jpwinkler.daf.dafcore.workflow;

import java.util.List;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

public interface StepImpl {

    <T extends ModelObject> void setParameter(String name, List<T> value);

    <T extends ModelObject> List<T> getParameter(String name);

    <T extends ModelObject> T getSingleParameter(String name);

    void setVariable(String name, Object value);

    String getStringVariable(String name);

    int getIntVariable(String name);

    Set<String> getParameterNames();

}
