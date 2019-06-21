/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import net.harawata.appdirs.AppDirsFactory;

/**
 *
 * @author fwiesweg
 */
public class ApplicationPreference {

    public static final Path APPLICATION_DATA_PATH = Paths.get(AppDirsFactory.getInstance().getUserConfigDir(Main.class.getPackageName(), null, null)).toAbsolutePath();
    public static final Path APPLICATION_PREFERENCES_PATH = APPLICATION_DATA_PATH.resolve("preferences");
    public static final Path APPLICATION_PLUGINS_PATH = APPLICATION_DATA_PATH.resolve("plugins");

    static {
        try {
            Files.createDirectories(APPLICATION_PREFERENCES_PATH);
            Files.createDirectories(APPLICATION_PLUGINS_PATH);
            
            System.setProperty("java -Djava.util.prefs.userRoot", APPLICATION_PREFERENCES_PATH.toString());
        } catch (IOException ex) {
            Logger.getLogger(ApplicationPreference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected <T extends Serializable> ApplicationPreference(String name, Class<T> valueType, Object defaultValue) {
        this.name = name;
        this.valueType = valueType;
        this.defaultValue = defaultValue;
        this.prefs = Preferences.userNodeForPackage(this.getClass());
    }

    private final String name;
    private final Class<?> valueType;
    private final Object defaultValue;
    private final HashSet<Consumer<Object>> onChangedHandlers = new HashSet<>();
    private final Preferences prefs;

    public final <T extends Serializable> void store(T object) {
        if (object != null && !valueType.isAssignableFrom(object.getClass())) {
            throw new IllegalArgumentException("Object must be null or a " + valueType.getCanonicalName());
        }
        try (final java.io.ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            java.io.ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            prefs.put(this.name, Base64.getEncoder().encodeToString(bos.toByteArray()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.onChangedHandlers.forEach(h -> h.accept(object));
    }

    public final <T extends Serializable> T retrieve() {
        String data = prefs.get(this.name, null);
        if (data == null) {
            return (T) defaultValue;
        }
        try (final java.io.ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(data))) {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) valueType.cast(ois.readObject());
        } catch (ObjectStreamException | ClassNotFoundException | ClassCastException ex) {
            prefs.remove(this.name);
            return (T) defaultValue;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public final void addOnChangedHandler(Consumer<Object> handler) {
        this.onChangedHandlers.add(handler);
    }

    public final void removeOnChangedHandler(Consumer<Object> handler) {
        this.onChangedHandlers.remove(handler);
    }
}
