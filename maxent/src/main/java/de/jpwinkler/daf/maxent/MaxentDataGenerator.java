package de.jpwinkler.daf.maxent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;
import de.jpwinkler.daf.maxent.preprocessing.Preprocessor;

public class MaxentDataGenerator {

    private final List<Preprocessor> preprocessors = new ArrayList<>();

    private final List<FeatureGenerator> featureGenerators = new ArrayList<>();

    private OutcomeFunction outcomeFunction;

    private boolean ignoreObjectsWithoutOutcome = false;

    public boolean isIgnoreObjectsWithoutOutcome() {
        return ignoreObjectsWithoutOutcome;
    }

    public void setIgnoreObjectsWithoutOutcome(final boolean ignoreObjectsWithoutOutcome) {
        this.ignoreObjectsWithoutOutcome = ignoreObjectsWithoutOutcome;
    }

    public MaxentDataGenerator() {
    }

    public void addPreprocessor(final Preprocessor preprocessor) {
        preprocessors.add(preprocessor);
    }

    public void addFeatureGenerator(final FeatureGenerator featureGenerator) {
        featureGenerators.add(featureGenerator);
    }

    public void setOutcomeFunction(final OutcomeFunction outcomeFunction) {
        this.outcomeFunction = outcomeFunction;
    }

    private void applyPreprocessors(final DoorsObject object) {
        for (final Preprocessor preprocessor : preprocessors) {
            preprocessor.apply(object);
        }
    }

    private MaxentDataElement runGenerators(final DoorsObject object) {
        final List<String> x = new ArrayList<>();
        for (final FeatureGenerator generator : featureGenerators) {
            generator.run(feature -> {
                x.add(feature.getName() + "=" + feature.getValue());
            } , object);
        }
        if (outcomeFunction != null && outcomeFunction.getOutcome(object) != null && !outcomeFunction.getOutcome(object).isEmpty()) {
            return new MaxentDataElement(outcomeFunction.getOutcome(object), x.toArray(new String[x.size()]));
        } else if (!ignoreObjectsWithoutOutcome) {
            return new MaxentDataElement("", x.toArray(new String[x.size()]));
        } else {
            return null;
        }
    }

    public void run(final DoorsModule module, final BiConsumer<DoorsObject, MaxentDataElement> consumer) {
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                applyPreprocessors(object);
                return true;
            }

        });

        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                final MaxentDataElement record = runGenerators(object);
                if (record != null) {
                    consumer.accept(object, record);
                }
                return true;
            }

        });

    }

    public MaxentDataElement run(final DoorsObject object) {
        applyPreprocessors(object);
        return runGenerators(object);
    }

}
