package de.jpwinkler.daf.reqinfclassifier;

import org.junit.Test;

import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;

public class ClassifierTest {

    @Test
    public void testConvNetClassifier() {
        final ConvNetClassifier classifier = new ConvNetClassifier(ClassifierContext.getInstance());

        final ConvNetClassificationResult result = classifier.classify(new ExampleImpl("Das System muss entsprechend der bestimmungen in xyz funktionieren."));
        System.out.println(result.getObjectType());
        System.out.println(result.getFractionOfKnownWords());
        System.out.println(result.getProbabilities());
        System.out.println(result.getSentenceMarkup());
    }

    @Test
    public void testClusterClassifier() {
        // final ClusterClassifier classifier = new ClusterClassifier(ClassifierContext.getInstance());

        // final ClusterClassificationResult result = classifier.classify(new ExampleImpl("die funktion innenlicht ist in der systembeschreibung außenlicht beschrieben ."));

        // System.out.println(result);
    }

    // @Test
    // public void multiSentenceClassifierTest() {
    // final MultiSentenceClassifier classifier = new
    // MultiSentenceClassifier(ClassifierContext.getInstance(), new
    // ConvNetClassifier(ClassifierContext.getInstance()));
    //
    // final MultiSentenceClassificationResult result = classifier.classify(new
    // ExampleImpl("Die Anforderung muss zwingend umgesetzt werden. Weitere Details siehe Kapitel 2."));
    //
    // System.out.println(result);
    //
    // }
}
