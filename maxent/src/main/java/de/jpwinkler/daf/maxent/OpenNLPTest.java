package de.jpwinkler.daf.maxent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.maxent.features.impl.ASILFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.FOObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.ObjectTypeFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialCharacterFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.SpecialTokenFeatureGenerator;
import de.jpwinkler.daf.maxent.features.impl.WordFeatureGenerator;
import de.jpwinkler.daf.maxent.preprocessing.CompoundSplitterPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.NewLineRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.StopwordRemovalPreprocessor;
import de.jpwinkler.daf.maxent.preprocessing.WordStemmerPreprocessor;
import de.jpwinkler.daf.maxent.util.MaxentUtils;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class OpenNLPTest {

    final MaxentDataGenerator generator;

    public OpenNLPTest() {
        generator = new MaxentDataGenerator();
        generator.setOutcomeFunction(object -> object.getAttributes().get("pod_tags"));
        generator.setIgnoreObjectsWithoutOutcome(true);
        generator.addPreprocessor(new NewLineRemovalPreprocessor());
        generator.addPreprocessor(new CompoundSplitterPreprocessor());
        generator.addPreprocessor(new WordStemmerPreprocessor());
        generator.addPreprocessor(new StopwordRemovalPreprocessor());
        generator.addFeatureGenerator(new ASILFeatureGenerator());
        generator.addFeatureGenerator(new ObjectTypeFeatureGenerator());
        generator.addFeatureGenerator(new FOObjectTypeFeatureGenerator());
        // generator.addFeatureGenerator(new NeighborhoodFeatureGenerator());
        generator.addFeatureGenerator(new SpecialTokenFeatureGenerator());
        generator.addFeatureGenerator(new SpecialCharacterFeatureGenerator());
        generator.addFeatureGenerator(new WordFeatureGenerator());
    }

    public void run() throws IOException, CSVParseException {

        final DoorsModule wwc222 = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\WWC222_system_req.csv"));
        final DoorsModule as = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\AS_system_req.csv"));
        final DoorsModule testModule = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\SDR222_system_req.csv"));

        final GISModel model = GIS.trainModel(new DoorsModuleEventStream(generator, wwc222, as));

        generator.setIgnoreObjectsWithoutOutcome(false);

        final Map<DoorsObject, double[]> results = new HashMap<>();

        testModule.accept(new DoorsTreeNodeVisitor() {
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

        writer.writeModule(testModule);

        writer.close();
    }

    public static void main(final String[] args) throws IOException, CSVParseException {
        new OpenNLPTest().run();
    }

}
