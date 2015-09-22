package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;

public class NeighborhoodPredicateGenerator extends PredicateGenerator {

    // TODO: assign these
    private final String podTagAttributeName;
    private final String parentPodTagAttributeName;
    private final String previousPodTagAttributeName;

    public NeighborhoodPredicateGenerator(final String podTagAttributeName, final String parentPodTagAttributeName, final String previousPodTagAttributeName) {
        super();
        this.podTagAttributeName = podTagAttributeName;
        this.parentPodTagAttributeName = parentPodTagAttributeName;
        this.previousPodTagAttributeName = previousPodTagAttributeName;
    }

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getParent() != null) {
            final String parent_pod_tags = object.getParent().getAttributes().get(podTagAttributeName);
            if (parent_pod_tags != null) {

                if (!parent_pod_tags.isEmpty()) {
                    emitPredicate(parentPodTagAttributeName + "=" + parent_pod_tags);
                }
                final DoorsObject previous = DoorsModuleUtil.getPreviousObject(object);
                if (previous != null) {
                    final String prev_pod_tag = previous.getAttributes().get(podTagAttributeName);
                    if (!prev_pod_tag.isEmpty()) {
                        emitPredicate(previousPodTagAttributeName + "=" + prev_pod_tag);
                    }
                }
            }
        }
    }

    @Override
    public boolean useOnlyInTraining() {
        return true;
    }
}
