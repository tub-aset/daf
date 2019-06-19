/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.plugin;

import de.jpwinkler.daf.gui.ApplicationPreference;
import java.io.Serializable;

/**
 *
 * @author fwiesweg
 */
public class PluginPreferences extends ApplicationPreference {

    public static final ApplicationPreference MENU_NAME = new PluginPreferences("MENU_NAME", String.class, "Application Part Extension Menu");

    public PluginPreferences(String name, Class<? extends Serializable> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }
}
