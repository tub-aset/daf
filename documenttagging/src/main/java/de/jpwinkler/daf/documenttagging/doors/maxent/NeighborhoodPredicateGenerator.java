package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;

public class NeighborhoodPredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getParent() != null) {
            final String parent_pod_tags = object.getParent().getAttributes().get("pod_tag");
            if (parent_pod_tags != null) {

                if (!parent_pod_tags.isEmpty()) {
                    emitPredicate("parent_pod_tag=" + parent_pod_tags);
                }
                final DoorsObject previous = DoorsModuleUtil.getPreviousObject(object);
                if (previous != null) {
                    final String prev_pod_tag = previous.getAttributes().get("pod_tag");
                    if (!prev_pod_tag.isEmpty()) {
                        emitPredicate("prev_pod_tag=" + prev_pod_tag);
                    }
                }
            }
        }
    }

}
