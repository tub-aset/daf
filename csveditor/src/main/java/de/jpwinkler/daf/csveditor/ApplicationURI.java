/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.csveditor;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fwiesweg
 */
public class ApplicationURI implements Serializable {

    public ApplicationURI(ApplicationPart applicationPart, String path) {
        this.applicationPart = applicationPart;
        this.path = path;
    }

    private final ApplicationPart applicationPart;
    private final String path;

    public ApplicationPart getApplicationPart() {
        return applicationPart;
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

}
