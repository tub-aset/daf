package de.jpwinkler.daf.datasetutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;

public class DataSetStorage {

    private HashSet<DataSetRecord> records = new HashSet<>();

    public void add(final DataSetRecord record) {
        synchronized (records) {
            if (!records.contains(record)) {
                records.add(record);
            }
        }
    }

    public void forEach(final Consumer<DataSetRecord> f) {
        synchronized (records) {
            records.forEach(f);
        }
    }

    public void load(final String filename) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(new GZIPInputStream(new FileInputStream(filename)))) {
            records = new Gson().fromJson(reader, new TypeToken<HashSet<DataSetRecord>>() {
            }.getType());
        }

    }

    public void save(final String filename) throws IOException {
        synchronized (records) {
            try (OutputStreamWriter writer = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(filename)))) {
                new GsonBuilder().setPrettyPrinting().create().toJson(records, new TypeToken<HashSet<DataSetRecord>>() {
                }.getType(), writer);
            }
        }
    }

    public void export(final String filename) throws IOException {
        synchronized (records) {
            final ClassifierContext context = ClassifierContext.getInstance();
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename))) {
                for (final DataSetRecord record : records) {
                    if (record.getCorrectedObjectType() != null && record.getBody() != null) {
                        writer.write(record.getCorrectedObjectType());
                        writer.write(" ");
                        writer.write(context.convNetPreprocess(record.getBody()));
                        writer.write("\n");
                    }
                }
            }
        }
    }

    public boolean exists(final DataSetRecord r) {
        synchronized (records) {
            return records.contains(r);
        }
    }

    public int size() {
        synchronized (records) {
            return records.size();
        }
    }
}
