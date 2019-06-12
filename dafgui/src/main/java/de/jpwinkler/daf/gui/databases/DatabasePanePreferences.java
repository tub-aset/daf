/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.gui.ApplicationPreference;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author fwiesweg
 */
class DatabasePanePreferences extends ApplicationPreference {

    public static final ApplicationPreference SPLITPOS = new DatabasePanePreferences("SPLITPOS", double[].class, new double[]{0.25, 0.75});
    public static final ApplicationPreference BOTTOM_SPLITPOS = new DatabasePanePreferences("BOTTOM_SPLITPOS", double[].class, new double[]{0.8});
    public static final ApplicationPreference SIDE_EXTENSION = new DatabasePanePreferences("SIDE_EXTENSION", String.class, null);
    public static final ApplicationPreference BOTTOM_EXTENSION = new DatabasePanePreferences("BOTTOM_EXTENSION", String.class, null);
    public static final ApplicationPreference ATTRIBUTENAME_WIDTH = new DatabasePanePreferences("ATTRIBUTENAME_WIDTH", Double.class, 100d);
    public static final ApplicationPreference ATTRIBUTEVALUE_WIDTH = new DatabasePanePreferences("ATTRIBUTEVALUE_WIDTH", Double.class, 300d);
    public static final ApplicationPreference SNAPSHOT_LISTS = new DatabasePanePreferences("SNAPSHOT_LISTS", TreeMap.class, new TreeMap<String, TreeSet<String>>());

    public DatabasePanePreferences(String name, Class<?> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }
}
