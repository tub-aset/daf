package de.jpwinkler.daf.gui.commands;

import de.jpwinkler.daf.gui.ApplicationPartController;
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
        command.setOriginController(originController);
        command.setPrevious(lastExecuted);
        this.lastExecuted.setNext(command);
        this.lastExecuted = command;

        onDirty.accept(isDirty());
    }

    public AbstractCommand undo(ApplicationPartController originController) {
        final AbstractCommand commandToUndo = lastExecuted;
        if (!commandToUndo.canUndo()) {
            return null;
        }

        if (commandToUndo.getOriginController() != originController) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "This action was initiated from another view, still undo from here?", ButtonType.YES, ButtonType.NO);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) {
                return null;
            }
        }

        this.lastExecuted = lastExecuted.getPrevious();
        commandToUndo.undo();

        onDirty.accept(isDirty());
        return commandToUndo;
    }

    public AbstractCommand redo(ApplicationPartController originController) {
        AbstractCommand commandToRedo = lastExecuted.getNext();
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
