/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.doorsdb.bridge.utils;

import java.util.logging.Logger;

import de.jpwinkler.daf.doorsdb.bridge.DoorsItemType;

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
		case "Descriptive":
			return DoorsItemType.DESCRIPTIVE;
		default:
			LOGGER.severe(String.format("Unknown DOORS item type: %s.", type));
			return DoorsItemType.UNKNOWN;
		}
	}

}
