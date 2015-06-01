package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import java.util.Arrays;
import java.util.List;

public abstract class CompositeObjectPrecondition implements ObjectPrecondition {

    private final List<ObjectPrecondition> objectPreconditions;

    public CompositeObjectPrecondition(final ObjectPrecondition... objectPreconditions) {
        super();
        this.objectPreconditions = Arrays.asList(objectPreconditions);
    }

    public CompositeObjectPrecondition(final List<ObjectPrecondition> objectPreconditions) {
        super();
        this.objectPreconditions = objectPreconditions;
    }

    public List<ObjectPrecondition> getObjectPreconditions() {
        return objectPreconditions;
    }

}
