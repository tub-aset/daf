package de.jpwinkler.daf.db;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.model.DoorsPackage;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 *
 * @author fwiesweg
 */
public class DatabasePath implements Serializable {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        DoorsPackage.eINSTANCE.eClass();
    }

    public DatabasePath(Class<? extends DatabaseInterface> databaseInterface, String databasePath, String path) {
        this(databaseInterface.getCanonicalName(), databasePath, path);
    }

    public DatabasePath(String databaseInterface, String databasePath, String path) {
        Objects.requireNonNull(databaseInterface);

        this.databaseInterface = databaseInterface;
        this.databasePath = databasePath;
        this.path = path;
    }

    private final String databaseInterface;
    private final String databasePath;
    private final String path;

    public String getDatabaseInterface() {
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
        final DatabasePath other = (DatabasePath) obj;
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

    public boolean isParent(DatabasePath possibleParent) {
        if (!Objects.equals(this.databaseInterface, possibleParent.databaseInterface)) {
            return false;
        }
        if (!Objects.equals(this.databasePath, possibleParent.databasePath)) {
            return false;
        }
        if (this.path == null || possibleParent.path == null) {
            return false;
        }
        if (Objects.equals(this.path, possibleParent.path)) {
            return false;
        }
        if (!possibleParent.path.startsWith(this.path)) {
            return false;
        }

        return true;
    }

    public List<String> getDatabasePathSegments() {
        return databasePath == null || databasePath.isEmpty() ? Collections.emptyList() : Arrays.asList(databasePath.split("/"));
    }

    public List<String> getPathSegments() {
        return path == null || path.isEmpty() ? Collections.emptyList() : Arrays.asList(path.split("/"));
    }

    public boolean isRoot() {
        return path == null || path.isEmpty() || "/".equals(path);
    }

    @Override
    public String toString() {
        String shortName = this.databaseInterface;
        shortName = shortName.substring(shortName.lastIndexOf('.') + 1);

        return shortName + ":" + this.databasePath + (this.path == null || this.path.isEmpty() ? "" : (":" + this.path));
    }

}
