/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.modules;

import de.jpwinkler.daf.gui.ApplicationPreference;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fwiesweg
 */
class ModulePanePreferences extends ApplicationPreference {

    public static final ApplicationPreference SPLITPOS = new ModulePanePreferences("SPLITPOS", Double.class, 0.3);
    public static final ApplicationPreference VIEWS = new ModulePanePreferences("VIEWS", ArrayList.class, new ArrayList<>());
    public static final ApplicationPreference CURRENT_VIEW = new ModulePanePreferences("CURRENT_VIEW", Integer.class, -1);

    public <T extends Serializable> ModulePanePreferences(String name, Class<T> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }
}
