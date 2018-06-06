package de.jpwinkler.daf.dafimpl.cluster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class MakeClusterDataSetTask {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withQuoteMode(QuoteMode.ALL)
            .withRecordSeparator("\n");

    private static class Pass extends ObjectCSVPass {

        private final Set<String> writtenObjects = new HashSet<>();
        private final CSVPrinter writer;

        public Pass() throws IOException {
            writer = new CSVPrinter(new OutputStreamWriter(new FileOutputStream(new File("temp/cluster_dataset.txt"))), FORMAT);
        }

        @Override
        public void postprocessModule(final DBModule module) {
            System.out.println(module.getFullName());
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading()) {
                return;
            }

            final String text = object.getText();

            if (writtenObjects.contains(text)) {
                return;
            }
            writtenObjects.add(text);

            final String ot = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            if (ot == null || ot.isEmpty()) {
                return;
            }
            try {
                writer.printRecord(ot, text);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
