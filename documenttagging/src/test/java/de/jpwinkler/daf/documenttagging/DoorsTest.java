package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.doors.preprocessing.DoorsModulePreprocessor;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.SimpleMaxEntAlgorithm;
import opennlp.maxent.GIS;

public class DoorsTest {

    private Map<String, DoorsModule> modulesToTest;
    private DoorsModule wwcTest;
    private DoorsModule wlTest;
    private DoorsModule asProd;
    private DoorsModule wwcProd;

    @Before
    public void setupClass() throws IOException, CSVParseException {
        modulesToTest = new HashMap<>();
        wwcTest = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wwc.csv"));
        wlTest = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wl.csv"));
        asProd = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\AS_system_req.CSV"));
        wwcProd = new ModuleCSVParser().parseCSV(new File("C:/WORK\\DOORS\\export\\pod\\WWC222_system_req.CSV"));

        modulesToTest.put("wwctest", wwcTest);
        modulesToTest.put("wltest", wlTest);
        // modulesToTest.put("as222prod", asProd);

        // modulesToTest.put("wwc222", wwcProd);

        final DoorsModulePreprocessor preprocessor = DoorsModulePreprocessor.getDefaultPreprocessor();

        for (final Entry<String, DoorsModule> e : modulesToTest.entrySet()) {
            final DoorsModule module = e.getValue();
            preprocessor.preprocessModule(module);
        }

        GIS.PRINT_MESSAGES = false;
    }

    @Test
    public void test1() throws IOException, CSVParseException {

        // final DoorsModule sdrProd = new ModuleCSVParser().parseCSV(new
        // File("C:\\WORK\\DOORS\\export\\pod\\SDR222_system_req.CSV"));

        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator();

        for (final Entry<String, DoorsModule> e : modulesToTest.entrySet()) {
            final DoorsModule module = e.getValue();

            final DocumentTaggingAlgorithm<DoorsTreeNode, String> simpleMaxEntAlgorithm = new SimpleMaxEntAlgorithm<>(generator, Arrays.asList(new DoorsDocumentAccessor(module)));
            final TaggedDocument<DoorsTreeNode, String> simpleMaxEntTags = simpleMaxEntAlgorithm.tagDocument(new DoorsDocumentAccessor(module));

            final DocumentTaggingAlgorithm<DoorsTreeNode, String> maxEntRecursiveViterbiAlgorithm = new MaxEntRecursiveViterbiAlgorithm<>(generator, Arrays.asList(new DoorsDocumentAccessor(module)));
            final TaggedDocument<DoorsTreeNode, String> maxEntRecursiveViterbiTags = maxEntRecursiveViterbiAlgorithm.tagDocument(new DoorsDocumentAccessor(module));

            final ConfusionMatrix<String> simpleMaxEntConfusionMatrix = new ConfusionMatrix<>(simpleMaxEntTags);
            final ConfusionMatrix<String> maxEntRecursiveViterbiConfusionMatrix = new ConfusionMatrix<>(maxEntRecursiveViterbiTags);

            System.out.println(String.format("Document name: %s", e.getKey()));

            printEvaluationMetrics(simpleMaxEntConfusionMatrix, maxEntRecursiveViterbiConfusionMatrix);

        }

    }

    @Test
    public void test2() throws IOException {

        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator();

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg1 = new SimpleMaxEntAlgorithm<>(generator, Arrays.asList(new DoorsDocumentAccessor(wwcProd)));
        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg2 = new MaxEntRecursiveViterbiAlgorithm<>(generator, Arrays.asList(new DoorsDocumentAccessor(wwcProd)));

        final TaggedDocument<DoorsTreeNode, String> alg1result = alg1.tagDocument(new DoorsDocumentAccessor(asProd));
        final TaggedDocument<DoorsTreeNode, String> alg2result = alg2.tagDocument(new DoorsDocumentAccessor(asProd));

        printEvaluationMetrics(new ConfusionMatrix<>(alg1result), new ConfusionMatrix<>(alg2result));

        final SimpleModuleWriter simpleModuleWriter = new SimpleModuleWriter(System.out);
        simpleModuleWriter.setObjectAnnotationFunction(o -> "predicted: " + alg2result.getPredictedTag(o) + ", actual: " + alg2result.getActualTag(o));
        simpleModuleWriter.writeModule(wlTest);
        simpleModuleWriter.close();

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
