package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

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
    private final NormalizedLevenshtein distance;

    public ClusterClassifier(final ClassifierContext context) {
        super(context);
        try {
            clusters = new Gson().fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/clusterclassifier/clusters.json")), Cluster[].class);
        } catch (JsonSyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        distance = new NormalizedLevenshtein();
    }

    @Override
    protected ClusterClassificationResult run(final ExampleContext context) {
        final String text = context.getExample().getText();

        double minDistance = Double.MAX_VALUE;
        Cluster cluster = null;

        final Map<String, Double> distances = new HashMap<>();

        for (final Cluster c : clusters) {

            double distanceSum = 0;

            for (final String example : c.getExamples()) {

                final double d = distance.distance(example, text);
                distanceSum += d;
                distances.put(example, d);

            }
            final double avgDistance = (distanceSum / c.getExamples().size());
            if (avgDistance < minDistance) {
                minDistance = avgDistance;
                cluster = c;
            }
        }

        if (cluster != null && minDistance < 0.5) {

            final ClusterClassificationResult result = new ClusterClassificationResult(cluster.getLabel(), ClassifiedBy.CLUSTER_CLASSIFIER, ClassificationReliability.MOST_LIKELY_CORRECT);
            final List<String> list = new ArrayList<>(cluster.getExamples());
            Collections.sort(list, new Comparator<String>() {

                @Override
                public int compare(final String o1, final String o2) {
                    return Double.compare(distances.get(o1), distances.get(o2));
                }
            });
            result.getSimiliarExamples().addAll(list.subList(0, Math.min(list.size(), 3)));
            result.setMinDistance(minDistance);
            return result;
        } else {
            return null;
        }
    }

}
