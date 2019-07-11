/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

/**
 *
 * @author fwiesweg
 */
public class BackgroundTaskAbortedException extends RuntimeException {
    private final BackgroundTaskNotifier notifier;

    public BackgroundTaskAbortedException(BackgroundTaskNotifier notifier) {
        this.notifier = notifier;
    }

    public BackgroundTaskNotifier getNotifier() {
        return notifier;
    }
}
