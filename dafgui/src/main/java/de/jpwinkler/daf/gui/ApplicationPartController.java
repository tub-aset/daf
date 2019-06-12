/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public abstract class ApplicationPartController<THIS extends ApplicationPartController> extends AutoloadingPaneController<THIS> implements ApplicationPartInterface {

    private final ApplicationPaneController applicationController;
    private final DatabasePath path;
    private final DatabaseInterface databaseInterface;
    private final CommandStack commandStack;
    private final ApplicationPart applicationPart;

    private final List<ApplicationPartExtension> extensions = new ArrayList<>();

    private final ObservableList<Menu> menus = FXCollections.observableArrayList();
    private final Map<Menu, PluginWrapper> extensionMenus = new HashMap<>();

    public ApplicationPartController(ApplicationPaneController applicationController, ApplicationPart applicationPart,
            DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        this.applicationController = applicationController;
        this.applicationPart = applicationPart;
        this.path = path;
        this.databaseInterface = databaseInterface;
        this.commandStack = databaseCommandStack;

        URL menuUrl = MainFX.class.getResource(
                this.getClass().getSimpleName().replaceFirst("Controller$", "") + "Menu.fxml");

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
        };
    }

    protected final void setStatus(final String status) {
        applicationController.setStatus(status);
    }

    @Override
    public final ApplicationPartInterface open(DatabasePath path, OpenFlag openFlag) {
        return applicationController.open(path, openFlag);
    }

    @Override
    public final DatabasePath createSnapshot(Predicate<DoorsTreeNode> include, DatabasePath destination) {
        return applicationController.createSnapshot(this.getDatabaseInterface(), this.getDatabaseInterface().getPath(), include, destination);
    }

    protected final <T extends ApplicationPartExtension> List<T> getExtensions(Class<T> cls) {
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

    public final ObservableList<Menu> getMenus() {
        return menus;
    }

    public final DatabasePath getPath() {
        return path;
    }

    @Override
    public final DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public final CommandStack getCommandStack() {
        return commandStack;
    }

    public void addPlugin(PluginWrapper plugin) {
        List<ApplicationPartExtension> newExts = plugin.getPluginManager().getExtensions(ApplicationPartExtension.class, plugin.getPluginId());
        newExts.forEach(e -> e.initialise(this));

        extensions.addAll(newExts);
        newExts.stream()
                .flatMap(e -> e.getMenus().stream())
                .peek(m -> extensionMenus.put(m, plugin))
                .forEach(menus::add);
    }

    public void removePlugin(PluginWrapper plugin) {
        extensions.removeIf(ext -> ext.getClass().getClassLoader() == plugin.getPluginClassLoader());

        List<MenuItem> extMenus = extensionMenus.entrySet().stream().filter(e -> e.getValue() == plugin).map(e -> e.getKey()).collect(Collectors.toList());
        menus.removeAll(extMenus);
        extMenus.forEach(extensionMenus::remove);
    }

    public ApplicationPart<?> getApplicationPart() {
        return applicationPart;
    }
}
