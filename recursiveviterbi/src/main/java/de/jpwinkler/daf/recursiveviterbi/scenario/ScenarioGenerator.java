package de.jpwinkler.daf.recursiveviterbi.scenario;

import java.util.Random;

import de.jpwinkler.daf.recursiveviterbi.scenario.util.ProbabilityUtils;

public class ScenarioGenerator {

    private final Random random = new Random();

    private final int totalNodes;
    private final int minNodesPerLevel;
    private final int maxNodesPerLevel;
    private final int totalStates;
    private final int totalObservations;

    public ScenarioGenerator(final int totalNodes, final int minNodesPerLevel, final int maxNodesPerLevel, final int totalStates, final int totalObservations) {
        super();
        this.totalNodes = totalNodes;
        this.minNodesPerLevel = minNodesPerLevel;
        this.maxNodesPerLevel = maxNodesPerLevel;
        this.totalStates = totalStates;
        this.totalObservations = totalObservations;
    }

    private TreeNode generateTree(final int nodesLeft) {
        final TreeNode node = new TreeNode();

        final int nodesOnThisLevel = Math.min(random.nextInt(maxNodesPerLevel - minNodesPerLevel + 1) + minNodesPerLevel, nodesLeft - 1);

        if (nodesOnThisLevel > 0) {
            final int[] distribution = ProbabilityUtils.getDistribution(nodesLeft - 1, nodesOnThisLevel);
            for (int i = 0; i < nodesOnThisLevel; i++) {
                node.addChild(generateTree(distribution[i]));
            }
        }

        node.finalizeTree();
        return node;
    }

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

        for (int iState = 1; iState <= totalStates; iState++) {
            model.setObservationProbabilities(iState, ProbabilityUtils.getProbabilities(totalObservations));
        }

        return model;
    }

}
