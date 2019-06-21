package de.jpwinkler.daf.gui;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.db.BackgroundTaskNotifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;

public class BackgroundTaskExecutorImpl implements BackgroundTaskExecutor {

    private final BiConsumer<BackgroundTask, Double> sharedListener;
    private final BiConsumer<BackgroundTask, Throwable> errorListener;

    public BackgroundTaskExecutorImpl(BiConsumer<BackgroundTask, Double> sharedListener, BiConsumer<BackgroundTask, Throwable> errorListener) {
        this.sharedListener = sharedListener;
        this.errorListener = errorListener;
    }

    private final List<BackgroundTask> tasks = Collections.synchronizedList(new ArrayList<>());
    private final Map<BackgroundTaskNotifier, Consumer<BackgroundTask>> taskSpecificListeners = Collections.synchronizedMap(new WeakHashMap<>());
    private final AtomicReference<Pair<Long, Long>> totalProgress = new AtomicReference<>(Pair.of(0l, 0l));

    private final ExecutorService executor = Executors.newCachedThreadPool((Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public boolean hasRunningTasks() {
        return !tasks.isEmpty();
    }

    @Override
    public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable) {
        return this.runBackgroundTask(name, runnable, executor);
    }

    @Override
    public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService) {
        BackgroundTask<T> bt = new BackgroundTask<>(name, runnable, this::onBackgroundTaskUpdate);
        this.tasks.add(bt);
        return bt.run(executorService).exceptionally((t) -> {
            this.errorListener.accept(bt, t);
            throw new RuntimeException(t);
        });
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
