/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fwiesweg
 */
public class DatabasePath<T extends DatabaseInterface> implements Serializable {

    public DatabasePath(Class<T> databaseInterface, String databasePath, String path) {
        Objects.requireNonNull(databaseInterface);

        this.databaseInterface = databaseInterface;
        this.databasePath = databasePath;
        this.path = path;
    }

    public DatabasePath(Class<T> databaseInterface, String fullPath) {
        Objects.requireNonNull(databaseInterface);
        this.databaseInterface = databaseInterface;

        String[] s = fullPath.split(":");
        if (s.length == 0 || s.length > 2) {
            throw new IllegalArgumentException("fullPath must contain at most one colon");
        }

        this.databasePath = s[1];
        this.path = s.length > 1 ? s[2] : "";
    }

    private final Class<T> databaseInterface;
    private final String databasePath;
    private final String path;

    public Class<T> getDatabaseInterface() {
        return databaseInterface;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public String getPath() {
        return path;
    }

    public DatabasePath withPath(String path) {
        return new DatabasePath(this.databaseInterface, this.databasePath, path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.databaseInterface, this.databasePath, this.path);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatabasePath<?> other = (DatabasePath<?>) obj;
        if (!Objects.equals(this.databasePath, other.databasePath)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.databaseInterface, other.databaseInterface)) {
            return false;
        }
        return true;
    }

    public List<String> getPathSegments() {
        return path == null ? Collections.emptyList() : Arrays.asList(path.split("/"));
    }

    @Override
    public String toString() {
        return this.databaseInterface.getSimpleName() + ": " + this.databasePath + (this.path == null || this.path.isEmpty() ? "" : (":" + this.path));
    }

}
