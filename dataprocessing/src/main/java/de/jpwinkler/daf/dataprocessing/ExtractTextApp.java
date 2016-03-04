package de.jpwinkler.daf.dataprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;

public class ExtractTextApp {

    private static Iterator<DoorsObject> getIterator() {
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:\\WORK\\DOORS\\export\\body\\comp\\de"), true), false).iterator();
    }

    public static void main(final String[] args) throws IOException {
        final Pattern objectTypePattern = Pattern.compile("[a-z]+");

        final Map<String, Writer> writers = new HashMap<>();

        final Set<Integer> writtenObjects = new HashSet<>();

        final Iterator<DoorsObject> i = getIterator();
        while (i.hasNext()) {
            final DoorsObject next = i.next();
            final String ot = next.getAttributes().get("Object Type");
            if (ot != null && objectTypePattern.matcher(ot).matches() && next.getText() != null && !next.getText().trim().isEmpty()) {

                final String text = next.getText();

                if (!text.trim().isEmpty() && !writtenObjects.contains(text.hashCode())) {
                    if (!writers.containsKey(ot)) {
                        writers.put(ot, new OutputStreamWriter(new FileOutputStream(new File("temp/" + ot + ".txt"))));
                    }

                    writers.get(ot).write(text + "\n");

                    writtenObjects.add(text.hashCode());

                }
            }
        }

        for (final Writer w : writers.values()) {
            w.close();
        }
    }

}
