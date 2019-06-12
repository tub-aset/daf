/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.DoorsApplicationDatabaseInterface;
import de.jpwinkler.daf.db.FolderDatabaseInterface;
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.db.XmiDatabaseInterface;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import org.apache.commons.io.FilenameUtils;
import org.pf4j.PluginState;

/**
 *
 * @author fwiesweg
 */
public final class ApplicationPart<T extends DatabaseInterface> {

    static void registerDefault(ApplicationPartRegistry applicationPartRegistry) {
        applicationPartRegistry.register(new ApplicationPart<>("Doors Bridge", DoorsApplicationDatabaseInterface.class,
                ApplicationPart::dynamicPartConstructor, defaultSelector("", null), false));
        applicationPartRegistry.register(new ApplicationPart<>("Local folder database", FolderDatabaseInterface.class,
                ApplicationPart::dynamicPartConstructor, localFolderDatabaseSelector(), true));
        applicationPartRegistry.register(new ApplicationPart<>("Local xmi database", XmiDatabaseInterface.class,
                ApplicationPart::dynamicPartConstructor, fileChooserSelector(new FileChooser.ExtensionFilter("XMI", "*.xmi")), true));
        applicationPartRegistry.register(new ApplicationPart<>("Local module", RawFileDatabaseInterface.class,
                ModulePaneController::new, fileChooserSelector(f -> FilenameUtils.removeExtension(f.getAbsolutePath()), new FileChooser.ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), true));
    }

    public static interface ApplicationPartConstructor {

        public ApplicationPartController construct(ApplicationPaneController appController, ApplicationPart applicationPart, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack);
    }

    public static interface DatabaseSelector {

        public Stream<DatabasePath> select(Window window, ApplicationPart part, boolean save);
    }

    private final String name;
    private final Class<T> databaseInterfaceClass;
    private final ApplicationPartConstructor partConstructor;
    private final DatabaseSelector selector;
    private final boolean allowNew;

    public ApplicationPart(String name, Class<T> databaseInterface,
            ApplicationPartConstructor partConstructor, DatabaseSelector selector, boolean allowNew) {
        this.name = name;
        this.databaseInterfaceClass = databaseInterface;
        this.partConstructor = partConstructor;
        this.selector = selector;
        this.allowNew = allowNew;
    }

    public static ApplicationPartController dynamicPartConstructor(ApplicationPaneController appController, ApplicationPart applicationPart, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        if (path.getPath() == null || path.getPath().isEmpty()) {
            return new DatabasePaneController(appController, applicationPart, path, databaseInterface, databaseCommandStack);
        } else {
            return new ModulePaneController(appController, applicationPart, path, databaseInterface, databaseCommandStack);
        }
    }

    public static DatabaseSelector defaultSelector(String dbPath, String path) {
        return (window, part, save) -> save ? Stream.empty() : Stream.of(new DatabasePath<>(part.getDatabaseInterfaceClass(), dbPath, path));
    }

    public static DatabaseSelector fileChooserSelector(ExtensionFilter... extensionFilters) {
        return fileChooserSelector(f -> f.getAbsolutePath(), extensionFilters);
    }

