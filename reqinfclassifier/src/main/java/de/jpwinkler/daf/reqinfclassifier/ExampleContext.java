package de.jpwinkler.daf.reqinfclassifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.stanford.nlp.trees.Tree;

public class ExampleContext {

    private final Example example;

    private String preprocessedText;
    private String convNetPreprocessedText;

    private List<String> lines;
    private List<String> preprocessedLines;

    private final ClassifierContext context;

    private final Map<String, Object> properties = new HashMap<>();

    public ExampleContext(final Example example, final ClassifierContext context) {
        this.example = example;
        this.context = context;
    }

    public Example getExample() {
        return example;
    }

    public List<Tree> getTrees(final String text) {
        return context.parseTrees(text);
    }

    public String getPreprocessedText() {
        if (preprocessedText == null) {
            preprocessedText = context.preprocess(example.getText());
        }
        return preprocessedText;
    }

    public String getConvNetPreprocessedText() {
        if (convNetPreprocessedText == null) {
            convNetPreprocessedText = context.convNetPreprocess(example.getText());
        }
        return convNetPreprocessedText;
    }

    public List<String> getLines() {
        if (lines == null) {
            lines = Arrays.asList(example.getText().split("\n")).stream().map(l -> l.trim()).filter(l -> !l.isEmpty()).collect(Collectors.toList());
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
