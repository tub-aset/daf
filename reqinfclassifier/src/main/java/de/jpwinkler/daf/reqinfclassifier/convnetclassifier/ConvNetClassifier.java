package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import de.jpwinkler.libs.convnet.model.ConvNetModel;
import de.jpwinkler.libs.convnet.model.Word2VecModel;
import de.jpwinkler.libs.convnet.tensors.Tensor;

public class ConvNetClassifier extends Classifier<ConvNetClassificationResult> {

    private Word2VecModel word2VecModel;
    private ConvNetModel convNetModel;

    public ConvNetClassifier(final ClassifierContext context) {
        super(context);

        try {
            word2VecModel = new Gson().fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/w2v.json")), Word2VecModel.class);
            convNetModel = new Gson().fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/wcnn.json")), ConvNetModel.class);
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
        result.getProbabilities().put("information", eval.get(0));
        result.getProbabilities().put("requirement", eval.get(1));
        if (eval.get(0) > eval.get(1)) {
            result.setObjectType("information");
        } else {
            result.setObjectType("requirement");
        }
        return result;
    }

}
