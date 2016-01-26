package de.jpwinkler.daf.fap5.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class Fap5ProgressTrackingRow {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final Map<Integer, String> values = new HashMap<>();
    private final Map<Integer, Integer> columnIndexMap;
    private final Row row;
    private final short percentFormat;

    public Fap5ProgressTrackingRow(final Row row, final Map<Integer, Integer> columnIndexMap, final short percentFormat) {
        this.row = row;
        this.columnIndexMap = columnIndexMap;
        this.percentFormat = percentFormat;
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
        // update(column, String.valueOf(newValue));
    }

    public void update(final int column, final String newValue) {
        Cell cell = row.getCell(columnIndexMap.get(column));
        if (cell == null) {
            cell = row.createCell(columnIndexMap.get(column));
        }
        cell.setCellValue(newValue);
        final CellStyle cs = row.getSheet().getWorkbook().createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);

    }

    public void updatePercent(final int column, final double newValue) {
        Cell cell = row.getCell(columnIndexMap.get(column));
        if (cell == null) {
            cell = row.createCell(columnIndexMap.get(column));
        }
        cell.setCellValue(newValue);
        cell.getCellStyle().setDataFormat(percentFormat);
        // update(column, DECIMAL_FORMAT.format(newValue * 100) + " %");
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
