package de.jpwinkler.daf.csveditor.background;

public interface BackgroundTaskStatusListener {

    void onDone();

    void onUpdateStatus(String taskName, int current, int max);

}
