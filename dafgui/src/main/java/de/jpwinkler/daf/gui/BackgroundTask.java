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
import de.jpwinkler.daf.db.BackgroundTaskAbortedException;
import de.jpwinkler.daf.db.BackgroundTaskNotifier;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;

public class BackgroundTask<T> {

    public static final int PRIORITY_ATTRIBUTES = 100;
    public static final int PRIORITY_FOLDERS = -100;
    public static final int PRIORITY_MODULE_CONTENT = -200;

    private final String name;
    private final Function<BackgroundTaskNotifier, T> runnable;
    private final BiConsumer<BackgroundTask, Pair<Long, Long>> monitor;

    private final Throwable notDoneYetThrowable = new Exception();

    private final AtomicReference<Pair<Long, Long>> taskProgress = new AtomicReference<>(Pair.of(0l, 1l));
    private final AtomicReference<Throwable> taskError = new AtomicReference<>(notDoneYetThrowable);
    private final AtomicReference<CompletableFuture<T>> future = new AtomicReference<>();

    public BackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, BiConsumer<BackgroundTask, Pair<Long, Long>> monitor) {
        this.name = name;
        this.runnable = runnable;
        this.monitor = monitor;
    }

    final synchronized CompletableFuture<T> run(ExecutorService executor, int priority) {
        BackgroundTaskNotifierImpl notifier = new BackgroundTaskNotifierImpl();
        if (!this.future.compareAndSet(null, notifier.taskFuture)) {
            throw new IllegalStateException("Task already started");
        }

        this.monitor.accept(this, taskProgress.get());
        executor.execute(new ComparableRunnable(priority, () -> {
            try {
                T value = this.runnable.apply(notifier);
                notifier.taskFuture.complete(value);
            } catch (Throwable t) {
                t.printStackTrace();
                notifier.taskFuture.completeExceptionally(t);
            }
        }));
        return notifier.taskFuture;
    }

    private final class ComparableRunnable implements Comparable<Runnable>, Runnable {

        private final Runnable runnable;
        private final int priority;

        public ComparableRunnable(int priority, Runnable runnable) {
            this.runnable = runnable;
            this.priority = priority;
        }

        @Override
        public int compareTo(Runnable t) {
            return Integer.compare(priority, ((ComparableRunnable) t).priority);
        }

        @Override
        public void run() {
            runnable.run();
        }

    }

    public CompletableFuture<T> getFuture() {
        return future.get();
    }

    public TaskStatus getTaskStatus() {
        Throwable taskError = this.taskError.get();

        if (taskError == notDoneYetThrowable) {
            return TaskStatus.RUNNING;
        } else if (taskError == null) {
            return TaskStatus.DONE;
        } else {
            return TaskStatus.FAILED;
        }
    }

    public Double getCurrentProgress() {
        Pair<Long, Long> existingTaskProgress = this.taskProgress.get();
        if(existingTaskProgress.getLeft() == 0 && existingTaskProgress.getRight() == 1) {
            return -1d;
        }
        
        return (double) existingTaskProgress.getLeft() / (double) existingTaskProgress.getRight();
    }

    public Throwable getError() {
        Throwable existingTaskError = this.taskError.get();
        return existingTaskError == notDoneYetThrowable ? null : existingTaskError;
    }

    public String getName() {
        return name;
    }

    public static enum TaskStatus {
        RUNNING,
        FAILED,
        DONE
    }

    private class BackgroundTaskNotifierImpl implements BackgroundTaskNotifier {

        private final CompletableFuture<T> taskFuture = new CompletableFuture<T>() {
            @Override
            public boolean cancel(boolean bln) {
                return this.completeExceptionally(new BackgroundTaskAbortedException(BackgroundTaskNotifierImpl.this));
            }

            @Override
            public boolean completeExceptionally(Throwable t) {
                if (!this.completeTask(t)) {
                    return false;
                }

                return super.completeExceptionally(t);
            }

            @Override
            public boolean complete(T t) {
                if (!this.completeTask(null)) {
                    return false;
                }

                return super.complete(t);
            }

            private boolean completeTask(Throwable t) {
                if (!taskError.compareAndSet(notDoneYetThrowable, t)) {
                    return false;
                }

                Pair<Long, Long> existingProgress;
                do {
                    existingProgress = taskProgress.get();
                    if (existingProgress.getLeft() >= existingProgress.getRight()) {
                        throw new IllegalStateException("Progress exceeds max progress");
                    }
                } while (!taskProgress.compareAndSet(existingProgress, Pair.of(-existingProgress.getRight(), -existingProgress.getRight())));

                monitor.accept(BackgroundTask.this, taskProgress.get());
                return true;
            }

        };

        @Override
        public final boolean isCancelled() {
            return BackgroundTask.this.getTaskStatus() == TaskStatus.FAILED;
        }

        @Override
        public final void incrementProgress(long increment) {
            this.incrementProgress(increment, 0);
        }

        @Override
        public final void incrementProgress(long increment, long maxProgressIncrement) {
            if (increment == 0 && maxProgressIncrement == 0) {
                return;
            } else if (increment < 0 || maxProgressIncrement < 0) {
                throw new IllegalArgumentException("Negative increments are not allowed");
            }

            Pair<Long, Long> existingProgress;
            do {
                existingProgress = taskProgress.get();
                if (existingProgress.getLeft() < 0 && existingProgress.getRight() < 0) {
                    throw new IllegalStateException("Task already done");
                }

                if (Math.addExact(existingProgress.getLeft(), increment) >= Math.addExact(existingProgress.getRight(), maxProgressIncrement)) {
                    throw new IllegalStateException("Progress exceeds max progress");
                }
            } while (!taskProgress.compareAndSet(existingProgress, Pair.of(existingProgress.getLeft() + increment, existingProgress.getRight() + maxProgressIncrement)));

            monitor.accept(BackgroundTask.this, Pair.of(increment, maxProgressIncrement));
        }
    }
}
