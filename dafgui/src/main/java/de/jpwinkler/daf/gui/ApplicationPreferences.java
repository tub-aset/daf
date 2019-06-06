/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabasePath;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.prefs.Preferences;

/**
 *
 * @author fwiesweg
 */
public enum ApplicationPreferences {
    RECENT_FILES(TreeMap.class, new TreeMap<Long, DatabasePath>()),
    SAVE_DIRECTORY(File.class, new File(System.getProperty("user.home")).getAbsoluteFile()),
    OPEN_DIRECTORY(File.class, new File(System.getProperty("user.home")).getAbsoluteFile()),
    PLUGIN_DIRECTORY(File.class, new File(System.getProperty("user.home")).getAbsoluteFile()),
    MODULE_PANE_SPLITPOS(Double.class, 0.3),
    MODULE_PANE_VIEWS(ArrayList.class, new ArrayList<>()),
    MODULE_PANE_CURRENT_VIEW(Integer.class, -1),
    DATABASE_PANE_SPLITPOS(double[].class, new double[]{0.25, 0.75}),
    DATABASE_BOTTOMPANE_SPLITPOS(double[].class, new double[]{0.8}),
    DATABASE_PANE_SIDE_EXTENSION(String.class, null),
    DATABASE_PANE_ATTRIBUTENAME_WIDTH(Double.class, 100d),
    DATABASE_PANE_ATTRIBUTEVALUE_WIDTH(Double.class, 300d),
    DATABASE_PANE_SNAPSHOT_LISTS(TreeMap.class, new TreeMap<String, TreeSet<String>>());

    <T extends Serializable> ApplicationPreferences(Class<T> valueType, T defaultValue) {
        this.valueType = valueType;
        this.defaultValue = defaultValue;
    }

    private final Class<?> valueType;
    private final Object defaultValue;
    private final HashSet<Consumer<Object>> onChangedHandlers = new HashSet<>();
    private static final Preferences prefs = Preferences.userNodeForPackage(ApplicationPreferences.class);

    public <T extends Serializable> void store(T object) {
        if (object != null && !valueType.isAssignableFrom(object.getClass())) {
            throw new IllegalArgumentException("Object must be null or a " + valueType.getCanonicalName());
        }
        try (final java.io.ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            java.io.ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            prefs.put(this.name(), Base64.getEncoder().encodeToString(bos.toByteArray()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.onChangedHandlers.forEach(h -> h.accept(object));
    }

    public <T extends Serializable> T retrieve() {
        String data = prefs.get(this.name(), null);
        if (data == null) {
            return (T) defaultValue;
        }
        try (final java.io.ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(data))) {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) valueType.cast(ois.readObject());
        } catch (ObjectStreamException | ClassNotFoundException | ClassCastException ex) {
            prefs.remove(this.name());
            return (T) defaultValue;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addOnChangedHandler(Consumer<Object> handler) {
        this.onChangedHandlers.add(handler);
    }

    public void removeOnChangedHandler(Consumer<Object> handler) {
        this.onChangedHandlers.remove(handler);
    }

}
