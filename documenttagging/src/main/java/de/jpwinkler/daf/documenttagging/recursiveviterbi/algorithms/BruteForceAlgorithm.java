package de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.TreeNode;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util.BigDecimals;

public class BruteForceAlgorithm extends AbstractAlgorithm {

    private List<TreeNode> treeNodes;

    private BigDecimal greatest = BigDecimals.ZERO;

    private void permute(final int index) {
        for (int i = 0; i < getScenario().getProbabilityModel().getStateCount(); i++) {
            getCurrentStates().put(treeNodes.get(index), i + 1);
            if (!(index == treeNodes.size() - 1)) {
                permute(index + 1);
            } else {
                final BigDecimal probability = calcProbability();
                if (probability.compareTo(greatest) > 0) {
                    greatest = probability;
                    saveResult();
                }
            }
        }

    }

    @Override
    protected void solve() {

        treeNodes = new ArrayList<>();

        getScenario().getTree().accept(treeNode -> treeNodes.add(treeNode));

        greatest = BigDecimals.ZERO;

        permute(0);

    }

}
