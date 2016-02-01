package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.libs.stringprocessing.tokens.Token;

public class WordFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private final int maxNGramLength;
    private final int minWordLength;

    private final ObjectTextPreprocessor objectTextPreprocessor;

    public WordFeatureGenerator(final int maxNGramLength, final int minWordLength) throws IOException {
        this.maxNGramLength = maxNGramLength;
        this.minWordLength = minWordLength;
        objectTextPreprocessor = ObjectTextPreprocessor.getDefaultPreprocessor();
    }

    @Override
    protected void runGenerator(final DoorsObject element) {

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
