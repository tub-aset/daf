package de.jpwinkler.daf.reqinfclassifier.templateclassifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class TemplateClassifier extends Classifier<ClassificationResult> {

    private Map<String, String> templateTypes;

    public TemplateClassifier(final ClassifierContext context, final String templateName) {
        super(context);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/templateclassifier/" + templateName + ".json")) {
            templateTypes = new Gson().fromJson(IOUtils.toString(is), Map.class);
        } catch (JsonSyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected ClassificationResult run(final ExampleContext context) {
        final String srcId = context.getExample().getAttributeValue("SourceID");
        if (srcId != null && templateTypes.containsKey(srcId)) {
            return new ClassificationResult(templateTypes.get(srcId), "template");
        } else {
            return null;
        }
    }

}
