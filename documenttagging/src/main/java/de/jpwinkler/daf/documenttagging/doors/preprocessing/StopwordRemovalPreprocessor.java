package de.jpwinkler.daf.documenttagging.doors.preprocessing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class StopwordRemovalPreprocessor extends ObjectTextPreprocessor {

    private final Set<String> stopWords = new HashSet<>();

    public StopwordRemovalPreprocessor(final File stopWordListFile) throws IOException {
        stopWords.addAll(FileUtils.readLines(stopWordListFile));
    }

    public StopwordRemovalPreprocessor(final InputStream stopWordListStream) throws IOException {
        stopWords.addAll(IOUtils.readLines(stopWordListStream));
    }

    @Override
    protected String preprocessString(final String string) {
        final List<String> objectTextWords = new ArrayList<>(Arrays.asList(string.split(" ")));

        CollectionUtils.filter(objectTextWords, s -> !stopWords.contains(s));

        return StringUtils.join(objectTextWords, " ");
    }


}
