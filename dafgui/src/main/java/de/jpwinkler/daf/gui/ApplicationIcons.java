/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import de.jpwinkler.daf.gui.MainFX;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author fwiesweg
 */
public enum ApplicationIcons {
    DATABASE("icons/database.svg"),
    FOLDER("icons/folder.svg"),
    FORMAL("icons/files.svg"),
    LINK("icons/link.svg"),
    PROJECT("icons/folder-zip.svg"),
    LOADING("icons/refresh.svg"), 
    OBJECT("icons/file.svg");

    private final Image image;

    private ApplicationIcons(String resourceName) {
        try (InputStream is = MainFX.class.getResourceAsStream(resourceName)) {
            this.image = new Image(is, 20, 20, true, true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ImageView toImageView() {
        return new ImageView(this.image);
    }
    
    public static ApplicationIcons getImage(DoorsTreeNode value) {
        if (value.getParent() == null) {
            return ApplicationIcons.DATABASE;
        } else if (value instanceof DoorsModule) {
            return ApplicationIcons.FORMAL;
        } else if (value instanceof DoorsFolder) {
            return ApplicationIcons.FOLDER;
        } else if(value instanceof DoorsObject) {
            return ApplicationIcons.OBJECT;
        } else {
            throw new AssertionError();
        }
    }

}
