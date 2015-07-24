package de.jpwinkler.daf.fap5gui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DocumentSnapshot {

    private String documentName;

    private String documentPath;

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(final String documentPath) {
        this.documentPath = documentPath;
    }

    private final Map<String, Integer> metrics = new HashMap<>();

    private final List<Issue> issues = new ArrayList<>();

    public List<Issue> getIssues() {
        return issues;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(final String documentName) {
        this.documentName = documentName;
    }

    public Integer getMetric(final String name) {
        return metrics.get(name);
    }

    public void setMetric(final String name, final int value) {
        metrics.put(name, value);
    }

    public Set<String> getMetricNames() {
        return metrics.keySet();
    }
}
