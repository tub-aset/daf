package de.jpwinkler.daf.reqinfclassifier.templateclassifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class TemplateClassifier extends Classifier<String> {

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
    protected String run(final ExampleContext context) {
        final String srcId = context.getExample().getKey();
        if (srcId != null) {
            return templateTypes.get(srcId);
        } else {
            return null;
        }
    }

}
