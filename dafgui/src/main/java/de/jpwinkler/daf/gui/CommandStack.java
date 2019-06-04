package de.jpwinkler.daf.gui;

import java.lang.ref.WeakReference;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class CommandStack {

    private AbstractCommand lastExecuted = INITIAL_COMMAND;
    private AbstractCommand lastSaved = INITIAL_COMMAND;
    private final Consumer<Boolean> onDirty;

    public CommandStack(Consumer<Boolean> onDirty) {
        this.onDirty = onDirty;
    }

    public void addCommand(ApplicationPartController originController, final AbstractCommand command) {
        command.originController = new WeakReference<>(originController);
        command.previous = lastExecuted;
        this.lastExecuted.next = command;
        this.lastExecuted = command;

        onDirty.accept(isDirty());
    }

    public AbstractCommand undo(ApplicationPartController originController) {
        final AbstractCommand commandToUndo = lastExecuted;
        if (!commandToUndo.canUndo()) {
            return null;
        }

        if (commandToUndo.originController.get() != originController) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "This action was initiated from another view, still undo from here?", ButtonType.YES, ButtonType.NO);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) {
                return null;
            }
        }

        this.lastExecuted = lastExecuted.previous;
        commandToUndo.undo();

        onDirty.accept(isDirty());
        return commandToUndo;
    }

    public AbstractCommand redo(ApplicationPartController originController) {
        AbstractCommand commandToRedo = lastExecuted.next;
        if (commandToRedo != null) {
            this.lastExecuted = commandToRedo;
            commandToRedo.redo();
        }

        onDirty.accept(isDirty());
        return commandToRedo;
    }

    public boolean isDirty() {
        return lastExecuted != lastSaved;
    }

    public void setSavePoint() {
        this.lastSaved = lastExecuted;
        onDirty.accept(isDirty());
    }

    public static abstract class AbstractCommand {

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

        private WeakReference<ApplicationPartController> originController;
        private AbstractCommand previous;
        private AbstractCommand next;
    }

    public static final AbstractCommand INITIAL_COMMAND = new AbstractCommand() {
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
