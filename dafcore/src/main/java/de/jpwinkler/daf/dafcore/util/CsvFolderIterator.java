package de.jpwinkler.daf.dafcore.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class CsvFolderIterator implements Iterator<DoorsModule> {

    private final Iterator<File> files;

    public CsvFolderIterator(final File csvFolder) {
        super();
        files = listFiles(csvFolder).iterator();
    }

    private List<File> listFiles(final File csvFolder) {

        final List<File> result = new ArrayList<>();

        final File[] listFiles = csvFolder.listFiles();
        if (listFiles != null && listFiles.length > 0) {

            for (final File child : listFiles) {
                if (child.isFile() && "csv".equals(FilenameUtils.getExtension(child.getAbsolutePath()))) {
                    result.add(child);
                } else if (child.isDirectory()) {
                    result.addAll(listFiles(child));
                }
            }
        }
        return result;

    }

    @Override
    public boolean hasNext() {
        return files.hasNext();
    }

    @Override
    public DoorsModule next() {
        try {
            final File next = files.next();
            final DoorsModule module = new ModuleCSVParser().parseCSV(next);
            final File metaDataFile = new File(next.getAbsoluteFile() + ".mmd");
            if (metaDataFile.exists()) {
                new ModuleMetaDataParser().updateModuleMetaData(metaDataFile, module);
            }
            return module;
        } catch (IOException | CSVParseException e) {
            throw new RuntimeException(e);
        }
    }

}
