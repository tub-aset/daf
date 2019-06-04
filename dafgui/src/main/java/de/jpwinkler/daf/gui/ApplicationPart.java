/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.DoorsApplicationDatabaseInterface;
import de.jpwinkler.daf.db.FileDatabaseInterface;
import de.jpwinkler.daf.db.XmiDatabaseInterface;
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author fwiesweg
 */
public class ApplicationPart<T extends DatabaseInterface> {

    private static final Map<Class<? extends DatabaseInterface>, ApplicationPart<?>> REGISTRY = new HashMap<>();

    public static Stream<ApplicationPart<?>> registry() {
        return REGISTRY.values().stream();
    }

    private static interface ApplicationPartConstructor {

        public ApplicationPartController construct(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack);
    }

    private static interface DatabaseSelector {

        public Stream<DatabasePath> select(Window window, ApplicationPart part, boolean save);
    }

    private ApplicationPart register() {
        REGISTRY.put(this.databaseInterfaceClass, this);
        return this;
    }

    public static final ApplicationPart<DoorsApplicationDatabaseInterface> DOORS_DATABASE = new ApplicationPart<>("Doors Bridge", DoorsApplicationDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, defaultSelector("/", null), false).register();
    public static final ApplicationPart<FileDatabaseInterface> LOCAL_FILE_DATABASE = new ApplicationPart<>("Local file database", FileDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, fileChooserSelector(new ExtensionFilter("CSV database", "__folder__.mmd")), true).register();
    public static final ApplicationPart<XmiDatabaseInterface> LOCAL_XMI_DATABASE = new ApplicationPart<>("Local XMI database", XmiDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, fileChooserSelector(new ExtensionFilter("XMI", "*.xmi")), true).register();
    public static final ApplicationPart<RawFileDatabaseInterface> LOCAL_MODULE = new ApplicationPart<>("Local module", RawFileDatabaseInterface.class,
            ModulePaneController::new, fileChooserSelector(f -> FilenameUtils.removeExtension(f.getAbsolutePath()), new ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), true).register();

    private final String name;
    private final Class<T> databaseInterfaceClass;
    private final ApplicationPartConstructor partConstructor;
    private final DatabaseSelector selector;
    private final boolean allowNew;

    private ApplicationPart(String name, Class<T> databaseInterface,
            ApplicationPartConstructor partConstructor, DatabaseSelector selector, boolean allowNew) {
        this.name = name;
        this.databaseInterfaceClass = databaseInterface;
        this.partConstructor = partConstructor;
        this.selector = selector;
        this.allowNew = allowNew;
    }

    private static ApplicationPartController dynamicPartConstructor(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        if (path.getPath() == null || path.getPath().isEmpty()) {
            return new DatabasePaneController(appController, path, databaseInterface, databaseCommandStack);
        } else {
            return new ModulePaneController(appController, path, databaseInterface, databaseCommandStack);
        }
    }

    private static DatabaseSelector defaultSelector(String dbPath, String path) {
        return (window, part, save) -> save ? Stream.empty() : Stream.of(new DatabasePath<>(part.getDatabaseInterfaceClass(), dbPath, path));
    }

    private static DatabaseSelector fileChooserSelector(ExtensionFilter... extensionFilters) {
        return fileChooserSelector(f -> f.getAbsolutePath(), extensionFilters);
    }

    private static DatabaseSelector fileChooserSelector(Function<File, String> transform, ExtensionFilter... extensionFilters) {
        return (window, part, save) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle((save ? "Save a " : "Open a ") + part.toString());
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

    private static DatabaseSelector directorySelector() {
        return (window, part, save) -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle((save ? "Save a " : "Open a ") + part.toString());
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

    private static DatabaseSelector genericSelector() {
        return (window, part, save) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + part.toString());
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

    public Stream<DatabasePath> openWithSelector(Window window) {
        return selector.select(window, this, false);
    }

    public static Stream<DatabasePath> openWithSelector(DatabasePath path, Window window) {
        return REGISTRY.get(path.getDatabaseInterface()).openWithSelector(window);
    }

    public Stream<DatabasePath> saveWithSelector(Window window) {
        return selector.select(window, this, true);
    }

    public static Stream<DatabasePath> saveWithSelector(DatabasePath path, Window window) {
        return REGISTRY.get(path.getDatabaseInterface()).saveWithSelector(window);
    }

    public ApplicationPartController createController(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        return partConstructor.construct(appController, path, databaseInterface, databaseCommandStack);
    }

    public static ApplicationPartController createControllerForAny(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        return REGISTRY.get(databaseInterface.getClass()).createController(appController, path, databaseInterface, databaseCommandStack);
    }
}
