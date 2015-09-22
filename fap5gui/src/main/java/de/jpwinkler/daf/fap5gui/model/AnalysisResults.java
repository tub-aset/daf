package de.jpwinkler.daf.fap5gui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalysisResults {

    private final List<Version> versions = new ArrayList<>();

    public List<Version> getVersions() {
        return Collections.unmodifiableList(versions);
    }

    public List<String> getVersionNumbers() {
        return versions.stream().map(Version::getVersionString).collect(Collectors.toList());
    }

    public List<String> getAllDocumentNames() {
        return versions.stream().map(Version::getDocumentNames).flatMap(set -> set.stream()).distinct().collect(Collectors.toList());
    }

    public Version getLatestVersion() {
        return versions.get(versions.size() - 1);
    }

    public Version findVersion(final String versionNumber) {
        final Optional<Version> version = versions.stream().filter(v -> v.getVersionString().equals(versionNumber)).findFirst();

        return version.isPresent() ? version.get() : null;
    }

    public void addVersion(final Version v) {
        Version oldVersion;
        while ((oldVersion = findVersion(v.getVersionString())) != null) {
            versions.remove(oldVersion);
        }
        versions.add(v);
        Collections.sort(versions);
    }
}
