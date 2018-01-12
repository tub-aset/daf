package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import de.jpwinkler.libs.convnet.model.ConvNetModel;
import de.jpwinkler.libs.convnet.tensors.Tensor;
import de.jpwinkler.libs.convnet.tensors.Tensor2D;
import de.jpwinkler.libs.convnet.util.ConvNetUtils;
import de.jpwinkler.libs.stringprocessing.tokens.Token;

public class ConvNetClassifier extends Classifier<ConvNetClassificationResult> {

    private ConvNetModel convNetModel;

    public ConvNetClassifier(final ClassifierContext context) {
        super(context);

        try {
            convNetModel = ConvNetModel.loadFromJson(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/model.json.gz"));
            convNetModel.buildNet();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            convNetModel = null;
        }
    }

    @Override
    protected ConvNetClassificationResult run(final ExampleContext context) {
        final Tensor eval = convNetModel.eval(context.getConvNetPreprocessedText());

        final ConvNetClassificationResult result = new ConvNetClassificationResult();

        if (eval.get(0) > eval.get(1)) {
            result.setObjectType("information");
        } else {
            result.setObjectType("requirement");
        }

        result.setClassifiedBy(ClassifiedBy.CONVNET_CLASSIFIER);

        final double fractionOfKnownWords = convNetModel.getWord2VecModel().calculateFractionOfKnownWords(context.getConvNetPreprocessedText());

        if (fractionOfKnownWords > 0.5) {
            result.setFractionOfKnownWords(fractionOfKnownWords);

            result.getProbabilities().put("information", eval.get(0));
            result.getProbabilities().put("requirement", eval.get(1));

            if (result.getFractionOfKnownWords() > 0.8 && result.getHighestProbability() > 0.95) {
                result.setReliability(ClassificationReliability.MOST_LIKELY_CORRECT);
            } else if (result.getFractionOfKnownWords() < 0.2 || result.getHighestProbability() < 0.7) {
                result.setReliability(ClassificationReliability.AMBIGUOUS);
            } else {
                result.setReliability(ClassificationReliability.MAYBE_CORRECT);
            }

            final Tensor2D influenceVector = ConvNetUtils.calculateInfluenceVectorNormalized2(convNetModel, 0.4);
            final SentenceMarkup sentenceMarkup = createSentenceMarkup(context.getExample().getText().length(), influenceVector, context.getConvNetPreprocessedTokens());
            result.setSentenceMarkup(sentenceMarkup);

            return result;
        } else {
            return null;
        }
    }

    private SentenceMarkup createSentenceMarkup(final int sentenceLength, final Tensor2D influenceVector, final List<Token> tokens) {
        final SentenceMarkup sentenceMarkup = new SentenceMarkup();

        for (int i = 0; i < Math.min(tokens.size(), influenceVector.getShape()[0]); i++) {
            final Token t = tokens.get(i);
            final double[] strength = new double[influenceVector.getShape()[1]];
            for (int j = 0; j < strength.length; j++) {
                strength[j] = influenceVector.get(i, j);
            }
            sentenceMarkup.getRanges().add(new SentenceMarkupRange(t.getStartPosition(), t.getEndPosition() - t.getStartPosition(), strength));
        }

        return sentenceMarkup;
    }

}
