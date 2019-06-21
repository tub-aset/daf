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
