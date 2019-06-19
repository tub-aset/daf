/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

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
