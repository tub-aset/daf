package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class NgramOrderFilter extends FeatureFilter<String> {

    private Set<String> forbiddenFeatures;

    @Override
    public boolean test(final String feature) {
        if (forbiddenFeatures == null) {
            buildForbiddenFeatureSet();
        }
        return !forbiddenFeatures.contains(feature);
    }

    private void buildForbiddenFeatureSet() {
        forbiddenFeatures = new HashSet<>();

        for (final String feature : getFeatureVectorGenerator().getFeatureCounts().keySet()) {
            final List<String> f = Arrays.asList(feature.split(" "));

            for (int ngramsize = 1; ngramsize < f.size(); ngramsize++) {
                for (int i = 0; i < f.size() - ngramsize + 1; i++) {
                    final List<String> subList = f.subList(i, i + ngramsize);
                    forbiddenFeatures.add(StringUtils.join(subList, " "));
                }
            }
        }
    }

}
