package de.jpwinkler.daf.maxent.features.impl;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;
import de.jpwinkler.daf.maxent.features.StringFeature;

public class NeighborhoodFeatureGenerator extends FeatureGenerator {

    @Override
    protected void processObject(final DoorsObject o) {

        if (o.getParent() != null) {
            final String parent_pod_tags = o.getParent().getAttributes().get("pod_tags");
            if (parent_pod_tags != null) {

                if (!parent_pod_tags.isEmpty()) {
                    emitFeature(new StringFeature("parent_pod_tags", parent_pod_tags));
                }
                final int i = o.getParent().getObjects().indexOf(o);

                if (i > 0) {
                    final DoorsTreeNode prev = o.getParent().getObjects().get(i - 1);
                    final String prev_pod_tag = prev.getAttributes().get("pod_tags");
                    if (prev != null && !prev_pod_tag.isEmpty()) {
                        emitFeature(new StringFeature("prev_pod_tags", prev_pod_tag));
                    }
                }
            }
        }

    }

}
