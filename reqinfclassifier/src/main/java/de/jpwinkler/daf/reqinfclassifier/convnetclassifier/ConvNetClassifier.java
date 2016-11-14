package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.io.IOException;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import de.jpwinkler.libs.convnet.model.ConvNetModel;
import de.jpwinkler.libs.convnet.model.Word2VecModel;
import de.jpwinkler.libs.convnet.tensors.Tensor;
import de.jpwinkler.libs.convnet.util.ConvNetUtils;

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

        result.setFractionOfKnownWords(word2VecModel.calculateFractionOfKnownWords(context.getConvNetPreprocessedText()));

        result.getProbabilities().put("information", eval.get(0));
        result.getProbabilities().put("requirement", eval.get(1));

        result.setInfluenceVector(ConvNetUtils.calculateInfluenceVector(eval, word2VecModel.getSentenceLength()));

        return result;
    }

}
