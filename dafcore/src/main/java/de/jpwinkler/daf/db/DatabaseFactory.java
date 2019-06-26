package de.jpwinkler.daf.db;

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
import de.jpwinkler.daf.model.UnresolvedLink;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseFactory {

    DoorsFolder createFolder(DoorsTreeNode parent, String name);

    DoorsModule createModule(DoorsTreeNode parent, String name);

    DoorsObject createObject(DoorsTreeNode parent, String objectText);

    UnresolvedLink createLink(DoorsObject source, String targetModule, String targetObject);

    default <T extends DoorsTreeNode> T createCopy(T source, DoorsTreeNode newParent) {
        return (T) createCopy(source, newParent, x -> true);
    }

    default <T extends DoorsTreeNode> T createCopy(T source, DoorsTreeNode newParent, Predicate<DoorsTreeNode> nodeFilter) {
        T copy;
        if (source instanceof DoorsObject) {
            copy = (T) this.createObject(newParent, null);
        } else if (source instanceof DoorsModule) {
            copy = (T) this.createModule(newParent, null);
        } else if (source instanceof DoorsFolder) {
            copy = (T) this.createFolder(newParent, null);
        } else {
            throw new AssertionError();
        }

        return this.copy(source, copy, nodeFilter);
    }

    default <T extends DoorsTreeNode> T copy(T source, T destination, Predicate<DoorsTreeNode> nodeFilter) {

        if (!destination.canCopyFrom(source)) {
            throw new IllegalArgumentException("Cannot copy from a " + source.getClass().getSimpleName() + " to a " + destination.getClass().getSimpleName());
        }

        if (!nodeFilter.test(source)) {
            return null;
        }

        destination.setName(source.getName());
        destination.getAttributes().clear();
        destination.getAttributes().putAll(source.getAttributes());

        destination.getChildren().clear();
        source.getChildren().stream()
                .map(c -> this.createCopy(c, destination, nodeFilter))
                .filter(c -> c != null)
                .forEach(destination.getChildren()::add);

        if (source instanceof DoorsObject) {
            DoorsObject sourceObj = (DoorsObject) source;
            DoorsObject copyObj = (DoorsObject) destination;

            sourceObj.getOutgoingLinks().clear();
            copyObj.getOutgoingLinks().stream()
                    .map(l -> this.createLink(copyObj, l.getTargetModule(), l.getTargetObject()))
                    .forEach(sourceObj.getOutgoingLinks()::add);
        }

        return destination;
    }

}
