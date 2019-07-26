package de.jpwinkler.daf.gui.databases;

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
import de.jpwinkler.daf.gui.ApplicationPreference;
import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author fwiesweg
 */
class DatabasePanePreferences<T extends Serializable> extends ApplicationPreference<T> {

    public static final ApplicationPreference<HashMap<Integer, double[]>> SPLITPOS = new DatabasePanePreferences<>("SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.5});
            put(2, new double[]{0.25, 0.75});
        }
    });
    public static final ApplicationPreference<HashMap<Integer, double[]>> BOTTOM_SPLITPOS = new DatabasePanePreferences<>("BOTTOM_SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.8});
        }
    });
    public static final ApplicationPreference<HashMap<Integer, double[]>> ATTRIBUTES_MODULES_SPLITPOS = new DatabasePanePreferences<>("ATTRIBUTES_MODULES_SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.7});
        }
    });

    public static final ApplicationPreference<String> SIDE_EXTENSION = new DatabasePanePreferences<>("SIDE_EXTENSION", String.class, null);
    public static final ApplicationPreference<String> BOTTOM_EXTENSION = new DatabasePanePreferences<>("BOTTOM_EXTENSION", String.class, null);
    
    public static final ApplicationPreference<Double> ATTRIBUTENAME_WIDTH = new DatabasePanePreferences<>("ATTRIBUTENAME_WIDTH", Double.class, 100d);
    public static final ApplicationPreference<Double> ATTRIBUTEVALUE_WIDTH = new DatabasePanePreferences<>("ATTRIBUTEVALUE_WIDTH", Double.class, 300d);
    
    public static final ApplicationPreference<Double> MODULENAME_WIDTH = new DatabasePanePreferences<>("MODULENAME_WIDTH", Double.class, 200d);
    public static final ApplicationPreference<Double> MODULEDESC_WIDTH = new DatabasePanePreferences<>("MODULEDESC_WIDTH", Double.class, 400d);
    public static final ApplicationPreference<Double> MODULESNAPLIST_WIDTH = new DatabasePanePreferences<>("MODULELIST_WIDTH", Double.class, 150d);
    
    public static final ApplicationPreference<TreeMap<String, SnapshotList>> SNAPSHOT_LISTS = new DatabasePanePreferences<>("SNAPSHOT_LISTS", TreeMap.class, new TreeMap<String, SnapshotList>());

    public DatabasePanePreferences(String name, Class<? super T> valueType, T defaultValue) {
        super(name, valueType, defaultValue);
    }
}
