/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.gui.commands.module.UpdateAction;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;

/**
 *
 * @author fwiesweg
 */
public abstract class ApplicationPartController {

    private ApplicationPaneController applicationController;
    private final Parent node;
    private final List<Menu> menus;
    private final CommandStack commandStack = new CommandStack();
    private ApplicationPart part;

    private ApplicationURI uri;

    public ApplicationPartController(ApplicationPaneController applicationController) {
        this.applicationController = applicationController;

        try {
            final FXMLLoader paneLoader = new FXMLLoader(MainFX.class.getResource(
                    this.getClass().getSimpleName().replaceFirst("Controller$", "") + ".fxml"));
            paneLoader.setController(this);
            this.node = paneLoader.load();

            final FXMLLoader menuLoader = new FXMLLoader(MainFX.class.getResource(
                    this.getClass().getSimpleName().replaceFirst("Controller$", "") + "Menu.fxml"));
            menuLoader.setController(this);
            this.menus = ((Menu) menuLoader.load()).getItems().stream()
                    .filter(m -> m instanceof Menu)
                    .map(m -> (Menu) m)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    final void setStatus(final String status) {
        if (applicationController != null) {
            applicationController.setStatus(status);
        }
    }

    public final CommandStack getCommandStack() {
        return commandStack;
    }

    protected final void executeCommand(final CommandStack.AbstractCommand command) {
        if (!command.isApplicable()) {
            this.setStatus(command.getName() + ": Command is not appicable for this selection.");
            return;
        }

        command.apply();
        getCommandStack().addCommand(command);
        updateGui(command.getUpdateActions());
    }

    @FXML
    public final void redoClicked() {
        final CommandStack.AbstractCommand commandToRedo = getCommandStack().redo();
        if (commandToRedo == null) {
            this.setStatus("Cannot redo.");
        } else {
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    @FXML
    public final void undoClicked() {
        final CommandStack.AbstractCommand commandToUndo = getCommandStack().undo();
        if (commandToUndo == null) {
            this.setStatus("Cannot undo.");
        } else {
            updateGui(commandToUndo.getUpdateActions());
        }
    }

    protected void updateGui(UpdateAction[] actions) {
    }

    public final Collection<Menu> getMenus() {
        return this.menus == null ? Collections.emptySet() : menus;
    }

    public Parent getNode() {
        return node;
    }

    public final ApplicationURI getURI() {
        return uri;
    }

    public void setURI(ApplicationURI file) {
        this.uri = file;
    }

    public void save() throws IOException {
        throw new UnsupportedOperationException();
    }
}
