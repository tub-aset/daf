package de.jpwinkler.daf.dataprocessing.preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.danielnaber.jwordsplitter.GermanWordSplitter;

public class CompoundSplitterPreprocessor extends ObjectTextPreprocessor {

    private final GermanWordSplitter wordSplitter;

    public CompoundSplitterPreprocessor() throws IOException {
        wordSplitter = new GermanWordSplitter(true);
    }

    @Override
    protected String preprocessString(final String string) {
        final List<String> result = new ArrayList<>();

        for (final String word : string.split(" ")) {
            if (!(word.startsWith("$$") && word.endsWith("$$"))) {
                result.addAll(wordSplitter.splitWord(word));
            }
        }

        final String r = StringUtils.join(result, " ");
        return r;
    }

}