    public static DatabaseSelector fileChooserSelector(Function<File, String> transform, ExtensionFilter... extensionFilters) {
        return (window, part, save) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle((save ? "Save a " : "Open a ") + part.getName());
            fileChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            fileChooser.getExtensionFilters().addAll(extensionFilters);

            return Stream.of(save ? fileChooser.showSaveDialog(window) : fileChooser.showOpenDialog(window))
                    .filter(f -> f != null)
                    .peek(f -> {
                        if (save) {
                            ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        } else {
                            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        }
                    })
                    .map(transform)
                    .map(f -> new DatabasePath<>(part.getDatabaseInterfaceClass(), f, ""));
        };
    }

    public static DatabaseSelector directorySelector() {
        return (window, part, save) -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle((save ? "Save a " : "Open a ") + part.getName());
            dirChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());

            return Stream.of(dirChooser.showDialog(window))
                    .filter(f -> f != null)
                    .peek(f -> {
                        if (save) {
                            ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        } else {
                            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        }
                    })
                    .map(f -> new DatabasePath<>(part.getDatabaseInterfaceClass(), f.getAbsolutePath(), ""));
        };
    }

    public static DatabaseSelector localFolderDatabaseSelector() {
        return (window, part, save)
                -> save ? directorySelector().select(window, part, save) : fileChooserSelector(f -> f.getParent(), new ExtensionFilter("Root folder MMD", "__folder__.mmd")).select(window, part, save);
    }

    public static DatabaseSelector genericSelector() {
        return (window, part, save) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + part.getName());
            dialog.setHeaderText("Please enter a URI to " + (save ? "save." : "open."));
            dialog.setContentText(part.getDatabaseInterfaceClass().getSimpleName() + ":");

            return dialog.showAndWait().stream()
                    .map(s -> new DatabasePath(part.getDatabaseInterfaceClass(), s));
        };
    }

    public String getName() {
        return name;
    }

    public boolean isAllowNew() {
        return allowNew;
    }
    
    public Class<T> getDatabaseInterfaceClass() {
        return databaseInterfaceClass;
    }

    public String getDatabaseInterfaceName() {
        return databaseInterfaceClass.getCanonicalName();
    }

    public Stream<DatabasePath> openWithSelector(Window window) {
        return selector.select(window, this, false);
    }

    public Stream<DatabasePath> saveWithSelector(Window window) {
        return selector.select(window, this, true);
    }

    public ApplicationPartController createController(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        ApplicationPartController pc = partConstructor.construct(appController, this, path, databaseInterface, databaseCommandStack);
        appController.pluginManager.getPlugins(PluginState.STARTED).forEach(pc::addPlugin);
        return pc;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static class ApplicationPartRegistry {

        private final Map<String, List<ApplicationPart<?>>> REGISTRY = new HashMap<>();
        private final HashSet<BiConsumer<ApplicationPart<?>, ApplicationPart<?>>> listeners = new HashSet<>();

        public void register(ApplicationPart part) {
            if (!REGISTRY.containsKey(part.getDatabaseInterfaceName())) {
                REGISTRY.put(part.getDatabaseInterfaceName(), new ArrayList<>());
            }
            REGISTRY.get(part.getDatabaseInterfaceName()).add(part);

            listeners.forEach(l -> l.accept(part, null));
        }

        public void unregister(ApplicationPart part) {
            if (!REGISTRY.containsKey(part.getDatabaseInterfaceName())) {
                return;
            }
            listeners.forEach(l -> l.accept(null, part));

            REGISTRY.get(part.getDatabaseInterfaceName()).remove(part);
            if (REGISTRY.get(part.getDatabaseInterfaceName()).isEmpty()) {
                REGISTRY.remove(part.getDatabaseInterfaceName());
            }
        }

        private ApplicationPart getPart(DatabasePath path) {
            List<ApplicationPart<?>> partList = REGISTRY.get(path.getDatabaseInterface());
            if (partList == null) {
                throw new RuntimeException("No application part available to handle this database path");
            }

            return partList.get(partList.size() - 1);
        }

        public Stream<DatabasePath> openWithSelector(DatabasePath path, Window window) {
            return getPart(path).openWithSelector(window);
        }

        public Stream<DatabasePath> saveWithSelector(DatabasePath path, Window window) {
            return getPart(path).saveWithSelector(window);
        }

        public ApplicationPartController createControllerForAny(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
            return getPart(path).createController(appController, path, databaseInterface, databaseCommandStack);
        }

        public void addListener(BiConsumer<ApplicationPart<?>, ApplicationPart<?>> listener) {
            listeners.add(listener);
        }

        public void removeListener(BiConsumer<ApplicationPart<?>, ApplicationPart<?>> listener) {
            listeners.remove(listener);
        }

        public Stream<ApplicationPart<?>> registry() {
            return REGISTRY.values().stream()
                    .flatMap(l -> l.stream())
                    .sorted((p1, p2) -> Objects.compare(p1.getName(), p2.getName(), Comparator.naturalOrder()));
        }
    }

}
