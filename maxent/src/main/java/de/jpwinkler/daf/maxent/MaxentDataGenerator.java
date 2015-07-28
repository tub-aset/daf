package de.jpwinkler.daf.maxent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;
import de.jpwinkler.daf.maxent.features.FeatureGeneratorMode;
import de.jpwinkler.daf.maxent.features.impl.ASILFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.FOObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.NeighborhoodFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.ObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialCharacterFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialTokenFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.WordFeatureGenerator;
import de.jpwinkler.daf.maxent.preprocessing.CompoundSplitterPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.NewLineRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.Preprocessor;
import de.jpwinkler.daf.maxent.preprocessing.StopwordRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.WordStemmerPreprocessor;

public class MaxentDataGenerator {

    private final List<Preprocessor> preprocessors = new ArrayList<>();

    private final List<FeatureGenerator> featureGenerators = new ArrayList<>();

    private OutcomeFunction outcomeFunction;

    private boolean isTraining = false;

    public boolean isTraining() {
        return isTraining;
    }

    public void setTraining(final boolean isTraining) {
        this.isTraining = isTraining;
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
            if (generator.getFeatureGeneratorMode() == FeatureGeneratorMode.ALWAYS || (isTraining && generator.getFeatureGeneratorMode() == FeatureGeneratorMode.ONLY_IN_TRAINIG)) {
                generator.run(feature -> {
                    x.add(feature.getName() + "=" + feature.getValue());
                } , object);
            }
        }
        if (isTraining) {
            if (outcomeFunction != null && outcomeFunction.getOutcome(object) != null && !outcomeFunction.getOutcome(object).isEmpty()) {
                return new MaxentDataElement(outcomeFunction.getOutcome(object), x.toArray(new String[x.size()]));
            } else {
                return null;
            }
        } else {
            return new MaxentDataElement("", x.toArray(new String[x.size()]));
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

    public static MaxentDataGenerator getDefaultGenerator() {
        final MaxentDataGenerator generator = new MaxentDataGenerator();
        generator.setOutcomeFunction(object -> object.getAttributes().get("pod_tags"));
        generator.addPreprocessor(new NewLineRemovalPreprocessor());
        generator.addPreprocessor(new CompoundSplitterPreprocessor());
        generator.addPreprocessor(new WordStemmerPreprocessor());
        generator.addPreprocessor(new StopwordRemovalPreprocessor());
        generator.addFeatureGenerator(new ASILFeatureGenerator());
        generator.addFeatureGenerator(new ObjectTypeFeatureGenerator());
        generator.addFeatureGenerator(new FOObjectTypeFeatureGenerator());
        generator.addFeatureGenerator(new NeighborhoodFeatureGenerator());
        generator.addFeatureGenerator(new SpecialTokenFeatureGenerator());
        generator.addFeatureGenerator(new SpecialCharacterFeatureGenerator());
        generator.addFeatureGenerator(new WordFeatureGenerator());

        return generator;
    }

}
