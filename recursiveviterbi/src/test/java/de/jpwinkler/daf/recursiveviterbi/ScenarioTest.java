package de.jpwinkler.daf.recursiveviterbi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.recursiveviterbi.algorithms.AbstractAlgorithm;
import de.jpwinkler.daf.recursiveviterbi.algorithms.BruteForceAlgorithm;
import de.jpwinkler.daf.recursiveviterbi.algorithms.RecursiveViterbiAlgorithm;
import de.jpwinkler.daf.recursiveviterbi.scenario.Scenario;
import de.jpwinkler.daf.recursiveviterbi.scenario.ScenarioResult;

public class ScenarioTest {

    private AbstractAlgorithm bruteForceAlgorithm, recursiveViterbiAlgorithm;

    @Before
    public void initialize() {
        bruteForceAlgorithm = new BruteForceAlgorithm();
        recursiveViterbiAlgorithm = new RecursiveViterbiAlgorithm();
    }

    @Test
    public void testScenarios() throws JsonSyntaxException, IOException {

        final Gson gson = new Gson();

        final File scenarioFolder = new File("scenarios");

        for (final File scenarioFile : scenarioFolder.listFiles()) {
            if (scenarioFile.isFile() && scenarioFile.getName().endsWith(".json")) {
                final Scenario scenario = gson.fromJson(FileUtils.readFileToString(scenarioFile), Scenario.class);
                scenario.getTree().finalizeTree();
                testScenario(scenario);
            }
        }

    }

    private void testScenario(final Scenario scenario) {
        final ScenarioResult result1 = bruteForceAlgorithm.run(scenario);
        final ScenarioResult result2 = recursiveViterbiAlgorithm.run(scenario);

        Assert.assertEquals("Results are not equal.", result1, result2);
    }

}
