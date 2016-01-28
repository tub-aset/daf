package de.jpwinkler.daf.fap5.util;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Fap5ProgressTrackingDocument {

    private static final Logger LOGGER = Logger.getLogger(Fap5ProgressTrackingDocument.class.getName());

    private static final int FIRST_ROW = 3;

    public static final int COLUMN_SOURCE_PATH = 0;
    public static final int COLUMN_SOURCE_MODULE = 1;
    public static final int COLUMN_REQ_COUNT = 2;
    public static final int COLUMN_TODO_COUNT = 3;
    public static final int COLUMN_MATURITY_OPEN = 4;
    public static final int COLUMN_MATURITY_SPECIFIED = 5;
    public static final int COLUMN_MATURITY_FOLLOW_UP = 6;
    public static final int COLUMN_MATURITY_FOLLOW_UP_HASHTAGS = 7;
    public static final int COLUMN_MATURITY_AGREED = 8;
    public static final int COLUMN_EMPTY_OBJECT_TYPE_COUNT = 9;
    public static final int COLUMN_REQUIREMENT_AS_HEADING_COUNT = 10;
    public static final int COLUMN_INFORMATION_WITH_LINK_COUNT = 11;
    public static final int COLUMN_REQUIREMENT_WITHOUT_LINK_COUNT = 12;
    public static final int COLUMN_SUM = 13;
    public static final int COLUMN_TARGET_PATH = 14;
    public static final int COLUMN_TARGET_MODULE = 15;
    public static final int COLUMN_INBOX_REQ_COUNT = 16;
    public static final int COLUMN_INBOX_ACCEPTANCE_EMPTY = 17;
    public static final int COLUMN_INBOX_ACCEPTANCE_DELETED = 18;
    public static final int COLUMN_INBOX_ACCEPTANCE_CHANGED = 19;
    public static final int COLUMN_INBOX_ACCEPTANCE_TO_CLARIFY = 20;
    public static final int COLUMN_INBOX_ACCEPTANCE_AGREED = 21;
    public static final int COLUMN_VERIFIED_REQ_COUNT = 22;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_EMPTY = 23;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_DELETED = 24;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_CHANGED = 25;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_TO_CLARIFY = 26;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_PARTLY_AGREED = 27;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_AGREED = 28;
    public static final int COLUMN_INBOX_VIEW = 29;
    public static final int COLUMN_VERIFIED_VIEW = 30;
    public static final int COLUMN_INBOX_MODULE_EXISTS = 31;
    public static final int COLUMN_VERIFIED_MODULE_EXISTS = 32;

    public static final int COLUMN_INBOX_ACCEPTANCE_CONFLICT = 33;
    public static final int COLUMN_VERIFIED_ACCEPTANCE_CONFLICT = 34;

    public static final int COLUMN_INBOX_ACCEPTANCE_PARTLY_AGREED = 35;
    public static final int COLUMN_INBOX_LINK = 36;

    public static final int COLUMN_VERIFIED_LINK = 37;

    private final Map<Integer, Integer> columnIndexMap = new HashMap<>();

    private final Workbook workbook;

    private final Sheet sheet;

    public Fap5ProgressTrackingDocument(final String file) throws FileNotFoundException, IOException {

        workbook = new XSSFWorkbook(new FileInputStream(file));
        sheet = workbook.getSheet("FAP5");

        columnIndexMap.put(COLUMN_SOURCE_PATH, findRow("Doors-Zielstruktur", "Allgemein", "Quell-Pfad"));
        columnIndexMap.put(COLUMN_SOURCE_MODULE, findRow("Doors-Zielstruktur", "Allgemein", "Modul"));
        columnIndexMap.put(COLUMN_REQ_COUNT, findRow("Doors-Zielstruktur", "Inhalt", "Requirements"));
        columnIndexMap.put(COLUMN_TODO_COUNT, findRow("Doors-Zielstruktur", "Inhalt", "todo"));
        columnIndexMap.put(COLUMN_MATURITY_OPEN, findRow("Doors-Zielstruktur", "Reifegrad", "open"));
        columnIndexMap.put(COLUMN_MATURITY_SPECIFIED, findRow("Doors-Zielstruktur", "Reifegrad", "specified"));
        columnIndexMap.put(COLUMN_MATURITY_FOLLOW_UP, findRow("Doors-Zielstruktur", "Reifegrad", "follow_up"));
        columnIndexMap.put(COLUMN_MATURITY_FOLLOW_UP_HASHTAGS, findRow("Doors-Zielstruktur", "Reifegrad", "follow_up_###"));
        columnIndexMap.put(COLUMN_MATURITY_AGREED, findRow("Doors-Zielstruktur", "Reifegrad", "agreed"));
        columnIndexMap.put(COLUMN_EMPTY_OBJECT_TYPE_COUNT, findRow("Doors-Zielstruktur", "Deklarations-Fehler", "Fehlende Attributierung"));
        columnIndexMap.put(COLUMN_REQUIREMENT_AS_HEADING_COUNT, findRow("Doors-Zielstruktur", "Deklarations-Fehler", "Heading"));
        columnIndexMap.put(COLUMN_INFORMATION_WITH_LINK_COUNT, findRow("Doors-Zielstruktur", "Deklarations-Fehler", "mit Link"));
        columnIndexMap.put(COLUMN_REQUIREMENT_WITHOUT_LINK_COUNT, findRow("Doors-Zielstruktur", "Deklarations-Fehler", "ohne Link"));
        columnIndexMap.put(COLUMN_SUM, findRow("Doors-Zielstruktur", "Deklarations-Fehler", "Summe"));

        columnIndexMap.put(COLUMN_TARGET_PATH, findRow("Inbox", "Allgemein", "Ziel-Pfad"));
        columnIndexMap.put(COLUMN_TARGET_MODULE, findRow("Inbox", "Allgemein", "Ziel-Modulname"));

        columnIndexMap.put(COLUMN_INBOX_LINK, findRow("Inbox", "Allgemein", "Link"));
        columnIndexMap.put(COLUMN_INBOX_MODULE_EXISTS, findRow("Inbox", "Allgemein", "Modul existiert"));
        columnIndexMap.put(COLUMN_INBOX_VIEW, findRow("Inbox", "Allgemein", "View"));
        columnIndexMap.put(COLUMN_INBOX_REQ_COUNT, findRow("Inbox", "Allgemein", "Anzahl"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_EMPTY, findRow("Inbox", "Acceptance Status", "\"\""));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_DELETED, findRow("Inbox", "Acceptance Status", "deleted"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_CHANGED, findRow("Inbox", "Acceptance Status", "changed"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_TO_CLARIFY, findRow("Inbox", "Acceptance Status", "clarify"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_CONFLICT, findRow("Inbox", "Acceptance Status", "conflict"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_PARTLY_AGREED, findRow("Inbox", "Acceptance Status", "partly agreed"));
        columnIndexMap.put(COLUMN_INBOX_ACCEPTANCE_AGREED, findRow("Inbox", "Acceptance Status", "partly agreed") + 1);

        columnIndexMap.put(COLUMN_VERIFIED_LINK, findRow("Verified", "Allgemein", "Link"));
        columnIndexMap.put(COLUMN_VERIFIED_MODULE_EXISTS, findRow("Verified", "Allgemein", "Modul existiert"));
        columnIndexMap.put(COLUMN_VERIFIED_VIEW, findRow("Verified", "Allgemein", "View"));
        columnIndexMap.put(COLUMN_VERIFIED_REQ_COUNT, findRow("Verified", "Allgemein", "Anzahl"));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_EMPTY, findRow("Verified", "Acceptance Status", "\"\""));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_DELETED, findRow("Verified", "Acceptance Status", "deleted"));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_CHANGED, findRow("Verified", "Acceptance Status", "changed"));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_CONFLICT, findRow("Verified", "Acceptance Status", "conflict"));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_TO_CLARIFY, findRow("Verified", "Acceptance Status", "clarify"));
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_PARTLY_AGREED, findRow("Verified", "Acceptance Status", "partly agreed"));

        // TODO: ugly, but works
        columnIndexMap.put(COLUMN_VERIFIED_ACCEPTANCE_AGREED, findRow("Verified", "Acceptance Status", "partly agreed") + 1);

    }

    private int findRow(final String row1text, final String row2text, final String row3text) {
        return findRow(row1text, row2text, row3text, (s1, s2) -> s1.contains(s2));
    }

    private int findRow(final String row1text, final String row2text, final String row3text, final BiPredicate<String, String> predicate) {
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
        LOGGER.warning(String.format("Could not find row with header %s, %s, &s.", row1text, row2text, row3text));
        return -1;
    }

    public List<Fap5ProgressTrackingRow> getRows() {
        final List<Fap5ProgressTrackingRow> result = new ArrayList<>();
        for (final Row row : sheet) {
            if (row.getRowNum() >= FIRST_ROW) {
                result.add(new Fap5ProgressTrackingRow(row, columnIndexMap));
            }
        }
        return result;
    }

    public void saveAs(final String targetExcelFile) throws IOException {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetExcelFile))) {
            workbook.write(outputStream);
        }
    }

    public void close() throws IOException {
        workbook.close();
    }

}
