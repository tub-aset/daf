package de.jpwinkler.daf.maxent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.maxent.features.impl.ASILFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.FOObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.NeighborhoodFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.ObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialCharacterFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialTokenFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.WordFeatureGenerator;
import de.jpwinkler.daf.maxent.preprocessing.CompoundSplitterPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.NewLineRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.StopwordRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.WordStemmerPreprocessor;
import de.jpwinkler.daf.maxent.util.TriMap;
import de.jpwinkler.viterbiontrees.algorithms.viterbiabstract.AbstractRecursiveViterbiAlgorithm;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class MaxentRecursiveViterbiAlgorithm extends AbstractRecursiveViterbiAlgorithm<DoorsTreeNode, String> {

    private final Map<DoorsTreeNode, String[]> records = new HashMap<>();

    private GISModel maxentModel;

    private List<String> states;

    private final TriMap<DoorsTreeNode, String, String, double[]> probabilityMap = new TriMap<>();

    @Override
    protected void prepare(final DoorsTreeNode tree) {
        final MaxentDataGenerator generator = new MaxentDataGenerator();
        generator.setOutcomeFunction(object -> object.getAttributes().get("pod_tags"));
        generator.setIgnoreObjectsWithoutOutcome(true);
        generator.addPreprocessor(new NewLineRemovalPreprocessor());
        generator.addPreprocessor(new CompoundSplitterPreprocessor());
        generator.addPreprocessor(new WordStemmerPreprocessor());
        generator.addPreprocessor(new StopwordRemovalPreprocessor());
        generator.addFeatureGenerator(new ASILFeatureGenerator());
        generator.addFeatureGenerator(new ObjectTypeFeatureGenerator());
        generator.addFeatureGenerator(new FOObjectTypeFeatureGenerator());
        generator.addFeatureGenerator(new NeighborhoodFeatureGenerator());
        generator.addFeatureGenerator(new SpecialTokenFeatureGenerator());
        generator.addFeatureGenerator(new SpecialCharacterFeatureGenerator());
        generator.addFeatureGenerator(new WordFeatureGenerator());

        try {
            final DoorsModule wwc222 = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\WWC222_system_req.csv"));
            final DoorsModule as = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\AS_system_req.csv"));
            maxentModel = GIS.trainModel(new DoorsModuleEventStream(generator, wwc222, as));
        } catch (IOException | CSVParseException e1) {
            throw new RuntimeException(e1);
        }

        states = Arrays.asList((String[]) maxentModel.getDataStructures()[2]);

        generator.setIgnoreObjectsWithoutOutcome(false);

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
            eval = maxentModel.eval(contextualPredicates);
            probabilityMap.put(node, parentState, previousState, eval);
        }
        return eval[maxentModel.getIndex(state)];
    }

    @Override
    protected String[] createArray(final int size) {
        return new String[size];
    }

    public static void main(final String[] args) throws IOException, CSVParseException {
        final MaxentRecursiveViterbiAlgorithm algorithm = new MaxentRecursiveViterbiAlgorithm();

        final DoorsModule testModule = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\SDR222_system_req.csv"));

        final Map<DoorsTreeNode, String> result = algorithm.recursiveViterbi(testModule);

        final SimpleModuleWriter writer = new SimpleModuleWriter(System.out);
        writer.setObjectAnnotationFunction(o -> result.containsKey(o) ? result.get(o) : "");

        writer.writeModule(testModule);
        writer.close();
    }

}
