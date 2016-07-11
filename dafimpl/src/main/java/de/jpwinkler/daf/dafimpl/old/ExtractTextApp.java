package de.jpwinkler.daf.dafimpl.old;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.WeightedRandomNumberGenerator;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;

public class ExtractTextApp extends AbstractApp {

    private final Map<String, String> m = new HashMap<>();
    private final ObjectTextPreprocessor PREPROCESSOR = ObjectTextPreprocessor.getDefaultPreprocessor();

    @Override
    protected void run() throws Exception {
        m.put("ü", "ue");
        m.put("ö", "oe");
        m.put("ä", "ae");
        m.put("Ü", "Ue");
        m.put("Ö", "Oe");
        m.put("Ä", "Ae");
        m.put("ß", "ss");

        final Pattern objectTypePattern = Pattern.compile("requirement|information");

        final Set<Integer> writtenObjects = new HashSet<>();

        final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File("temp/reqinf.txt")));

        int count_i = 0;
        int count_r = 0;

        final WeightedRandomNumberGenerator rng = new WeightedRandomNumberGenerator();
        rng.addOutput(1);
        rng.addOutput(2);
        rng.addOutput(3);
        rng.addOutput(4);
        rng.addOutput(5);
        rng.addOutput(6);
        rng.setTarget(4.93f);

        final Iterator<DoorsObject> i = getIterator(new HasTagsSearchExpression("deutsch"));
        while (i.hasNext()) {
            final DoorsObject next = i.next();
            final String ot = next.getAttributes().get("Object Type");
            if (ot != null && objectTypePattern.matcher(ot).matches() && next.getText() != null && !next.getText().trim().isEmpty()) {

                final String text = preprocess(next.getText());

                if (!text.trim().isEmpty() && !writtenObjects.contains(text.hashCode()) && !text.contains("name , vorname") && !text.contains("@ daimler . com")) {
                    int num_repetitions = 1;
                    if ("information".equals(ot)) {
                        num_repetitions = rng.next();
                        count_i += num_repetitions;
                    } else {
                        count_r++;
                    }

                    for (int c = 0; c < num_repetitions; c++) {
                        writer.write(ot + " " + text + "\n");
                    }
                    writtenObjects.add(text.hashCode());
                }
            }
        }

        System.out.println(count_i);
        System.out.println(count_r);

        writer.close();
    }

    private String preprocess(String text) {
        text = PREPROCESSOR.preprocessTextToString(text);
        for (final Entry<String, String> e : m.entrySet()) {
            text = text.replace(e.getKey(), e.getValue());
        }
        final int[] array = text.chars().filter(i -> i <= 128).toArray();
        return new String(array, 0, array.length);
    }

    public static void main(final String[] args) throws Exception {
        new ExtractTextApp().launch();
    }

}
