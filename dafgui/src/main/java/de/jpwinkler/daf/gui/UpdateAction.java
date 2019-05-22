package de.jpwinkler.daf.gui;

public interface UpdateAction<T extends ApplicationPartController<?>> {
    public void update(T ctrl);
}
