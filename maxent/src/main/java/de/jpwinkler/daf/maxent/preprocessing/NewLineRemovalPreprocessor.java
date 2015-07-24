package de.jpwinkler.daf.maxent.preprocessing;

public class NewLineRemovalPreprocessor extends ObjectTextPreprocessor {

    @Override
    protected String processString(final String s) {
        return s.replaceAll("\r\n", " ").replaceAll("\n", " ");
    }

}
