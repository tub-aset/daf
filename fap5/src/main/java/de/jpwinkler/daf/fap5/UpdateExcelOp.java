package de.jpwinkler.daf.fap5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class UpdateExcelOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(UpdateExcelOp.class.getName());

    private static final int START_COLUMN = 27;

    private short percentFormat;

    @Override
    public ModelObject execute() {

        final Map<String, CodeBeamerModel> models = new HashMap<>();

        for (final CodeBeamerModel model : getModels()) {
            models.put(model.getName(), model);
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

            final Sheet sheet = workbook.getSheet("Mapping CB-Doors");

            int startColumn = START_COLUMN;

            for (final Cell cell : sheet.getRow(2)) {
                if (cell.getStringCellValue().contains("Requirements") && cell.getStringCellValue().contains("Predefinitions")) {
                    startColumn = cell.getColumnIndex();
                    LOGGER.info(String.format("Using column %d as start column.", startColumn));
                }
            }

            for (final Row row : sheet) {
                final Cell cell = row.getCell(9);
                if (cell == null) {
                    continue;
                }
                final String rowDocName = cell.getStringCellValue();
                final CodeBeamerModel model = models.get(rowDocName);
                if (model != null) {
                    final Integer reqCount = model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);
                    updateRowCell(row, startColumn + 0, reqCount);
                    updateRowCell(row, startColumn + 1, model.getIntMetric(CodeBeamerConstants.METRIC_OPEN_TODOS));
                    if (model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT) > 0) {
                        updateRowCellPercent(row, startColumn + 2, (double) model.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT) / reqCount);
                        updateRowCellPercent(row, startColumn + 3, (double) model.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT) / reqCount);
                        updateRowCellPercent(row, startColumn + 4, (double) model.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT) / reqCount);
                        updateRowCellPercent(row, startColumn + 5, (double) model.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT) / reqCount);
                        updateRowCellPercent(row, startColumn + 6, (double) model.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT) / reqCount);
                    }
                    final Integer emptyObjectType = model.getIntMetric(CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE);
                    final Integer requirementAsHeading = model.getIntMetric(CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT);
                    final Integer informationWithLink = model.getIntMetric(CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK);
                    final Integer requirementWithoutLink = model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK);
                    updateRowCell(row, startColumn + 7, emptyObjectType);
                    updateRowCell(row, startColumn + 8, requirementAsHeading);
                    updateRowCell(row, startColumn + 9, informationWithLink);
                    updateRowCell(row, startColumn + 10, requirementWithoutLink);

                    updateRowCell(row, startColumn + 11, emptyObjectType + requirementAsHeading + informationWithLink + requirementWithoutLink);
                } else {
                    LOGGER.warning(String.format("No module found for module name %s.", rowDocName));
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
