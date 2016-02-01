package de.jpwinkler.daf.csveditor.background;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BackgroundTaskStatusMonitor {

    private final Map<BackgroundTask, BackgroundTaskStatus> taskStatus = new HashMap<>();

    private final List<BackgroundTaskStatusListener> listeners = new ArrayList<>();

    public synchronized void finishProgressTracking(final BackgroundTask backgroundTask) {
        taskStatus.remove(backgroundTask);
        notifyListeners();
    }

    public synchronized void updateProgress(final BackgroundTask backgroundTask, final int current, final int max) {
        if (!taskStatus.containsKey(backgroundTask)) {
            startProgressTracking(backgroundTask);
        }

        taskStatus.get(backgroundTask).setCurrent(current);
        taskStatus.get(backgroundTask).setMax(max);
        notifyListeners();
    }

    public synchronized void startProgressTracking(final BackgroundTask backgroundTask) {
        taskStatus.put(backgroundTask, new BackgroundTaskStatus(0, 1));
        notifyListeners();
    }

    private void notifyListeners() {
        final int current = taskStatus.values().stream().mapToInt(status -> status.getCurrent()).sum();
        final int max = taskStatus.values().stream().mapToInt(status -> status.getMax()).sum();

        if (taskStatus.size() == 0) {
            listeners.forEach(t -> {
                t.onDone();
            });
        } else if (taskStatus.size() == 1) {
            listeners.forEach(t -> {
                final Entry<BackgroundTask, BackgroundTaskStatus> e = taskStatus.entrySet().iterator().next();
                t.onUpdateStatus(e.getKey().getName(), e.getValue().getCurrent(), e.getValue().getMax());
            });
        } else {
            listeners.forEach(t -> {
                t.onUpdateStatus(String.format("%d tasks running.", taskStatus.size()), current, max);
            });
        }
    }

    public void addListener(final BackgroundTaskStatusListener backgroundTaskStatusListener) {
        listeners.add(backgroundTaskStatusListener);
    }

    public void stepProgress(final BackgroundTask backgroundTask) {
        taskStatus.get(backgroundTask).setCurrent(taskStatus.get(backgroundTask).getCurrent() + 1);
        notifyListeners();
    }

}
