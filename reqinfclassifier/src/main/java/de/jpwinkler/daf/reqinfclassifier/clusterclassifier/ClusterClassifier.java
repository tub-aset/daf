package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

public class ClusterClassifier extends Classifier<String> {

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
    protected String run(final DoorsObjectContext context) {
        final String text = context.getDoorsObject().getText();

        double minDistance = Double.MAX_VALUE;
        String label = null;

        for (final Cluster c : clusters) {

            if (c.getExamples().size() > 0 && c.getSourceDistribution() > 2 && c.getLabel() != null) {

                final double d = c.getExamples().stream().mapToDouble(s -> distance.distance(s, text)).min().getAsDouble();

                if (d < minDistance) {
                    minDistance = d;
                    label = c.getLabel();
                }

            }
        }

        if (label != null && minDistance < 0.5) {
            return label;
        } else {
            return null;
        }
    }

}
