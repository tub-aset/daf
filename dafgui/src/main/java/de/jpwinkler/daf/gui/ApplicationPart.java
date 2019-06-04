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
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import java.util.HashMap;
import java.util.Map;
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

    private static final Map<Class<? extends DatabaseInterface>, ApplicationPart> REGISTRY = new HashMap<>();
    private static interface ApplicationPartConstructor {
        public ApplicationPartController construct(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack);
    }
    private static interface DatabaseSelector {
        public Stream<DatabasePath> select(Window window, ApplicationPart part);
    }
    

    private ApplicationPart register() {
        REGISTRY.put(this.databaseInterfaceClass, this);
        return this;
    }

    public static final ApplicationPart<DoorsApplicationDatabaseInterface> DOORS_DATABASE = new ApplicationPart<>(DoorsApplicationDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, defaultSelector("/", null), directorySelector(true), null).register();
    public static final ApplicationPart<FileDatabaseInterface> LOCAL_DATABASE = new ApplicationPart<>(FileDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, directorySelector(false), directorySelector(true), "New local database").register();
    public static final ApplicationPart<RawFileDatabaseInterface> LOCAL_MODULE = new ApplicationPart<>(RawFileDatabaseInterface.class,
            ModulePaneController::new, fileChooserSelector(false, new ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), fileChooserSelector(true, new ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), "New local module").register();

    private final Class<T> databaseInterfaceClass;
    private final String unnamedName;
    private final ApplicationPartConstructor partConstructor;
    private final DatabaseSelector openSelector;
    private final DatabaseSelector saveSelector;

    private ApplicationPart(Class<T> databaseInterface,
            ApplicationPartConstructor partConstructor,
            DatabaseSelector openSelector,
            DatabaseSelector saveSelector,
            String unnamedName) {
        this.databaseInterfaceClass = databaseInterface;
        this.unnamedName = unnamedName;
        this.partConstructor = partConstructor;
        this.openSelector = openSelector;
        this.saveSelector = saveSelector;
    }

    private static ApplicationPartController dynamicPartConstructor(ApplicationPaneController appController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        if (path.getPath() == null || path.getPath().isEmpty()) {
            return new DatabasePaneController(appController, path, databaseInterface, databaseCommandStack);
        } else {
            return new ModulePaneController(appController, path, databaseInterface, databaseCommandStack);
        }
    }

    private static DatabaseSelector defaultSelector(String dbPath, String path) {
        return (window, part) -> Stream.of(new DatabasePath<>(part.getDatabaseInterfaceClass(), dbPath, path));
    }

    private static DatabaseSelector fileChooserSelector(boolean save, ExtensionFilter... extensionFilters) {
        return (window, part) -> {
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
                    .map(f -> FilenameUtils.removeExtension(f.getAbsolutePath()))
                    .map(f -> new DatabasePath<>(part.getDatabaseInterfaceClass(), f, ""));
        };
    }

    private static DatabaseSelector directorySelector(boolean save) {
        return (window, part) -> {
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

    private static DatabaseSelector genericSelector(boolean save) {
        return (window, part) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + part.toString());
            dialog.setHeaderText("Please enter a URI to " + (save ? "save." : "open."));
            dialog.setContentText(part.getDatabaseInterfaceClass().getSimpleName() + ":");

            return dialog.showAndWait().stream()
                    .map(s -> new DatabasePath(part.getDatabaseInterfaceClass(), s));
        };
    }

    public Class<T> getDatabaseInterfaceClass() {
        return databaseInterfaceClass;
    }

    public String getUnnamedName() {
        return unnamedName;
    }

    public Stream<DatabasePath> openWithSelector(Window window) {
        return openSelector.select(window, this);
    }

    public static Stream<DatabasePath> openWithSelector(DatabasePath path, Window window) {
        return REGISTRY.get(path.getDatabaseInterface()).openWithSelector(window);
    }

    public Stream<DatabasePath> saveWithSelector(Window window) {
        return saveSelector.select(window, this);
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
