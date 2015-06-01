package de.jpwinkler.daf.fap5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

public class ExportIssueListsOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(ExportCockpitOperation.class.getName());

    private List<CodeBeamerModel> getCodeBeamerModels() {
        return getParameter("codeBeamerModels");
    }

    @Override
    public ModelObject execute() {

        for (final CodeBeamerModel model : getCodeBeamerModels()) {
            final Workbook workbook = new XSSFWorkbook();
            exportIssues(model, workbook);
            final File file = new File(getStringVariable("issueFolder"), model.getName() + ".xlsx");
            final File altfile = new File(getStringVariable("issueFolder"), model.getName() + "_new.xlsx");
            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(file))) {
                workbook.write(fos);
            } catch (final IOException e) {
                LOGGER.log(Level.WARNING, String.format("Error while saving document %s.", file.getAbsolutePath()), e);
                try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(altfile))) {
                    workbook.write(fos);
                } catch (final IOException e2) {
                    LOGGER.log(Level.SEVERE, String.format("Error while saving document %s.", altfile.getAbsolutePath()), e2);
                }
            }
        }
        return null;
    }

    private void exportIssues(final CodeBeamerModel cbm, final Workbook workbook) {
        final Sheet currentSheet = workbook.createSheet("Probleme");
        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Objekt");
        headerRow.createCell(1).setCellValue("Problem");
        for (final Issue issue : cbm.getIssues()) {
            final Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
            if (issue.getSource() != null) {
                row.createCell(0).setCellValue(issue.getSource().getObjectNumber() + " / " + issue.getSource().getObjectIdentifier());
            }
            row.createCell(1).setCellValue(issue.getIssueType());
        }
        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 1));
        currentSheet.setColumnWidth(0, 256 * 40);
        currentSheet.setColumnWidth(1, 256 * 80);
    }

}
