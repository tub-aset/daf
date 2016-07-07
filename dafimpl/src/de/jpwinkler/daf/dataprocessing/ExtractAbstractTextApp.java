package de.jpwinkler.daf.dataprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;
import edu.stanford.nlp.trees.Tree;

public class ExtractAbstractTextApp {

    private static final List<String> REDUCE_TAGS = Arrays.asList("NP", "NN", "ART", "ADJA");

    private static class Pass extends ObjectCSVPass {

        private final Pattern objectTypePattern = Pattern.compile("requirement|information");
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();

        private final SyntacticClassifier syntacticClassifier = new SyntacticClassifier(classifierContext);

        private final Set<Integer> writtenObjects = new HashSet<>();

        private OutputStreamWriter writer;

        private final String reduce(final Tree tree) {
            final StringBuilder sb = new StringBuilder();
            for (final Tree t : tree.children()) {
                if (REDUCE_TAGS.contains(t.label().toString())) {
                    sb.append("XX ");
                } else if (t.isLeaf()) {
                    sb.append(t.label().toString().trim() + " ");
                } else {
                    sb.append(reduce(t));
                }
            }
            return sb.toString();
        }

        private String reduce(final List<Tree> trees) {
            return StringUtils.join(trees.stream().map(t -> reduce(t)).collect(Collectors.toList()), " ");
        }

        @Override
        public void preprocess() {
            try {
                writer = new OutputStreamWriter(new FileOutputStream(new File("temp/reqinf_abstract.txt")));
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String ot = object.getAttributes().get("Object Type");
            if (ot != null && objectTypePattern.matcher(ot).matches() && object.getText() != null && !object.getText().trim().isEmpty()) {

                final String text = classifierContext.preprocess(object.getText());

                if (!text.trim().isEmpty() && syntacticClassifier.classify(object).contains("sentence(")) {
                    final List<Tree> trees = classifierContext.parseTrees(text);
                    String reducedSentences = reduce(trees);
                    while (reducedSentences.contains("XX XX")) {
                        reducedSentences = reducedSentences.replace("XX XX", "XX");
                    }
                    if (!writtenObjects.contains(reducedSentences.hashCode())) {
                        try {
                            writer.write(ot + " " + reducedSentences + "\n");
                        } catch (final IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        writtenObjects.add(reducedSentences.hashCode());
                    }
                }
            }
        }

        @Override
        public void postprocess() {
            try {
                writer.close();
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws Exception {
        new ModuleTaskBuilder().withPass(new Pass()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
