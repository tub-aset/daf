package de.jpwinkler.daf.dataprocessing;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;

public class PreloadingCSVFolderSource implements DoorsModuleSource {

    private class CSVParseTask implements Runnable {

        private final ModuleCSVParser parser = new ModuleCSVParser();

        @Override
        public void run() {
            String moduleFileName;

            synchronized (moduleQueue) {
                while (moduleQueue.size() > moduleQueueCapacity) {
                    try {
                        moduleQueue.wait();
                    } catch (final InterruptedException e) {
                        return;
                    }
                }
            }

            synchronized (fileNameQueue) {
                moduleFileName = fileNameQueue.poll();
            }
            DoorsModule module;
            try {
                module = parser.parseCSV(new File(moduleFileName));
                synchronized (moduleQueue) {
                    moduleQueue.add(module);
                }
            } catch (IOException | CSVParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static final Logger LOGGER = Logger.getLogger(CSVFolderSource.class.getName());

    private final List<String> moduleFileNames;
    private final int moduleQueueCapacity;

    private final Queue<String> fileNameQueue;
    private final Queue<DoorsModule> moduleQueue;

    public PreloadingCSVFolderSource(final String folder, final boolean recursiveModuleSearch, final int moduleQueueCapacity) {
        super();
        this.moduleQueueCapacity = moduleQueueCapacity;
        moduleFileNames = new LinkedList<>();
        addModules(new File(folder), recursiveModuleSearch);
        moduleQueue = new LinkedList<>();
        fileNameQueue = new LinkedList<>(moduleFileNames);
    }

    private void addModules(final File file, final boolean recursiveModuleSearch) {
        for (final File child : file.listFiles()) {
            if (child.isFile() && child.getName().endsWith(".csv")) {
                moduleFileNames.add(child.getAbsolutePath());
            } else if (recursiveModuleSearch && child.isDirectory()) {
                addModules(child, recursiveModuleSearch);
            }
        }
    }

    @Override
    public DoorsModule next() throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int available() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
