package de.jpwinkler.daf.fap5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.codebeamerrules.CodeBeamerConstants;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.util.DoorsModuleMap;

public class UpdateExcelOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(UpdateExcelOp.class.getName());

    private short percentFormat;

    @Override
    public ModelObject execute() {

        // final Map<String, CodeBeamerModel> models = new HashMap<>();
        final DoorsModuleMap models = new DoorsModuleMap();

        for (final CodeBeamerModel model : getModels()) {
            models.put(model);
        }

        try {
            final String sourceExcelFile = getStringVariable("sourceExcelFile");
            final String targetExcelFile = getStringVariable("targetExcelFile");
            final String backupExcelFile = getStringVariable("backupExcelFile");

            if (backupExcelFile != null && new File(backupExcelFile).exists()) {
                throw new RuntimeException("Backup file already exists.");
            }

            if (sourceExcelFile == null || !new File(sourceExcelFile).exists()) {
                throw new RuntimeException("Source file does not exist or no source file given.");
            }

            if (targetExcelFile == null) {
                throw new RuntimeException("No target file given.");
            }

            if (sourceExcelFile.equalsIgnoreCase(targetExcelFile) && backupExcelFile == null) {
                throw new RuntimeException("Source file and target file are the same, but no backup file given.");
            }

            if (backupExcelFile != null) {
                FileUtils.copyFile(new File(sourceExcelFile), new File(backupExcelFile));
            }

            final Workbook workbook = new XSSFWorkbook(new FileInputStream(sourceExcelFile));

            percentFormat = workbook.createDataFormat().getFormat("0 %");

            final Sheet sheet = workbook.getSheet("FAP5");

            final int sourcePathColumn = findRow(sheet, "Doors-Zielstruktur", "Allgemein", "Quell-Pfad");
            final int sourceModuleColumn = findRow(sheet, "Doors-Zielstruktur", "Allgemein", "Modul");
            final int reqCountColumn = findRow(sheet, "Doors-Zielstruktur", "Inhalt", "Requirements");
            final int todoCountColumn = findRow(sheet, "Doors-Zielstruktur", "Inhalt", "todo");
            final int maturityOpenColumn = findRow(sheet, "Doors-Zielstruktur", "Reifegrad", "open");
            final int maturitySpecifiedColumn = findRow(sheet, "Doors-Zielstruktur", "Reifegrad", "specified");
            final int maturityFollowUpColumn = findRow(sheet, "Doors-Zielstruktur", "Reifegrad", "follow_up");
            final int maturityFollopwUpHashTagsColumn = findRow(sheet, "Doors-Zielstruktur", "Reifegrad", "follow_up_###");
            final int maturityAgreedColumn = findRow(sheet, "Doors-Zielstruktur", "Reifegrad", "agreed");
            final int emptyObjectTypeCountColumn = findRow(sheet, "Doors-Zielstruktur", "Deklarations-Fehler", "Fehlende Attributierung");
            final int requirementAsHeadingCountColumn = findRow(sheet, "Doors-Zielstruktur", "Deklarations-Fehler", "Heading");
            final int InformationWithLinkCountColumn = findRow(sheet, "Doors-Zielstruktur", "Deklarations-Fehler", "mit Link");
            final int requirementWithoutLinkCountColumn = findRow(sheet, "Doors-Zielstruktur", "Deklarations-Fehler", "ohne Link");
            final int sumColumn = findRow(sheet, "Doors-Zielstruktur", "Deklarations-Fehler", "Summe");

            final int targetPathColumn = findRow(sheet, "Inbox", "Allgemein", "Ziel-Pfad");
            final int targetModuleColumn = findRow(sheet, "Inbox", "Allgemein", "Ziel-Modulname");

            final int inboxReqCountColumn = findRow(sheet, "Inbox", "Allgemein", "Anzahl");
            final int inboxAcceptanceEmptyColumn = findRow(sheet, "Inbox", "Acceptance Status", "\"\"");
            final int inboxAcceptanceDeletedReqColumn = findRow(sheet, "Inbox", "Acceptance Status", "deleted");
            final int inboxAcceptanceChangedReqColumn = findRow(sheet, "Inbox", "Acceptance Status", "changed");
            final int inboxAcceptanceToClarifyColumn = findRow(sheet, "Inbox", "Acceptance Status", "clarify");
            final int inboxAcceptanceAgreedColumn = findRow(sheet, "Inbox", "Acceptance Status", "agreed");

            final int verifiedReqCountColumn = findRow(sheet, "Verified", "Allgemein", "Anzahl");
            final int verifiedAcceptanceEmptyColumn = findRow(sheet, "Verified", "Acceptance Status", "\"\"");
            final int verifiedAcceptanceDeletedReqColumn = findRow(sheet, "Verified", "Acceptance Status", "deleted");
            final int verifiedAcceptanceChangedReqColumn = findRow(sheet, "Verified", "Acceptance Status", "changed");
            final int verifiedAcceptanceToClarifyColumn = findRow(sheet, "Verified", "Acceptance Status", "clarify");
            final int verifiedAcceptancePartlyAgreedColumn = findRow(sheet, "Verified", "Acceptance Status", "partly agreed");

            // works, but is pretty ugly.
            final int verifiedAcceptanceAgreedColumn = verifiedAcceptancePartlyAgreedColumn + 1;

            for (final Row row : sheet) {
                if (row.getCell(sourcePathColumn) != null && row.getCell(sourceModuleColumn) != null) {
                    final String fullSourceDocumentName = row.getCell(sourcePathColumn).getStringCellValue() + "/" + row.getCell(sourceModuleColumn).getStringCellValue();
                    final CodeBeamerModel sourceModel = models.get(fullSourceDocumentName);
                    if (sourceModel != null) {
                        final Integer reqCount = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);
                        updateRowCell(row, reqCountColumn, reqCount);
                        updateRowCell(row, todoCountColumn, sourceModel.getIntMetric(CodeBeamerConstants.METRIC_OPEN_TODOS));
                        if (sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT) > 0) {
                            updateRowCellPercent(row, maturityOpenColumn, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT) / reqCount);
                            updateRowCellPercent(row, maturitySpecifiedColumn, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT) / reqCount);
                            updateRowCellPercent(row, maturityFollowUpColumn, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT) / reqCount);
                            updateRowCellPercent(row, maturityFollopwUpHashTagsColumn, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT) / reqCount);
                            updateRowCellPercent(row, maturityAgreedColumn, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT) / reqCount);
                        }
                        final Integer emptyObjectType = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE);
                        final Integer requirementAsHeading = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT);
                        final Integer informationWithLink = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK);
                        final Integer requirementWithoutLink = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK);
                        updateRowCell(row, emptyObjectTypeCountColumn, emptyObjectType);
                        updateRowCell(row, requirementAsHeadingCountColumn, requirementAsHeading);
                        updateRowCell(row, InformationWithLinkCountColumn, informationWithLink);
                        updateRowCell(row, requirementWithoutLinkCountColumn, requirementWithoutLink);

                        updateRowCell(row, sumColumn, emptyObjectType + requirementAsHeading + informationWithLink + requirementWithoutLink);
                    }
                }

                if (row.getCell(targetPathColumn) != null && row.getCell(targetModuleColumn) != null) {
                    final String fullTargetDocumentName = row.getCell(targetPathColumn).getStringCellValue() + "/" + row.getCell(targetModuleColumn).getStringCellValue();
                    final CodeBeamerModel inboxTargetModel = models.get(fullTargetDocumentName);
                    if (inboxTargetModel != null) {
                        final Integer reqCount = inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);

                        updateRowCell(row, inboxReqCountColumn, inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT));
                        updateRowCellPercent(row, inboxAcceptanceEmptyColumn, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_NONE_COUNT) / reqCount);
                        updateRowCellPercent(row, inboxAcceptanceDeletedReqColumn, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_DELETED_REQ_COUNT) / reqCount);
                        updateRowCellPercent(row, inboxAcceptanceChangedReqColumn, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CHANGED_REQ_COUNT) / reqCount);
                        updateRowCellPercent(row, inboxAcceptanceToClarifyColumn, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_TO_CLARIFY_COUNT) / reqCount);
                        updateRowCellPercent(row, inboxAcceptanceAgreedColumn, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_AGREED_COUNT) / reqCount);
                    }

                    final CodeBeamerModel verifiedTargetModel = models.get(fullTargetDocumentName.replace("/Inbox/", "/Verified/"));
                    if (verifiedTargetModel != null) {
                        final Integer reqCount = verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);

                        updateRowCell(row, verifiedReqCountColumn, verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT));
                        updateRowCellPercent(row, verifiedAcceptanceEmptyColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_NONE_COUNT) / reqCount);
                        updateRowCellPercent(row, verifiedAcceptanceDeletedReqColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_DELETED_REQ_COUNT) / reqCount);
                        updateRowCellPercent(row, verifiedAcceptanceChangedReqColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CHANGED_REQ_COUNT) / reqCount);
                        updateRowCellPercent(row, verifiedAcceptanceToClarifyColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_TO_CLARIFY_COUNT) / reqCount);
                        updateRowCellPercent(row, verifiedAcceptancePartlyAgreedColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_PARTLY_AGREED_COUNT) / reqCount);
                        updateRowCellPercent(row, verifiedAcceptanceAgreedColumn, (double) verifiedTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_AGREED_COUNT) / reqCount);
                    }

                }
            }

            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetExcelFile))) {
                workbook.write(outputStream);
            }

            workbook.close();

        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }

    private int findRow(final Sheet sheet, final String row1text, final String row2text, final String row3text) {
        return findRow(sheet, row1text, row2text, row3text, (s1, s2) -> s1.contains(s2));
    }

    private int findRow(final Sheet sheet, final String row1text, final String row2text, final String row3text, final BiPredicate<String, String> predicate) {
        String currentRow1Text = "";
        String currentRow2Text = "";
        String currentRow3Text = "";

        int columnIndex = 0;
        while (sheet.getRow(2).getCell(columnIndex) != null) {
            currentRow1Text = sheet.getRow(0).getCell(columnIndex) != null && !sheet.getRow(0).getCell(columnIndex).getStringCellValue().isEmpty() ? sheet.getRow(0).getCell(columnIndex).getStringCellValue() : currentRow1Text;
            currentRow2Text = sheet.getRow(1).getCell(columnIndex) != null && !sheet.getRow(1).getCell(columnIndex).getStringCellValue().isEmpty() ? sheet.getRow(1).getCell(columnIndex).getStringCellValue() : currentRow2Text;
            currentRow3Text = sheet.getRow(2).getCell(columnIndex) != null && !sheet.getRow(2).getCell(columnIndex).getStringCellValue().isEmpty() ? sheet.getRow(2).getCell(columnIndex).getStringCellValue() : currentRow3Text;

            if ((row1text.isEmpty() || predicate.test(currentRow1Text, row1text)) &&
                    (row2text.isEmpty() || predicate.test(currentRow2Text, row2text)) &&
                    (row3text.isEmpty() || predicate.test(currentRow3Text, row3text))) {
                return columnIndex;
            }
            columnIndex++;
        }
        return -1;
    }

    private void updateRowCell(final Row row, final int i, final int newValue) {
        Cell cell = row.getCell(i);
        if (cell == null) {
            cell = row.createCell(i);
        }
        cell.setCellValue(newValue);

    }

    private void updateRowCellPercent(final Row row, final int i, final double newValue) {
        Cell cell = row.getCell(i);
        if (cell == null) {
            cell = row.createCell(i);
        }
        cell.setCellValue(newValue);
        cell.getCellStyle().setDataFormat(percentFormat);
    }

    private List<CodeBeamerModel> getModels() {
        return getParameter("codeBeamerModels");
    }

}
