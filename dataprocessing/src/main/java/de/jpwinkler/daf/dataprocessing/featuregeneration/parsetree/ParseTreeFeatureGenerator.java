package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureGenerator;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;

public class ParseTreeFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private final LexicalizedParser parser;
    private final ObjectTextPreprocessor objectTextPreprocessor;

    private final List<ParseTreeFeatureGeneratorFragment> parseTreeFeatureGeneratorFragments = new ArrayList<>();

    public ParseTreeFeatureGenerator() throws IOException {
        parser = LexicalizedParser.loadModel("germanPCFG.ser.gz");

        objectTextPreprocessor = ObjectTextPreprocessor.getDefaultPreprocessor();

    }

    public void addParseTreeFeatureGeneratorFragment(final ParseTreeFeatureGeneratorFragment fragment) {
        parseTreeFeatureGeneratorFragments.add(fragment);
        fragment.setFeatureGenerator(this);
    }

    @Override
    protected void runGenerator(final DoorsObject element) {

        final String objectText = objectTextPreprocessor.preprocessTextToString(element.getObjectText());

        for (final String line : objectText.split("\n")) {

            final DocumentPreprocessor preprocessor = new DocumentPreprocessor(new StringReader(line));

            for (final List<HasWord> sentence : preprocessor) {
                final Tree tree = parser.apply(sentence);

                ParseTreeUtils.traverseTree(tree, t -> {
                    processTree(t);
                    return true;
                });

            }
        }

    }

    private void processTree(final Tree t) {

        for (final ParseTreeFeatureGeneratorFragment fragment : parseTreeFeatureGeneratorFragments) {
            fragment.apply(t);
        }

    }
}