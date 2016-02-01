package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;

public class ParseTreeFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private static final List<String> VERB_TAGS = Arrays.asList("ADV", "VVFIN", "VVIMP", "VVINF", "VVIZU", "VVPP", "VAFIN", "VAIMP", "VAINF", "VAPP", "VMFIN", "VMINF", "VMPP");

    private static final List<String> SENTENCE_TAGS = Arrays.asList("S");

    private final LexicalizedParser parser;
    private final ObjectTextPreprocessor objectTextPreprocessor;

    public ParseTreeFeatureGenerator() throws IOException {
        parser = LexicalizedParser.loadModel("germanPCFG.ser.gz");

        objectTextPreprocessor = ObjectTextPreprocessor.getDefaultPreprocessor();

    }

    @Override
    protected void runGenerator(final DoorsObject element) {

        final String objectText = objectTextPreprocessor.preprocessTextToString(element.getObjectText());

        for (final String line : objectText.split("\n")) {

            final DocumentPreprocessor preprocessor = new DocumentPreprocessor(new StringReader(line));

            for (final List<HasWord> x : preprocessor) {
                final Tree tree = parser.apply(x);

                traverseTree(tree, t -> {
                    processTree(t);
                    return true;
                });

            }
        }

    }

    private void traverseTree(final Tree tree, final Function<Tree, Boolean> f) {
        if (f.apply(tree)) {
            for (final Tree child : tree.children()) {
                traverseTree(child, f);
            }
        }
    }

    private void processTree(final Tree tree) {
        buildVerbFeatures(tree);
        // buildWordGroupFeatures(tree);
    }

    private void buildVerbFeatures(final Tree tree) {
        if (SENTENCE_TAGS.contains(tree.label().toString())) {
            final List<String> b = new ArrayList<>();
            final List<String> b2 = new ArrayList<>();
            traverseTree(tree, t -> {
                if (t != tree && SENTENCE_TAGS.contains(t.label().toString())) {
                    return false;
                } else if (t.numChildren() == 1 && t.getChild(0).isLeaf() && VERB_TAGS.contains(t.label().toString())) {
                    b.add(t.getChild(0).label().toString());
                    if (t.label().toString().startsWith("VV")) {
                        b2.add("X");
                    } else {
                        b2.add(t.getChild(0).label().toString());
                    }
                    return true;
                } else {
                    return true;
                }
            });
            emitFeature(StringUtils.join(b, " ").trim().toLowerCase());
            emitFeature(StringUtils.join(b2, " ").trim().toLowerCase());
        }

    }

    private void buildWordGroupFeatures(final Tree tree) {
        final List<String> words = new ArrayList<>();
        final List<String> words2 = new ArrayList<>();

        for (final Tree t : tree.children()) {
            if (t.numChildren() == 1 && t.getChild(0).isLeaf() && !t.label().toString().startsWith("$")) {
                words.add(t.getChild(0).label().toString());
                if (t.label().toString().equals("NN") || t.label().toString().equals("NE")) {
                    words2.add("X");
                } else {
                    words2.add(t.getChild(0).label().toString());
                }
            }
        }
        if (words.size() > 0) {
            for (final List<String> p : permute(words, 0, words.size() - 1)) {
                emitFeature(StringUtils.join(p, " ").trim().toLowerCase());
            }
        }
        if (words2.size() > 0) {
            for (final List<String> p : permute(words2, 0, words2.size() - 1)) {
                emitFeature(StringUtils.join(p, " ").trim().toLowerCase());
            }
        }
    }

    private List<List<String>> permute(final List<String> words, final int from, final int to) {
        if (from == to) {
            return Arrays.asList(Arrays.asList(""), Arrays.asList(words.get(from)));
        } else {
            final List<List<String>> tailPerm = permute(words, from + 1, to);
            final List<List<String>> result = new ArrayList<>();
            result.addAll(tailPerm);
            result.addAll(tailPerm.stream().map(list -> {
                final List<String> l = new ArrayList<>();
                l.add(words.get(from));
                l.addAll(list);
                return l;
            }).collect(Collectors.toList()));
            return result;
        }
    }

}
