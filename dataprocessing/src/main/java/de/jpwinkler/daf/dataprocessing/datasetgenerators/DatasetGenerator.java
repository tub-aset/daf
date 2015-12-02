package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.DoorsModuleSource;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;

public abstract class DatasetGenerator {

    private DoorsModuleSource doorsModuleSource;
    private FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator;

    private final String labelAttribute;
    private final List<String> allowedLabels;
    private final boolean unique;
    private final Map<String, Integer> outcomes = new HashMap<>();

    public DatasetGenerator(final String labelAttribute, final List<String> allowedLabels, final boolean unique) {
        super();
        this.labelAttribute = labelAttribute;
        this.allowedLabels = allowedLabels;
        this.unique = unique;
    }

    private boolean isObjectValid(final DoorsObject object) {
        return object.getAttributes().containsKey(labelAttribute) && (allowedLabels == null || allowedLabels.contains(object.getAttributes().get(labelAttribute)));
    }

    public void init(final DoorsModuleSource doorsModuleSource, final FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator) throws IOException {
        this.doorsModuleSource = doorsModuleSource;
        this.featureVectorGenerator = featureVectorGenerator;

        outcomes.clear();

        DoorsModule module = null;
        while ((module = doorsModuleSource.next()) != null) {
            module.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    if (isObjectValid(object)) {
                        getFeatureVectorGenerator().addElement(object);
                        final String label = object.getAttributes().get(labelAttribute);
                        if (!outcomes.containsKey(label)) {
                            outcomes.put(label, outcomes.size());
                        }
                    }
                    return true;
                }
            });
        }
        getFeatureVectorGenerator().buildFeatureIndexMap();

    }

    protected DoorsModuleSource getDoorsModuleSource() {
        return doorsModuleSource;
    }

    protected FeatureVectorGenerator<DoorsObject, String> getFeatureVectorGenerator() {
        return featureVectorGenerator;
    }

    protected Map<String, Integer> getOutcomes() {
        return Collections.unmodifiableMap(outcomes);
    }

    public final void generateDataset(final OutputStream stream) throws IOException {
        final Set<Integer> examples = new HashSet<>();

        beforeDatasetGeneration(stream);

        getDoorsModuleSource().reset();

        DoorsModule module;
        while ((module = getDoorsModuleSource().next()) != null) {
            module.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    if (isObjectValid(object)) {
                        final double[] featureVector = getFeatureVectorGenerator().getFeatureVector(object);
                        final String outcome = object.getAttributes().get(labelAttribute);
                        final int hashCode = 997 * Arrays.toString(featureVector).hashCode() ^ 991 * outcome.hashCode();

                        if (!unique || !examples.contains(hashCode)) {
                            examples.add(hashCode);
                            try {
                                addDatasetRecord(stream, featureVector, outcome);
                            } catch (final IOException e) {
                                e.printStackTrace();
                                return true;
                            }
                        }
                    }
                    return true;
                }

            });
        }

        afterDatasetGeneration(stream);
        stream.close();
    }

    protected void beforeDatasetGeneration(final OutputStream stream) throws IOException {
    }

    protected void addDatasetRecord(final OutputStream stream, final double[] featureVector, final String outcome) throws IOException {
    }

    protected void afterDatasetGeneration(final OutputStream stream) throws IOException {
    }

}
