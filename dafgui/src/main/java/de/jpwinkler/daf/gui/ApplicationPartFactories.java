/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.DoorsApplicationDatabaseInterface;
import de.jpwinkler.daf.db.DoorsApplicationDummyDatabaseInterface;
import de.jpwinkler.daf.db.FolderDatabaseInterface;
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.db.XmiDatabaseInterface;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPartFactory;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.DatabasePathFactory;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import de.jpwinkler.daf.model.DoorsFolder;
import java.io.File;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author fwiesweg
 */
public final class ApplicationPartFactories {

    private ApplicationPartFactories() {

    }

    public static void registerDefault(ApplicationPartFactoryRegistry registry) {
        registry.register(DoorsAppplicationPartFactory.class);
        registry.register(DoorsDummyAppplicationPartFactory.class);
        registry.register(LocalFolderApplicationPartFactory.class);
        registry.register(LocalXmiApplicationPartFactory.class);
        registry.register(LocalModuleApplicationPartFactory.class);
    }

    public static final class DoorsAppplicationPartFactory extends ApplicationPartFactory {

        public DoorsAppplicationPartFactory() {
            super("Doors Bridge", DoorsApplicationDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return defaultSelector("", null);
        }

    }

    public static final class DoorsDummyAppplicationPartFactory extends ApplicationPartFactory {

        public DoorsDummyAppplicationPartFactory() {
            super("Doors Bridge Dummy", DoorsApplicationDummyDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return defaultSelector("", null);
        }

    }

    public static final class LocalFolderApplicationPartFactory extends ApplicationPartFactory {

        public LocalFolderApplicationPartFactory() {
            super("Local folder database", FolderDatabaseInterface.class, true);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return localFolderDatabaseSelector();
        }
    }

    public static final class LocalXmiApplicationPartFactory extends ApplicationPartFactory {

        public LocalXmiApplicationPartFactory() {
            super("Local xmi database", XmiDatabaseInterface.class, true);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return fileChooserSelector(new FileChooser.ExtensionFilter("XMI", "*.xmi"));
        }
    }

    public static final class LocalModuleApplicationPartFactory extends ApplicationPartFactory {

        public LocalModuleApplicationPartFactory() {
            super("Local module", RawFileDatabaseInterface.class, true);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return fileChooserSelector(f -> FilenameUtils.removeExtension(f.getAbsolutePath()), new FileChooser.ExtensionFilter("CSV/MMD", "*.csv", "*.mmd"));
        }
    }

    public static DatabasePathFactory fileChooserSelector(FileChooser.ExtensionFilter... extensionFilters) {
        return fileChooserSelector((f) -> f.getAbsolutePath(), extensionFilters);
    }

    public static DatabasePathFactory fileChooserSelector(Function<File, String> transform, FileChooser.ExtensionFilter... extensionFilters) {
        return (window, partFactory, save) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            fileChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            fileChooser.getExtensionFilters().addAll(extensionFilters);
            return Stream.of(save ? fileChooser.showSaveDialog(window) : fileChooser.showOpenDialog(window)).filter((f) -> f != null).peek((f) -> {
                if (save) {
                    ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                } else {
                    ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                }
            }).map(transform).map((f) -> new DatabasePath<>(partFactory.getDatabaseInterface(), f, ""));
        };
    }

    public static DatabasePathFactory defaultSelector(String dbPath, String path) {
        return (window, partFactory, save) -> save ? Stream.empty() : Stream.of(new DatabasePath<>(partFactory.getDatabaseInterface(), dbPath, path));
    }

    public static DatabasePathFactory directorySelector() {
        return (window, partFactory, save) -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            dirChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            return Stream.of(dirChooser.showDialog(window)).filter((f) -> f != null).peek((f) -> {
                if (save) {
                    ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                } else {
                    ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                }
            }).map((f) -> new DatabasePath<>(partFactory.getDatabaseInterface(), f.getAbsolutePath(), ""));
        };
    }

    public static DatabasePathFactory genericSelector() {
        return (window, partFactory, save) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            dialog.setHeaderText("Please enter a URI to " + (save ? "save." : "open."));
            dialog.setContentText(partFactory + ":");
            return Main.asStream(dialog.showAndWait()).map((s) -> new DatabasePath(partFactory.getDatabaseInterface(), s));
        };
    }

    public static DatabasePathFactory localFolderDatabaseSelector() {
        return (window, partFactory, save) -> save ? directorySelector().create(window, partFactory, save) : fileChooserSelector((f) -> f.getParent(), new FileChooser.ExtensionFilter("Root folder MMD", "__folder__.mmd")).create(window, partFactory, save);
    }

    public static ApplicationPartController dynamicPartConstructor(ApplicationPaneController appController, ApplicationPart part) {
        if (part.getDatabasePath().isRoot() && part.getDatabaseInterface().getDatabaseRoot() instanceof DoorsFolder) {
            return new DatabasePaneController(appController, part);
        } else {
            return new ModulePaneController(appController, part);
        }
    }

}
