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
import java.util.function.BiFunction;
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

    private ApplicationPart register() {
        REGISTRY.put(this.databaseInterface, this);
        return this;
    }

    public static final ApplicationPart<DoorsApplicationDatabaseInterface> DOORS_DATABASE = new ApplicationPart<>(DoorsApplicationDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, defaultSelector("/", null), directorySelector(true), null).register();
    public static final ApplicationPart<FileDatabaseInterface> LOCAL_DATABASE = new ApplicationPart<>(FileDatabaseInterface.class,
            ApplicationPart::dynamicPartConstructor, directorySelector(false), directorySelector(true), "New local database").register();
    public static final ApplicationPart<RawFileDatabaseInterface> LOCAL_MODULE = new ApplicationPart<>(RawFileDatabaseInterface.class,
            ModulePaneController::new, fileChooserSelector(false, new ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), fileChooserSelector(true, new ExtensionFilter("CSV/MMD", "*.csv", "*.mmd")), "New local module").register();

    private final Class<T> databaseInterface;
    private final String unnamedName;
    private final BiFunction<ApplicationPaneController, DatabasePath, ApplicationPartController> partConstructor;
    private final BiFunction<Window, ApplicationPart, Stream<DatabasePath>> openSelector;
    private final BiFunction<Window, ApplicationPart, Stream<DatabasePath>> saveSelector;

    private ApplicationPart(Class<T> databaseInterface, BiFunction<ApplicationPaneController, DatabasePath, ApplicationPartController> partConstructor,
            BiFunction<Window, ApplicationPart, Stream<DatabasePath>> openSelector, BiFunction<Window, ApplicationPart, Stream<DatabasePath>> saveSelector,
            String unnamedName) {
        this.databaseInterface = databaseInterface;
        this.unnamedName = unnamedName;
        this.partConstructor = partConstructor;
        this.openSelector = openSelector;
        this.saveSelector = saveSelector;
    }

    private static <T extends DatabaseInterface> ApplicationPartController dynamicPartConstructor(ApplicationPaneController applicationController, DatabasePath<T> path) {
        if (path.getPath() == null || path.getPath().isEmpty()) {
            return new DatabasePaneController(applicationController, path);
        } else {
            return new ModulePaneController(applicationController, path);
        }
    }

    private static BiFunction<Window, ApplicationPart, Stream<DatabasePath>> defaultSelector(String dbPath, String path) {
        return (window, part) -> Stream.of(new DatabasePath<>(part.getDatabaseInterface(), dbPath, path));
    }

    private static BiFunction<Window, ApplicationPart, Stream<DatabasePath>> fileChooserSelector(boolean save, ExtensionFilter... extensionFilters) {
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
                    .map(f -> new DatabasePath<>(part.getDatabaseInterface(), f, ""));
        };
    }

    private static BiFunction<Window, ApplicationPart, Stream<DatabasePath>> directorySelector(boolean save) {
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
                    .map(f -> new DatabasePath<>(part.getDatabaseInterface(), f.getAbsolutePath(), ""));
        };
    }

    private static BiFunction<Window, ApplicationPart, Stream<DatabasePath>> genericSelector(boolean save) {
        return (window, part) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + part.toString());
            dialog.setHeaderText("Please enter a URI to " + (save ? "save." : "open."));
            dialog.setContentText(part.getDatabaseInterface().getSimpleName() + ":");

            return dialog.showAndWait().stream()
                    .map(s -> new DatabasePath(part.getDatabaseInterface(), s));
        };
    }

    public Class<T> getDatabaseInterface() {
        return databaseInterface;
    }

    public String getUnnamedName() {
        return unnamedName;
    }

    public DatabasePath newPath() {
        return new DatabasePath(databaseInterface, null, null);
    }

    public Stream<DatabasePath> openWithSelector(Window window) {
        return openSelector.apply(window, this);
    }

    public static Stream<DatabasePath> openWithSelector(DatabasePath path, Window window) {
        return REGISTRY.get(path.getDatabaseInterface()).openWithSelector(window);
    }

    public Stream<DatabasePath> saveWithSelector(Window window) {
        return saveSelector.apply(window, this);
    }

    public static Stream<DatabasePath> saveWithSelector(DatabasePath path, Window window) {
        return REGISTRY.get(path.getDatabaseInterface()).saveWithSelector(window);
    }

    public ApplicationPartController createController(ApplicationPaneController appController, DatabasePath path) {
        return partConstructor.apply(appController, path);
    }

    public static ApplicationPartController createControllerForAny(ApplicationPaneController appController, DatabasePath path) {
        return REGISTRY.get(path.getDatabaseInterface()).createController(appController, path);
    }
}
