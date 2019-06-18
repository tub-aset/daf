/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.DoorsApplicationDatabaseInterface;
import de.jpwinkler.daf.db.FolderDatabaseInterface;
import de.jpwinkler.daf.db.RawFileDatabaseInterface;
import de.jpwinkler.daf.db.XmiDatabaseInterface;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.pf4j.PluginWrapper;

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
        return (window, part, save) -> save ? Stream.empty() : Stream.of(new DatabasePath<>(part.databaseInterfaceClass, dbPath, path));
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
                    .map(f -> new DatabasePath<>(part.databaseInterfaceClass, f, ""));
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
                    .map(f -> new DatabasePath<>(part.databaseInterfaceClass, f.getAbsolutePath(), ""));
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
            dialog.setContentText(part.databaseInterfaceClass.getSimpleName() + ":");

            return Main.asStream(dialog.showAndWait())
                    .map(s -> new DatabasePath(part.databaseInterfaceClass, s));
        };
    }

    public boolean isAllowNew() {
        return allowNew;
    }

    public Stream<DatabasePath> openWithSelector(Window window) {
        return selector.select(window, this, false);
    }

    public Stream<DatabasePath> saveWithSelector(Window window) {
        return selector.select(window, this, true);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    private String getDatabaseInterfaceName() {
        return databaseInterfaceClass.getCanonicalName();
    }

    public static class ApplicationPartRegistry {

        public ApplicationPartRegistry(Supplier<Stream<PluginWrapper>> pluginSupplier, BiConsumer<DatabaseInterface, Boolean> dirtyListener) {
            this.dirtyListener = dirtyListener;
            this.pluginSupplier = pluginSupplier;
        }
        private final BiConsumer<DatabaseInterface, Boolean> dirtyListener;
        private final Supplier<Stream<PluginWrapper>> pluginSupplier;

        private final Map<String, List<ApplicationPart<?>>> REGISTRY = new HashMap<>();
        private final HashSet<BiConsumer<ApplicationPart<?>, ApplicationPart<?>>> listeners = new HashSet<>();

        private final Map<DatabasePath, Triple<MutableInt, DatabaseInterface, CommandStack>> databaseInterfaces = new HashMap<>();

        public Pair<DatabaseInterface, CommandStack> openDatabase(DatabasePath path, OpenFlag openFlag) {
            DatabasePath databasePath = path.withPath("");
            DatabaseInterface databaseInterface;
            CommandStack commandStack;

            if (databaseInterfaces.containsKey(databasePath)) {
                databaseInterfaces.get(databasePath).getLeft().increment();
                databaseInterface = databaseInterfaces.get(databasePath).getMiddle();
                commandStack = databaseInterfaces.get(databasePath).getRight();
            } else {
                try {
                    Class<? extends DatabaseInterface> dbInterface = Stream.concat(
                            Stream.of(ApplicationPart.class.getClassLoader()), pluginSupplier.get().map(pl -> pl.getPluginClassLoader()))
                            .map(cl -> {
                                try {
                                    return cl.loadClass(databasePath.getDatabaseInterface());
                                } catch (ClassNotFoundException ex) {
                                    return null;
                                }
                            })
                            .filter(cls -> cls != null)
                            .filter(cls -> DatabaseInterface.class.isAssignableFrom(cls))
                            .map(cls -> (Class<? extends DatabaseInterface>) cls)
                            .findFirst().orElseThrow(() -> new ClassNotFoundException("Database interface missing: " + databasePath.getDatabaseInterface()));

                    databaseInterface = dbInterface.getConstructor(DatabasePath.class, OpenFlag.class).newInstance(databasePath, openFlag);
                } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
                commandStack = new CommandStack(dirty -> dirtyListener.accept(databaseInterface, dirty));
                databaseInterfaces.put(databasePath, Triple.of(new MutableInt(1), databaseInterface, commandStack));
            }
            return Pair.of(databaseInterface, commandStack);
        }

        public void closeDatabase(DatabasePath path) {
            path = path.withPath("");
            MutableInt refCounter = databaseInterfaces.get(path).getLeft();
            refCounter.decrement();

            if (refCounter.intValue() == 0) {
                databaseInterfaces.remove(path);
            }
        }

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

        public ApplicationPartController createController(ApplicationPaneController appController, DatabasePath path, ApplicationPart part, OpenFlag openFlag) {
            Pair<DatabaseInterface, CommandStack> db = openDatabase(path, openFlag);

            ApplicationPartController pc = part.partConstructor.construct(appController, part, path, db.getLeft(), db.getRight());
            pluginSupplier.get().forEach(pc::addPlugin);
            return pc;
        }

        public ApplicationPartController createController(ApplicationPaneController appController, DatabasePath path, OpenFlag openFlag) {
            Pair<DatabaseInterface, CommandStack> db = openDatabase(path, openFlag);
            ApplicationPart part = getPart(path);

            ApplicationPartController pc = part.partConstructor.construct(appController, part, path, db.getLeft(), db.getRight());
            pluginSupplier.get().forEach(pc::addPlugin);
            return pc;
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
