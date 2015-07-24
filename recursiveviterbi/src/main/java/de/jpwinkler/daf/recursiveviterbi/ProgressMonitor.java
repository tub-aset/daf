package de.jpwinkler.daf.recursiveviterbi;

public interface ProgressMonitor {

    void onProgress(int current, int max);

}
