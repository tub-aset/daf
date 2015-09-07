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
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.ScenarioResult;

public class ScenarioTest {

    private AbstractAlgorithm bruteForceAlgorithm, recursiveViterbiAlgorithm;

    @Before
    public void initialize() {
        bruteForceAlgorithm = new BruteForceAlgorithm();
        recursiveViterbiAlgorithm = new RecursiveViterbiAlgorithm();
    }

    @Test
    public void testScenario1() throws JsonSyntaxException, IOException {
        testScenario("scenario1.json");
    }

    @Test
    public void testScenario2() throws JsonSyntaxException, IOException {
        testScenario("scenario2.json");
    }

    @Test
    public void testScenario3() throws JsonSyntaxException, IOException {
        testScenario("scenario3.json");
    }

    @Test
    public void testScenario4() throws JsonSyntaxException, IOException {
        testScenario("scenario4.json");
    }

    @Test
    public void testScenario5() throws JsonSyntaxException, IOException {
        testScenario("scenario5.json");
    }

    @Test
    public void testScenario6() throws JsonSyntaxException, IOException {
        testScenario("scenario6.json");
    }

    @Test
    public void testScenario7() throws JsonSyntaxException, IOException {
        testScenario("scenario7.json");
    }

    @Test
    public void testScenario8() throws JsonSyntaxException, IOException {
        testScenario("scenario8.json");
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
