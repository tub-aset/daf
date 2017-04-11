package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.interfaces.NormalizedStringSimilarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class ClusterClassifier extends Classifier<ClusterClassificationResult> {

    private Cluster[] clusters;
    private final NormalizedStringSimilarity similiarityFunction;

    public ClusterClassifier(final ClassifierContext context) {
        super(context);
        try {
            clusters = new Gson().fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/clusterclassifier/clusters.json")), Cluster[].class);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        similiarityFunction = new JaroWinkler();
    }

    @Override
    protected ClusterClassificationResult run(final ExampleContext context) {
        final String text = context.getPreprocessedText();

        double maxSimiliarity = Double.MIN_VALUE;
        Cluster cluster = null;

        final Map<String, Double> similiarityMap = new HashMap<>();

        for (final Cluster c : clusters) {

            double similiaritySum = 0;

            for (final String example : c.getExamples()) {

                final double similiarity = similiarityFunction.similarity(example, text);
                similiaritySum += similiarity;
                similiarityMap.put(example, similiarity);

            }
            final double avgSimiliarity = (similiaritySum / c.getExamples().size());
            if (avgSimiliarity > maxSimiliarity) {
                maxSimiliarity = avgSimiliarity;
                cluster = c;
            }
        }

        ClassificationReliability reliability = null;
        if (maxSimiliarity > 0.95) {
            reliability = ClassificationReliability.MOST_LIKELY_CORRECT;
        } else if (maxSimiliarity > 0.8) {
            reliability = ClassificationReliability.MAYBE_CORRECT;
        }

        if (cluster != null && reliability != null) {

            final ClusterClassificationResult result = new ClusterClassificationResult(cluster.getLabel(), ClassifiedBy.CLUSTER_CLASSIFIER, reliability);
            final List<String> list = new ArrayList<>(cluster.getExamples());
            Collections.sort(list, new Comparator<String>() {

                @Override
                public int compare(final String o1, final String o2) {
                    return Double.compare(similiarityMap.get(o2), similiarityMap.get(o1));
                }
            });
            for (int i = 0; i < Math.min(list.size(), 3); i++) {
                result.getMostSimiliarExamples().add(list.get(i));
                result.getExampleSimiliarities().add(similiarityMap.get(list.get(i)));
            }
            result.setObjectType(cluster.getLabel());
            return result;
        } else {
            return null;
        }
    }

}
