package de.jpwinkler.daf.documenttagging.doors.maxent;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;

public class DoorsMaxEntPredicateGenerator implements MaxEntPredicateGenerator<DoorsTreeNode> {

    private final List<PredicateGenerator> predicateGenerators = new ArrayList<>();

    public void addPredicateGenerator(final PredicateGenerator predicateGenerator) {
        predicateGenerators.add(predicateGenerator);
    }

    @Override
    public String[] getContextualPredicates(final DoorsTreeNode element) {
        if (element instanceof DoorsObject) {
            final List<String> predicates = new ArrayList<>();
            for (final PredicateGenerator predicateGenerator : predicateGenerators) {
                predicates.addAll(predicateGenerator.getPredicates((DoorsObject) element));
            }
            return predicates.toArray(new String[predicates.size()]);
        } else {
            return null;
        }
    }

    @Override
    public String getOutcome(final DoorsTreeNode element) {
        if (element.getAttributes().containsKey("pod_tags")) {
            return element.getAttributes().get("pod_tags");
        } else {
            return null;
        }
    }

    public static DoorsMaxEntPredicateGenerator getDefaultGenerator() {
        final DoorsMaxEntPredicateGenerator generator = new DoorsMaxEntPredicateGenerator();
        generator.addPredicateGenerator(new AsilPredicateGenerator());
        generator.addPredicateGenerator(new FoObjectTypePredicateGenerator());
        generator.addPredicateGenerator(new NeighborhoodPredicateGenerator());
        generator.addPredicateGenerator(new ObjectTypePredicateGenerator());
        generator.addPredicateGenerator(new SpecialCharacterPredicateGenerator());
        generator.addPredicateGenerator(new SpecialTokenPredicateGenerator());
        generator.addPredicateGenerator(new WordPredicateGenerator());

        return generator;
    }
}
