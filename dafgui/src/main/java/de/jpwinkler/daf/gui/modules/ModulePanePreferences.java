package de.jpwinkler.daf.gui.modules;

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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author fwiesweg
 */
class ModulePanePreferences extends ApplicationPreference {

    public static final ApplicationPreference SPLITPOS = new ModulePanePreferences("SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.3});
        }
    });
    public static final ApplicationPreference BOTTOM_SPLITPOS = new ModulePanePreferences("BOTTOM_SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.8});
        }
    });
    public static final ApplicationPreference SIDE_EXTENSION = new ModulePanePreferences("SIDE_EXTENSION", String.class, null);
    public static final ApplicationPreference BOTTOM_EXTENSION = new ModulePanePreferences("BOTTOM_EXTENSION", String.class, null);

    public static final ApplicationPreference VIEWS = new ModulePanePreferences("VIEWS", ArrayList.class, new ArrayList<>());
    public static final ApplicationPreference CURRENT_VIEW = new ModulePanePreferences("CURRENT_VIEW", Integer.class, -1);

    public <T extends Serializable> ModulePanePreferences(String name, Class<T> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }
}
