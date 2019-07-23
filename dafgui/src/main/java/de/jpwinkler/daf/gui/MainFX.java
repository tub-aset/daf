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
import de.jpwinkler.daf.gui.ApplicationPreferences.SerializableRectangle2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainFX extends Application {

    private Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        final ApplicationPaneController applicationPaneController = new ApplicationPaneController(
                title -> primaryStage.setTitle("DOORS Access Framework" + (title == null ? "" : " – " + title)));
        final Scene applicationScene = new Scene(applicationPaneController.getNode());
        
        primaryStage.getIcons().add(ApplicationIcons.DOOR.toImage());
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            if (!applicationPaneController.tryClose()) {
                event.consume();
            }
        });
        
        primaryStage.setMinHeight(((Region)applicationPaneController.getNode()).getMinHeight());
        primaryStage.setMinWidth(((Region)applicationPaneController.getNode()).getMinWidth());

        SerializableRectangle2D storedRectangle = ApplicationPreferences.WINDOW_RECTANGLE.retrieve();
        if (storedRectangle != null) {
            primaryStage.setX(storedRectangle.getX());
            primaryStage.setY(storedRectangle.getY());
            primaryStage.setWidth(storedRectangle.getWidth());
            primaryStage.setHeight(storedRectangle.getHeight());
        }

        primaryStage.setMaximized(ApplicationPreferences.WINDOW_MAXIMIZED.retrieve());

        primaryStage.setScene(applicationScene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ApplicationPreferences.WINDOW_MAXIMIZED.store(primaryStage.isMaximized());
        ApplicationPreferences.WINDOW_RECTANGLE.store(new ApplicationPreferences.SerializableRectangle2D(
                primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight()));
    }

}
