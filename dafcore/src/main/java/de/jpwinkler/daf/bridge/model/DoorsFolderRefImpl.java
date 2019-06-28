package de.jpwinkler.daf.bridge.model;

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
import de.jpwinkler.daf.bridge.DXLScript;
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fwiesweg
 */
class DoorsFolderRefImpl extends DoorsTreeNodeRefImpl implements DoorsFolder {

    public DoorsFolderRefImpl(DoorsApplication DoorsApplication, DoorsItemType type, DoorsTreeNode parent, String name) {
        super(DoorsApplication, type, parent, name);
    }

    private static final Logger LOGGER = Logger.getLogger(DoorsTreeNodeRefImpl.class.getName());

    private final AtomicReference<CompletableFuture<List<DoorsTreeNode>>> children = new AtomicReference<>();

    @Override
    public CompletableFuture<List<DoorsTreeNode>> getChildrenAsync(BackgroundTaskExecutor executor) {
        return executor.runBackgroundTask("Load node children", this.children, i -> {
            final String resultString = doorsApplication.runScript(builder -> {
                builder.addScript(DXLScript.fromResource("get_children.dxl"));
                builder.setVariable("folder", this.getDoorsPath());
            });

            final List<DoorsTreeNode> result = new ArrayList<>();

            if (resultString.trim().isEmpty()) {
                // no children available
                return result;
            }

            final String[] lines = resultString.split("\r\n");

            for (final String line : lines) {
                String[] split;
                if (line == null || line.isEmpty() || (split = line.split(":")).length != 2) {
                    LOGGER.severe(String.format("Invalid result format: %s.", line));
                    continue;
                }
                final DoorsItemType itemType = DoorsItemType.getType(split[0]);
                switch (itemType) {
                    case FORMAL:
                        result.add(new DoorsModuleRefImpl(doorsApplication, this, split[1]));
                        break;
                    case FOLDER:
                    case PROJECT:
                        result.add(new DoorsFolderRefImpl(doorsApplication, itemType, this, split[1]));
                        break;
                    case LINK:
                    case DESCRIPTIVE:
                        LOGGER.log(Level.WARNING, "Item type not supported: {0}", itemType);
                        break;
                    default:
                        throw new UnsupportedOperationException("unknown type: " + split[0]);
                }
            }
            return result;
        });
    }

    @Override
    public boolean isChildrenLoaded() {
        CompletableFuture<List<DoorsTreeNode>> childrenFuture = children.get();
        return childrenFuture != null && childrenFuture.isDone();
    }

    @Override
    public CompletableFuture<Map<String, String>> getAttributesAsync(BackgroundTaskExecutor executor) {
        return CompletableFuture.completedFuture(this.getAttributes());
    }
}
