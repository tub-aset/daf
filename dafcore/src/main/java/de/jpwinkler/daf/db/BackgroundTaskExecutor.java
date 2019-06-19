/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/**
 *
 * @author fwiesweg
 */
public interface BackgroundTaskExecutor {

    public static final BackgroundTaskExecutor SYNCHRONOUS = new BackgroundTaskExecutor() {
        @Override
        public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable) {
            return CompletableFuture.completedFuture(runnable.apply(new BackgroundTaskNotifier() {
                @Override
                public void incrementProgress(long increment) {
                }

                @Override
                public void incrementProgress(long increment, long maxProgressIncrement) {
                }
            }));
        }

        @Override
        public <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService) {
            return CompletableFuture.supplyAsync(() -> runnable.apply(new BackgroundTaskNotifier() {
                @Override
                public void incrementProgress(long increment) {
                }

                @Override
                public void incrementProgress(long increment, long maxProgressIncrement) {
                }
            }), executorService);
        }
    };

    <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable);

    <T> CompletableFuture<T> runBackgroundTask(String name, Function<BackgroundTaskNotifier, T> runnable, ExecutorService executorService);

}
