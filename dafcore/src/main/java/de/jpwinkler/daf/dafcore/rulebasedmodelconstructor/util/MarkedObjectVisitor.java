package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class MarkedObjectVisitor extends DoorsTreeNodeVisitor {

    private final RuleContext context;
    private final List<DoorsObject> unmarkedObjects = new ArrayList<>();
    private final List<DoorsObject> markedObjects = new ArrayList<>();

    public MarkedObjectVisitor(final RuleContext context) {
        super();
        this.context = context;
    }

    public List<DoorsObject> getUnmarkedObjects() {
        return unmarkedObjects;
    }

    public List<DoorsObject> getMarkedObjects() {
        return markedObjects;
    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        if (context.getMarkers(object).isEmpty()) {
            unmarkedObjects.add(object);
        } else {
            markedObjects.add(object);
        }
        return true;
    }

}
