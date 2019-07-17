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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTableRow;
import de.jpwinkler.daf.model.DoorsTreeNode;

public class EmfDatabaseFactory extends DatabaseFactory {

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name, boolean project) {
        DoorsFolder folder = create(parent, DoorsFactory.eINSTANCE.createDoorsFolder(), name);
        folder.setProject(project);
        return folder;
    }

    @Override
    public DoorsModule createModule(DoorsTreeNode parent, String name) {
        return create(parent, DoorsFactory.eINSTANCE.createDoorsModule(), name);
    }

    @Override
    public DoorsObject createObject(DoorsTreeNode parent, String objectText) {
        DoorsObject object = create(parent, DoorsFactory.eINSTANCE.createDoorsObject(), null);
        object.setObjectText(objectText);
        object.setObjectHeading("");
        return object;
    }

    @Override
    public DoorsTableRow createTableRow(DoorsTreeNode parent) {
        return create(parent, DoorsFactory.eINSTANCE.createDoorsTableRow(), null);
    }

    @Override
    public DoorsLink createLink(DoorsObject source, String targetModule, String targetObject) {
        DoorsLink lnk = DoorsFactory.eINSTANCE.createDoorsLink();
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
