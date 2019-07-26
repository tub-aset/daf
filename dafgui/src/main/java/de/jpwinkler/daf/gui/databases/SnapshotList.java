/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases;

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
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 *
 * @author fwiesweg
 */
public class SnapshotList implements Serializable {

    private final TreeSet<String> list = new TreeSet<>();
    private SnapshotListNode root;

    public NavigableSet<String> getList() {
        return Collections.unmodifiableNavigableSet(list);
    }

    public void add(String path) {
        list.add(path);
        this.root = null;
    }

    public void remove(String path) {
        list.remove(path);
        this.root = null;
    }

    public void replace(Collection<String> list) {
        this.list.clear();
        this.list.addAll(list);
        this.root = null;
    }

    private SnapshotListNode parseList() {
        if (root != null) {
            return root;
        }
        this.root = new SnapshotListNode("", false);
        for (String path : list) {
            this.assertIncluded(path);
        }
        this.root = root;
        return root;
    }

    private void assertIncluded(String pathStr) {
        // skip the first slash
        if (pathStr.length() <= 1) {
            return;
        }
        String[] path = pathStr.substring(1).split("/");
        SnapshotListNode current = root;
        for (String el : path) {
            if (!current.children.containsKey(el)) {
                current.children.put(el, new SnapshotListNode(el, false));
            }
            current = current.children.get(el);
            // abort if all children are included anyway
            if (current.includeChildren) {
                break;
            }
        }
        current.includeChildren = true;
    }

    public boolean includes(String path) {
        return this.includes(List.of(path.split("/")));
    }

    public boolean includes(List<String> path) {
        SnapshotListNode current = parseList();
        for (String el : path) {
            if (current.includeChildren) {
                return true;
            }
            current = current.children.get(el);
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    private static class SnapshotListNode {

        public SnapshotListNode(String name, boolean includeChildren) {
            this.includeChildren = includeChildren;
        }
        private boolean includeChildren;
        private final HashMap<String, SnapshotListNode> children = new HashMap<>();
    }

}
