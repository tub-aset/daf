package de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;

public class ParseTreeClassifier extends Classifier<ParseTreeClassificationResult> {

    private final Map<TregexPattern, String> patterns;

    public ParseTreeClassifier(final ClassifierContext context) {
        super(context);
        patterns = new HashMap<>();
        try {
            final List<String> lines = IOUtils.readLines(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/parsetreeclassifier/rules.txt"));
            for (final String l : lines) {
                if (!l.startsWith("#")) {

                    final String cls = l.substring(0, l.indexOf(' '));
                    final String pattern = l.substring(l.indexOf(' ') + 1);

                    patterns.put(TregexPattern.compile(pattern), cls);
                }
            }
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected ParseTreeClassificationResult run(final ExampleContext exampleContext) {


        final List<Tree> trees = exampleContext.getTrees(exampleContext.getPreprocessedText());

        final List<String> classes = trees.stream().map(t -> {
            System.out.println(t);
            for (final Entry<TregexPattern, String> e : patterns.entrySet()) {
                final TregexMatcher matcher = e.getKey().matcher(t);
                if (matcher.find()) {
                    return e.getValue();
                }
            }
            return null;
        }).distinct().collect(Collectors.toList());

        if (classes.size() == 1 && classes.get(0) != null) {
            final ParseTreeClassificationResult result = new ParseTreeClassificationResult();
            result.setClassifiedBy(ClassifiedBy.PARSE_TREE_CLASSIFIER);
            result.setObjectType(classes.get(0));
            return result;
        } else {
            return null;
        }

    }

}
