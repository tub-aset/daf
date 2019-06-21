package de.jpwinkler.daf.bridge;

/*-
 * #%L
 * dafcore
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

import java.util.logging.Logger;

public enum DoorsItemType {

    PROJECT,
    FOLDER,
    FORMAL,
    LINK,
    DESCRIPTIVE,
    OBJECT,
    UNKNOWN;

    private static final Logger LOGGER = Logger.getLogger(DoorsItemType.class.getName());
    public static DoorsItemType getType(final String type) {
        switch (type) {
            case "Folder":
                return DoorsItemType.FOLDER;
            case "Formal":
                return DoorsItemType.FORMAL;
            case "Link":
                return DoorsItemType.LINK;
            case "Project":
                return DoorsItemType.PROJECT;
            case "Descriptive":
                return DoorsItemType.DESCRIPTIVE;
            default:
                LOGGER.severe(String.format("Unknown DOORS item type: %s.", type));
                return DoorsItemType.UNKNOWN;
        }
    }

}
