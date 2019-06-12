/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabasePath;
import java.io.File;
import java.util.TreeMap;

/**
 *
 * @author fwiesweg
 */
class ApplicationPreferences extends ApplicationPreference {

    public static final ApplicationPreference RECENT_FILES = new ApplicationPreferences("RECENT_FILES", TreeMap.class, new TreeMap<Long, DatabasePath>());
    public static final ApplicationPreference SAVE_DIRECTORY = new ApplicationPreferences("SAVE_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());
    public static final ApplicationPreference OPEN_DIRECTORY = new ApplicationPreferences("OPEN_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());
    public static final ApplicationPreference PLUGIN_DIRECTORY = new ApplicationPreferences("PLUGIN_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());

    public ApplicationPreferences(String name, Class<?> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }
}
