package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import java.util.Arrays;
import java.util.List;

public abstract class CompositePrecondition implements Precondition {

    private final List<Precondition> preconditions;

    public CompositePrecondition(final Precondition... preconditions) {
        super();
        this.preconditions = Arrays.asList(preconditions);
    }

    public CompositePrecondition(final List<Precondition> preconditions) {
        super();
        this.preconditions = preconditions;
    }

    public List<Precondition> getPreconditions() {
        return preconditions;
    }
}
