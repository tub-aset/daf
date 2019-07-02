package de.jpwinkler.daf.db;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FilenameUtils;

public class ModuleCSV {

    private ModuleCSV() {
    }

    public static final Charset CHARSET = Charset.forName("UTF-8");

    public static void write(File autoDetectFile, DoorsModule module) throws IOException {
        String path = autoDetectFile.getAbsolutePath();
        final File csvFile;
        final File mmdFile;

        if (FilenameUtils.isExtension(path, (String) null)) {
            csvFile = new File(path + ".csv");
            mmdFile = new File(path + ".mmd");
        } else if (FilenameUtils.isExtension(path, "csv")) {
            csvFile = new File(path);
            mmdFile = new File(FilenameUtils.removeExtension(path) + ".mmd");
        } else if (FilenameUtils.isExtension(path, "mmd")) {
            mmdFile = new File(path);
            csvFile = new File(FilenameUtils.removeExtension(path) + ".csv");
        } else {
            throw new IOException("Bad file extension");
        }

        write(csvFile, mmdFile, module);
    }

    public static void write(File csvFile, File mmdFile, DoorsModule module) throws IOException {
        writeModule(csvFile, module);
        writeMetaData(mmdFile, module.getAttributes());
    }

    public static void writeModule(final File csvFile, final DoorsModule module) throws IOException {
        try (OutputStream os = new FileOutputStream(csvFile)) {
            writeModule(os, module);
        }
    }

    public static void writeModule(final OutputStream csvStream, final DoorsModule module) throws IOException {
        final String[] header = module.getObjectAttributes().toArray(new String[module.getObjectAttributes().size()]);

        CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(csvStream, CHARSET), WRITE_FORMAT);
        printer.printRecord((Object[]) header);

        module.accept(new DoorsTreeNodeVisitor<DoorsObject>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                final Object[] values = new Object[header.length];
                for (int i = 0; i < header.length; i++) {
                    final String attribute = header[i];
                    values[i] = object.getAttributes().get(attribute);
                }
                try {
                    printer.printRecord(values);
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        });
        printer.flush();
    }

    public static void writeMetaData(final File mmdFile, Map<String, String> metaData) throws IOException {
        try (OutputStream os = new FileOutputStream(mmdFile)) {
            writeMetaData(os, metaData);
        }
    }

    public static void writeMetaData(final OutputStream mmdStream, Map<String, String> metaData) throws IOException {
        CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(mmdStream, CHARSET), MMD_FORMAT);
        for (Entry<String, String> e : metaData.entrySet()) {
            printer.printRecord(e.getKey(), e.getValue());
        }
        printer.flush();
    }

    public static DoorsModule read(DatabaseFactory factory, File autoDetectFile) throws IOException {
        return read(factory, autoDetectFile, OpenFlag.OPEN_ONLY);
    }

    public static DoorsModule read(DatabaseFactory factory, File autoDetectFile, OpenFlag openFlag) throws IOException {
        String path = autoDetectFile.getAbsolutePath();
        final File csvFile;
        final File mmdFile;
        if (FilenameUtils.isExtension(path, (String) null)) {
            csvFile = new File(path + ".csv");
            mmdFile = new File(path + ".mmd");
        } else if (FilenameUtils.isExtension(path, "csv")) {
            csvFile = new File(path);
            mmdFile = new File(FilenameUtils.removeExtension(path) + ".mmd");
        } else if (FilenameUtils.isExtension(path, "mmd")) {
            mmdFile = new File(path);
            csvFile = new File(FilenameUtils.removeExtension(path) + ".csv");
        } else {
            throw new IOException("Bad file extension");
        }

        if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !csvFile.isFile() && !mmdFile.isFile()) {
            write(csvFile, mmdFile, factory.createModule(null, FilenameUtils.getBaseName(path)));
        } else if (openFlag == OpenFlag.ERASE_IF_EXISTS) {
            write(csvFile, mmdFile, factory.createModule(null, FilenameUtils.getBaseName(path)));
        }

        if (!csvFile.isFile()) {
            throw new FileNotFoundException(csvFile.getAbsolutePath());
        }
        if (!mmdFile.isFile()) {
            throw new FileNotFoundException(mmdFile.getAbsolutePath());
        }

        return read(factory, csvFile, mmdFile);
    }

    public static DoorsModule read(DatabaseFactory factory, File csvFile, File mmdFile) throws IOException {
        DoorsModule module = readModule(factory, csvFile);
        module.getAttributes().putAll(readMetaData(factory, mmdFile));
        return module;
    }

    public static DoorsModule readModule(DatabaseFactory factory, final File csvFile) throws IOException {
        try (InputStream csvStream = new FileInputStream(csvFile)) {
            return readModule(factory, csvStream, FilenameUtils.getBaseName(csvFile.getAbsolutePath()));
        }
    }

    public static DoorsModule readModule(DatabaseFactory factory, final InputStream csvStream, String moduleName) throws IOException {
        CSVParser csvParser = new CSVParser(new InputStreamReader(csvStream, CHARSET), READ_FORMAT);

        final DoorsModule module = factory.createModule(null, moduleName);

        DoorsTreeNode current = module;
        int currentLevel = 0;
        if (!csvParser.getHeaderMap().containsKey(DoorsAttributes.OBJECT_LEVEL.getKey())) {
            throw new IOException("This is no DOORS CSV file: Object Level missing");
        }

        for (final CSVRecord record : csvParser.getRecords()) {
            final int objectLevel = Integer.parseInt(record.get(DoorsAttributes.OBJECT_LEVEL.getKey()));

            while (objectLevel <= currentLevel) {
                currentLevel--;
                current = current.getParent();
            }

            while (objectLevel > currentLevel + 1) {
                if (current.getChildren().isEmpty()) {
                    final DoorsObject createDoorsObject = factory.createObject(current, "");
                    current.getChildren().add(createDoorsObject);
                }
                current = current.getChildren().get(current.getChildren().size() - 1);
                currentLevel++;
            }

            final DoorsObject newObject = factory.createObject(current, "");
            for (final Entry<String, String> e : record.toMap().entrySet()) {
                newObject.getAttributes().put(e.getKey(), e.getValue());
            }

            current.getChildren().add(newObject);
        }

        module.setObjectAttributes(new ArrayList<>(csvParser.getHeaderMap().keySet()));

        return module;
    }

    public static Map<String, String> readMetaData(DatabaseFactory factory, final File mmdFile) throws IOException {
        try (InputStream mmdStream = new FileInputStream(mmdFile)) {
            return readMetaData(factory, mmdStream);
        }
    }

    public static Map<String, String> readMetaData(DatabaseFactory factory, final InputStream mmdStream) throws IOException {
        CSVParser parser = new CSVParser(new InputStreamReader(mmdStream, CHARSET), MMD_FORMAT);
        final Map<String, String> metadata = new HashMap<>();
        for (final CSVRecord record : parser.getRecords()) {
            metadata.put(record.get(0), record.get(1));
        }

        return metadata;
    }

    private static final CSVFormat WRITE_FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n")
            .withQuoteMode(QuoteMode.NON_NUMERIC);
    private static final CSVFormat READ_FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n")
            .withQuoteMode(QuoteMode.NON_NUMERIC)
            .withHeader();
    private static final CSVFormat MMD_FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

}
