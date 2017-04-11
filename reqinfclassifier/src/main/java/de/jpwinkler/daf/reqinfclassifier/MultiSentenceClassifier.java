package de.jpwinkler.daf.reqinfclassifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.daimler.re.methods.nlp.exceptions.NLPImplementationNotFoundException;
import com.daimler.re.methods.nlp.exceptions.NLPImplementationNotInstantiatableException;
import com.daimler.re.methods.nlp.exceptions.NLPSentenceDetectionException;
import com.daimler.re.methods.nlp.exceptions.NLPTokenizationException;
import com.daimler.re.methods.nlp.processing.SentenceDetector;

public class MultiSentenceClassifier extends Classifier<MultiSentenceClassificationResult> {

    private final Classifier<? extends ClassificationResult> innerClassifier;

    public MultiSentenceClassifier(final ClassifierContext context, final Classifier<? extends ClassificationResult> classifier) {
        super(context);
        innerClassifier = classifier;
    }

    @Override
    protected MultiSentenceClassificationResult run(final ExampleContext exampleContext) {
        try {
            final List<String> sentences = SentenceDetector.getInstance().detectSentences(exampleContext.getExample().getText()).stream().map(s -> s.getText()).collect(Collectors.toList());

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

        } catch (IllegalArgumentException | NLPSentenceDetectionException | NLPTokenizationException | NLPImplementationNotFoundException | NLPImplementationNotInstantiatableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
