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
package de.jpwinkler.libs.doorsbridge.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.DoorsRuntimeException;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import de.jpwinkler.libs.doorsbridge.ModuleRef;

public class ItemRefImpl implements ItemRef {

    private static final Logger LOGGER = Logger.getLogger(ItemRefImpl.class.getName());

    private final DoorsApplicationImpl doorsApplicationImpl;
    private final DoorsItemType type;
    private final String path;
    private final String name;

    public ItemRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final String path, final String name, final DoorsItemType type) {
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.path = path;
        this.name = name;
        this.type = type;
    }

    @Override
    public DoorsItemType getType() {
        return type;
    }

    @Override
    public List<ItemRef> getChildren() throws DoorsException {
        if (doorsApplicationImpl.isBatchMode()) {
            throw new UnsupportedOperationException("Operation not supported in batch mode.");
        }
        final String resultString = doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("get_children.dxl"));
            builder.setVariable("folder", getFullName());
        });

        final List<ItemRef> result = new ArrayList<>();

        if (resultString.trim().isEmpty()) {
            //no children available
            return result;
        }

        final String[] lines = resultString.split("\r\n");

        for (final String line : lines) {
            String[] split;
            if (line == null || line.isEmpty() || (split = line.split(":")).length != 2) {
                LOGGER.severe(String.format("Invalid result format: %s.", line));
                continue;
            }
            DoorsItemType type;
            switch (split[0]) {
            case "Folder":
                type = DoorsItemType.FOLDER;
                break;
            case "Formal":
                type = DoorsItemType.FORMAL;
                break;
            case "Link":
                type = DoorsItemType.LINK;
                break;
            case "Project":
                type = DoorsItemType.PROJECT;
                break;
            default:
                LOGGER.severe(String.format("Unknown DOORS item type: %s.", split[0]));
                type = DoorsItemType.UNKNOWN;
            }
            result.add(new ItemRefImpl(doorsApplicationImpl, getFullName(), split[1], type));
        }

        return result;
    }

    @Override
    public ModuleRef open() throws DoorsException {
        if (type == DoorsItemType.FORMAL) {
            return doorsApplicationImpl.openModule(getFullName());
        } else {
            throw new DoorsRuntimeException("Not a formal model: " + getFullName());
        }
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        if (getPath().endsWith("/")) {
            return getPath() + getName();
        } else {
            return getPath() + "/" + getName();
        }

    }
}

