package de.jpwinkler.daf.gui.commands;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.gui.ApplicationPartController;
import java.util.HashSet;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class CommandStack {

    private AbstractCommand lastExecuted = INITIAL_COMMAND;
    private AbstractCommand lastSaved = INITIAL_COMMAND;
    private final HashSet<Consumer<Boolean>> dirtyListeners = new HashSet<>();

    public void addDirtyListener(Consumer<Boolean> listener) {
        this.dirtyListeners.add(listener);
    }

    public void removeDirtyListener(Consumer<Boolean> listener) {
        this.dirtyListeners.remove(listener);
    }

    public void addCommand(ApplicationPartController originController, final AbstractCommand command) {
        command.setOriginController(originController);
        command.setPrevious(lastExecuted);
        this.lastExecuted.setNext(command);
        this.lastExecuted = command;

        dirtyListeners.forEach(l -> l.accept(isDirty()));
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

        dirtyListeners.forEach(l -> l.accept(isDirty()));
        return commandToUndo;
    }

    public AbstractCommand redo(ApplicationPartController originController) {
        AbstractCommand commandToRedo = lastExecuted.getNext();
        if (commandToRedo != null) {
            this.lastExecuted = commandToRedo;
            commandToRedo.redo();
        }

        dirtyListeners.forEach(l -> l.accept(isDirty()));
        return commandToRedo;
    }

    public boolean isDirty() {
        return lastExecuted != lastSaved;
    }

    public void setSavePoint() {
        this.lastSaved = lastExecuted;
        dirtyListeners.forEach(l -> l.accept(isDirty()));
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
