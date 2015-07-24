package de.jpwinkler.daf.dafcore.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.ResolvedLink;
import de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink;

public class LinkReconstructor {

    private List<DoorsModule> modules;

    // Maps module names to object identifiers and then to objects
    private Map<String, Map<String, DoorsObject>> map;

    public void reconstructLinks(final List<DoorsModule> modules) {

        this.modules = modules;
        prepare();

        for (final DoorsModule module : modules) {
            module.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    fixLinks(object);
                    return true;
                }
            });
        }
    }

    private void fixLinks(final DoorsObject object) {
        if (object.getAttributes().containsKey("DOORS_LINKS") && !object.getAttributes().get("DOORS_LINKS").isEmpty()) {
            final String[] links = object.getAttributes().get("DOORS_LINKS").split("\r\n");

            for (final String link : links) {
                // TODO: unsafe!
                final String linkTargetModule = link.split(":")[0];
                final String linkTargetObject = link.split(":")[1];
                if (map.containsKey(linkTargetModule) && map.get(linkTargetModule).containsKey(linkTargetObject)) {
                    final ResolvedLink resolvedLink = CSVFactory.eINSTANCE.createResolvedLink();
                    resolvedLink.setTarget(map.get(linkTargetModule).get(linkTargetObject));
                    object.getOutgoingLinks().add(resolvedLink);
                } else {
                    final UnresolvedLink unresolvedLink = CSVFactory.eINSTANCE.createUnresolvedLink();
                    unresolvedLink.setTargetModule(linkTargetModule);
                    unresolvedLink.setTargetObject(linkTargetObject);
                    object.getOutgoingLinks().add(unresolvedLink);
                }
            }
        }
    }

    private void prepare() {

        map = new HashMap<String, Map<String, DoorsObject>>();

        for (final DoorsModule module : modules) {
            final Map<String, DoorsObject> objectMap = new HashMap<>();

            module.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    objectMap.put(object.getObjectIdentifier(), object);
                    return true;

                }
            });

            map.put(module.getName(), objectMap);
        }

    }

}
