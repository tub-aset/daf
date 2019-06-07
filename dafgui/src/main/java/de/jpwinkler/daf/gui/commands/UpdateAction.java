package de.jpwinkler.daf.gui.commands;

import de.jpwinkler.daf.gui.ApplicationPartInterface;

public interface UpdateAction<T extends ApplicationPartInterface> {

    void update(T ctrl);

    default UpdateAction[] asArray() {
        return new UpdateAction[]{this};
    }

    public static UpdateAction[] of(UpdateAction... action) {
        return action;
    }
}
