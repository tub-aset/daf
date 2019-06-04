/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;

/**
 *
 * @author fwiesweg
 */
public abstract class ApplicationPartController<T extends ApplicationPartController> extends AutoloadingPaneController<T> {

    private final ApplicationPaneController applicationController;
    private final DatabasePath path;
    private final DatabaseInterface databaseInterface;
    private final CommandStack commandStack;

    private final List<Menu> menus;

    public ApplicationPartController(ApplicationPaneController applicationController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        this.applicationController = applicationController;
        this.path = path;
        this.databaseInterface = databaseInterface;
        this.commandStack = databaseCommandStack;

        URL menuUrl = MainFX.class.getResource(
                this.getClass().getSimpleName().replaceFirst("Controller$", "") + "Menu.fxml");
        if (menuUrl == null) {
            this.menus = Collections.emptyList();
        } else {
            try {
                final FXMLLoader menuLoader = new FXMLLoader(menuUrl);
                menuLoader.setController(this);
                this.menus = ((Menu) menuLoader.load()).getItems().stream()
                        .filter(m -> m instanceof Menu)
                        .map(m -> (Menu) m)
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    protected final void setStatus(final String status) {
        applicationController.setStatus(status);
    }

    protected final boolean open(DatabasePath path, OpenFlag openFlag) {
        return applicationController.open(path, openFlag);
    }

    protected final void executeCommand(final CommandStack.AbstractCommand command) {
        if (getDatabaseInterface().isReadOnly()) {
            this.setStatus(command.getName() + ": Database is read-only.");
            return;
        }

        if (!command.isApplicable()) {
            this.setStatus(command.getName() + ": Command is not applicable for this selection.");
            return;
        }

        command.apply();
        getCommandStack().addCommand(this, command);
        updateGui(command.getUpdateActions());
    }

    protected final void updateGui(UpdateAction... actions) {
        Stream.of(actions).forEach(a -> a.update(this));
    }

    @FXML
    public final void redoClicked() {
        final CommandStack.AbstractCommand commandToRedo = getCommandStack().redo(this);
        if (commandToRedo == null) {
            this.setStatus("Cannot redo.");
        } else {
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    @FXML
    public final void undoClicked() {
        final CommandStack.AbstractCommand commandToUndo = getCommandStack().undo(this);
        if (commandToUndo == null) {
            this.setStatus("Cannot undo.");
        } else {
            updateGui(commandToUndo.getUpdateActions());
        }
    }

    public final Collection<Menu> getMenus() {
        return this.menus == null ? Collections.emptySet() : menus;
    }

    public DatabasePath getPath() {
        return path;
    }

    public final DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public final CommandStack getCommandStack() {
        return commandStack;
    }

}
