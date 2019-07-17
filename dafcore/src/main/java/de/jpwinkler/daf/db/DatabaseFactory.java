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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTableRow;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.RuntimeExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fwiesweg
 */
public abstract class DatabaseFactory {

    public abstract DoorsFolder createFolder(DoorsTreeNode parent, String name, boolean project);

    public abstract DoorsModule createModule(DoorsTreeNode parent, String name);

    public abstract DoorsObject createObject(DoorsTreeNode parent, String objectText);

    public abstract DoorsTableRow createTableRow(DoorsTreeNode parent);

    public abstract DoorsLink createLink(DoorsObject source, String targetModule, String targetObject);

    public final <T extends DoorsTreeNode> T createCopy(T source, DoorsTreeNode newParent, boolean resilient) {
        return this.createCopy(source, newParent, resilient, BackgroundTaskNotifier.SYNCHRONOUS);
    }

    public final <T extends DoorsTreeNode> T createCopy(T source, DoorsTreeNode newParent, boolean resilient, BackgroundTaskNotifier btNotifier) {
        btNotifier.throwIfCancelled();

        T copy;
        if (source instanceof DoorsTableRow) {
            copy = (T) this.createTableRow(newParent);
        } else if (source instanceof DoorsObject) {
            copy = (T) this.createObject(newParent, null);
        } else if (source instanceof DoorsModule) {
            copy = (T) this.createModule(newParent, null);
        } else if (source instanceof DoorsFolder) {
            copy = (T) this.createFolder(newParent, null, ((DoorsFolder) source).isProject());
        } else {
            throw new AssertionError();
        }

        return this.copy(source, copy, resilient, btNotifier);
    }

    public final <T extends DoorsTreeNode> T copy(T source, T destination, boolean resilient) {
        return this.copy(source, destination, resilient, BackgroundTaskNotifier.SYNCHRONOUS);
    }

    public final <T extends DoorsTreeNode> T copy(T source, T destination, boolean resilient, BackgroundTaskNotifier btNotifier) {
        if (!destination.canCopyFrom(source)) {
            throw new IllegalArgumentException("Cannot copy from a " + source.getClass().getSimpleName() + " to a " + destination.getClass().getSimpleName());
        }
        do {
            btNotifier.throwIfCancelled();
            try {
                destination.setName(source.getName());
                destination.getAttributes().clear();
                destination.getAttributes().putAll(source.getAttributes());

                destination.getChildren().clear();
                source.getChildren().stream()
                        .map(c -> this.createCopy(c, destination, resilient, btNotifier))
                        .filter(c -> c != null)
                        .forEach(destination.getChildren()::add);
                source.getTags().forEach(destination::setTag);

                if (source instanceof DoorsObject) {
                    DoorsObject sourceObj = (DoorsObject) source;
                    DoorsObject destinationObject = (DoorsObject) destination;

                    destinationObject.getOutgoingLinks().clear();
                    sourceObj.getOutgoingLinks().stream()
                            .map(l -> this.createLink(destinationObject, l.getTargetModule(), l.getTargetObject()))
                            .forEach(destinationObject.getOutgoingLinks()::add);
                }
                return destination;
            } catch (RuntimeExecutionException ex) {
                if (resilient) {
                    Logger.getLogger(DatabaseFactory.class.getName()).log(Level.SEVERE, "Loading from database failed, trying again", ex);
                } else {
                    throw new RuntimeException(ex);
                }
            }
        } while (resilient);

        throw new AssertionError();
    }

}
