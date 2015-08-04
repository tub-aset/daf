package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class WordPredicateGenerator extends PredicateGenerator {

    private String[] tokenizer(final String s) {
        String temp = s.toLowerCase();
        temp = temp.replaceAll("(?![äöüßa-z0-9 ])(.)", " ");
        return temp.split(" ");
    }

    @Override
    protected void runGenerator(final DoorsObject object) {
        for (final String word : tokenizer(object.getText())) {
            if (!word.isEmpty()) {
                emitPredicate(word);
            }
        }
    }

}
