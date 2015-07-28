package de.jpwinkler.daf.documenttagging.maxent.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.danielnaber.jwordsplitter.GermanWordSplitter;
import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;

public class CompoundSplitter {

    public static void main(final String[] args) throws IOException, CSVParseException {

        final String name = "WWC222_system req";
        final Set<String> words = new HashSet<>();
        final DoorsModule module = new ModuleCSVParser().parseCSV(new File("C:\\WORK\\DOORS\\export\\pod\\" + name + ".csv"));

        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                String text = object.getText();
                text = text.replaceAll("[-_:;\"„“\t'!=&'\n()*+./\\\\,<>»«°§\\[\\]]", " ").toLowerCase();
                final String[] split = text.split(" ");
                words.addAll(Arrays.asList(split));
                return true;
            }
        });

        final List<String> sortedWords = new ArrayList<>(words);
        Collections.sort(sortedWords);

        final GermanWordSplitter splitter = new GermanWordSplitter(true);

        for (final String word : sortedWords) {
            System.out.println(word + "=" + splitter.splitWord(word));
        }

        final Set<String> splittedWords = new HashSet<>();
    }

}
