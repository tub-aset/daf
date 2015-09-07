package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public abstract class AbstractCommand {

    private final DoorsModule module;

    private final CSVViewerTabController controller;

    public AbstractCommand(final DoorsModule module, final CSVViewerTabController controller) {
        this.module = module;
        this.controller = controller;
    }

    protected DoorsModule getModule() {
        return module;
    }

    protected CSVViewerTabController getController() {
        return controller;
    }

    public abstract boolean isApplicable();

    public abstract void apply();

    public abstract void undo();

}
