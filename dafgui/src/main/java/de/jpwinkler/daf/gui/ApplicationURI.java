/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fwiesweg
 */
public class ApplicationURI implements Serializable {

    public ApplicationURI(ApplicationURI parentURI, ApplicationPart applicationPart, String path) {
        this.parentURI = parentURI;
        this.applicationPart = applicationPart;
        this.path = path;
    }

    public ApplicationURI(ApplicationPart applicationPart, String path) {
        this(null, applicationPart, path);
    }

    private final ApplicationURI parentURI;
    private final ApplicationPart applicationPart;
    private final String path;

    public ApplicationPart getApplicationPart() {
        return applicationPart;
    }

    public ApplicationURI getParentURI() {
        return parentURI;
    }


    public String getPath() {
        return path;
    }

    public boolean isValid() {
        return path != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.path, this.applicationPart);
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
        final ApplicationURI other = (ApplicationURI) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (this.applicationPart != other.applicationPart) {
            return false;
        }
        return true;
    }

    public List<String> getPathSegments() {
        return path == null ? Collections.emptyList() : Arrays.asList(path.split("/"));
    }

    @Override
    public String toString() {
        return this.applicationPart.toString() + ": " + (this.path == null ? applicationPart.getUnnamedName() : this.path.replaceFirst("^.*/", ""));
    }

}
