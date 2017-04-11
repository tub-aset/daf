package de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier;

import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import edu.stanford.nlp.trees.Tree;

public class ParseTreeClassificationResult extends ClassificationResult {

    private Tree matchedSubTree;

    public Tree getMatchedSubTree() {
        return matchedSubTree;
    }

    public void setMatchedSubTree(final Tree matchedSubTree) {
        this.matchedSubTree = matchedSubTree;
    }

}
