package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class GuessLanguageTask {

    private static final Logger LOGGER = Logger.getLogger(GuessLanguageTask.class.getName());

    private static class Pass extends ObjectCSVPass {

        private Map<String, Integer> langs = new HashMap<>();
        private Counter total = new Counter();

        @Override
        public void preprocessModule(final DBModule module) {
            final Iterator<DBTag> iterator = getModule().getTags().iterator();
            while (iterator.hasNext()) {
                final DBTag tag = iterator.next();
                if (tag.getName().startsWith("lang:")) {
                    iterator.remove();
                }
            }
            langs = new HashMap<>();
            total = new Counter();
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (!object.getText().isEmpty()) {
                try {
                    Detector detector;
                    detector = DetectorFactory.create();
                    detector.append(object.getText());
                    final String detect = detector.detect();
                    if (langs.containsKey(detect)) {
                        langs.put(detect, langs.get(detect) + 1);
                    } else {
                        langs.put(detect, 1);
                    }
                    total.inc();
                } catch (final LangDetectException e) {
                }
            }
        }

        @Override
        public void postprocessModule(final DBModule module) {
            if (langs.size() > 0) {
                final List<String> langsSorted = new ArrayList<>(langs.keySet());
                Collections.sort(langsSorted, new Comparator<String>() {
                    @Override
                    public int compare(final String o1, final String o2) {
                        return -langs.get(o1).compareTo(langs.get(o2));
                    }
                });
                if ((double) langs.get(langsSorted.get(0)) > total.get() * 0.9) {
                    getDatabaseInterface().addTag(getModule(), "lang:" + langsSorted.get(0));
                    LOGGER.info(getModule().getFullName() + " " + langsSorted.get(0));
                } else {
                    LOGGER.info(getModule().getFullName() + " multi (closest: " + langsSorted.get(0) + ")");
                }
            }
            saveDatabase();
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException, LangDetectException {
        DetectorFactory.loadProfile("profiles");
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
