package de.jpwinkler.daf.gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.commons.lang3.tuple.Pair;

public class BackgroundTask {

    private final String name;
    private final Consumer<BackgroundTaskInterface> runnable;
    private final BiConsumer<BackgroundTask, Pair<Long, Long>> monitor;

    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    private final AtomicReference<Pair<Long, Long>> taskProgress = new AtomicReference<>(null);
    private final AtomicReference<Throwable> taskError = new AtomicReference<>();

    public BackgroundTask(String name, Consumer<BackgroundTaskInterface> runnable, BiConsumer<BackgroundTask, Pair<Long, Long>> monitor) {
        this.name = name;
        this.runnable = runnable;
        this.monitor = monitor;
    }

    public final Future<?> run(ExecutorService executor) {
        if (!taskProgress.compareAndSet(null, Pair.of(0l, 1l))) {
            throw new IllegalStateException("Task already started");
        }
        this.monitor.accept(this, taskProgress.get());

        return executor.submit(() -> {
            try {
                this.runnable.accept(new BackgroundTaskInterface());
                this.finish(null);
            } catch (Throwable t) {
                this.finish(t);
            }
        });
    }

    public final void cancel() {
        this.cancelled.set(true);
    }

    public final boolean isCancelled() {
        return this.cancelled.get();
    }

    public TaskStatus getTaskStatus() {
        Pair<Long, Long> existingTaskProgress = this.taskProgress.get();

        if (existingTaskProgress == null || existingTaskProgress.getLeft() < existingTaskProgress.getRight()) {
            return TaskStatus.RUNNING;
        } else if (taskError.get() == null) {
            return TaskStatus.FAILED;
        } else {
            return TaskStatus.DONE;
        }
    }

    public Double getCurrentProgress() {
        Pair<Long, Long> existingTaskProgress = this.taskProgress.get();
        if (existingTaskProgress == null || (existingTaskProgress.getLeft() == 0 && existingTaskProgress.getRight() == 1)) {
            return null;
        }

        return (double) existingTaskProgress.getLeft() / (double) existingTaskProgress.getRight();
    }

    public Throwable getError() {
        return taskError.get();
    }

    public String getName() {
        return name;
    }

    public static enum TaskStatus {
        RUNNING,
        FAILED,
        DONE
    }

    protected void finish(Throwable t) {
        Pair<Long, Long> existingProgress;
        do {
            existingProgress = taskProgress.get();
            if (existingProgress.getLeft() >= existingProgress.getRight()) {
                throw new IllegalStateException("Progress exceeds max progress");
            }
        } while (!taskProgress.compareAndSet(existingProgress, Pair.of(existingProgress.getRight(), existingProgress.getRight())));

        if (!taskError.compareAndSet(null, t)) {
            // should never happen
            throw new IllegalStateException("Error has already been set");
        }

        monitor.accept(this, Pair.of(-existingProgress.getLeft(), -existingProgress.getRight()));
    }

    public class BackgroundTaskInterface {

        protected final void incrementProgress(long increment) {
            this.incrementProgress(increment, 0);
        }

        protected final void incrementProgress(long increment, long maxProgressIncrement) {
            if (increment < 0 || maxProgressIncrement < 0) {
                throw new IllegalArgumentException("Negative increments are not allowed");
            } else if (increment == 0 && maxProgressIncrement == 0) {
                return;
            }

            Pair<Long, Long> existingProgress;
            do {
                existingProgress = taskProgress.get();
                if (Math.addExact(existingProgress.getLeft(), increment) >= Math.addExact(existingProgress.getRight(), maxProgressIncrement)) {
                    throw new IllegalStateException("Progress exceeds max progress");
                }
            } while (!taskProgress.compareAndSet(existingProgress, Pair.of(existingProgress.getLeft() + increment, existingProgress.getRight() + maxProgressIncrement)));

            monitor.accept(BackgroundTask.this, Pair.of(increment, maxProgressIncrement));
        }
    }
}
