/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 *
 * @author fwiesweg
 */
public enum ApplicationPart {
    DOORS_BRIDGE("doorsdb", null, DatabasePaneController::openDoors, defaultSelector("/"), directorySelector(true)),
    LOCAL_DATABASE("localdb", "New local database", DatabasePaneController::openLocal, directorySelector(false), directorySelector(true)),
    LOCAL_MODULE("localmodule", "New local module", ModulePaneController::open, fileChooserSelector(false, new ExtensionFilter("CSV", "*.csv")), fileChooserSelector(true, new ExtensionFilter("CSV", "*.csv"))),
    DATABASE_MODULE("databasemodule", null, ModulePaneController::openInDB, null, null);

    private final String unnamedName;
    private final String scheme;
    private final BiFunction<ApplicationPaneController, ApplicationURI, ApplicationPartController> opener;
    private final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> openSelector;
    private final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> saveSelector;

    private ApplicationPart(String scheme, String unnamedName, BiFunction<ApplicationPaneController, ApplicationURI, ApplicationPartController> opener,
            BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> openSelector, BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> saveSelector) {
        this.scheme = scheme;
        this.unnamedName = unnamedName;
        this.opener = opener;
        this.openSelector = openSelector;
        this.saveSelector = saveSelector;
    }

    public Stream<ApplicationURI> openWithSelector(ApplicationPaneController parent, Window window) {
        return openSelector.apply(window, this);
    }

    public Stream<ApplicationURI> saveWithSelector(ApplicationPaneController parent, Window window) {
        return saveSelector.apply(window, this);
    }

    public ApplicationPartController open(ApplicationPaneController parent, ApplicationURI uri) {
        ApplicationPartController controller = opener.apply(parent, uri);
        controller.setURI(uri);
        return controller;
    }

    public final String getScheme() {
        return scheme;
    }

    public static final ApplicationPartController openAny(ApplicationPaneController parent, ApplicationURI uri) {
        return uri.getApplicationPart().open(parent, uri);
    }

    public static final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> defaultSelector(String path) {
        return (window, part) -> Stream.of(part.newURI(path));
    }

    public static final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> fileChooserSelector(boolean save, FileChooser.ExtensionFilter... extensionFilters) {
        return (window, part) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle((save ? "Save a " : "Open a ") + part.toString());
            fileChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());
            fileChooser.getExtensionFilters().addAll(extensionFilters);

            return Stream.of(save ? fileChooser.showSaveDialog(window) : fileChooser.showOpenDialog(window))
                    .filter(f -> f != null)
                    .peek(f -> {
                        if (save) {
                            ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        } else {
                            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        }
                    })
                    .map(f -> part.newURI(f.getAbsolutePath()));
        };
    }

    public static final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> directorySelector(boolean save) {
        return (window, part) -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle((save ? "Save a " : "Open a ") + part.toString());
            dirChooser.setInitialDirectory(save ? ApplicationPreferences.SAVE_DIRECTORY.retrieve() : ApplicationPreferences.OPEN_DIRECTORY.retrieve());

            return Stream.of(dirChooser.showDialog(window))
                    .filter(f -> f != null)
                    .peek(f -> {
                        if (save) {
                            ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        } else {
                            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
                        }
                    })
                    .map(f -> part.newURI(f.getAbsolutePath()));
        };
    }

    public static final BiFunction<Window, ApplicationPart, Stream<ApplicationURI>> genericSelector(boolean save) {
        return (window, part) -> {
            TextInputDialog dialog = new TextInputDialog(part.getScheme() + "://");
            dialog.setTitle((save ? "Save a " : "Open a ") + part.toString());
            dialog.setHeaderText("Please enter a URI to " + (save ? "save." : "open."));
            dialog.setContentText("URI");

            return dialog.showAndWait().stream()
                    .map(part::newURI);
        };
    }

    public ApplicationURI newURI() {
        return this.newURI(null);
    }

    public ApplicationURI newURI(String path) {
        return new ApplicationURI(this, path);
    }

    public String getUnnamedName() {
        return unnamedName;
    }
}
