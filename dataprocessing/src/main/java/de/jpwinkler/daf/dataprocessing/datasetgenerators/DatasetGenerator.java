package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;

public abstract class DatasetGenerator<E, F> {

    private FeatureVectorGenerator<E, F> featureVectorGenerator;

    private final boolean unique;
    private final Map<String, Integer> outcomes = new HashMap<>();
    private final LabelGenerator<E> labelGenerator;

    public DatasetGenerator(final LabelGenerator<E> labelGenerator, final boolean unique) {
        super();
        this.labelGenerator = labelGenerator;
        this.unique = unique;
    }

    private boolean isObjectValid(final E object) {
        return labelGenerator.hasLabel(object);
    }

    public void init(final Iterator<E> iterator, final FeatureVectorGenerator<E, F> featureVectorGenerator) throws IOException {
        this.featureVectorGenerator = featureVectorGenerator;

        outcomes.clear();

        while (iterator.hasNext()) {
            final E element = iterator.next();
            if (isObjectValid(element)) {
                featureVectorGenerator.addElement(element);
                final List<String> labels = labelGenerator.getLabels(element);
                for (final String label : labels) {
                    if (!outcomes.containsKey(label)) {
                        outcomes.put(label, outcomes.size());
                    }
                }
            }
        }
        featureVectorGenerator.filter();
        featureVectorGenerator.buildFeatureIndexMap();

    }

    public FeatureVectorGenerator<E, F> getFeatureVectorGenerator() {
        return featureVectorGenerator;
    }

    public Map<String, Integer> getLabels() {
        return Collections.unmodifiableMap(outcomes);
    }

    public final void generateDataset(final Iterator<E> iterator, final OutputStream stream) throws IOException {
        final Set<Integer> examples = new HashSet<>();
        
        setStream(stream);

        beforeDatasetGeneration();

        while (iterator.hasNext()) {
            final E element = iterator.next();
            if (isObjectValid(element)) {
                final int[] featureVector = featureVectorGenerator.getFeatureVector(element);
                final List<String> labels = labelGenerator.getLabels(element);
                final int hashCode = 997 * Arrays.toString(featureVector).hashCode() ^ 991 * labels.hashCode();

                if (!unique || !examples.contains(hashCode)) {
                    examples.add(hashCode);
                    try {
                        if (labels.size() > 1) {
                            addMultiClassDatasetRecord(element, featureVector, labels);
                        } else {
                            addDatasetRecord(element, featureVector, labels.get(0));
                        }
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        afterDatasetGeneration();
    }

    protected abstract void setStream(OutputStream stream);

	protected void beforeDatasetGeneration() throws IOException {
    }

    protected void addMultiClassDatasetRecord(final E object, final int[] featureVector, final List<String> outcome) throws IOException {
        throw new UnsupportedOperationException("Multilabel classification is not supported by dataset generator " + getClass().getName());
    }

    protected void addDatasetRecord(final E object, final int[] featureVector, final String outcome) throws IOException {
    }

    protected void afterDatasetGeneration() throws IOException {
    }

}
