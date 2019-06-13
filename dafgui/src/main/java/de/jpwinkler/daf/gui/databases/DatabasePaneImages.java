/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.gui.MainFX;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author fwiesweg
 */
public enum DatabasePaneImages {

    IMAGE_FOLDER(MainFX.class.getResourceAsStream("icons/doors_folder.png")),
    IMAGE_DB(MainFX.class.getResourceAsStream("icons/doors_db.png")),
    IMAGE_FORMAL(MainFX.class.getResourceAsStream("icons/doors_formal.png")),
    IMAGE_LINK(MainFX.class.getResourceAsStream("icons/doors_link.png")),
    IMAGE_PROJECT(MainFX.class.getResourceAsStream("icons/doors_project.png"));

    private final Image image;

    private DatabasePaneImages(InputStream is) {
        try (InputStream wrappedIs = is) {
            this.image = new Image(wrappedIs);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ImageView toImageView() {
        return new ImageView(this.image);
    }

}
