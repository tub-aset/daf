package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

import java.util.function.Predicate;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public final class MarkerUtil {

    private MarkerUtil() {
    }

    public static DoorsObject findFirstParent(final DoorsObject object, final RuleContext context, final Predicate<DoorsObject> p) {
        if (object.getParent() != null && p.test(object.getParent())) {
            return object.getParent();
        } else if (object.getParent() != null) {
            return findFirstParent(object.getParent(), context, p);
        } else {
            return null;
        }
    }

    public static DoorsObject findFirstParentWithMarker(final DoorsObject object, final RuleContext context, final String markerType) {
        return findFirstParent(object, context, (final DoorsObject o) -> context.getMarker(o, markerType) != null);
    }

    public static DoorsObject findFirstParentWithAttribute(final DoorsObject object, final RuleContext context, final String attributeName) {
        return findFirstParent(object, context, (final DoorsObject o) -> o.getAttributes().containsKey(attributeName));
    }

    public static DoorsObject findFirstParentWithAttributeValue(final DoorsObject object, final RuleContext context, final String attributeName, final String attributeValue) {
        return findFirstParent(object, context, (final DoorsObject o) -> o.getAttributes().containsKey(attributeName) && o.getAttributes().get(attributeName).equals(attributeValue));
    }
}
