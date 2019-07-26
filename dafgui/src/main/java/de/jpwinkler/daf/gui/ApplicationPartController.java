package de.jpwinkler.daf.gui;

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
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.controls.ExtensionPane;
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public abstract class ApplicationPartController<THIS extends ApplicationPartController> extends AutoloadingPaneController<THIS> implements ApplicationPartInterface {

    private final ApplicationPaneController applicationController;
    private final ApplicationPart applicationPart;

    private final List<ApplicationPartExtension> extensions = new ArrayList<>();

    private final ObservableList<Menu> menus = FXCollections.observableArrayList();
    private final Map<Menu, PluginWrapper> extensionMenus = new HashMap<>();
    private final Class<? extends ApplicationPartExtension> extensionClass;

    public ApplicationPartController(ApplicationPaneController applicationController, ApplicationPart applicationPart, Class<? extends ApplicationPartExtension> extensionClass) {
        this.applicationController = applicationController;
        this.applicationPart = applicationPart;

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
        this.extensionClass = extensionClass;
    }

    protected static void setupColumnWidthStorage(TableColumn<?, ?> col, ApplicationPreference<Double> pref) {
        col.setPrefWidth((double) pref.retrieve());
        col.widthProperty().addListener((obs, oldValue, newValue) -> {
            pref.store(newValue.doubleValue());
        });
    }

    protected static void setupDividerStorage(SplitPane splitPane, ApplicationPreference<HashMap<Integer, double[]>> SPLITPOS, ExtensionPane<?> extensionPane) {
        HashMap<Integer, double[]> dividerPos = (HashMap<Integer, double[]>) SPLITPOS.retrieve();
        if (dividerPos.containsKey(splitPane.getDividers().size())) {
            double[] positions = dividerPos.get(splitPane.getDividers().size());
            Platform.runLater(() -> {
                splitPane.setDividerPositions(positions);
            });
        }

        Consumer<SplitPane.Divider> dividerChangeRunnable = d -> d.positionProperty().addListener((obs, oldValue, newValue) -> {
            dividerPos.put(splitPane.getDividers().size(), splitPane.getDividerPositions());
            SPLITPOS.store(dividerPos);
        });
        splitPane.getDividers().forEach(dividerChangeRunnable);
        splitPane.getDividers().addListener((ListChangeListener.Change<? extends SplitPane.Divider> change) -> {
            while (change.next()) {
                change.getAddedSubList().forEach(dividerChangeRunnable);

                if (change.getAddedSize() != 0 || change.getRemovedSize() != 0 && dividerPos.containsKey(splitPane.getDividers().size())) {
                    double[] positions = dividerPos.get(splitPane.getDividers().size());
                    Platform.runLater(() -> splitPane.setDividerPositions(positions));
                }
            }
        });

        if (extensionPane != null) {
            extensionPane.visiblePanesProperty().addListener(change -> {
                if (!extensionPane.visiblePanesProperty().get()) {
                    splitPane.getItems().remove(extensionPane.getNode());
                }
                if (extensionPane.visiblePanesProperty().get() && !splitPane.getItems().contains(extensionPane.getNode())) {
                    splitPane.getItems().add(extensionPane.getNode());
                }
            });
        }
    }

    protected final void setStatus(final String status) {
        applicationController.setStatus(status);
    }

    @Override
    public ApplicationPartInterface open(ApplicationPart part, OpenFlag openFlag) {
        return applicationController.open(part, openFlag);
    }

    @Override
    public final ApplicationPartInterface open(DatabasePath path, OpenFlag openFlag) {
        return applicationController.open(path, openFlag);
    }

    @Override
    public CompletableFuture<ApplicationPartInterface> open(DoorsLink dl, OpenFlag openFlag) {
        return dl.resolveAsync(applicationController.getBackgroundTaskExecutor())
                .whenComplete((linkTarget, ex) -> {
                    if (ex != null) {
                        Platform.runLater(() -> setStatus(ex.getMessage()));
                    }
                })
                .thenCompose(linkTarget -> {
                    CompletableFuture<ApplicationPartInterface> appPartInterfaceFuture = new CompletableFuture<>();
                    Platform.runLater(() -> {
                        ApplicationPartInterface appPartInterface = applicationController.open(applicationPart.getDatabasePath().withPath(dl.getTargetModule()), openFlag);
                        appPartInterface.select(linkTarget);
                        appPartInterfaceFuture.complete(appPartInterface);
                    });
                    return appPartInterfaceFuture;
                });
    }

    @Override
    public final CompletableFuture<DatabasePath> createSnapshot(Predicate<DoorsTreeNode> include, DatabasePath destination) {
        return applicationController.createSnapshot(this.getDatabaseInterface(), this.getDatabaseInterface().getPath(), include, destination);
    }

    protected final <T extends ApplicationPartExtension> List<T> getExtensions(Class<T> cls) {
        return extensions.stream()
                .filter(e -> cls.isAssignableFrom(e.getClass()))
                .map(e -> {
                    @SuppressWarnings("unchecked")
                    T ret = (T) e;
                    return ret;
                })
                .collect(Collectors.toList());
    }

    @Override
    public final boolean executeCommand(final AbstractCommand command) {
        if (getDatabaseInterface().isReadOnly()) {
            this.setStatus(command.getName() + ": Database is read-only.");
            return false;
        }

        String notApplicableReason = command.getNotApplicableReason();
        if (notApplicableReason != null) {
            this.setStatus(command.getName() + ": " + notApplicableReason);
            return false;
        }

        command.apply();
        applicationPart.getCommandStack().addCommand(this, command);
        updateGui(command.getUpdateActions());
        return true;
    }

    @SuppressWarnings("unchecked")
    public final void updateGui(UpdateAction... actions) {
        Stream.of(actions).forEach(a -> a.preUpdate((THIS)this));
        Stream.of(actions).forEach(a -> a.update((THIS) this));
        Stream.of(actions).forEach(a -> a.postUpdate((THIS)this));
    }

    @FXML
    public final void redoClicked() {
        final AbstractCommand commandToRedo = applicationPart.getCommandStack().redo(this);
        if (commandToRedo == null) {
            this.setStatus("Cannot redo.");
        } else {
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    @FXML
    public final void undoClicked() {
        final AbstractCommand commandToUndo = applicationPart.getCommandStack().undo(this);
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
        return applicationPart.getDatabasePath();
    }

    @Override
    public final DatabaseInterface getDatabaseInterface() {
        return applicationPart.getDatabaseInterface();
    }

    public void addPlugin(PluginWrapper plugin) {
        List<? extends ApplicationPartExtension> newExts = plugin.getPluginManager().getExtensions(extensionClass, plugin.getPluginId());
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

    public ApplicationPart getApplicationPart() {
        return applicationPart;
    }

    @Override
    public BackgroundTaskExecutorImpl getBackgroundTaskExecutor() {
        return applicationController.getBackgroundTaskExecutor();
    }

    public void shutdown() {
        extensions.clear();
        menus.removeAll(extensionMenus.keySet());
        extensionMenus.clear();
    }

    public boolean isOpened(DoorsTreeNode node) {
        return this.isOpened(this.getPath().withPath(node.getFullName()));
    }

    public boolean isOpened(DatabasePath path) {
        return this.applicationController.getApplicationPartControllers().stream()
                .filter(apc -> apc != this)
                .map(ap -> ap.getPath())
                .filter(dbPath -> path.equals(dbPath) || path.isParent(dbPath))
                .findAny().isPresent();
    }

}
