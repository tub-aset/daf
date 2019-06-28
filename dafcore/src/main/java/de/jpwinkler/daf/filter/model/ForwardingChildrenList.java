/*
 * DoorsTreeNodeo change this license header, choose License Headers in Project Properties.
 * DoorsTreeNodeo change this template file, choose DoorsTreeNodeools | DoorsTreeNodeemplates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.filter.model;

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

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * @author fwiesweg
 */
public class ForwardingChildrenList implements List<DoorsTreeNode> {

    private final Class<DoorsTreeNode> clz;
    private final Supplier<List<DoorsTreeNode>> listSupplier;
    private final Predicate<DoorsTreeNode> predicate;

    private final WeakHashMap<DoorsTreeNode, DoorsTreeNodeImpl<?>> childrenMap = new WeakHashMap<>();

    public ForwardingChildrenList(Class<DoorsTreeNode> clz, Supplier<List<DoorsTreeNode>> listSupplier, Predicate<DoorsTreeNode> predicate) {
        this.clz = clz;
        this.listSupplier = listSupplier;
        this.predicate = predicate;
    }

    public Predicate<DoorsTreeNode> getPredicate() {
        return predicate;
    }

    private DoorsTreeNode wrap(DoorsTreeNode node) {
        if (childrenMap.containsKey(node)) {
            return node;
        } else if (node instanceof DoorsFolder) {
            childrenMap.putIfAbsent(node, new DoorsFolderImpl((DoorsFolder) node, predicate));
        } else if (node instanceof DoorsModule) {
            childrenMap.putIfAbsent(node, new DoorsModuleImpl((DoorsModule) node, predicate));
        } else if (node instanceof DoorsObject) {
            childrenMap.putIfAbsent(node, new DoorsObjectImpl((DoorsObject) node, predicate));
        } else {
            throw new AssertionError();
        }

        return childrenMap.get(node);
    }

    private <T> T unwrap(T node) {
        if (node instanceof DoorsTreeNodeImpl) {
            return (T) ((DoorsTreeNodeImpl) node).getSelf();
        } else {
            return node;
        }
    }

    private boolean classMatch(Object o) {
        return o == null || clz.isAssignableFrom(o.getClass());
    }

    @Override
    public int size() {
        return (int) listSupplier.get().stream().filter(predicate).count();
    }

    @Override
    public boolean isEmpty() {
        return !listSupplier.get().stream().filter(predicate).findFirst().isPresent();
    }

    @Override
    public boolean contains(Object o) {
        Object ou = unwrap(o);
        return listSupplier.get().stream().filter(predicate).filter(e -> ou == null ? e == null : ou.equals(e)).findAny().isPresent();
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        return clctn.stream().allMatch(this::contains);
    }

    @Override
    public Iterator<DoorsTreeNode> iterator() {
        return listSupplier.get().stream().filter(predicate).map(this::wrap).iterator();
    }

    @Override
    public Object[] toArray() {
        return listSupplier.get().stream().filter(predicate).map(this::wrap).toArray();
    }

    @Override
    public <DoorsTreeNode> DoorsTreeNode[] toArray(DoorsTreeNode[] ts) {
        return listSupplier.get().stream().filter(predicate).map(this::wrap).toArray(size -> {
            try {
                return size == ts.length ? ts : (DoorsTreeNode[]) ts.getClass().getConstructor(int.class).newInstance(size);
            } catch (SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public boolean add(DoorsTreeNode e) {
        return listSupplier.get().add(unwrap(e));
    }

    @Override
    public boolean remove(Object o) {
        Object ou = unwrap(o);
        if (!classMatch(ou) || !this.predicate.test((DoorsTreeNode) ou)) {
            return false;
        }

        return listSupplier.get().remove(ou);
    }

    @Override
    public boolean addAll(Collection<? extends DoorsTreeNode> clctn) {
        return listSupplier.get().addAll(clctn.stream().map(this::unwrap).collect(Collectors.toList()));
    }

    @Override
    public boolean addAll(int i, Collection<? extends DoorsTreeNode> clctn) {
        return listSupplier.get().addAll(i, clctn.stream().map(this::unwrap).collect(Collectors.toList()));
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return listSupplier.get().removeAll(clctn.stream()
                .filter(o -> classMatch(o) && this.predicate.test((DoorsTreeNode) o))
                .map(this::unwrap)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        List<DoorsTreeNode> fullList = listSupplier.get();
        return fullList.stream()
                .filter(predicate)
                .filter(t -> !clctn.contains(t) && !clctn.contains(wrap(t)))
                .map(fullList::remove)
                .anyMatch(p -> p);
    }

    @Override
    public void clear() {
        listSupplier.get().clear();
    }

    @Override
    public DoorsTreeNode get(int i) {
        return wrap(listSupplier.get().get(i));
    }

    @Override
    public DoorsTreeNode set(int i, DoorsTreeNode e) {
        return listSupplier.get().set(i, wrap(e));
    }

    @Override
    public void add(int i, DoorsTreeNode e) {
        listSupplier.get().add(i, wrap(e));
    }

    @Override
    public DoorsTreeNode remove(int i) {
        return wrap(listSupplier.get().remove(i));
    }

    @Override
    public int indexOf(Object o) {
        return listSupplier.get().stream()
                .filter(predicate)
                .collect(Collectors.toList())
                .indexOf(unwrap(o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return listSupplier.get().stream()
                .filter(predicate)
                .collect(Collectors.toList())
                .lastIndexOf(unwrap(o));
    }

    @Override
    public ListIterator<DoorsTreeNode> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<DoorsTreeNode> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<DoorsTreeNode> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported");
    }

}
