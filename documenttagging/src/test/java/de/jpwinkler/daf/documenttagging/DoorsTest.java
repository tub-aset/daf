package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;

import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.dataprocessing.CSVFolderSource;
import de.jpwinkler.daf.dataprocessing.ClasspathModuleSource;
import de.jpwinkler.daf.dataprocessing.CollectorSink;
import de.jpwinkler.daf.dataprocessing.DoorsModulePipelineBuilder;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChainBuilder;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.NoSmoothing;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.SimpleMaxEntAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.TrainingDataEventStream;
import de.jpwinkler.daf.documenttagging.maxent.util.MaxentUtils;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class DoorsTest {

    private static final String[] TEST_MODULE_NAMES = new String[] { "maxent/slh-wwc.csv", "maxent/slh-wl.csv", "maxent/slh-wl2.csv" };

    private static final String PROD_BASE_PATH = "C:/WORK/DOORS/export/pod/FO_functions_only_tagged";

    private static final File RESULT_CACHE_FILE = new File("resultCache");

    private static final String[] PROD_MODULE_NAMES = new String[] {
            "BCSF_System_Content.csv",
            "HFA_system req.csv",
            "IHTM_Star3_System_Content.csv",
            "Schließung_System_Content.csv",
            "Anforderungen von AbC und AbP an Telematik.csv",
            "Anforderungen von AbC und AbP an Telematik2.csv",
            "Anforderungen von AbC und AbP an Telematik3.csv",
            "Funktionsbeschreibung AbC.csv",
            "Funktionsbeschreibung AbP.csv",
    };

    private CollectorSink testModules;
    private CollectorSink productionModules;

    @Before
    public void setupClass() throws IOException, CSVParseException {
        testModules = new DoorsModulePipelineBuilder()
                .withSource(new ClasspathModuleSource(getClass(), Arrays.asList(TEST_MODULE_NAMES)))
                .addDefaultPreprocessors().buildWithSink(new CollectorSink());
        productionModules = new DoorsModulePipelineBuilder()
                .withSource(new CSVFolderSource(PROD_BASE_PATH, true, (fn) -> Arrays.asList(PROD_MODULE_NAMES).contains(fn)))
                .addDefaultPreprocessors().buildWithSink(new CollectorSink());
        GIS.PRINT_MESSAGES = false;
    }

    // @Test
    public void test4() throws Exception {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final List<DocumentAccessor<DoorsTreeNode>> documentAccessors = productionModules.getModules().stream().map(m -> new DoorsDocumentAccessor(m)).collect(Collectors.toList());
        final DoorsDocumentAccessor documentToTag = (DoorsDocumentAccessor) documentAccessors.remove(0);

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg1 = new MaxEntRecursiveViterbiAlgorithm<DoorsTreeNode>(generator, documentAccessors, new NoSmoothing<>(), GrowRateFunction.LINEAR, 200, 0);
        final TaggedDocument<DoorsTreeNode, String> algResult1 = alg1.tagDocument(documentToTag);
        final ConfusionMatrix<String> confusionMatrix1 = new ConfusionMatrix<>(algResult1);

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg2 = new SimpleMaxEntAlgorithm<DoorsTreeNode>(generator, documentAccessors, 1000, 0);
        final TaggedDocument<DoorsTreeNode, String> algResult2 = alg2.tagDocument(documentToTag);
        final ConfusionMatrix<String> confusionMatrix2 = new ConfusionMatrix<>(algResult2);

        final SimpleModuleWriter writer = new SimpleModuleWriter(System.out);

        writer.setObjectStringFunction(o -> o.getObjectIdentifier());
        writer.setObjectAnnotationFunction(new Function<DoorsObject, String>() {
            @Override
            public String apply(final DoorsObject o) {
                if (algResult1.getActualTag(o).equals(algResult1.getPredictedTag(o))) {
                    return "||| ok: " + algResult1.getPredictedTag(o);
                } else {
                    return "||| actual: " + algResult1.getActualTag(o) + ", rv: " + algResult1.getPredictedTag(o) + ", me: " + algResult2.getPredictedTag(o);
                }
            }
        });

        writer.writeModule(documentToTag.getDoorsModule());

        System.out.println(confusionMatrix1.toStringEvaluationMetrics());
        System.out.println(confusionMatrix2.toStringEvaluationMetrics());
    }

    // @Test
    public void test3() throws Exception {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final List<DocumentAccessor<DoorsTreeNode>> documentAccessors = productionModules.getModules().stream().map(m -> new DoorsDocumentAccessor(m)).collect(Collectors.toList());
        final DoorsDocumentAccessor documentToTag = (DoorsDocumentAccessor) documentAccessors.remove(1);

        final List<DoorsTreeNode> trainingElements = new ArrayList<>();
        for (final DocumentAccessor<DoorsTreeNode> documentAccessor : documentAccessors) {
            documentAccessor.visit(e -> trainingElements.add(e));
        }

        System.out.println("PERSISTENT_CONDITION training objects:");
        for (final DoorsTreeNode n : trainingElements) {
            if ("PERSISTENT_CONDITION".equals(generator.getOutcome(n))) {
                System.out.println(Arrays.toString(generator.getContextualPredicates(n, true)));
            }
        }

        System.out.println();
        System.out.println("TRIGGER training objects:");
        for (final DoorsTreeNode n : trainingElements) {
            if ("TRIGGER".equals(generator.getOutcome(n))) {
                System.out.println(Arrays.toString(generator.getContextualPredicates(n, true)));
            }
        }

        final GISModel model = GIS.trainModel(new TrainingDataEventStream<>(generator, trainingElements), 200, 0);

        final DoorsObject object = (DoorsObject) documentToTag.getDoorsModule().getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0);

        final String[] contextualPredicates = generator.getContextualPredicates(object, false);

        final double[] eval = model.eval(contextualPredicates);

        final double p1 = eval[model.getIndex("PERSISTENT_CONDITION")];
        final double p2 = eval[model.getIndex("TRIGGER")];

        System.out.println("predicates for TRIGGER");
        System.out.println(MaxentUtils.printMostInfluencingPredicates(model, "TRIGGER"));
        System.out.println("predicates for PERSISTENT_CONDITION");
        System.out.println(MaxentUtils.printMostInfluencingPredicates(model, "PERSISTENT_CONDITION"));

        System.out.println(MaxentUtils.printMostLikelyOutcomes((String[]) model.getDataStructures()[2], eval, eval.length));

        System.out.println("predicates: " + Arrays.toString(contextualPredicates));

        System.out.println("persistent condition: " + p1);
        System.out.println("trigger: " + p2);
    }

    // @Test
    public void testMarkovChainBuilder() throws IOException {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>(new NoSmoothing<>(), GrowRateFunction.LINEAR);

        for (final DoorsModule m : productionModules.getModules()) {
            if (!m.getName().equals("Schließung_System_Content")) {
                hyperMarkovChainBuilder.addAll(new DoorsDocumentAccessor(m), o -> generator.getOutcome(o), t -> !t.isEmpty());
            } else {
                System.out.println("skipping " + m.getName());
            }
        }

        System.out.println(hyperMarkovChainBuilder.build().getDOT(0.0));

    }

    private void printEvaluationMetrics(final ConfusionMatrix<String> simpleMaxEntConfusionMatrix, final ConfusionMatrix<String> maxEntRecursiveViterbiConfusionMatrix) {
        System.out.println(String.format("simple macro p/r/f1: %f  %f  %f", simpleMaxEntConfusionMatrix.getMacroPrecision(), simpleMaxEntConfusionMatrix.getMacroRecall(), simpleMaxEntConfusionMatrix.getMacroF1Score()));
        System.out.println(String.format("    rv macro p/r/f1: %f  %f  %f", maxEntRecursiveViterbiConfusionMatrix.getMacroPrecision(), maxEntRecursiveViterbiConfusionMatrix.getMacroRecall(), maxEntRecursiveViterbiConfusionMatrix.getMacroF1Score()));
        System.out.println(String.format("simple micro p/r/f1: %f  %f  %f", simpleMaxEntConfusionMatrix.getMicroPrecision(), simpleMaxEntConfusionMatrix.getMicroRecall(), simpleMaxEntConfusionMatrix.getMicroF1Score()));
        System.out.println(String.format("    rv micro p/r/f1: %f  %f  %f", maxEntRecursiveViterbiConfusionMatrix.getMicroPrecision(), maxEntRecursiveViterbiConfusionMatrix.getMicroRecall(), maxEntRecursiveViterbiConfusionMatrix.getMicroF1Score()));

        System.out.println(simpleMaxEntConfusionMatrix.toString());
        System.out.println(maxEntRecursiveViterbiConfusionMatrix.toString());
    }

}
