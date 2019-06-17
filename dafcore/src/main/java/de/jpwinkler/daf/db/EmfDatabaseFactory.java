/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.UnresolvedLink;
import java.util.stream.Collectors;

/**
 *
 * @author fwiesweg
 */
public class EmfDatabaseFactory implements DatabaseFactory {

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name) {
        return create(parent, DoorsFactory.eINSTANCE.createDoorsFolder(), name);
    }

    @Override
    public DoorsModule createModule(DoorsTreeNode parent, String name) {
        DoorsModule module = create(parent, DoorsFactory.eINSTANCE.createDoorsModule(), name);
        module.setObjectAttributes(DoorsAttributes.valuesFor(DoorsObject.class)
                .filter(v -> !v.isSystemKey())
                .map(a -> a.getKey())
                .collect(Collectors.toList()));
        return module;
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
