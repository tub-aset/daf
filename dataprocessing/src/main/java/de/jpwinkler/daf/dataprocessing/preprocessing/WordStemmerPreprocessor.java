package de.jpwinkler.daf.dataprocessing.preprocessing;

import org.apache.commons.lang.StringUtils;
import org.tartarus.snowball.ext.germanStemmer;

public class WordStemmerPreprocessor extends ObjectTextPreprocessor {
    private final germanStemmer germanStemmer = new germanStemmer();

    @Override
    protected String preprocessString(final String string) {
        final String[] split = string.split(" ");
        for (int i = 0; i < split.length; i++) {
            germanStemmer.setCurrent(split[i]);
            germanStemmer.stem();
            split[i] = germanStemmer.getCurrent();
        }
        return StringUtils.join(split, " ");
    }

}
