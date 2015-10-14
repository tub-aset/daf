package de.jpwinkler.daf.documenttagging.recursiveviterbi;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms.AbstractAlgorithm;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms.BruteForceAlgorithm;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms.RecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.Scenario;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.ScenarioGenerator;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.ScenarioResult;

public class ScenarioTest {

    private AbstractAlgorithm bruteForceAlgorithm, recursiveViterbiAlgorithm;

    @Before
    public void initialize() {
        bruteForceAlgorithm = new BruteForceAlgorithm();
        recursiveViterbiAlgorithm = new RecursiveViterbiAlgorithm();
    }

    @Test
    public void testRandomScenarios() {
        final ScenarioGenerator generator = new ScenarioGenerator(8, 2, 4, 3, 20);

        for (int i = 1; i <= 100; i++) {
            System.out.println("testing scenario " + i);
            testScenario(generator.generateScenario());
        }
    }

    private void testScenario(final String scenarioName) throws JsonSyntaxException, IOException {
        final Gson gson = new Gson();
        final Scenario scenario = gson.fromJson(IOUtils.toString(this.getClass().getResourceAsStream(scenarioName)), Scenario.class);
        scenario.getTree().finalizeTree();
        testScenario(scenario);
    }

    private void testScenario(final Scenario scenario) {
        final ScenarioResult result1 = bruteForceAlgorithm.run(scenario);
        final ScenarioResult result2 = recursiveViterbiAlgorithm.run(scenario);

        Assert.assertEquals("Results are not equal.", result1, result2);
    }

}
