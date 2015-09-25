package de.jpwinkler.daf.documenttagging.recursiveviterbi;

/**
 * This interface may be implemented by subclasses to observe the progress of
 * the document tagging algorithm {@link AbstractRecursiveViterbiAlgorithm}.
 *
 * @author jonwink
 *
 */
public interface ProgressMonitor {

    /**
     * Called whenever the progress of the document tagging algorithm advances.
     * The algorithm is (nearly) done, if current is equal to max or only
     * insignificantly smaller than max. The algorithm may not call this method
     * with current = max after completion.
     *
     * @param current
     * @param max
     */
    void onProgress(int current, int max);

}
