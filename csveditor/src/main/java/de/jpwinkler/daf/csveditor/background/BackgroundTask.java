package de.jpwinkler.daf.csveditor.background;

public abstract class BackgroundTask implements Runnable {

    private BackgroundTaskStatusMonitor monitor;
    private final String name;

    public BackgroundTask(final String name) {
        super();
        this.name = name;
    }

    protected void startProgress() {
        monitor.startProgressTracking(this);
    }

    protected void updateProgress(final int current, final int max) {
        monitor.updateProgress(this, current, max);
    }

    protected void finishProgress() {
        monitor.finishProgressTracking(this);
    }

    public String getName() {
        return name;
    }

    public void setMonitor(final BackgroundTaskStatusMonitor monitor) {
        this.monitor = monitor;
    }

    protected void stepProgress() {
        monitor.stepProgress(this);
    }
}