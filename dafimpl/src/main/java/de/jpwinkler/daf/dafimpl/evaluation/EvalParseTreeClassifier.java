package de.jpwinkler.daf.dafimpl.evaluation;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.aliasi.classify.ConfusionMatrix;

import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleImpl;
import de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier.ParseTreeClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.parsetreeclassifier.ParseTreeClassifier;

public class EvalParseTreeClassifier {

    public static void main(final String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

        final ParseTreeClassifier classifier = new ParseTreeClassifier(ClassifierContext.getInstance());

        final Workbook wb = WorkbookFactory.create(new File("temp/cnn_dataset_raw.xlsx"));

        final Sheet sheet = wb.getSheetAt(0);

        final ConfusionMatrix confusionMatrix = new ConfusionMatrix(new String[] { "requirement", "information" });

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            final String clazz = sheet.getRow(i).getCell(2).getStringCellValue().trim();
            final String example = sheet.getRow(i).getCell(7).getStringCellValue().trim();

            final ParseTreeClassificationResult result = classifier.classify(new ExampleImpl(example));

            if (result != null) {
                confusionMatrix.increment(clazz, result.getObjectType());
                System.out.println(clazz + " " + example);
            }
        }

        System.out.println(confusionMatrix);

    }

}
