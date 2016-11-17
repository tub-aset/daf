package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import de.jpwinkler.libs.convnet.model.ConvNetModel;
import de.jpwinkler.libs.convnet.model.Word2VecModel;
import de.jpwinkler.libs.convnet.tensors.Tensor;
import de.jpwinkler.libs.convnet.util.ConvNetUtils;
import de.jpwinkler.libs.stringprocessing.tokens.Token;

public class ConvNetClassifier extends Classifier<ConvNetClassificationResult> {

	private Word2VecModel word2VecModel;
	private ConvNetModel convNetModel;

	public ConvNetClassifier(final ClassifierContext context) {
		super(context);

		try {
			word2VecModel = Word2VecModel.loadFromJson(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/w2v.json.gz"));
			convNetModel = ConvNetModel.loadFromJson(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/simple-wcnn.json.gz"));
			convNetModel.setWord2VecModel(word2VecModel);
			convNetModel.buildNet();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			word2VecModel = null;
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
		result.setReliability(ClassificationReliability.MAYBE_CORRECT);

		result.setFractionOfKnownWords(word2VecModel.calculateFractionOfKnownWords(context.getConvNetPreprocessedText()));

		result.getProbabilities().put("information", eval.get(0));
		result.getProbabilities().put("requirement", eval.get(1));

		final List<Double> influenceVector = ConvNetUtils.calculateInfluenceVector(eval, word2VecModel.getSentenceLength());
		final SentenceMarkup sentenceMarkup = createSentenceMarkup(context.getExample().getText().length(), influenceVector, context.getConvNetPreprocessedTokens());
		result.setSentenceMarkup(sentenceMarkup);

		return result;
	}

	private SentenceMarkup createSentenceMarkup(final int sentenceLength, final List<Double> influenceVector, final List<Token> tokens) {
		final SentenceMarkup sentenceMarkup = new SentenceMarkup();

		for (int i = 0; i < Math.min(tokens.size(), influenceVector.size()); i++) {
			final Token t = tokens.get(i);
			sentenceMarkup.getRanges().add(new SentenceMarkupRange(t.getStartPosition(), t.getEndPosition() - t.getStartPosition(), influenceVector.get(i)));
		}

		return sentenceMarkup;
	}

}
