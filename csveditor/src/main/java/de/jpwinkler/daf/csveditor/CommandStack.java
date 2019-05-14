package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.model.DoorsModule;

public class CommandStack {

    private AbstractCommand lastExecuted = INITIAL_COMMAND;
    private AbstractCommand lastSaved = INITIAL_COMMAND;

    public void addCommand(final AbstractCommand command) {
        command.previous = lastExecuted;
        this.lastExecuted.next = command;
        this.lastExecuted = command;
    }

    public AbstractCommand undo() {
        final AbstractCommand commandToUndo = lastExecuted;
        if (!commandToUndo.canUndo()) {
            return null;
        }

        this.lastExecuted = lastExecuted.previous;
        commandToUndo.undo();
        return commandToUndo;
    }

    public AbstractCommand redo() {
        AbstractCommand commandToRedo = lastExecuted.next;
        if (commandToRedo != null) {
            this.lastExecuted = commandToRedo;
            commandToRedo.redo();
        }
        return commandToRedo;
    }

    public boolean isDirty() {
        return lastExecuted != lastSaved;
    }

    public void setSavePoint() {
        this.lastSaved = lastExecuted;
    }

    public static abstract class AbstractCommand {

        private final DoorsModule module;

        public AbstractCommand() {
            this.module = null;
        }

        public AbstractCommand(final DoorsModule module) {
            this.module = module;
        }

        protected DoorsModule getModule() {
            return module;
        }

        public boolean isApplicable() {
            return true;
        }

        public abstract String getName();

        public abstract void apply();

        public abstract void redo();

        public abstract void undo();

        public UpdateAction[] getUpdateActions() {
            return new UpdateAction[0];
        }

        public boolean canUndo() {
            return true;
        }

        private AbstractCommand previous;
        private AbstractCommand next;
    }

    public static final AbstractCommand INITIAL_COMMAND = new AbstractCommand(null) {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public void apply() {
        }

        @Override
        public void redo() {
        }

        @Override
        public void undo() {
        }

        @Override
        public boolean canUndo() {
            return false;
        }
    };
}
