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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum ApplicationIcons {
    DATABASE("icons/database.svg.png"),
    FOLDER("icons/folder.svg.png"),
    FORMAL("icons/files.svg.png"),
    LINK("icons/link.svg.png"),
    PROJECT("icons/folder-zip.svg.png"),
    OBJECT("icons/file.svg.png"),
    LOADING("icons/refresh.svg.png"),
    ERROR("icons/close-rectangle.svg.png");

    private final Image image;

    private ApplicationIcons(String resourceName) {
        this.image = new Image(MainFX.class.getResource(resourceName).toString(), 20, 20, true, true);
        if (this.image.getException() != null) {
            throw new RuntimeException(this.image.getException());
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
        } else if (value instanceof DoorsFolder && ((DoorsFolder) value).isProject()) {
            return ApplicationIcons.PROJECT;
        } else if (value instanceof DoorsFolder && !((DoorsFolder) value).isProject()) {
            return ApplicationIcons.FOLDER;
        } else if (value instanceof DoorsObject) {
            return ApplicationIcons.OBJECT;
        } else {
            throw new AssertionError();
        }
    }

}
