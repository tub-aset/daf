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
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.DoorsApplicationDatabaseInterface;
import de.jpwinkler.daf.db.DoorsApplicationDummyDatabaseInterface;
import de.jpwinkler.daf.db.FolderDatabaseInterface;
import static de.jpwinkler.daf.db.FolderDatabaseInterface.DOORS_DB_MMD;
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.db.XmiDatabaseInterface;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPartFactory;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.DatabasePathFactory;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
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
        registry.register(DoorsApplicationStandardViewPartFactory.class);
        registry.register(DoorsApplicationPartFactory.class);
        registry.register(DoorsDummyApplicationStandardViewPartFactory.class);
        registry.register(DoorsDummyApplicationPartFactory.class);
        registry.register(LocalFolderApplicationPartFactory.class);
        registry.register(LocalXmiApplicationPartFactory.class);
        registry.register(LocalModuleApplicationPartFactory.class);
    }

    public static final class DoorsApplicationStandardViewPartFactory extends ApplicationPartFactory {

        public DoorsApplicationStandardViewPartFactory() {
            super("Doors Bridge (standard view)", DoorsApplicationDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return defaultSelector(DoorsApplication.STANDARD_VIEW, null);
        }

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return false;
        }

    }

    public static final class DoorsApplicationPartFactory extends ApplicationPartFactory {

        public DoorsApplicationPartFactory() {
            super("Doors Bridge", DoorsApplicationDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return genericSelector(save -> "Please enter a view name.");
        }

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return false;
        }

    }

    public static final class DoorsDummyApplicationStandardViewPartFactory extends ApplicationPartFactory {

        public DoorsDummyApplicationStandardViewPartFactory() {
            super("Doors Bridge Dummy (standard view)", DoorsApplicationDummyDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return defaultSelector(DoorsApplication.STANDARD_VIEW, null);
        }

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return false;
        }

    }

    public static final class DoorsDummyApplicationPartFactory extends ApplicationPartFactory {

        public DoorsDummyApplicationPartFactory() {
            super("Doors Bridge Dummy", DoorsApplicationDummyDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartFactoryRegistry.DatabasePathFactory getDatabasePathFactory() {
            return genericSelector(save -> "Please enter a view name.");
        }

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return false;
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

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return databaseRoot instanceof DoorsFolder;
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

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return databaseRoot instanceof DoorsFolder;
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

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return databaseRoot instanceof DoorsModule;
        }
    }

    public static DatabasePathFactory fileChooserSelector(FileChooser.ExtensionFilter... extensionFilters) {
        return fileChooserSelector((f) -> f.getAbsolutePath(), extensionFilters);
    }

    public static DatabasePathFactory fileChooserSelector(Function<File, String> transform, FileChooser.ExtensionFilter... extensionFilters) {
        return (window, partFactory, save, proposedName) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            fileChooser.setInitialFileName(proposedName + (extensionFilters.length == 0 ? "" : extensionFilters[0].getExtensions().get(0).substring(1)));

            fileChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            fileChooser.getExtensionFilters().addAll(extensionFilters);
            return Stream.of(save ? fileChooser.showSaveDialog(window) : fileChooser.showOpenDialog(window)).filter((f) -> f != null).peek((f) -> {
                if (save) {
                    ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                } else {
                    ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                }
            }).map(transform).map((f) -> new DatabasePath(partFactory.getDatabaseInterface(), f, ""));
        };
    }

    public static DatabasePathFactory defaultSelector(String dbPath, String path) {
        return (window, partFactory, save, proposedName) -> save ? Stream.empty() : Stream.of(new DatabasePath(partFactory.getDatabaseInterface(), dbPath, path));
    }

    public static DatabasePathFactory directorySelector() {
        return (window, partFactory, save, proposedName) -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            dirChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            return Stream.of(dirChooser.showDialog(window)).filter((f) -> f != null).peek((f) -> {
                if (save) {
                    ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                } else {
                    ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                }
            }).map((f) -> new DatabasePath(partFactory.getDatabaseInterface(), f.getAbsolutePath(), ""));
        };
    }

    public static DatabasePathFactory genericSelector(Function<Boolean, String> headerGenerator) {
        return (window, partFactory, save, proposedName) -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle((save ? "Save a " : "Open a ") + partFactory.getName());
            dialog.setHeaderText(headerGenerator.apply(save));
            dialog.setContentText(partFactory + ":" + proposedName != null ? proposedName : null);
            return dialog.showAndWait().stream()
                    .map((s) -> s.split(":"))
                    .filter(s -> s.length <= 2)
                    .map(s -> new DatabasePath(partFactory.getDatabaseInterface(), s[0], s.length == 1 ? "" : s[1]));
        };
    }

    public static DatabasePathFactory localFolderDatabaseSelector() {
        return (window, partFactory, save, proposedName) -> save ? directorySelector().create(window, partFactory, save, proposedName) : fileChooserSelector((f) -> f.getParent(), new FileChooser.ExtensionFilter("Root folder MMD", DOORS_DB_MMD)).create(window, partFactory, save, proposedName);
    }

    public static ApplicationPartController dynamicPartConstructor(ApplicationPaneController appController, ApplicationPart part) {
        if (part.getDatabasePath().isRoot() && DoorsFolder.class.isAssignableFrom(part.getDatabaseInterface().getDatabaseRootClass())) {
            return new DatabasePaneController(appController, part);
        } else {
            return new ModulePaneController(appController, part);
        }
    }

}
