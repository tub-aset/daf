package de.jpwinkler.daf.fap5gui.model;

import java.util.HashMap;
import java.util.Map;

public class Version {

    private final Map<String, DocumentSnapshot> documentSnapshots = new HashMap<>();
    private String versionString;

    public Map<String, DocumentSnapshot> getDocumentSnapshots() {
        return documentSnapshots;
    }

    public void setVersionString(final String versionString) {
        this.versionString = versionString;
    }

    public String getVersionString() {
        return versionString;
    }

}
