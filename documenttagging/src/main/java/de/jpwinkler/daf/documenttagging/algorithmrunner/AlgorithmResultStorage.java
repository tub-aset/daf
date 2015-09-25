package de.jpwinkler.daf.documenttagging.algorithmrunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class AlgorithmResultStorage {

    private final List<AlgorithmResult> storage = new ArrayList<>();
    private transient Map<AlgorithmConfiguration, AlgorithmResult> storageRefs = new HashMap<>();

    public void loadFromFile(final File file) throws JsonSyntaxException, IOException {

        final AlgorithmResultStorage result = new Gson().fromJson(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))), AlgorithmResultStorage.class);

        storage.clear();
        storage.addAll(result.storage);

        storageRefs.clear();
        for (final AlgorithmResult ar : storage) {
            storageRefs.put(ar.getAlgorithmConfiguration(), ar);
        }

    }

    public void saveToFile(final File file) throws IOException {
        final String json = new GsonBuilder().setPrettyPrinting().create().toJson(this);
        // FileUtils.writeStringToFile(file, json);

        final GZIPOutputStream os = new GZIPOutputStream(new FileOutputStream(file));

        os.write(json.getBytes());
        os.close();
    }

    public void add(final AlgorithmResult result) {
        if (storageRefs.containsKey(result.getAlgorithmConfiguration())) {
            // That's O(n), not quite optimal.
            storage.remove(storageRefs.get(result.getAlgorithmConfiguration()));
        }
        storage.add(result);
        storageRefs.put(result.getAlgorithmConfiguration(), result);
    }

    public AlgorithmResult get(final AlgorithmConfiguration configuration) {
        return storageRefs.get(configuration);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("algorithm name;test document;gis iterations;gis cutoff;smoothing technique;smoothing d;smoothing k;growrate functions;macro precision;macro recall;macro f1; micro f1\n");
        for (final AlgorithmResult result : storage) {
            builder.append(result.getAlgorithmConfiguration().getAlgorithmName());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getTestDocument());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getGisIterations());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getGisCutoff());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getSmoothingTechnique());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getSmoothingD());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getSmoothingK());
            builder.append(";");
            builder.append(result.getAlgorithmConfiguration().getGrowRateFunction());
            builder.append(";");
            builder.append(result.getConfusionMatrix().getMacroPrecision());
            builder.append(";");
            builder.append(result.getConfusionMatrix().getMacroRecall());
            builder.append(";");
            builder.append(result.getConfusionMatrix().getMacroF1Score());
            builder.append(";");
            builder.append(result.getConfusionMatrix().getMicroF1Score());
            builder.append("\n");
        }

        return builder.toString();
    }

}
