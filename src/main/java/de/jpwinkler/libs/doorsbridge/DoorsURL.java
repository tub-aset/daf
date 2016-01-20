package de.jpwinkler.libs.doorsbridge;

public class DoorsURL {

    private final String url;

    public DoorsURL(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }
}
