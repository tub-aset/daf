package de.jpwinkler.daf.db;

/*-
 * #%L
 * dafcore
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 *
 * @author fwiesweg
 */
public interface BackgroundTaskExecutor {

    public static final BackgroundTaskExecutor SYNCHRONOUS = new BackgroundTaskExecutor() {
        @Override
        public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable) {
            return CompletableFuture.completedFuture(runnable.apply(BackgroundTaskNotifier.SYNCHRONOUS));
        }

        @Override
        public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService) {
            return CompletableFuture.supplyAsync(() -> runnable.apply(BackgroundTaskNotifier.SYNCHRONOUS), executorService);
        }
    };

    <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable);

    <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService);

    default <T> CompletableFuture<T> runBackgroundTask(String name, AtomicReference<CompletableFuture<T>> promiseReference, Function<BackgroundTaskNotifier, T> runnable) {
        CompletableFuture<T> ref = promiseReference.get();

        if (ref == null || ref.isCompletedExceptionally()) {
            synchronized (promiseReference) {
                ref = promiseReference.get();

                if (ref != null && !ref.isCompletedExceptionally()) {
                    return ref;
                }

                promiseReference.compareAndSet(ref, this.runBackgroundTask(name, runnable));
            }
        }

        return promiseReference.get();
    }

    default <T> CompletableFuture<T> runBackgroundTask(String name, AtomicReference<CompletableFuture<T>> promiseReference, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService) {
        CompletableFuture<T> ref = promiseReference.get();

        if (ref == null || ref.isCompletedExceptionally()) {
            synchronized (promiseReference) {
                ref = promiseReference.get();

                if (ref != null && !ref.isCompletedExceptionally()) {
                    return ref;
                }

                promiseReference.compareAndSet(ref, this.runBackgroundTask(name, runnable, executorService));
            }
        }

        return promiseReference.get();
    }
}
