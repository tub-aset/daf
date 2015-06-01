package de.jpwinkler.daf.fap5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

    private static final int START_COLUMN = 23;

    private short percentFormat;

    @Override
    public ModelObject execute() {

        final Map<String, CodeBeamerModel> models = new HashMap<>();

        for (final CodeBeamerModel model : getModels()) {
            models.put(model.getName(), model);
        }

        try {
            final String sourceExcel = getStringVariable("sourceExcel");
            final String targetExcel = getStringVariable("targetExcel");
            final Workbook workbook = new XSSFWorkbook(new File(sourceExcel));

            percentFormat = workbook.createDataFormat().getFormat("0 %");

            final Sheet sheet = workbook.getSheet("Mapping CB-Doors");

            for (final Row row : sheet) {
                final Cell cell = row.getCell(9);
                if (cell == null) {
                    continue;
                }
                final String rowDocName = cell.getStringCellValue();
                final CodeBeamerModel model = models.get(rowDocName);
                if (model != null) {
                    updateRowCell(row, START_COLUMN + 0, model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT));
                    updateRowCell(row, START_COLUMN + 1, model.getIntMetric(CodeBeamerConstants.METRIC_OPEN_TODOS));
                    if (model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT) > 0) {
                        updateRowCellPercent(row, START_COLUMN + 2, model.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN));
                        updateRowCellPercent(row, START_COLUMN + 3, model.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED));
                        updateRowCellPercent(row, START_COLUMN + 4, model.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP));
                        updateRowCellPercent(row, START_COLUMN + 5, model.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS));
                        updateRowCellPercent(row, START_COLUMN + 6, model.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED));
                    }
                    final Integer emptyObjectType = model.getIntMetric(CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE);
                    final Integer requirementAsHeading = model.getIntMetric(CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT);
                    final Integer informationWithLink = model.getIntMetric(CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK);
                    final Integer requirementWithoutLink = model.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK);
                    updateRowCell(row, START_COLUMN + 7, emptyObjectType);
                    updateRowCell(row, START_COLUMN + 8, requirementAsHeading);
                    updateRowCell(row, START_COLUMN + 9, informationWithLink);
                    updateRowCell(row, START_COLUMN + 10, requirementWithoutLink);

                    updateRowCell(row, START_COLUMN + 11, emptyObjectType + requirementAsHeading + informationWithLink + requirementWithoutLink);
                } else {
                    LOGGER.warning(String.format("No module found for module name %s.", rowDocName));
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(targetExcel)) {
                try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                    workbook.write(bos);
                }
            }

        } catch (InvalidFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
