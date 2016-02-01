package de.jpwinkler.daf.fap5.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;

public class Fap5ProgressTrackingRow {
    private final Map<Integer, String> values = new HashMap<>();
    private final Map<Integer, Integer> columnIndexMap;
    private final Row row;

    private final CellStyle csPercent;

    private final CellStyle csNumber;

    private final CellStyle csMultiLine;


    public Fap5ProgressTrackingRow(final Row row, final Map<Integer, Integer> columnIndexMap) {
        this.row = row;
        this.columnIndexMap = columnIndexMap;
        final DataFormat df = row.getSheet().getWorkbook().createDataFormat();

        csPercent = row.getSheet().getWorkbook().createCellStyle();
        csPercent.setAlignment(CellStyle.ALIGN_CENTER);
        csPercent.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        csPercent.setDataFormat(df.getFormat("0.00%"));

        csNumber = row.getSheet().getWorkbook().createCellStyle();
        csNumber.setAlignment(CellStyle.ALIGN_CENTER);
        csNumber.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        csNumber.setDataFormat(df.getFormat("0"));

        csMultiLine = row.getSheet().getWorkbook().createCellStyle();
        csMultiLine.setAlignment(CellStyle.ALIGN_CENTER);
        csMultiLine.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        csMultiLine.setDataFormat(df.getFormat("General"));
        csMultiLine.setWrapText(true);

        columnIndexMap.entrySet().stream()
        .filter(e -> e.getValue() != -1)
        .filter(e -> row.getCell(e.getValue()) != null)
        .forEach(e -> values.put(e.getKey(), row.getCell(e.getValue()).toString()));
    }

    public void update(final int column, final int newValue) {
        Cell cell = row.getCell(columnIndexMap.get(column));
        if (cell == null) {
            cell = row.createCell(columnIndexMap.get(column));
        }
        cell.setCellValue(newValue);
        cell.setCellStyle(csNumber);
        // update(column, String.valueOf(newValue));
    }

    public void update(final int column, final String newValue) {
        Cell cell = row.getCell(columnIndexMap.get(column));
        if (cell == null) {
            cell = row.createCell(columnIndexMap.get(column));
        }
        cell.setCellValue(newValue);
        cell.setCellStyle(csMultiLine);

    }

    public void updatePercent(final int column, final double newValue) {
        Cell cell = row.getCell(columnIndexMap.get(column));
        if (cell == null) {
            cell = row.createCell(columnIndexMap.get(column));
        }
        if (Double.isInfinite(newValue) || Double.isNaN(newValue)) {
            cell.setCellValue("-");
            cell.setCellStyle(csMultiLine);
        } else {
            cell.setCellValue(newValue);
            cell.setCellStyle(csPercent);
        }
    }

    public void update(final int column, final List<String> newValues) {
        update(column, StringUtils.join(newValues, "\r\n"));
    }

    public String get(final int column) {
        return values.get(column);
    }

    public boolean hasColumn(final int column) {
        return values.containsKey(column);
    }
}
