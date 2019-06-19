/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.plugin;

import de.jpwinkler.daf.gui.ApplicationPaneExtension;
import de.jpwinkler.daf.gui.ApplicationPaneInterface;
import de.jpwinkler.daf.gui.ApplicationPart;
import de.jpwinkler.daf.gui.ApplicationPartExtension;
import de.jpwinkler.daf.gui.ApplicationPartInterface;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.databases.DatabasePaneExtension;
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
     * @param <T>
     * @param args
     * @return
     * @deprecated
     */
    @Deprecated
    public static <T> List<T> List_of(T... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    @Extension
    public static class TestApplicationPartExtension implements ApplicationPartExtension, DatabasePaneExtension {

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
    public static class TestApplicationPaneExtension implements ApplicationPaneExtension {

        private final List<Menu> menus = List_of(new Menu("Global extension menu"));
        private final List<ApplicationPart> applicationParts = List_of(new ApplicationPart("Test Extension Part", PluginDatabaseInterface.class,
                DatabasePaneController::new, ApplicationPart.defaultSelector("", ""), false));
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
        public List<ApplicationPart> getApplicationParts() {
            return applicationParts;
        }

    }


}
