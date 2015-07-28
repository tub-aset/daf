package de.jpwinkler.daf.documenttagging.recursiveviterbi;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util.ProbabilityUtils;

public class ProbabilityUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetProbabilities() {
        for (int i = 1; i < 1000; i++) {
            final BigDecimal[] probabilities = ProbabilityUtils.getProbabilities(i);
            // assertEquals(1, StatUtils.sum(probabilities), 0.001);
        }
    }

    @Test
    public void testGetDistribution() {
        for (int i = 1; i < 1000; i++) {
            final int[] distribution = ProbabilityUtils.getDistribution(i * 10, i);
            int sum = 0;
            for (int j = 0; j < distribution.length; j++) {
                sum += distribution[j];
            }
            Assert.assertEquals(sum, i * 10);
        }
    }

}
