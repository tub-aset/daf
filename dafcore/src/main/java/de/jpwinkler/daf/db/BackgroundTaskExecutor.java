/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 *
 * @author fwiesweg
 */
public interface BackgroundTaskExecutor {

    <T> Future<T> runBackgroundTask(String name, Function<BackgroundTaskInterface, T> runnable);

    <T> Future<T> runBackgroundTask(String name, Function<BackgroundTaskInterface, T> runnable, ExecutorService executorService);
    
}
