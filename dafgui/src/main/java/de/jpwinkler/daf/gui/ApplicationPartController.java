/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.gui.extensions.UpdateAction;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.gui.extensions.ApplicationPartExtensionPoint;
import de.jpwinkler.daf.gui.extensions.ApplicationPartInterface;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;

/**
 *
 * @author fwiesweg
 */
public abstract class ApplicationPartController<THIS extends ApplicationPartController> extends AutoloadingPaneController<THIS> implements ApplicationPartInterface {

    final ApplicationPaneController applicationController;
    final DatabasePath path;
    final DatabaseInterface databaseInterface;
    final CommandStack commandStack;

    protected final List<ApplicationPartExtensionPoint> extensions = Collections.unmodifiableList(Main.PLUGIN_MANAGER.getExtensions(ApplicationPartExtensionPoint.class));

    private final List<Menu> menus;

    public ApplicationPartController(ApplicationPaneController applicationController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        this.applicationController = applicationController;
        this.path = path;
        this.databaseInterface = databaseInterface;
        this.commandStack = databaseCommandStack;

        URL menuUrl = MainFX.class.getResource(
                this.getClass().getSimpleName().replaceFirst("Controller$", "") + "Menu.fxml");

        menus = new ArrayList<>();
        if (menuUrl != null) {
            try {
                final FXMLLoader menuLoader = new FXMLLoader(menuUrl);
                menuLoader.setController(this);
                ((Menu) menuLoader.load()).getItems().stream()
                        .filter(m -> m instanceof Menu)
                        .map(m -> (Menu) m)
                        .forEach(menus::add);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        extensions.forEach(e -> e.initialise(this));
        extensions.stream()
                .flatMap(e -> e.getMenus().stream())
                .forEach(menus::add);
    }

    protected final void setStatus(final String status) {
        applicationController.setStatus(status);
    }

    protected final boolean open(DatabasePath path, OpenFlag openFlag) {
        return applicationController.open(path, openFlag);
    }

    @Override
    public final void createSnapshot(Predicate<DoorsTreeNode> include) {
        applicationController.createSnapshot(this.getDatabaseInterface(), this.getDatabaseInterface().getPath(), include);
    }

    protected final <T extends ApplicationPartExtensionPoint> List<T> getExtensions(Class<T> cls) {
        return extensions.stream()
                .filter(e -> cls.isAssignableFrom(e.getClass()))
                .map(e -> (T) e)
                .collect(Collectors.toList());
    }

    @Override
    public final void executeCommand(final AbstractCommand command) {
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

    public final void updateGui(UpdateAction... actions) {
        Stream.of(actions).forEach(a -> a.update(this));
    }

    @FXML
    public final void redoClicked() {
        final AbstractCommand commandToRedo = getCommandStack().redo(this);
        if (commandToRedo == null) {
            this.setStatus("Cannot redo.");
        } else {
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    @FXML
    public final void undoClicked() {
        final AbstractCommand commandToUndo = getCommandStack().undo(this);
        if (commandToUndo == null) {
            this.setStatus("Cannot undo.");
        } else {
            updateGui(commandToUndo.getUpdateActions());
        }
    }

    public final Collection<Menu> getMenus() {
        return menus;
    }

    public DatabasePath getPath() {
        return path;
    }

    @Override
    public final DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public final CommandStack getCommandStack() {
        return commandStack;
    }

}
