/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.csveditor;

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
import java.util.TreeMap;
import java.util.prefs.Preferences;

/**
 *
 * @author fwiesweg
 */
public enum ApplicationPreferences {
    RECENT_FILES(TreeMap.class, new TreeMap<>()),
    SAVE_DIRECTORY(File.class, new File(System.getProperty("user.home")).getAbsoluteFile()),
    OPEN_DIRECTORY(File.class, new File(System.getProperty("user.home")).getAbsoluteFile()),
    FILE_PANE_SPLITPOS(Double.class, 0.3),
    FILE_PANE_VIEWS(ArrayList.class, new ArrayList<>()),
    FILE_PANE_CURRENT_VIEW(Integer.class, -1);

    <T extends Serializable> ApplicationPreferences(Class<T> valueType, T defaultValue) {
        this.valueType = valueType;
        this.defaultValue = defaultValue;
    }
    private final Class<?> valueType;
    private final Object defaultValue;
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

}
