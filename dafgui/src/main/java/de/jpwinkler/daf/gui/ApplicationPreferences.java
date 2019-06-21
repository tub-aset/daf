package de.jpwinkler.daf.gui;

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

import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author fwiesweg
 */
class ApplicationPreferences extends ApplicationPreference {

    public static final ApplicationPreference EXIT_FILES = new ApplicationPreferences("EXIT_FILES", ArrayList.class, new ArrayList<ApplicationPart>(0));
    public static final ApplicationPreference RECENT_FILES = new ApplicationPreferences("RECENT_FILES", TreeMap.class, new TreeMap<Long, ApplicationPart>());
    public static final ApplicationPreference SAVE_DIRECTORY = new ApplicationPreferences("SAVE_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());
    public static final ApplicationPreference OPEN_DIRECTORY = new ApplicationPreferences("OPEN_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());
    public static final ApplicationPreference PLUGIN_DIRECTORY = new ApplicationPreferences("PLUGIN_DIRECTORY", File.class, new File(System.getProperty("user.home")).getAbsoluteFile());
    public static final ApplicationPreference WINDOW_MAXIMIZED = new ApplicationPreferences("WINDOW_MAXIMIZED", Boolean.class, false);
    public static final ApplicationPreference WINDOW_RECTANGLE = new ApplicationPreferences("WINDOW_RECTANGLE", SerializableRectangle2D.class, null);

    public <T extends Serializable> ApplicationPreferences(String name, Class<T> valueType, Object defaultValue) {
        super(name, valueType, defaultValue);
    }

    public static final class SerializableRectangle2D implements Serializable {

        private final double x;
        private final double y;
        private final double width;
        private final double height;

        public SerializableRectangle2D(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        public Rectangle2D toRectangle2D() {
            return new Rectangle2D(x, y, width, height);
        }

    }
}
