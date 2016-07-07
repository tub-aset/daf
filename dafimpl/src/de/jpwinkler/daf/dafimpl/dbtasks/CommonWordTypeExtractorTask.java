package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.NounPopularity;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.trees.Tree;

public class CommonWordTypeExtractorTask {

    private static class Pass extends ObjectCSVPass {
        private final ClassifierContext context;
        private final SyntacticClassifier classifier;
        private final Map<String, Map<String, Counter>> nouns = new HashMap<>();

        public Pass() {
            context = ClassifierContext.getInstance();
            classifier = new SyntacticClassifier(context);
        }

        private double calculateNounDocumentDistribution(final String noun) {
            final Map<String, Counter> nounMap = nouns.get(noun);
            final int sum = nounMap.values().stream().mapToInt(c -> c.get()).sum();
            final int max = nounMap.values().stream().mapToInt(c -> c.get()).max().getAsInt();

            return (double) sum / max;
        }

        private void extractWords(final Tree t) {
            for (final Tree child : t.getChildrenAsList()) {
                if (child.isLeaf()) {
                    if (t.label().toString().startsWith("NN")) {
                        addNoun(child.label().toString());
                    }
                } else {
                    extractWords(child);
                }
            }
        }

        private void addNoun(final String noun) {
            Map<String, Counter> nounMap = nouns.get(noun);
            if (nounMap == null) {
                nounMap = new HashMap<>();
                nouns.put(noun, nounMap);
            }
            Counter nounDocumentCounter = nounMap.get(getModule().getFullName());
            if (nounDocumentCounter == null) {
                nounDocumentCounter = new Counter();
                nounMap.put(getModule().getFullName(), nounDocumentCounter);
            }
            nounDocumentCounter.inc();
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String srcId = object.getAttributes().get("SourceID");
            if (srcId != null && !srcId.contains("STLH-") && !srcId.contains("SB-") && classifier.classify(object).startsWith("sentence")) {
                final List<Tree> parsedSentences = context.parseTrees(context.preprocess(object.getText()));

                parsedSentences.forEach(t -> extractWords(t));
            }
        }

        @Override
        public void postprocess() {
            final List<String> sortedNouns = new ArrayList<>(nouns.keySet());
            Collections.sort(sortedNouns, (o1, o2) -> (int) Math.signum(calculateNounDocumentDistribution(o1) - calculateNounDocumentDistribution(o2)));
            final List<NounPopularity> nounPopularities = new ArrayList<>();
            for (final String noun : sortedNouns) {
                final NounPopularity np = new NounPopularity(noun, calculateNounDocumentDistribution(noun), nouns.get(noun).values().stream().mapToInt(c -> c.get()).sum());
                nounPopularities.add(np);
            }
            try {
                IOUtils.writeStringToFile(new GsonBuilder().setPrettyPrinting().create().toJson(nounPopularities), "nouns.json", "UTF-8");
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
