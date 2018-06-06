package de.jpwinkler.daf.dafimpl.cnn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;

public class MakeCNNDataSetTask {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withQuoteMode(QuoteMode.ALL)
            .withRecordSeparator("\n");

    private static class Pass extends ObjectCSVPass {
        private int inf = 0;
        private int req = 0;

        private final Set<String> writtenObjects = new HashSet<>();
        private final CSVPrinter writer;
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();

        private final Random r = new Random();
        private String currentModuleFullName;
        private final Workbook wb;
        private final Sheet sheet;

        public Pass() throws IOException {
            writer = new CSVPrinter(new OutputStreamWriter(new FileOutputStream(new File("temp/cnn_dataset.txt"))), FORMAT);
            wb = new SXSSFWorkbook(100);
            sheet = wb.createSheet();
        }

        @Override
        public void preprocessModule(final DBModule module) {
            currentModuleFullName = module.getFullName();
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
            final String srcId = object.getAttributes().get(Attributes.SOURCE_ID);
            if (srcId == null || srcId.startsWith("STLH-") || srcId.startsWith("SB-")) {
                return;
            }
            final String ot = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            if (!("requirement".equals(ot)) && !("information".equals(ot))) {
                return;
            }
            final String maturity = object.getAttributes().get("Maturity");
            if ("requirement".equals(ot) && !("agreed".equals(maturity))) {
                return;
            }

            try {
                Detector detector;
                detector = DetectorFactory.create();
                detector.append(object.getText());
                final String detect = detector.detect();
                if (!"de".equals(detect) || (detector.getProbabilities().get(0).prob < 0.9)) {
                    return;
                }
            } catch (final LangDetectException e1) {
                e1.printStackTrace();
                return;
            }

            final String text = StringUtils.join(classifierContext.convNetPreprocess(object.getText()), ' ');
            final DoorsObject parentObject = object.getParent() instanceof DoorsObject ? (DoorsObject) object.getParent() : null;
            final DoorsObject parentOfParent = parentObject != null && parentObject.getParent() instanceof DoorsObject ? (DoorsObject) parentObject.getParent() : null;
            final DoorsObject previousObject = DoorsModuleUtil.getPreviousObject(object);
            final DoorsObject secondPreviousObject = previousObject != null ? DoorsModuleUtil.getPreviousObject(previousObject) : null;
            final DoorsObject nextObject = DoorsModuleUtil.getNextObject(object);
            final DoorsObject secondNextObject = nextObject != null ? DoorsModuleUtil.getNextObject(nextObject) : null;

            final String parentText = parentObject != null ? StringUtils.join(classifierContext.convNetPreprocess(parentObject.getText()), ' ') : "";
            final String prevText = previousObject != null ? StringUtils.join(classifierContext.convNetPreprocess(DoorsModuleUtil.getPreviousObject(object).getText()), ' ') : "";
            final String nextText = nextObject != null ? StringUtils.join(classifierContext.convNetPreprocess(DoorsModuleUtil.getNextObject(object).getText()), ' ') : "";

            final String parentOfParentTextRaw = parentOfParent != null ? parentOfParent.getObjectNumber() + " " + parentOfParent.getText() : "";
            final String parentTextRaw = parentObject != null ? parentObject.getObjectNumber() + " " + parentObject.getText() : "";
            final String prevPrevTextRaw = secondPreviousObject != null ? (secondPreviousObject.isHeading() ? secondPreviousObject.getObjectNumber() + " " : "") + secondPreviousObject.getText() : "";
            final String prevTextRaw = previousObject != null ? (previousObject.isHeading() ? previousObject.getObjectNumber() + " " : "") + previousObject.getText() : "";
            final String textRaw = object.getText();
            final String nextTextRaw = nextObject != null ? (nextObject.isHeading() ? nextObject.getObjectNumber() + " " : "") + nextObject.getText() : "";
            final String nextNextTextRaw = secondNextObject != null ? (secondNextObject.isHeading() ? secondNextObject.getObjectNumber() + " " : "") + secondNextObject.getText() : "";

            if (writtenObjects.contains(text)) {
                return;
            }
            writtenObjects.add(text);

            try {
                boolean write = false;
                if ("information".equals(ot) && r.nextDouble() < 0.81915725941656421146753101598301) {
                    inf++;
                    write = true;
                }
                if ("requirement".equals(ot)) {
                    req++;
                    write = true;
                }
                if (write) {
                    writer.printRecord(ot, text, parentText, prevText, nextText);
                    final Row row = sheet.createRow(sheet.getLastRowNum() + 1);
                    row.createCell(0).setCellValue(srcId);
                    row.createCell(1).setCellValue(currentModuleFullName);
                    row.createCell(2).setCellValue(ot);
                    row.createCell(3).setCellValue(parentOfParentTextRaw);
                    row.createCell(4).setCellValue(parentTextRaw);
                    row.createCell(5).setCellValue(prevPrevTextRaw);
                    row.createCell(6).setCellValue(prevTextRaw);
                    row.createCell(7).setCellValue(textRaw);
                    row.createCell(8).setCellValue(nextTextRaw);
                    row.createCell(9).setCellValue(nextNextTextRaw);
                }
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void postprocess() {
            System.out.println(inf);
            System.out.println(req);
            System.out.println(inf + req);
            try {
                writer.close();
                final FileOutputStream f = new FileOutputStream("temp/cnn_dataset_raw.xlsx");
                wb.write(f);
                f.close();
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException, LangDetectException {
        DetectorFactory.loadProfile("profiles");
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
