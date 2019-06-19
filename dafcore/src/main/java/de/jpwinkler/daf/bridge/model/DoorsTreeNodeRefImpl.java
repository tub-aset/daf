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

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

abstract class DoorsTreeNodeRefImpl implements DoorsTreeNode {

    protected final DoorsApplication doorsApplication;

    private DoorsTreeNode parent;

    private final List<String> pathSegments;
    private final DoorsItemType type;

    public DoorsTreeNodeRefImpl(final DoorsApplication DoorsApplication, final DoorsItemType type, final DoorsTreeNode parent, String name) {
        this.doorsApplication = DoorsApplication;
        this.type = type;
        // make sure root does not show up in the path
        this.pathSegments = parent == null ? Collections.emptyList() : Stream.concat(parent.getFullNameSegments().stream(), Stream.of(name)).collect(Collectors.toList());
        this.parent = parent;
    }

    @Override
    public List<DoorsTreeNode> getChildren() {
        try {
            return getChildrenAsync(BackgroundTaskExecutor.SYNCHRONOUS).get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public DoorsTreeNode getParent() {
        return parent;
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
        this.parent = value;
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
    public CompletableFuture<Void> acceptAsync(BackgroundTaskExecutor executor, DoorsTreeNodeVisitor visitor) {
        return executor.runBackgroundTask("Visiting nodes", i -> {
            this.accept(visitor);
            return null;
        });
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
    public synchronized DoorsTreeNode getChild(String name) {
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
    public CompletableFuture<DoorsTreeNode> getChildAsync(BackgroundTaskExecutor executor, String name) {
        return executor.runBackgroundTask("Searching for child", i -> this.getChild(name));
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
