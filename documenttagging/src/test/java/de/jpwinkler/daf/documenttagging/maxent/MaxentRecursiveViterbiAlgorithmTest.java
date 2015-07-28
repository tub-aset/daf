package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class MaxentRecursiveViterbiAlgorithmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws IOException, CSVParseException {
        final MaxentDataGenerator generator = MaxentDataGenerator.getDefaultGenerator();
        generator.setTraining(true);

        final DoorsModule wwc = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("slh-wwc.csv"));
        final DoorsModule wl = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("slh-wl.csv"));
        final GISModel model = GIS.trainModel(new DoorsModuleEventStream(generator, wwc), 10, 0);

        final MaxentRecursiveViterbiAlgorithm algorithm = new MaxentRecursiveViterbiAlgorithm(model, generator);

        algorithm.setProgressMonitor((current, max) -> System.out.println(current + "/" + max));

        final Map<DoorsTreeNode, String> result = algorithm.recursiveViterbi(wl);

        final SimpleModuleWriter writer = new SimpleModuleWriter(System.out);
        writer.setObjectAnnotationFunction(o -> result.get(o));

        writer.writeModule(wl);
        writer.close();
    }

}
