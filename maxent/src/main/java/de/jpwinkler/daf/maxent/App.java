package de.jpwinkler.daf.maxent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.datumbox.common.dataobjects.Dataset;
import com.datumbox.common.dataobjects.Record;
import com.datumbox.common.persistentstorage.ConfigurationFactory;
import com.datumbox.common.persistentstorage.inmemory.InMemoryConfiguration;
import com.datumbox.framework.machinelearning.classification.MaximumEntropy;

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

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(final String[] args) throws IOException, CSVParseException
    {
        final InMemoryConfiguration dbConf = (InMemoryConfiguration) ConfigurationFactory.INMEMORY.getConfiguration();
        dbConf.setOutputFolder("C:\\WORK\\temp\\datumbox");
        final Dataset trainingDataset = new Dataset(dbConf);
        final Dataset testDataset = new Dataset(dbConf);

        final DoorsModule wwc222 = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\WWC222_system_req.csv"));
        // final DoorsModule as = new ModuleCSVParser().parseCSV(new
        // File("C:\\WORK\\DOORS\\export\\pod\\AS_system_req.csv"));
        final DoorsModule testModule = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\SDR222_system_req.csv"));

        populateDataSet(trainingDataset, wwc222, true);
        // populateDataSet(trainingDataset, as, true);
        final Map<Record, DoorsObject> recordMap = populateDataSet(testDataset, testModule, false);
        final Map<DoorsObject, Record> inverseRecordMap = new HashMap<>();

        trainingDataset.recalculateMeta();

        final MaximumEntropy.TrainingParameters trainingParameters = new MaximumEntropy.TrainingParameters();
        trainingParameters.setTotalIterations(2);

        final MaximumEntropy maximumEntropy = new MaximumEntropy("Modules", dbConf);

        System.out.println("training");
        maximumEntropy.fit(trainingDataset, trainingParameters);

        // final MaximumEntropy.ValidationMetrics validationMetrics =
        // maximumEntropy.validate(trainingDataset);

        // System.out.println("Classifier Statistics: " +
        // PHPfunctions.var_export(validationMetrics));
        System.out.println("predicting");
        maximumEntropy.predict(testDataset);
        for (int i = 0; i < testDataset.getRecordNumber(); i++) {
            final Record record = testDataset.get(i);
            inverseRecordMap.put(recordMap.get(record), record);
            if (record.getYPredicted() != null) {
                recordMap.get(record).getAttributes().put("pod_tags", record.getYPredicted().toString());
            } else {
                recordMap.get(record).getAttributes().put("pod_tags", "");
            }
        }

        try (SimpleModuleWriter writer = new SimpleModuleWriter(System.out)) {
            writer.setObjectAnnotationFunction(t -> {

                final Record record = inverseRecordMap.get(t);
                return record.getYPredicted().toString() + "(" + record.getYPredictedProbabilities().getDouble(record.getYPredicted()) + ")";
            });
            writer.writeModule(testModule);
        }

        // System.out.println(PHPfunctions.var_export(validationMetrics));

        maximumEntropy.getModelParameters().getLambdas().forEach((l, d) -> {
            if (d > 0.3) {
                System.out.println(l + " " + d);
            }
        });

        maximumEntropy.erase();
        trainingDataset.erase();
        testDataset.erase();

    }

    private static Map<Record, DoorsObject> populateDataSet(final Dataset dataset, final DoorsModule module, final boolean isTrainingDataSet) throws IOException, CSVParseException {
        final MaxentDataGenerator generator = new MaxentDataGenerator();
        generator.setOutcomeFunction(object -> object.getAttributes().get("pod_tags"));
        generator.setIgnoreObjectsWithoutOutcome(isTrainingDataSet);
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

        final Map<MaxentDataElement, DoorsObject> recordMap = new HashMap<>();

        generator.run(module, (o, r) -> {
            dataset.add(new Record);
            recordMap.put(r, o);
        });

        return recordMap;
    }

}
