package de.jpwinkler.daf.dafimpl.cnn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;

public class MakeWord2VecTask {

    private static class Pass extends ObjectCSVPass {

        private final Set<String> writtenObjects = new HashSet<>();
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();
        private int i = 0;
        private final List<String> w2vData = new ArrayList<>();

        @Override
        protected void processObject(final DoorsObject object) {
            final String text = StringUtils.join(classifierContext.convNetPreprocess(object.getText()), ' ');

            if (writtenObjects.contains(text)) {
                return;
            }

            writtenObjects.add(text);
            w2vData.add(object.getText());
            i++;
        }

        @Override
        public void preprocessModule(final DBModule module) {
            System.out.println(module.getFullName());
        }

        @Override
        public void postprocessModule(final DBModule module) {
            System.out.println(i);
        }

        @Override
        public void postprocess() {
            try {
                PrintWriter writerW2V;

                writerW2V = new PrintWriter(new FileOutputStream("temp/w2v-chassis.txt"));
                for (final String s : w2vData) {
                    writerW2V.write(s + "\n");
                }
                writerW2V.close();

                Collections.shuffle(w2vData);

                writerW2V = new PrintWriter(new FileOutputStream("temp/w2v-shuffled.txt"));
                for (final String s : w2vData) {
                    final String[] sublines = s.split("\n");
                    for (final String subline : sublines) {
                        if (!subline.trim().isEmpty()) {
                            writerW2V.write(subline.trim() + "\n");
                        }
                    }
                }
                writerW2V.close();
            } catch (final FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Chassis")).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();

    }

}
