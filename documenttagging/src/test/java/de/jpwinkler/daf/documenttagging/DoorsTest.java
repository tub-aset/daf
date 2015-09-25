package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.doors.preprocessing.DoorsModulePreprocessor;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChainBuilder;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.KatzBackoff;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.NoSmoothing;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import opennlp.maxent.GIS;

public class DoorsTest {

    private static final String[] TEST_MODULE_NAMES = new String[] { "maxent/slh-wwc.csv", "maxent/slh-wl.csv", "maxent/slh-wl2.csv" };

    private static final String PROD_BASE_PATH = "C:/WORK/DOORS/export/pod/FO_functions_only_tagged";

    private static final File RESULT_CACHE_FILE = new File("resultCache");

    private static final String[] PROD_MODULE_NAMES = new String[] {
            "ACAP_217_Content.csv",
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

    private Map<String, DoorsModule> testModules;
    private Map<String, DoorsModule> prodModules;

    @Before
    public void setupClass() throws IOException, CSVParseException {
        testModules = new LinkedHashMap<>();
        prodModules = new LinkedHashMap<>();

        final ModuleCSVParser moduleCSVParser = new ModuleCSVParser();
        final DoorsModulePreprocessor preprocessor = DoorsModulePreprocessor.getDefaultPreprocessor();

        for (final String testModuleName : TEST_MODULE_NAMES) {
            final DoorsModule module = moduleCSVParser.parseCSV(getClass().getResourceAsStream(testModuleName));
            preprocessor.preprocessModule(module);
            testModules.put(new File(testModuleName).getName(), module);
        }

        for (final String prodModuleName : PROD_MODULE_NAMES) {
            final DoorsModule module = moduleCSVParser.parseCSV(new File(PROD_BASE_PATH, prodModuleName));
            preprocessor.preprocessModule(module);
            prodModules.put(prodModuleName, module);
        }

        GIS.PRINT_MESSAGES = false;
    }

    @Test
    public void test4() throws Exception {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final List<DocumentAccessor<DoorsTreeNode>> documentAccessors = prodModules.values().stream().map(m -> new DoorsDocumentAccessor(m)).collect(Collectors.toList());
        final DocumentAccessor<DoorsTreeNode> documentToTag = documentAccessors.get(1);

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg1 = new MaxEntRecursiveViterbiAlgorithm<DoorsTreeNode>(generator, documentAccessors, new KatzBackoff<>(0, 0.7), GrowRateFunction.LINEAR, 100, 0);
        final TaggedDocument<DoorsTreeNode, String> algResult1 = alg1.tagDocument(documentToTag);
        final ConfusionMatrix<String> confusionMatrix1 = new ConfusionMatrix<>(algResult1);

        // final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg2 = new
        // SimpleMaxEntAlgorithm<DoorsTreeNode>(generator, documentAccessors,
        // 1000, 0);
        // final TaggedDocument<DoorsTreeNode, String> algResult2 =
        // alg2.tagDocument(documentToTag);
        // final ConfusionMatrix<String> confusionMatrix2 = new
        // ConfusionMatrix<>(algResult2);

        System.out.println(confusionMatrix1.toStringEvaluationMetrics());
        // System.out.println(confusionMatrix2.toStringEvaluationMetrics());
    }

    // @Test
    public void test3() throws Exception {


    }

    // @Test
    public void testMarkovChainBuilder() {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>(new NoSmoothing<>(), GrowRateFunction.LINEAR);

        for (final DoorsModule module : prodModules.values()) {
            hyperMarkovChainBuilder.addAll(new DoorsDocumentAccessor(module), o -> generator.getOutcome(o), t -> !t.isEmpty());
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
