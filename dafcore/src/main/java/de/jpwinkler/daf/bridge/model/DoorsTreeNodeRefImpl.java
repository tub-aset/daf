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
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DXLScript;
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

abstract class DoorsTreeNodeRefImpl implements DoorsTreeNode {

    private static final Logger LOGGER = Logger.getLogger(DoorsTreeNodeRefImpl.class.getName());

    protected final DoorsApplication doorsApplication;

    private DoorsItemType type;
    private final DoorsTreeNode parent;

    private final List<String> pathSegments;
    private List<DoorsTreeNode> children;

    public DoorsTreeNodeRefImpl(final DoorsApplication doorsApplicationImpl, final DoorsItemType type, final DoorsTreeNode parent, String name) {
        this.doorsApplication = doorsApplicationImpl;
        this.type = type;
        // make sure root does not show up in the path
        this.pathSegments = parent == null ? Collections.emptyList() : Stream.concat(
                parent.getFullNameSegments().stream(), Stream.of(name)).collect(Collectors.toList());
        this.parent = parent;
    }

    @Override
    public DoorsTreeNode getParent() {
        return parent;
    }

    @Override
    public synchronized List<DoorsTreeNode> getChildren() {
        if (this.children == null) {

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
                final DoorsItemType type = DoorsItemType.getType(split[0]);
                switch (type) {
                    case FORMAL:
                        result.add(new DoorsModuleRefImpl(doorsApplication, this, split[1]));
                        break;
                    case FOLDER:
                    case PROJECT:
                        result.add(new DoorsFolderRefImpl(doorsApplication, type, this, split[1]));
                        break;
                    case LINK:
                    case DESCRIPTIVE:
                        LOGGER.warning("Item type not supported: " + type);
                        break;
                    default:
                        throw new UnsupportedOperationException("unknown type: " + split[0]);
                }
            }
            this.children = result;
        }

        return children;
    }

    @Override
    public Future<List<DoorsTreeNode>> getChildrenAsync() {
        return doorsApplication.getBackgroundTaskExecutor().runBackgroundTask("Load node children", i -> this.getChildren());
    }

    @Override
    public String getName() {
        return pathSegments.size() > 0 ? pathSegments.get(pathSegments.size() - 1) : "/";
    }

    @Override
    public String getFullName() {
        return "/" + StringUtils.join(pathSegments, "/");
    }

    @Override
    public void setParent(DoorsTreeNode value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Map<String, String> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public void setName(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<String> getFullNameSegments() {
        return pathSegments;
    }

    @Override
    public void accept(DoorsTreeNodeVisitor visitor) {
        visitor.traverse(this);
    }

    @Override
    public boolean hasTag(String tag) {
        return false;
    }

    @Override
    public boolean hasTag(Pattern pattern) {
        return false;
    }

    @Override
    public boolean canCopyFrom(DoorsTreeNode node) {
        return false;
    }

    @Override
    public DoorsTreeNode getChild(String name) {
        name = name.replaceFirst("^/", "");
        final String[] currentSegment = name.split("/", 2);
        for (DoorsTreeNode child : this.getChildren()) {
            if (currentSegment[0].equals(child.getName())) {
                return currentSegment.length == 2 ? child.getChild(currentSegment[1]) : child;
            }

        }

        return null;
    }

    @Override
    public void setTag(String tag) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void removeTag(String tag) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void removeTag(Pattern pattern) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<String> getTags() {
        return Collections.emptyList();
    }

}
