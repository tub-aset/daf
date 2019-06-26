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

import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.UnresolvedLink;
public class EmfDatabaseFactory implements DatabaseFactory {

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name) {
        return create(parent, DoorsFactory.eINSTANCE.createDoorsFolder(), name);
    }

    @Override
    public DoorsModule createModule(DoorsTreeNode parent, String name) {
        return create(parent, DoorsFactory.eINSTANCE.createDoorsModule(), name);
    }

    @Override
    public DoorsObject createObject(DoorsTreeNode parent, String objectText) {
        DoorsObject object = create(parent, DoorsFactory.eINSTANCE.createDoorsObject(), null);
        if (parent instanceof DoorsModule) {
            object.setObjectLevel(1);
        } else if (parent instanceof DoorsObject) {
            object.setObjectLevel(((DoorsObject) parent).getObjectLevel() + 1);
        } else if (parent instanceof DoorsFolder) {
            throw new IllegalArgumentException("parent");
        }

        object.setObjectText(objectText);
        object.setObjectHeading("");
        return object;
    }

    @Override
    public UnresolvedLink createLink(DoorsObject source, String targetModule, String targetObject) {
        UnresolvedLink lnk = DoorsFactory.eINSTANCE.createUnresolvedLink();
        lnk.setSource(source);
        lnk.setTargetModule(targetModule);
        lnk.setTargetObject(targetModule);
        return lnk;
    }

    private static <T extends DoorsTreeNode> T create(DoorsTreeNode parent, T object, String name) {
        object.setName(name);
        object.setParent(parent);
        return object;
    }
}
