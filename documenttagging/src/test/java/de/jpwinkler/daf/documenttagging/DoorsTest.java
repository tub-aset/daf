package de.jpwinkler.daf.documenttagging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.maxent.MaxentDataGenerator;

public class DoorsTest {

    @Test
    public void test() throws IOException, CSVParseException {

        final DoorsModule wwc = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wwc.csv"));
        final DoorsModule wl = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("maxent/slh-wl.csv"));

        final MaxentDataGenerator internalGenerator = MaxentDataGenerator.getDefaultGenerator();
        internalGenerator.setTraining(true);

        final MaxentPredicateGenerator<DoorsTreeNode> generator = new MaxentPredicateGenerator<DoorsTreeNode>() {


            @Override
            public String getOutcome(final DoorsTreeNode element) {
                if (element instanceof DoorsObject) {
                    return internalGenerator.run((DoorsObject) element).getOutcome();
                } else {
                    return null;
                }
            }

            @Override
            public String[] getContextualPredicates(final DoorsTreeNode element) {
                if (element instanceof DoorsObject) {
                    return internalGenerator.run((DoorsObject) element).getFeatures();
                } else {
                    return null;
                }
            }
        };

        final List<DoorsTreeNode> trainingData = new ArrayList<>();

        wwc.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                trainingData.add(object);
                return true;
            }
        });

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> algo = new MaxEntRecursiveViterbiAlgorithm<>(generator, trainingData);

        final TaggedDocument<DoorsTreeNode, String> taggedDocument = algo.tagDocument(new DoorsDocumentAccessor(wl));

        final SimpleModuleWriter moduleWriter = new SimpleModuleWriter(System.out);
        moduleWriter.setObjectAnnotationFunction(o -> "predicted: " + taggedDocument.getPredictedTag(o) + ", actual: " + taggedDocument.getActualTag(o));
        moduleWriter.writeModule(wl);

        final ConfusionMatrix<String> confusionMatrix = new ConfusionMatrix<>( taggedDocument);
        System.out.println(confusionMatrix.toString());

        System.out.println();

        float totalPrecision = 0;
        float totalRecall = 0;
        int c = 0;
        for (final String tag : taggedDocument.getTags()) {
            final float precision = confusionMatrix.getPrecision(tag);
            final float recall = confusionMatrix.getRecall(tag);
            System.out.println(tag + "(precision: " + precision + ", recall: " + recall + ", f1: " + confusionMatrix.getF1Score(tag) + ")");
            totalPrecision += precision;
            totalRecall += recall;
            c++;
        }

        System.out.println("average precision: " + (totalPrecision / c));
        System.out.println("average recall: " + (totalRecall / c));
    }

}
