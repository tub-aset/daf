package de.jpwinkler.daf.dataprocessing.streaming;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SimpleFolderCSVSpliterator implements Spliterator<SimpleDoorsObject> {

    private final Queue<String> csvFileNames = new LinkedList<>();
    private Iterator<SimpleDoorsObject> currentModuleIterator;

    public SimpleFolderCSVSpliterator(final File folder, final boolean recursive) {
        findCSVFiles(folder, recursive);
    }

    private void findCSVFiles(final File folder, final boolean recursive) {
        for (final File child : folder.listFiles()) {
            if (child.isFile() && child.getName().endsWith(".csv")) {
                csvFileNames.add(child.getAbsolutePath());
            } else if (recursive && child.isDirectory()) {
                findCSVFiles(child, recursive);
            }
        }
    }

    @Override
    public boolean tryAdvance(final Consumer<? super SimpleDoorsObject> action) {
        if (csvFileNames.size() > 0 && (currentModuleIterator == null || !currentModuleIterator.hasNext())) {
            try {
                currentModuleIterator = new SimpleModuleCSVParser(csvFileNames.poll()).iterator();
            } catch (final IOException e) {
                // TODO: Not very nice
                throw new RuntimeException(e);
            }
        }

        if (currentModuleIterator != null && currentModuleIterator.hasNext()) {
            action.accept(currentModuleIterator.next());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Spliterator<SimpleDoorsObject> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return ORDERED | IMMUTABLE | NONNULL;
    }

}
