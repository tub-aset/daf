package de.jpwinkler.daf.reqinfclassifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import edu.stanford.nlp.trees.Tree;

public class DoorsObjectContext {

    private final DoorsObject doorsObject;

    private final String text;
    private String preprocessedText;

    private List<String> lines;
    private List<String> preprocessedLines;

    private final ClassifierContext context;


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

}
