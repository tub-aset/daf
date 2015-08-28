package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class NeighborhoodPredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getParent() != null) {
            final String parent_pod_tags = object.getParent().getAttributes().get("pod_tags");
            if (parent_pod_tags != null) {

                if (!parent_pod_tags.isEmpty()) {
                    emitPredicate("parent_pod_tags=" + parent_pod_tags);
                }
                final int i = object.getParent().getChildren().indexOf(object);

                if (i > 0) {
                    final DoorsTreeNode prev = object.getParent().getChildren().get(i - 1);
                    final String prev_pod_tag = prev.getAttributes().get("pod_tags");
                    if (prev != null && !prev_pod_tag.isEmpty()) {
                        emitPredicate("prev_pod_tags=" + prev_pod_tag);
                    }
                }
            }
        }
    }

}
