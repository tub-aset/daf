/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
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
    IMAGE_DB(MainFX.class.getResourceAsStream("icons/database.svg")),
    IMAGE_FOLDER(MainFX.class.getResourceAsStream("icons/folder.svg")),
    IMAGE_FORMAL(MainFX.class.getResourceAsStream("icons/file.svg")),
    IMAGE_LINK(MainFX.class.getResourceAsStream("icons/link.svg")),
    IMAGE_PROJECT(MainFX.class.getResourceAsStream("icons/folder-zip.svg")),
    IMAGE_LOADING(MainFX.class.getResourceAsStream("icons/refresh.svg"));

    private final Image image;

    private DatabasePaneImages(InputStream is) {
        try (InputStream wrappedIs = is) {
            this.image = new Image(wrappedIs, 20, 20, true, true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ImageView toImageView() {
        return new ImageView(this.image);
    }

}
