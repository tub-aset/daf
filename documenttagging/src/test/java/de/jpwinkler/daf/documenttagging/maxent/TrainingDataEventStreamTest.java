package de.jpwinkler.daf.documenttagging.maxent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TrainingDataEventStreamTest {

    @Test
    public void test() throws IOException {
        final List<Object> trainingData = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object());

        final MaxEntPredicateGenerator<Object> dataGenerator = mock(MaxEntPredicateGenerator.class);
        when(dataGenerator.getOutcome(trainingData.get(0))).thenReturn("");
        when(dataGenerator.getOutcome(trainingData.get(1))).thenReturn("a");
        when(dataGenerator.getOutcome(trainingData.get(2))).thenReturn("b");
        when(dataGenerator.getOutcome(trainingData.get(3))).thenReturn("");
        when(dataGenerator.getOutcome(trainingData.get(4))).thenReturn(null);
        when(dataGenerator.getOutcome(trainingData.get(5))).thenReturn("c");
        when(dataGenerator.getOutcome(trainingData.get(6))).thenReturn("");

        final TrainingDataEventStream<Object> stream = new TrainingDataEventStream<>(dataGenerator, trainingData);

        assertEquals(true, stream.hasNext());
        assertEquals("a", stream.next().getOutcome());
        assertEquals(true, stream.hasNext());
        assertEquals("b", stream.next().getOutcome());
        assertEquals(true, stream.hasNext());
        assertEquals("c", stream.next().getOutcome());
        assertEquals(false, stream.hasNext());
        Assertions.assertThatThrownBy(() -> stream.next()).isInstanceOf(NoSuchElementException.class);

    }

}
