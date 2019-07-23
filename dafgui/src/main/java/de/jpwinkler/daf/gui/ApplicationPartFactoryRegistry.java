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
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.stage.Window;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public final class ApplicationPartFactoryRegistry {

    public ApplicationPartFactoryRegistry(BackgroundTaskExecutorImpl backgroundTaskExecutor, Supplier<Stream<PluginWrapper>> pluginSupplier, BiConsumer<ApplicationPart, Boolean> dirtyListener) {
        this.backgroundTaskExecutor = backgroundTaskExecutor;
        this.dirtyListener = dirtyListener;
        this.pluginSupplier = pluginSupplier;
    }
    private final BiConsumer<ApplicationPart, Boolean> dirtyListener;
    private final Supplier<Stream<PluginWrapper>> pluginSupplier;
    private final BackgroundTaskExecutorImpl backgroundTaskExecutor;

    private final LinkedHashMap<String, ApplicationPartFactory> REGISTRY = new LinkedHashMap<>();
    private final HashSet<BiConsumer<ApplicationPartFactory, ApplicationPartFactory>> listeners = new HashSet<>();

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
                        Stream.of(ApplicationPartFactory.class.getClassLoader()), pluginSupplier.get().map(pl -> pl.getPluginClassLoader()))
                        .map(cl -> {
                            try {
                                return cl.loadClass(databasePath.getDatabaseInterface());
                            } catch (ClassNotFoundException ex) {
                                return null;
                            }
                        })
                        .filter(cls -> cls != null)
                        .filter(cls -> DatabaseInterface.class.isAssignableFrom(cls))
                        .map(cls -> {
                            @SuppressWarnings("unchecked")
                            Class<? extends DatabaseInterface> ret = (Class<? extends DatabaseInterface>) cls;
                            return ret;
                        })
                        .findFirst().orElseThrow(() -> new ClassNotFoundException("Database interface missing: " + databasePath.getDatabaseInterface()));

                databaseInterface = dbInterface.getConstructor(BackgroundTaskExecutor.class, DatabasePath.class, OpenFlag.class).newInstance(backgroundTaskExecutor, databasePath, openFlag);
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
            commandStack = new CommandStack();
            databaseInterfaces.put(databasePath, Triple.of(new MutableInt(1), databaseInterface, commandStack));
        }
        return Pair.of(databaseInterface, commandStack);
    }

    public void closeDatabase(DatabasePath path) {
        path = path.withPath("");
        MutableInt refCounter = databaseInterfaces.get(path).getLeft();
        refCounter.decrement();

        if (refCounter.intValue() == 0) {
            databaseInterfaces.remove(path).getMiddle().close();
        }
    }

    public void register(Class<? extends ApplicationPartFactory> partFactoryClass) {
        this.unregister(partFactoryClass);

        ApplicationPartFactory partFactory;
        try {
            partFactory = partFactoryClass.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }

        REGISTRY.put(partFactoryClass.getCanonicalName(), partFactory);
        listeners.forEach(l -> l.accept(partFactory, null));
    }

    public void unregister(Class<? extends ApplicationPartFactory> partFactoryClass) {
        ApplicationPartFactory partFactory = REGISTRY.remove(partFactoryClass.getCanonicalName());

        if (partFactory != null) {
            listeners.forEach(l -> l.accept(null, partFactory));
        }
    }

    public ApplicationPart getDefaultPart(DatabasePath path) {
        return REGISTRY.values().stream()
                .filter(ap -> ap.databaseInterface.equals(path.getDatabaseInterface()))
                .reduce((ap1, ap2) -> ap2)
                .orElseThrow(() -> new RuntimeException("No application partFactory available to handle this database path"))
                .openPath(path);
    }

    public void addListener(BiConsumer<ApplicationPartFactory, ApplicationPartFactory> listener) {
        listeners.add(listener);
    }

    public void removeListener(BiConsumer<ApplicationPartFactory, ApplicationPartFactory> listener) {
        listeners.remove(listener);
    }

    public Collection<ApplicationPartFactory> registry() {
        return REGISTRY.values();
    }

    private ApplicationPartFactory getPartFactory(String className) {
        return REGISTRY.values().stream()
                .filter(pf -> className.equals(pf.getClass().getCanonicalName()))
                .findAny().orElse(null);
    }

    public static interface DatabasePathFactory {

        public Stream<DatabasePath> create(Window window, ApplicationPartFactory partFactory, boolean save, String proposedName);
    }

    public static interface ApplicationPartControllerFactory {

        public ApplicationPartController construct(ApplicationPaneController appController, ApplicationPart applicationPart);
    }

    public static abstract class ApplicationPartFactory implements Serializable {

        private final String name;
        private final String databaseInterface;
        private final boolean allowNew;

        protected ApplicationPartFactory(String name, Class<? extends DatabaseInterface> databaseInterface, boolean allowNew) {
            this.name = name;
            this.databaseInterface = databaseInterface.getCanonicalName();
            this.allowNew = allowNew;
        }

        public final boolean isAllowNew() {
            return allowNew;
        }

        public final String getName() {
            return name;
        }

        @Override
        public final String toString() {
            return getName();
        }

        public final String getDatabaseInterface() {
            return databaseInterface;
        }

        public final Stream<ApplicationPart> openWithSelector(Window window) {
            return this.getDatabasePathFactory().create(window, this, false, null).map(this::openPath);
        }

        public final Stream<ApplicationPart> saveWithSelector(Window window, String proposedName) {
            return this.getDatabasePathFactory().create(window, this, true, proposedName).map(this::openPath);
        }

        protected abstract DatabasePathFactory getDatabasePathFactory();

        protected ApplicationPartControllerFactory getApplicationPartControllerFactory() {
            return ApplicationPartFactories::dynamicPartConstructor;
        }

        private ApplicationPart openPath(DatabasePath path) {
            if (!this.databaseInterface.equals(path.getDatabaseInterface())) {
                throw new IllegalArgumentException();
            }

            return new ApplicationPart(this.getClass().getCanonicalName(), path);
        }

        public abstract boolean canStore(DoorsTreeNode databaseRoot);
    }

    public static final class ApplicationPart implements Serializable {

        private final String partFactoryClass;
        private final DatabasePath databasePath;

        private transient ApplicationPartFactoryRegistry registry;

        private transient DatabaseInterface databaseInterface;
        private transient CommandStack commandStack;
        private transient ApplicationPartController controller;
        private transient Consumer<Boolean> dirtyListenerRef;

        private ApplicationPart(String partFactoryClass, DatabasePath databasePath) {
            this.partFactoryClass = partFactoryClass;
            this.databasePath = databasePath;
        }

        public ApplicationPartFactory getApplicationPartFactory() {
            return registry.getPartFactory(partFactoryClass);
        }

        public DatabasePath getDatabasePath() {
            return databasePath;
        }

        public boolean isStarted() {
            return this.databaseInterface != null && this.commandStack != null;
        }

        public ApplicationPartController start(ApplicationPaneController appController, OpenFlag openFlag) {
            if (this.isStarted()) {
                throw new IllegalStateException("Already started");
            }

            this.registry = appController.getApplicationPartFactoryRegistry();
            Pair<DatabaseInterface, CommandStack> db = registry.openDatabase(databasePath, openFlag);
            this.databaseInterface = db.getLeft();
            this.commandStack = db.getRight();
            this.dirtyListenerRef = dirty -> this.registry.dirtyListener.accept(this, dirty);
            this.commandStack.addDirtyListener(this.dirtyListenerRef);
            Platform.runLater(() -> this.dirtyListenerRef.accept(commandStack.isDirty()));

            this.controller = appController.getApplicationPartFactoryRegistry().getPartFactory(this.partFactoryClass).getApplicationPartControllerFactory().construct(appController, this);
            this.registry.pluginSupplier.get().forEach(controller::addPlugin);

            return this.controller;
        }

        public ApplicationPart stop() {
            if (!this.isStarted()) {
                return this;
            }

            this.controller.shutdown();
            this.registry.closeDatabase(databasePath);
            this.controller = null;

            this.commandStack.removeDirtyListener(this.dirtyListenerRef);
            this.dirtyListenerRef = null;
            this.commandStack = null;
            this.databaseInterface = null;
            this.registry = null;

            return this;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.partFactoryClass, this.databasePath);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ApplicationPart other = (ApplicationPart) obj;
            if (!Objects.equals(this.partFactoryClass, other.partFactoryClass)) {
                return false;
            }
            if (!Objects.equals(this.databasePath, other.databasePath)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return databasePath.toString();
        }

        public DatabaseInterface getDatabaseInterface() {
            return databaseInterface;
        }

        public CommandStack getCommandStack() {
            return commandStack;
        }

        public ApplicationPartController getController() {
            return controller;
        }

    }
}
