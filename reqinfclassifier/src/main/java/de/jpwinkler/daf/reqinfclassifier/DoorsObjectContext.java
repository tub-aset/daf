package de.jpwinkler.daf.reqinfclassifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import edu.stanford.nlp.trees.Tree;

public class DoorsObjectContext {

    public static final String PROPERTY_CONVNET_CLASSIFIER_INFORMATION = "convnet_info";
    public static final String PROPERTY_CONVNET_CLASSIFIER_REQUIREMENT = "convnet_req";

    public static final String PROPERTY_REMOTE_CONVNET_CLASSIFIER_INFORMATION = "remote_convnet_info";
    public static final String PROPERTY_REMOTE_CONVNET_CLASSIFIER_REQUIREMENT = "remote_convnet_req";
    public static final String PROPERTY_REMOTE_CONVNET_CLASSIFIER_ZERO_FRAC = "remote_convnet_zero_frac";
    public static final String PROPERTY_REMOTE_CONVNET_CLASSIFIER_PREDEFINITION = "remote_convnet_predef";
    public static final String PROPERTY_REMOTE_CONVNET_CLASSIFIER_PROCESS_REQUIREMENT = "remote_convnet_process_req";

    private final DoorsObject doorsObject;

    private final String text;
    private String preprocessedText;
    private String convNetPreprocessedText;

    private List<String> lines;
    private List<String> preprocessedLines;

    private final ClassifierContext context;

    private final Map<String, Object> properties = new HashMap<>();

    public DoorsObjectContext(final DoorsObject doorsObject, final ClassifierContext context) {
        this.doorsObject = doorsObject;
        this.context = context;
        text = doorsObject.getText();
    }

    public DoorsObject getDoorsObject() {
        return doorsObject;
    }

    public List<Tree> getTrees(final String text) {
        return context.parseTrees(text);
    }

    public String getText() {
        return text;
    }

    public String getPreprocessedText() {
        if (preprocessedText == null) {
            preprocessedText = context.preprocess(getText());
        }
        return preprocessedText;
    }

    public String getConvNetPreprocessedText() {
        if (convNetPreprocessedText == null) {
            convNetPreprocessedText = context.convNetPreprocess(getText());
        }
        return convNetPreprocessedText;
    }

    public List<String> getLines() {
        if (lines == null) {
            lines = Arrays.asList(doorsObject.getText().split("\n")).stream().map(l -> l.trim()).filter(l -> !l.isEmpty()).collect(Collectors.toList());
        }
        return lines;
    }

    public List<String> getPreprocessedLines() {
        if (preprocessedLines == null) {
            preprocessedLines = getLines().stream().map(l -> context.preprocess(l)).collect(Collectors.toList());
        }
        return preprocessedLines;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
