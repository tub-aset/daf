package de.jpwinkler.daf.reqinfclassifier.multisentenceclassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleImpl;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class MultiSentenceClassifier extends Classifier<MultiSentenceClassificationResult> {

    private final Classifier<? extends ClassificationResult> innerClassifier;
    private SentenceDetectorME detector;

    public MultiSentenceClassifier(final ClassifierContext context, final Classifier<? extends ClassificationResult> classifier) {
        super(context);
        innerClassifier = classifier;

        try {
            final SentenceModel model = new SentenceModel(getClass().getResourceAsStream("/de/jpwinkler/daf/reqinfclassifier/multisentenceclassifier/de-sent.bin"));
            detector = new SentenceDetectorME(model);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected MultiSentenceClassificationResult run(final ExampleContext exampleContext) {
        final List<String> sentences = Arrays.asList(detector.sentDetect(exampleContext.getExample().getText()));

        if (sentences.size() < 2) {
            return null;
        }

        final List<ClassificationResult> results = new ArrayList<>();

        final ExampleImpl clone = new ExampleImpl(exampleContext.getExample());
        for (final String sentence : sentences) {
            clone.setText(sentence);
            results.add(innerClassifier.classify(clone));
        }

        if ((results.stream().map(c -> c.getObjectType()).distinct().count() > 1) && (results.stream().allMatch(cr -> cr.getReliability().atLeast(ClassificationReliability.MAYBE_CORRECT)))) {
            final MultiSentenceClassificationResult result = new MultiSentenceClassificationResult();
            result.setObjectType(null);
            result.setReliability(ClassificationReliability.AMBIGUOUS);
            result.setClassifiedBy(ClassifiedBy.MULTI_SENTENCE_CLASSIFIER);
            result.setSentences(sentences);
            result.setResults(results);
            return result;
        } else {
            return null;
        }

    }

}
