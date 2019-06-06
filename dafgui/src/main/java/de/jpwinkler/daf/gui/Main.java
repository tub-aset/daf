/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import static javafx.application.Application.launch;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

/**
 *
 * @author fwiesweg
 */
public class Main {
    
    public static final PluginManager PLUGIN_MANAGER = new DefaultPluginManager();

    public static void main(final String[] args) {
        PLUGIN_MANAGER.loadPlugins();
        PLUGIN_MANAGER.startPlugins();

        launch(MainFX.class, args);
    }
}
