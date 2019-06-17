package de.jpwinkler.daf.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.commons.lang3.tuple.Pair;

public class BackgroundTaskMonitor {

    private final BiConsumer<BackgroundTask, Double> sharedListener;

    public BackgroundTaskMonitor(BiConsumer<BackgroundTask, Double> sharedListener) {
        this.sharedListener = sharedListener;
    }

    private final List<BackgroundTask> tasks = Collections.synchronizedList(new ArrayList<>());
    private final Map<BackgroundTask, Consumer<BackgroundTask>> taskSpecificListeners = Collections.synchronizedMap(new WeakHashMap<>());
    private final AtomicReference<Pair<Long, Long>> totalProgress = new AtomicReference<>(Pair.of(0l, 0l));

    private final ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public boolean hasRunningTasks() {
        return !tasks.isEmpty();
    }

    public Future<?> runBackgroundTask(String name, Consumer<BackgroundTask.BackgroundTaskInterface> runnable) {
        return this.runBackgroundTask(name, runnable, executor);
    }

    public Future<?> runBackgroundTask(String name, Consumer<BackgroundTask.BackgroundTaskInterface> runnable, ExecutorService executorService) {
        BackgroundTask bt = new BackgroundTask(name, runnable, this::onBackgroundTaskUpdate);
        this.tasks.add(bt);
        return bt.run(executorService);
    }

    private void onBackgroundTaskUpdate(BackgroundTask t, Pair<Long, Long> increment) {
        taskSpecificListeners.getOrDefault(t, bt -> {
        }).accept(t);

        Pair<Long, Long> existingTotalProgress = totalProgress.get();
        if (increment != null) {
            while (!totalProgress.compareAndSet(existingTotalProgress, Pair.of(existingTotalProgress.getLeft() + increment.getLeft(), existingTotalProgress.getRight() + increment.getRight()))) {
                existingTotalProgress = totalProgress.get();
            }
        }

        int taskSize;
        if (t.getTaskStatus() != BackgroundTask.TaskStatus.RUNNING) {
            synchronized (tasks) {
                tasks.remove(t);
                taskSize = tasks.size();
            }
        } else {
            taskSize = tasks.size();
        }

        Double value;
        if (taskSize == 0) {
            value = 1d;
        } else if (existingTotalProgress.getRight() <= taskSize) {
            value = Double.NaN;
        } else {
            value = (double) existingTotalProgress.getLeft() / (double) existingTotalProgress.getRight();
        }

        sharedListener.accept(t, value);
    }

}
