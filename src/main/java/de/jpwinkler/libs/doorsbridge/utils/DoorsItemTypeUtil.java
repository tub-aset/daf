package de.jpwinkler.libs.doorsbridge.utils;

import java.util.logging.Logger;

import de.jpwinkler.libs.doorsbridge.DoorsItemType;

public class DoorsItemTypeUtil {

    private static final Logger LOGGER = Logger.getLogger(DoorsItemTypeUtil.class.getName());

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
        default:
            LOGGER.severe(String.format("Unknown DOORS item type: %s.", type));
            return DoorsItemType.UNKNOWN;
        }
    }

}
