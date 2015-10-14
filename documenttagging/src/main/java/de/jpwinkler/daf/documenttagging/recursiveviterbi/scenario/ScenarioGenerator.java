package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.util.Random;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util.ProbabilityUtils;

/**
 * This class generates random scenarios.
 *
 * @author jonwink
 *
 */
public class ScenarioGenerator {

    private final Random random = new Random();

    private final int totalNodes;
    private final int minNodesPerParentNode;
    private final int maxNodesPerParentNode;
    private final int totalStates;
    private final int totalObservations;

    /**
     * Instantiates a new instance of this class which is able to generate
     * scenarios according to the given parameters.
     *
     * @param totalNodes
     *            The total amount of nodes that each generated tree should
     *            have.
     * @param minNodesPerParentNode
     *            The minimum amount of nodes per parent node. Please note that
     *            subtrees with less than <code>minNodesPerLevel</code> may
     *            still be generated, because the used algorithm tries to
     *            exactly match the total amount of nodes given by
     *            <code>totalNodes</code>.
     * @param maxNodesPerParentNode
     *            The maximum amount of nodes per parent node.
     * @param totalStates
     *            The total amount of states.
     * @param totalObservations
     *            The total amount of observations.
     */
    public ScenarioGenerator(final int totalNodes, final int minNodesPerParentNode, final int maxNodesPerParentNode, final int totalStates, final int totalObservations) {
        super();
        this.totalNodes = totalNodes;
        this.minNodesPerParentNode = minNodesPerParentNode;
        this.maxNodesPerParentNode = maxNodesPerParentNode;
        this.totalStates = totalStates;
        this.totalObservations = totalObservations;
    }

    private TreeNode generateTree(final int nodesLeft) {
        final TreeNode node = new TreeNode();

        final int nodesOnThisLevel = Math.min(random.nextInt(maxNodesPerParentNode - minNodesPerParentNode + 1) + minNodesPerParentNode, nodesLeft - 1);

        if (nodesOnThisLevel > 0) {
            final int[] distribution = ProbabilityUtils.getDistribution(nodesLeft - 1, nodesOnThisLevel);
            for (int i = 0; i < nodesOnThisLevel; i++) {
                node.addChild(generateTree(distribution[i]));
            }
        }

        node.finalizeTree();
        return node;
    }

    /**
     * Please note that calling this algorithm inevitably destroys all things in
     * the universe. (Generates a scenario.)
     *
     * @return
     */
    public Scenario generateScenario() {
        final Scenario scenario = new Scenario();

        final TreeNode generateTree = generateTree(totalNodes);
        scenario.setTree(generateTree);

        final ProbabilityModel probabilityModel = generateProbabilityModel();
        generateTree.accept(node -> node.setObservation(random.nextInt(probabilityModel.getObservationCount())));
        scenario.setProbabilityModel(probabilityModel);

        return scenario;
    }

    private ProbabilityModel generateProbabilityModel() {
        final ProbabilityModel model = new ProbabilityModel(totalStates, totalObservations);

        for (int iParent = 0; iParent <= totalStates; iParent++) {
            for (int iPrecedessor = 0; iPrecedessor <= totalStates; iPrecedessor++) {
                model.setTransitionProbabilities(iParent, iPrecedessor, ProbabilityUtils.getProbabilities(totalStates));
            }
        }

        for (int iObservation = 0; iObservation < totalObservations; iObservation++) {
            model.setStateObservationProbabilities(iObservation, ProbabilityUtils.getProbabilities(totalStates));
        }

        model.setStateProbabilities(ProbabilityUtils.getProbabilities(totalStates));

        return model;
    }

}
