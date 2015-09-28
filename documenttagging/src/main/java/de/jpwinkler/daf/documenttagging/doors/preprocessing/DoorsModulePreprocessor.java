package de.jpwinkler.daf.documenttagging.doors.preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class DoorsModulePreprocessor {

    private static final Logger LOGGER = Logger.getLogger(DoorsModulePreprocessor.class.getName());

    private final List<DoorsObjectPreprocessor> objectPreprocessors = new ArrayList<>();

    public void addObjectPreprocessor(final DoorsObjectPreprocessor objectPreprocessor) {
        objectPreprocessors.add(objectPreprocessor);
    }

    public static DoorsModulePreprocessor getDefaultPreprocessor() {
        final DoorsModulePreprocessor doorsModulePreprocessor = new DoorsModulePreprocessor();

        doorsModulePreprocessor.addObjectPreprocessor(new IgnoreCasePreprocessor());
        doorsModulePreprocessor.addObjectPreprocessor(new SpecialCharacterRemovalPreprocessor());
        try {
            doorsModulePreprocessor.addObjectPreprocessor(new CompoundSplitterPreprocessor());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        try {
            doorsModulePreprocessor.addObjectPreprocessor(new StopwordRemovalPreprocessor(DoorsModulePreprocessor.class.getResourceAsStream("stopwords.txt")));
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        doorsModulePreprocessor.addObjectPreprocessor(new WordStemmerPreprocessor());

        return doorsModulePreprocessor;
    }

    public void preprocessModule(final DoorsModule module) {
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                for (final DoorsObjectPreprocessor objectPreprocessor : objectPreprocessors) {
                    objectPreprocessor.preprocessObject(object);
                }
                return true;
            }
        });
    }

}
