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

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
