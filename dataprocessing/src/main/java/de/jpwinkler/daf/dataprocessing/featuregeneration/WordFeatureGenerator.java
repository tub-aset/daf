package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.util.Arrays;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class WordFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private final int maxNGramLength;
    private final int minWordLength;

    public WordFeatureGenerator(final int maxNGramLength, final int minWordLength) {
        this.maxNGramLength = maxNGramLength;
        this.minWordLength = minWordLength;
    }

    @Override
    protected void runGenerator(final DoorsObject element) {
        if (element instanceof DoorsObject) {

            final String[] words = Arrays.asList(element.getText().split(" ")).stream().filter(s -> s.length() >= minWordLength).toArray(i -> new String[i]);

            for (int nGramLength = 1; nGramLength <= maxNGramLength; nGramLength++) {
                for (int i = 0; i < words.length - nGramLength + 1; i++) {
                    final StringBuilder sb = new StringBuilder();
                    for (int word = 0; word < nGramLength; word++) {
                        if (word > 0) {
                            sb.append(" ");
                        }
                        sb.append(words[i + word]);
                    }
                    emitFeature(sb.toString());
                }

            }
        }

    }

}
