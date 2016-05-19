package de.jpwinkler.daf.reqinfclassifier.syntacticclassifier;

import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;
import de.jpwinkler.daf.reqinfclassifier.utils.SentenceUtils;
import edu.stanford.nlp.trees.Tree;

public class SentenceClassifier extends Classifier<String> {

    public SentenceClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        final List<Tree> trees = context.getTrees(context.getPreprocessedText());

        if (trees.size() == 1 && SentenceUtils.isSentence(trees.get(0))) {
            return "sentence(single)";
        } else if (trees.size() > 1 && trees.stream().allMatch(s -> SentenceUtils.isSentence(s))) {
            return "sentence(multi)";
        } else if (trees.size() == 1 && SentenceUtils.containsSentence(trees.get(0))) {
            return "sentence(single_wrapped)";
        } else if (trees.size() > 1 && trees.stream().allMatch(s -> SentenceUtils.containsSentence(s))) {
            return "sentence(multi_wrapped)";
        } else {
            // final List<String> collect =
            // context.getPreprocessedLines().stream().map(l ->
            // context.getTrees(l)).filter(tl -> !tl.isEmpty()).map(tl ->
            // tl.stream().filter(t -> t.numChildren() == 1).map(t ->
            // t.getChild(0).label().toString()).collect(Collectors.toList())).map(l
            // -> StringUtils.join(l, ",")).collect(Collectors.toList());
            // return StringUtils.join(collect, "\n");
            return "other";
        }
    }

}
