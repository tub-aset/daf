package de.jpwinkler.daf.reqinfclassifier;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier.ParseTreeClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier.ParseTreeClassifier;

public class ParseTreeClassifierTest {

    private final List<String> examples = Arrays.asList(
            "Das System muss innerhalb von 10 Sekunden reagieren.",
            "Das hier beschriebene System muss innerhalb von 10 Sekunden reagieren.",
            "Das System soll auf Schäden untersucht werden.",
            "Der Durchmesser der Leitung beträgt 3x0,75mm.",
            "Die HHS Last wird abgeschaltet.",
            "Die Blockiererkennung ist freigegeben (siehe RRB-2271ff).",
            "Die Funktion muss mit allen Beleuchtungsvarianten abgesichert werden.",
            "Ein Hinweis muss angezeigt werden, wenn ein Überholverbot endet.");

    @Test
    public void parseTreeClassifierTest() {

        final ParseTreeClassifier classifier = new ParseTreeClassifier(ClassifierContext.getInstance());

        for (final String e : examples) {
            final ParseTreeClassificationResult result = classifier.classify(new ExampleImpl(e));

            if (result != null) {
                System.out.println(result + ": " + result.getMatchedSubTree());
            } else {
                System.out.println("null");
            }
        }

    }

}
