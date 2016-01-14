package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;
import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import de.jpwinkler.libs.stringprocessing.tokens.Token;

public class WordFeatureGenerator extends FeatureGenerator<SimpleDoorsObject, String> {

    private final int maxNGramLength;
    private final int minWordLength;

    private final ObjectTextPreprocessor objectTextPreprocessor;

    public WordFeatureGenerator(final int maxNGramLength, final int minWordLength) throws IOException {
        this.maxNGramLength = maxNGramLength;
        this.minWordLength = minWordLength;
        objectTextPreprocessor = new ObjectTextPreprocessor(PatternProgram.compile(FileUtils.readFileToString(new File("preprocess.pp"))));
    }

    @Override
    protected void runGenerator(final SimpleDoorsObject element) {

        final List<Token> preprocessedText = objectTextPreprocessor.preprocessText(element.getObjectText());

        final Token[] words = preprocessedText.stream().filter(s -> s.getString().length() >= minWordLength).toArray(i -> new Token[i]);

        for (int nGramLength = 1; nGramLength <= maxNGramLength; nGramLength++) {
            for (int i = 0; i < words.length - nGramLength + 1; i++) {
                final StringBuilder sb = new StringBuilder();
                for (int word = 0; word < nGramLength; word++) {
                    if (word > 0) {
                        sb.append(" ");
                    }
                    sb.append(words[i + word].getString());
                }
                emitFeature(sb.toString());
            }

        }

    }

}
