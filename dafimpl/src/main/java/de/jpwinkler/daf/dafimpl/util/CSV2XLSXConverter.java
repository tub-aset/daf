package de.jpwinkler.daf.dafimpl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.CSVParseException;

public class CSV2XLSXConverter extends DoorsTreeNodeVisitor {

    private Workbook wb;
    private Sheet sheet;
    private CellStyle headingStyle;
    private CellStyle requirementStyle;
    private CellStyle informationStyle;

    public static void main(final String[] args) throws IOException, CSVParseException {

        new CSV2XLSXConverter().convert("C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe A\\Runde_1_HandsFreeAccess.csv", "C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe B\\Runde_1_HandsFreeAccess.xlsx");
        new CSV2XLSXConverter().convert("C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe B\\Runde_2_WiperControl.csv", "C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe A\\Runde_2_WiperControl.xlsx");

    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {

        final Row createRow = sheet.createRow(sheet.getLastRowNum() + 1);
        createRow.createCell(0).setCellValue(object.getAttributes().get("SourceID"));

        final StringBuilder sb = new StringBuilder();
        // for (int i = 1; i < object.getObjectLevel(); i++) {
        // sb.append(" ");
        // }
        final XSSFCell cell = (XSSFCell) createRow.createCell(1);
        final String ot = object.getAttributes().get("Object Type");
        final CellStyle style = wb.createCellStyle();
        cell.setCellStyle(style);
        if (object.isHeading()) {
            cell.setCellValue(sb.toString() + object.getObjectNumber() + " " + object.getText());
            style.cloneStyleFrom(headingStyle);
        } else {
            cell.setCellValue(sb.toString() + object.getText());
            if ("requirement".equals(ot)) {
                style.cloneStyleFrom(requirementStyle);
            } else if ("information".equals(ot)) {
                style.cloneStyleFrom(informationStyle);
            }
        }
        cell.getCellStyle().setWrapText(true);
        cell.getCellStyle().setIndention((short) (object.getObjectLevel()));
        createRow.createCell(2).setCellValue(ot);
        createRow.createCell(3).setCellValue(object.getAttributes().get("True Object Type"));
        createRow.createCell(4).setCellValue(object.getAttributes().get("Proposed Object Type"));

        final String remarkReliability = object.getAttributes().get("Remark Reliability");
        if (remarkReliability != null && !remarkReliability.isEmpty()) {
            createRow.createCell(5).setCellValue(Double.parseDouble(remarkReliability));
        }
        return true;
    }

    private void convert(final String in, final String out) throws IOException, CSVParseException {

        final DoorsModule module = new ModuleCSVParser().parseCSV(new File(in));

        wb = new XSSFWorkbook();

        headingStyle = wb.createCellStyle();
        final Font headingFont = wb.createFont();
        headingFont.setBold(true);
        headingFont.setFontHeightInPoints((short) 16);
        headingStyle.setFont(headingFont);

        requirementStyle = wb.createCellStyle();
        final Font requirementFont = wb.createFont();
        requirementFont.setColor(IndexedColors.GREEN.index);
        requirementStyle.setFont(requirementFont);

        informationStyle = wb.createCellStyle();
        final Font informationFont = wb.createFont();
        informationFont.setColor(IndexedColors.DARK_RED.index);
        informationStyle.setFont(informationFont);

        sheet = wb.createSheet();
        final Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("SourceID");
        firstRow.createCell(1).setCellValue("Text");
        firstRow.createCell(2).setCellValue("Object Type");
        firstRow.createCell(3).setCellValue("True Object Type");
        firstRow.createCell(4).setCellValue("Proposed Object Type");
        firstRow.createCell(5).setCellValue("Remark Reliability");

        module.accept(new MakeCsvAnonymous());
        module.accept(this);

        try (FileOutputStream outStream = new FileOutputStream(new File(out))) {
            wb.write(outStream);
        }

    }

}
