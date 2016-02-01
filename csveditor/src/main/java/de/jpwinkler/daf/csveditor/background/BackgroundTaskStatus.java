package de.jpwinkler.daf.csveditor.background;

public class BackgroundTaskStatus {

    private int current;
    private int max;

    public BackgroundTaskStatus(final int current, final int max) {
        super();
        this.current = current;
        this.max = max;
    }

    public BackgroundTaskStatus() {
        super();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(final int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(final int max) {
        this.max = max;
    }


}
