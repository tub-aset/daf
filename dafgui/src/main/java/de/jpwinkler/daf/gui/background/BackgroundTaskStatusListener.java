package de.jpwinkler.daf.gui.background;

public interface BackgroundTaskStatusListener {

    void onDone();

    void onUpdateStatus(String taskName, int current, int max);

}
