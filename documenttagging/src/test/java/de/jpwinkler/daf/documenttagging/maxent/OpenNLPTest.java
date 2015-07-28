package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.maxent.util.MaxentUtils;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class OpenNLPTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws IOException, CSVParseException {
        final DoorsModule wwc = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("slh-wwc.csv"));
        final DoorsModule wl = new ModuleCSVParser().parseCSV(getClass().getResourceAsStream("slh-wl.csv"));

        final MaxentDataGenerator generator = MaxentDataGenerator.getDefaultGenerator();
        generator.setTraining(true);

        final GISModel model = GIS.trainModel(new DoorsModuleEventStream(generator, wwc));

        generator.setTraining(false);

        final Map<DoorsObject, double[]> results = new HashMap<>();

        wl.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                final double[] eval = model.eval(generator.run(object).getFeatures());
                object.getAttributes().put("pod_tags", model.getBestOutcome(eval));
                results.put(object, eval);
                return true;
            }
        });

        final SimpleModuleWriter writer = new SimpleModuleWriter(System.out);

        writer.setObjectAnnotationFunction(o -> MaxentUtils.printMostLikelyOutcomes((String[]) model.getDataStructures()[2], results.get(o), 3));

        writer.writeModule(wl);

        writer.close();
    }

}
