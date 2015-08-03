package de.jpwinkler.daf.documenttagging;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestConfusionMatrix {

    private ConfusionMatrix<String> matrix;

    @Before
    public void setup() {
        final List<String> classes = Arrays.asList("A", "B", "C");

        matrix = new ConfusionMatrix<>(classes);
        matrix.set("A", "A", 32);
        matrix.set("A", "B", 1);
        matrix.set("A", "C", 5);

        matrix.set("B", "A", 2);
        matrix.set("B", "B", 13);
        matrix.set("B", "C", 13);

        matrix.set("C", "A", 3);
        matrix.set("C", "B", 6);
        matrix.set("C", "C", 27);
    }

    @Test
    public void testGet() {

        assertEquals(32, matrix.get("A", "A"));
        assertEquals(1, matrix.get("A", "B"));
        assertEquals(5, matrix.get("A", "C"));
        assertEquals(2, matrix.get("B", "A"));
        assertEquals(13, matrix.get("B", "B"));
        assertEquals(13, matrix.get("B", "C"));
        assertEquals(3, matrix.get("C", "A"));
        assertEquals(6, matrix.get("C", "B"));
        assertEquals(27, matrix.get("C", "C"));

    }

    @Test
    public void testPrecision() {
        assertEquals(16.0 / 19.0, matrix.getPrecision("A"), 0.000001);
        assertEquals(13.0 / 28.0, matrix.getPrecision("B"), 0.000001);
        assertEquals(3.0 / 4.0, matrix.getPrecision("C"), 0.000001);
    }

    @Test
    public void testRecall() {
        assertEquals(32.0 / 37.0, matrix.getRecall("A"), 0.000001);
        assertEquals(13.0 / 20.0, matrix.getRecall("B"), 0.000001);
        assertEquals(3.0 / 5.0, matrix.getRecall("C"), 0.000001);
    }

    @Test
    public void testF1Score() {
        assertEquals(64.0 / 75.0, matrix.getF1Score("A"), 0.000001);
        assertEquals(13.0 / 24.0, matrix.getF1Score("B"), 0.000001);
        assertEquals(2.0 / 3.0, matrix.getF1Score("C"), 0.000001);
    }

    @Test
    public void testMicroPrecision() {
        assertEquals(12.0 / 17.0, matrix.getMicroPrecision(), 0.000001);
    }

    @Test
    public void testMicroRecall() {
        assertEquals(12.0 / 17.0, matrix.getMicroRecall(), 0.000001);
    }

    @Test
    public void testMacroPrecision() {
        assertEquals(547.0 / 798.0, matrix.getMacroPrecision(), 0.000001);
    }

    @Test
    public void testMacroRecall() {
        assertEquals(313.0 / 444.0, matrix.getMacroRecall(), 0.000001);
    }

    @Test
    public void testMicroF1Score() {
        assertEquals(12.0 / 17.0, matrix.getMicroF1Score(), 0.000001);
    }

    @Test
    public void testMacroF1Score() {
        assertEquals(0.6950726897, matrix.getMacroF1Score(), 0.000001);
    }

}
