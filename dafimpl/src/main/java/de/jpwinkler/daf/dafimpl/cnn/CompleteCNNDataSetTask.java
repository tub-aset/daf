package de.jpwinkler.daf.dafimpl.cnn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;

public class CompleteCNNDataSetTask {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withQuoteMode(QuoteMode.ALL)
            .withRecordSeparator("\n");

    private static class Pass extends ObjectCSVPass {
        private final CSVPrinter writer;
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();

        private final Set<String> examples = new HashSet<>();

        public Pass() throws IOException {
            writer = new CSVPrinter(new OutputStreamWriter(new FileOutputStream(new File("temp/reqinf-sentence-min-completed.csv"))), FORMAT);
        }

        @Override
        public void preprocess() {
            try {
                final List<String> lines = IOUtils.readLines(new FileInputStream("temp/reqinf-sentence-min.txt"));

                lines.stream().map(s -> s.substring(s.indexOf(" ") + 1)).forEach(s -> examples.add(s));

            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void postprocessModule(final DBModule module) {
            System.out.println(module.getFullName());
            System.out.println(examples.size());
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading()) {
                return;
            }
            final String srcId = object.getAttributes().get(Attributes.SOURCE_ID);
            if (srcId == null || srcId.startsWith("STLH-") || srcId.startsWith("SB-")) {
                return;
            }

            final String text = StringUtils.join(classifierContext.convNetPreprocess(object.getText()), ' ');

            if (!examples.contains(text)) {
                return;
            }
            examples.remove(text);

            final DoorsObject parentObject = object.getParent() instanceof DoorsObject ? (DoorsObject) object.getParent() : null;
            final String parentText = parentObject != null ? StringUtils.join(classifierContext.convNetPreprocess(parentObject.getText()), ' ') : "";
            final String prevText = DoorsModuleUtil.getPreviousObject(object) != null ? StringUtils.join(classifierContext.convNetPreprocess(DoorsModuleUtil.getPreviousObject(object).getText()), ' ') : "";
            final String nextText = DoorsModuleUtil.getNextObject(object) != null ? StringUtils.join(classifierContext.convNetPreprocess(DoorsModuleUtil.getNextObject(object).getText()), ' ') : "";

            final String ot = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            try {
                writer.printRecord(ot, text, parentText, prevText, nextText);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void postprocess() {
            System.out.println(examples.size());
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
