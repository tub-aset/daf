package de.jpwinkler.daf.dafimpl.cnn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class MakeRNNDatasetTask {

    private static class Pass extends ObjectCSVPass {

        private int i = 0;
        private final List<String> rnnData = new ArrayList<>();

        @Override
        protected void processObject(final DoorsObject object) {
            final String text = object.getText();
            rnnData.add(text);
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

                writerW2V = new PrintWriter(new FileOutputStream("temp/rnn-body-functions.txt"));
                for (final String s : rnnData) {
                    writerW2V.write(s + "\n");
                }
                writerW2V.close();

            } catch (final FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body/B3 - Functions")).withSource(new FolderSource("/Body/B3.1 - Functions")).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();

    }

}
