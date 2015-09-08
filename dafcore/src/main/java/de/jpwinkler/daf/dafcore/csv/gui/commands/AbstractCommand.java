package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public abstract class AbstractCommand {

    private final DoorsModule module;

    public AbstractCommand(final DoorsModule module) {
        this.module = module;
    }

    protected DoorsModule getModule() {
        return module;
    }

    public abstract boolean isApplicable();

    public abstract String getName();

    public abstract void apply();

    public abstract void redo();

    public abstract void undo();

    public abstract UpdateAction[] getUpdateActions();

    public boolean canUndo() {
        return true;
    }

}
