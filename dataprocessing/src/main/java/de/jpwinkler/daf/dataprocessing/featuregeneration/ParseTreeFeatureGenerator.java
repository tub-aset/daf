package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;
import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;

public class ParseTreeFeatureGenerator extends FeatureGenerator<SimpleDoorsObject, String> {

    private final LexicalizedParser parser;
    // private final PTBTokenizerFactory<CoreLabel> tokenizerFactory;
    private final ObjectTextPreprocessor objectTextPreprocessor;

    public ParseTreeFeatureGenerator() throws IOException {
        parser = LexicalizedParser.loadModel("germanPCFG.ser.gz");

        objectTextPreprocessor = new ObjectTextPreprocessor(PatternProgram.compile(FileUtils.readFileToString(new File("preprocess.pp"))));

    }

    @Override
    protected void runGenerator(final SimpleDoorsObject element) {

        final String objectText = objectTextPreprocessor.preprocessTextToString(element.getObjectText());

        for (final String line : objectText.split("\n")) {

            final DocumentPreprocessor preprocessor = new DocumentPreprocessor(new StringReader(line));

            for (final List<HasWord> x : preprocessor) {
                final Tree tree = parser.apply(x);

                tree.forEach(t -> {
                    processTree(t);
                });
            }
        }

    }

    private void processTree(final Tree t) {
        if (t.isLeaf()) {
            emitFeature(t.label().toString());
        } else {
            for (int i1 = 0; i1 < t.numChildren(); i1++) {
                for (int i2 = i1; i2 < t.numChildren(); i2++) {
                    final StringBuilder builder = new StringBuilder();
                    builder.append(t.label());
                    builder.append("(");
                    for (int p = i1; p <= i2; p++) {
                        builder.append(t.getChild(p).label());
                        if (t.getChild(p).numChildren() == 1 && t.getChild(p).getChild(0).isLeaf()) {
                            builder.append("(");
                            builder.append(t.getChild(p).getChild(0).label());
                            builder.append(")");
                        }
                        builder.append(",");
                    }
                    builder.append(")");
                    emitFeature(builder.toString());
                }
            }
        }

    }

}
