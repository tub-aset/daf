package de.jpwinkler.daf.gui.extensions;

public interface UpdateAction<T extends ApplicationPartInterface> {

    void update(T ctrl);

    default UpdateAction[] asArray() {
        return new UpdateAction[]{this};
    }

    public static UpdateAction[] of(UpdateAction... action) {
        return action;
    }
}
