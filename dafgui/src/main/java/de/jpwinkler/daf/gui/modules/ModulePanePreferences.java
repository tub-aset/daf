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
class ModulePanePreferences<T extends Serializable> extends ApplicationPreference<T> {

    public static final ApplicationPreference<HashMap<Integer, double[]>> SPLITPOS = new ModulePanePreferences<>("SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.3});
        }
    });
    public static final ApplicationPreference<HashMap<Integer, double[]>> BOTTOM_SPLITPOS = new ModulePanePreferences<>("BOTTOM_SPLITPOS", HashMap.class, new HashMap<Integer, double[]>() {
        {
            put(0, new double[]{});
            put(1, new double[]{0.8});
        }
    });
    public static final ApplicationPreference<String> SIDE_EXTENSION = new ModulePanePreferences<>("SIDE_EXTENSION", String.class, null);
    public static final ApplicationPreference<String> BOTTOM_EXTENSION = new ModulePanePreferences<>("BOTTOM_EXTENSION", String.class, null);

    public static final ApplicationPreference<ArrayList<ViewDefinition>> VIEWS = new ModulePanePreferences<>("VIEWS", ArrayList.class, new ArrayList<>());
    public static final ApplicationPreference<Integer> CURRENT_VIEW = new ModulePanePreferences<>("CURRENT_VIEW", Integer.class, -1);

    public static final ApplicationPreference<ViewDefinition> STANDARD_VIEW = new ModulePanePreferences<>("STANDARD_VIEW", ViewDefinition.class, generateDefaultViewDefinition());
    private static ViewDefinition generateDefaultViewDefinition() {
        ViewDefinition viewDef = new ViewDefinition("Standard");
        ViewDefinition.ColumnDefinition cd;

        cd = new ViewDefinition.ColumnDefinition(ViewDefinition.ColumnType.ATTRIBUTE, "Absolute Number");
        cd.setAttributeName("Absolute Number");
        cd.setWidth(100);
        cd.setVisible(true);
        viewDef.getColumns().add(cd);
        
        cd = new ViewDefinition.ColumnDefinition(ViewDefinition.ColumnType.ATTRIBUTE, "Object Identifier");
        cd.setAttributeName("Object Identifier");
        cd.setWidth(100);
        cd.setVisible(true);
        viewDef.getColumns().add(cd);

        cd = new ViewDefinition.ColumnDefinition(ViewDefinition.ColumnType.COMBINED_TEXT_HEADING, "Object Heading/Text");
        cd.setWidth(700);
        cd.setVisible(true);
        viewDef.getColumns().add(cd);
        
        cd = new ViewDefinition.ColumnDefinition(ViewDefinition.ColumnType.ATTRIBUTE, "Object Type");
        cd.setAttributeName("Object Type");
        cd.setWidth(200);
        cd.setVisible(true);
        viewDef.getColumns().add(cd);

        cd = new ViewDefinition.ColumnDefinition(ViewDefinition.ColumnType.LINKS, "Links (right-click to navigate)");
        cd.setWidth(200);
        cd.setVisible(true);
        viewDef.getColumns().add(cd);

        viewDef.setDisplayRemainingColumns(false);
        return viewDef;
    }

    public ModulePanePreferences(String name, Class<? super T> valueType, T defaultValue) {
        super(name, valueType, defaultValue);
    }
}
