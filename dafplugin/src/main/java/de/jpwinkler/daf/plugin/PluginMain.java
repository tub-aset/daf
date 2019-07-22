package de.jpwinkler.daf.plugin;

/*-
 * #%L
 * dafplugin
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
import de.jpwinkler.daf.gui.ApplicationPaneExtension;
import de.jpwinkler.daf.gui.ApplicationPaneInterface;
import de.jpwinkler.daf.gui.ApplicationPartFactories;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPartControllerFactory;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPartFactory;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.DatabasePathFactory;
import de.jpwinkler.daf.gui.ApplicationPartInterface;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.databases.DatabasePaneExtension;
import de.jpwinkler.daf.gui.modules.ModulePaneExtension;
import de.jpwinkler.daf.gui.modules.ViewDefinition;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public class PluginMain extends Plugin {

    public PluginMain(PluginWrapper wrapper) {
        super(wrapper);
    }

    /**
     * Java 8 replacement for List.of
     *
     * @param <T>
     * @param args
     * @return
     * @deprecated
     */
    @Deprecated
    @SafeVarargs
    public static <T> List<T> List_of(T... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    @Extension
    public static class TestApplicationPartExtension implements DatabasePaneExtension {

        private final List<Menu> menus = List_of(new Menu(PluginPreferences.MENU_NAME.retrieve()));
        private final List<Node> sidePanes = List_of(new Label("Test Extension Side Panel"));
        private final List<Node> bottomPanes = List_of(new Label("Test Extension Bottom Panel"));
        private ApplicationPartInterface applicationPartInterface;

        @Override
        public void initialise(ApplicationPartInterface applicationPartInterface) {
            this.applicationPartInterface = applicationPartInterface;
            this.sidePanes.forEach(sp -> sp.setUserData("Test Plugin SP"));
            this.bottomPanes.forEach(sp -> sp.setUserData("Test Plugin BP"));
        }

        @Override
        public List<Menu> getMenus() {
            return menus;
        }

        @Override
        public List<Node> getBottomPanes() {
            return bottomPanes;
        }

        @Override
        public List<Node> getSidePanes() {
            return sidePanes;
        }

    }

    @Extension
    public static class TestModulePaneExtension implements ModulePaneExtension {

        private final List<Node> sidePanes = List_of(new Label("Test Extension Side Panel for Modules"));
        private final List<Node> bottomPanes = List_of(new Label("Test Extension Bottom Panel for Modules"));
        private final List<ViewDefinition> views = List_of(new ViewDefinition("Test Extension View"));
        private ApplicationPartInterface applicationPartInterface;

        @Override
        public void initialise(ApplicationPartInterface applicationPartInterface) {
            this.applicationPartInterface = applicationPartInterface;
            this.sidePanes.forEach(sp -> sp.setUserData("Test Plugin SP"));
            this.bottomPanes.forEach(sp -> sp.setUserData("Test Plugin BP"));
        }

        @Override
        public List<Node> getBottomPanes() {
            return bottomPanes;
        }

        @Override
        public List<Node> getSidePanes() {
            return sidePanes;
        }

        @Override
        public List<ViewDefinition> getAdditionalViews() {
            return views;
        }
    }

    @Extension
    public static class TestApplicationPaneExtension implements ApplicationPaneExtension {

        private final List<Menu> menus = List_of(new Menu("Global extension menu"));
        private final List<Class<? extends ApplicationPartFactory>> applicationParts = List_of(TestApplicationPartFactory.class);
        private ApplicationPaneInterface applicationPaneInterface;

        @Override
        public void initialise(ApplicationPaneInterface applicationPaneInterface) {
            this.applicationPaneInterface = applicationPaneInterface;
        }

        @Override
        public List<Menu> getApplicationMenus() {
            return menus;
        }

        @Override
        public List<Class<? extends ApplicationPartFactory>> getApplicationPartFactories() {
            return applicationParts;
        }

    }

    public static class TestApplicationPartFactory extends ApplicationPartFactory {

        public TestApplicationPartFactory() {
            super("Test Extension Part", PluginDatabaseInterface.class, false);
        }

        @Override
        protected ApplicationPartControllerFactory getApplicationPartControllerFactory() {
            return DatabasePaneController::new;
        }

        @Override
        protected DatabasePathFactory getDatabasePathFactory() {
            return ApplicationPartFactories.defaultSelector("", "");
        }

        @Override
        public boolean canStore(DoorsTreeNode databaseRoot) {
            return false;
        }

    }

}
