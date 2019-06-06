/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.plugin;

import de.jpwinkler.daf.gui.extensions.ApplicationPartExtension;
import de.jpwinkler.daf.gui.extensions.ApplicationPartInterface;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import de.jpwinkler.daf.gui.extensions.DatabasePanesExtension;
import de.jpwinkler.daf.gui.extensions.MenuExtension;

/**
 *
 * @author fwiesweg
 */
public class PluginMain extends Plugin {

    public PluginMain(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class TestExtension implements ApplicationPartExtension, MenuExtension, DatabasePanesExtension {

        private final List<Menu> menus = List.of(new Menu("Test Extension Menu"));
        private final List<Node> sidePanel = List.of(new Label("Test Extension Side Panel"));
        private final List<Node> bottomPanel = List.of(new Label("Test Extension Bottom Panel"));
        private ApplicationPartInterface applicationPartInterface;

        @Override
        public void initialise(ApplicationPartInterface applicationPartInterface) {
            this.applicationPartInterface = applicationPartInterface;
        }

        @Override
        public List<Menu> getMenus() {
            return menus;
        }

        @Override
        public List<Node> getBottomPanes() {
            return bottomPanel;
        }

        @Override
        public List<Node> getSidePanes() {
            return sidePanel;
        }

    }
}
