package de.jpwinkler.daf.gui;

public interface UpdateAction<T extends ApplicationPartController<?>> {

    void update(T ctrl);

    default UpdateAction[] asArray() {
        return new UpdateAction[]{this};
    }

    public static UpdateAction[] of(UpdateAction... action) {
        return action;
    }
}
