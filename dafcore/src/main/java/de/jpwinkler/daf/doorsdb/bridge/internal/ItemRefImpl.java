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
package de.jpwinkler.daf.doorsdb.bridge.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.jpwinkler.daf.doorsdb.bridge.DoorsException;
import de.jpwinkler.daf.doorsdb.bridge.DoorsItemType;
import de.jpwinkler.daf.doorsdb.bridge.DoorsRuntimeException;
import de.jpwinkler.daf.doorsdb.bridge.ItemName;
import de.jpwinkler.daf.doorsdb.bridge.ItemRef;
import de.jpwinkler.daf.doorsdb.bridge.ModuleRef;
import de.jpwinkler.daf.doorsdb.bridge.DoorsItemTypeUtil;

public class ItemRefImpl implements ItemRef {

    private static final Logger LOGGER = Logger.getLogger(ItemRefImpl.class.getName());

    private final DoorsApplicationImpl doorsApplicationImpl;
    private DoorsItemType type;
    private final ItemName name;

    public ItemRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final String fullName, final DoorsItemType type) {
        this(doorsApplicationImpl, new ItemName(fullName), type);
    }

    private ItemRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final ItemName name, final DoorsItemType type) {
        super();
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.name = name;
        this.type = type;
    }

    @Override
    public DoorsItemType getType() throws DoorsException {
        if (type == null) {
            final String typeStr = doorsApplicationImpl.buildAndRunCommand(builder -> {
                builder.addScript(new InternalDXLScript("get_type.dxl"));
                builder.setVariable("item", name.getFullName());
            });
            type = DoorsItemTypeUtil.getType(typeStr);
        }
        return type;
    }

    @Override
    public ItemRef getParent() {
        if (name.getParent() == null) {
            return null;
        } else {
            return new ItemRefImpl(doorsApplicationImpl, name.getParent(), null);
        }
    }

    @Override
    public List<ItemRef> getChildren() throws DoorsException {
        if (doorsApplicationImpl.isBatchMode()) {
            throw new UnsupportedOperationException("Operation not supported in batch mode.");
        }
        final String resultString = doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("get_children.dxl"));
            builder.setVariable("folder", name.getFullName());
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
            final DoorsItemType type = DoorsItemTypeUtil.getType(split[0]);
            result.add(new ItemRefImpl(doorsApplicationImpl, new ItemName(name, split[1]), type));
        }
        return result;
    }

    @Override
    public ModuleRef open() throws DoorsException {
        if (getType() == DoorsItemType.FORMAL) {
            return doorsApplicationImpl.openModule(name.getFullName());
        } else {
            throw new DoorsRuntimeException("Not a formal model: " + name.getFullName());
        }
    }

    @Override
    public boolean exists() {
        try {
            final String result = doorsApplicationImpl.buildAndRunCommand(builder -> {
                builder.addScript(new InternalDXLScript("exists.dxl"));
                builder.setVariable("item", name.getFullName());
            });
            return result.equals("true");
        } catch (final DoorsException e) {
            // Should never throw this exception
            throw new DoorsRuntimeException();
        }
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public ItemName getItemName() {
        return name;
    }
}

