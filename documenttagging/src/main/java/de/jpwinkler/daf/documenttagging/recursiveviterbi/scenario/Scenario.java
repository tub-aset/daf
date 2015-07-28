package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;


public class Scenario {

    private TreeNode tree;

    private ProbabilityModel probabilityModel;

    public TreeNode getTree() {
        return tree;
    }

    public void setTree(final TreeNode tree) {
        this.tree = tree;
    }

    public ProbabilityModel getProbabilityModel() {
        return probabilityModel;
    }

    public void setProbabilityModel(final ProbabilityModel probabilityModel) {
        this.probabilityModel = probabilityModel;
    }

}
