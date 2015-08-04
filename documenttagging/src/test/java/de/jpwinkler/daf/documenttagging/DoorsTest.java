package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.DoorsTreeNodeIterator;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;

public class DoorsTest {

    @Test
    public void test() throws IOException, CSVParseException {

        final DoorsModule wwcProd = new ModuleCSVParser().parseCSV(new File("C:/WORK\\DOORS\\export\\pod\\WWC222_system_req.CSV"));
        // final DoorsModule sdrProd = new ModuleCSVParser().parseCSV(new
        // File("C:\\WORK\\DOORS\\export\\pod\\SDR222_system_req.CSV"));
        final DoorsModule asProd = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\AS_system_req.CSV"));
        final DoorsModule wwc = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wwc.csv"));
        final DoorsModule wl = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wl.csv"));

        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator();

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> algo = new MaxEntRecursiveViterbiAlgorithm<>(generator, new DoorsTreeNodeIterator(true, asProd));

        final TaggedDocument<DoorsTreeNode, String> taggedDocument = algo.tagDocument(new DoorsDocumentAccessor(asProd));

        final SimpleModuleWriter moduleWriter = new SimpleModuleWriter(System.out);
        moduleWriter.setObjectAnnotationFunction(o -> "predicted: " + taggedDocument.getPredictedTag(o) + ", actual: " + taggedDocument.getActualTag(o));
        moduleWriter.writeModule(asProd);

        final ConfusionMatrix<String> confusionMatrix = new ConfusionMatrix<>(taggedDocument);
        System.out.println(confusionMatrix.toString());

        System.out.println();

        for (final String tag : taggedDocument.getTags()) {
            final float precision = confusionMatrix.getPrecision(tag);
            final float recall = confusionMatrix.getRecall(tag);
            System.out.println(tag + "(precision: " + precision + ", recall: " + recall + ", f1: " + confusionMatrix.getF1Score(tag) + ")");
        }

        System.out.println("macro precision: " + confusionMatrix.getMacroPrecision());
        System.out.println("macro recall: " + confusionMatrix.getMacroRecall());
        System.out.println("macro f1: " + confusionMatrix.getMacroF1Score());
        System.out.println("micro f1: " + confusionMatrix.getMicroF1Score());
    }

}
