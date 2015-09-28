package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms.AbstractAlgorithm;

/**
 * Instances of this class describe a scenario that was either generated using
 * the {@link ScenarioGenerator} or loaded from a file. Scenarios are evaluated
 * using implementations of the abstract class {@link AbstractAlgorithm}.
 *
 * @author jonwink
 *
 */
public class Scenario {

    private TreeNode tree;

    private ProbabilityModel probabilityModel;

    /**
     * Returns the tree of this scenario.
     * 
     * @return
     */
    public TreeNode getTree() {
        return tree;
    }

    /**
     * Sets the tree of this scenario.
     * 
     * @param tree
     */
    public void setTree(final TreeNode tree) {
        this.tree = tree;
    }

    /**
     * Returns the probability model of this scenario.
     * 
     * @return
     */
    public ProbabilityModel getProbabilityModel() {
        return probabilityModel;
    }

    /**
     * Sets the probability model of this scenario.
     * 
     * @param probabilityModel
     */
    public void setProbabilityModel(final ProbabilityModel probabilityModel) {
        this.probabilityModel = probabilityModel;
    }

}
