/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

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
            throw new IllegalArgumentException();
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
