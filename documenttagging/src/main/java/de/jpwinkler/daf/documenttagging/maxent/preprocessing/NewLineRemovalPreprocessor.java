package de.jpwinkler.daf.documenttagging.maxent.preprocessing;

public class NewLineRemovalPreprocessor extends ObjectTextPreprocessor {

    @Override
    protected String processString(final String s) {
        return s.replaceAll("\r\n", " ").replaceAll("\n", " ");
    }

}
