package de.jpwinkler.daf.maxent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.maxent.util.TriMap;
import de.jpwinkler.daf.recursiveviterbi.AbstractRecursiveViterbiAlgorithm;
import opennlp.model.AbstractModel;

public class MaxentRecursiveViterbiAlgorithm extends AbstractRecursiveViterbiAlgorithm<DoorsTreeNode, String> {

    private final Map<DoorsTreeNode, String[]> records = new HashMap<>();

    private final AbstractModel model;

    private final List<String> states;

    private final TriMap<DoorsTreeNode, String, String, double[]> probabilityMap = new TriMap<>();

    private final MaxentDataGenerator generator;

    public MaxentRecursiveViterbiAlgorithm(final AbstractModel model, final MaxentDataGenerator generator) {
        this.model = model;
        states = Arrays.asList((String[]) this.model.getDataStructures()[2]);
        this.generator = generator;
    }

    @Override
    protected void prepare(final DoorsTreeNode tree) {

        generator.setTraining(false);

        generator.run((DoorsModule) tree, (o, e) -> {
            final String[] contextualPredicates = Arrays.copyOf(e.getFeatures(), e.getFeatures().length + 2);

            records.put(o, contextualPredicates);
        });

    }

    @Override
    protected List<String> getStates() {
        return states;
    }

    @Override
    protected List<DoorsTreeNode> getChildren(final DoorsTreeNode node) {
        return node.getObjects();
    }

    @Override
    protected double getProbability(final DoorsTreeNode node, final String state, final String parentState, final String previousState) {
        if (!records.containsKey(node)) {
            return 1.0;
        }
        double[] eval = probabilityMap.get(node, parentState, previousState);
        if (eval == null) {
            final String[] contextualPredicates = records.get(node);
            contextualPredicates[contextualPredicates.length - 2] = "parent_pod_tags=" + parentState;
            contextualPredicates[contextualPredicates.length - 1] = "prev_pod_tags=" + previousState;
            eval = model.eval(contextualPredicates);
            probabilityMap.put(node, parentState, previousState, eval);
        }
        return eval[model.getIndex(state)];
    }

    @Override
    protected String[] createArray(final int size) {
        return new String[size];
    }

}
