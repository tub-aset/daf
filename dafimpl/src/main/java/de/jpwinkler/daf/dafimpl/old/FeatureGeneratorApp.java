package de.jpwinkler.daf.dafimpl.old;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree.ParseTreeFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree.SentenceFragment;
import de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree.SkeletonFragment;
import de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree.VerbFragment;

public class FeatureGeneratorApp {

    public static void main(final String[] args) throws IOException {
        final ParseTreeFeatureGenerator featureGenerator = new ParseTreeFeatureGenerator();
        featureGenerator.addParseTreeFeatureGeneratorFragment(new SentenceFragment());
        featureGenerator.addParseTreeFeatureGeneratorFragment(new VerbFragment());
        featureGenerator.addParseTreeFeatureGeneratorFragment(new SkeletonFragment());

        final DoorsObject object = CSVFactory.eINSTANCE.createDoorsObject();
        object.setObjectText("Lieferungen oder Leistungen des Auftragnehmers dürfen FOSS nur nach Zustimmung durch den Auftraggeber und unter Einhaltung des in Anhang 2 beschriebenen Verfahrens enthalten.");
        final Map<String, Integer> features = new HashMap<>();
        featureGenerator.getFeatures(object, features);

        for (final Entry<String, Integer> feature : features.entrySet()) {
            System.out.println(feature.getValue() + " " + feature.getKey());
        }
    }
}
