package de.jpwinkler.daf.dafcore.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

public abstract class AbstractStepImpl implements StepImpl {

    private final Map<String, List<?>> parameters = new HashMap<>();
    private final Map<String, Object> variables = new HashMap<>();

    @Override
    public <T extends ModelObject> void setParameter(final String name, final List<T> value) {
        parameters.put(name, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ModelObject> List<T> getParameter(final String name) {
        return (List<T>) parameters.get(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ModelObject> T getSingleParameter(final String name) {
        final List<?> list = parameters.get(name);
        if (list != null && list.size() == 1) {
            return (T) list.get(0);
        } else if (list != null && list.size() != 1) {
            throw new RuntimeException("Argument list does not contain exactly one argument!");
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getParameterNames() {
        return parameters.keySet();
    }

    @Override
    public void setVariable(final String name, final Object value) {
        variables.put(name, value);
    }

    @Override
    public int getIntVariable(final String name) {
        return (int) variables.get(name);
    }

    @Override
    public String getStringVariable(final String name) {
        return (String) variables.get(name);
    }
}
