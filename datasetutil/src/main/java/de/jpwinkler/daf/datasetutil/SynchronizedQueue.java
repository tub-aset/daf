package de.jpwinkler.daf.datasetutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SynchronizedQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private final Random random = new Random();
    private final int capacity;

    public SynchronizedQueue(final int capacity) {
        this.capacity = capacity;
    }

    public void load(final String filename) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(new GZIPInputStream(new FileInputStream(filename)))) {
            queue = new Gson().fromJson(reader, new TypeToken<LinkedList<T>>() {
            }.getType());
        }

    }

    public void save(final String filename) throws IOException {
        synchronized (queue) {
            try (OutputStreamWriter writer = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(filename)))) {
                new Gson().toJson(queue, new TypeToken<LinkedList<T>>() {
                }.getType(), writer);
            }
        }
    }


    public void enqueue(final T t) {
        synchronized (queue) {
            while (queue.size() > capacity) {
                try {
                    queue.wait();
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            queue.add(t);
            queue.notifyAll();
        }
    }

    public T dequeue() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            final T t = queue.remove(random.nextInt(queue.size()));
            queue.notifyAll();
            return t;
        }
    }

    public int size() {
        synchronized (queue) {
            return queue.size();
        }
    }

}
