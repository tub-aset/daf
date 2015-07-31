package de.jpwinkler.daf.fap5gui.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Version implements Comparable<Version> {

    private final Map<String, DocumentSnapshot> documentSnapshots = new HashMap<>();
    private String versionString;
    private Date date;

    public Set<String> getDocumentNames() {
        return documentSnapshots.keySet();
    }

    public Map<String, DocumentSnapshot> getDocumentSnapshots() {
        return documentSnapshots;
    }

    public void setVersionString(final String versionString) {
        this.versionString = versionString;
    }

    public String getVersionString() {
        return versionString;
    }

    public DocumentSnapshot accumulateResults(final Predicate<DocumentSnapshot> filter) {

        final DocumentSnapshot summary = new DocumentSnapshot();
        final List<String> allMetrics = documentSnapshots.values().stream().map(ds -> ds.getMetricNames()).flatMap(metrics -> metrics.stream()).distinct().collect(Collectors.toList());

        for (final String metric : allMetrics) {
            summary.setMetric(metric, documentSnapshots.values().stream().filter(ds -> filter.test(ds)).mapToInt(ds -> ds.getMetric(metric) != null ? ds.getMetric(metric) : 0).sum());
        }

        documentSnapshots.values().stream().forEach(ds -> summary.getIssues().addAll(ds.getIssues()));

        return summary;
    }

    public DocumentSnapshot accumulateAllResults() {
        return accumulateResults(ds -> true);
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(final Version o) {
        return date.compareTo(o.date);
    }
}
