/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DXLScript;
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;
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
        if (this.children.get() == null) {
            synchronized (this.children) {
                if (this.children.get() != null) {
                    return this.children.get();
                }

                this.children.compareAndSet(null, executor.runBackgroundTask("Load node children", i -> {
                    final String resultString = doorsApplication.runScript(builder -> {
                        builder.addScript(DXLScript.fromResource("get_children.dxl"));
                        builder.setVariable("folder", this.getFullName());
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
                }));
            }
        }

        return children.get();
    }
}
