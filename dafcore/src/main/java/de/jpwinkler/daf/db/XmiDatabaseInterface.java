package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.impl.DoorsFolderImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class XmiDatabaseInterface implements DatabaseInterface {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        DoorsPackage.eINSTANCE.eClass();
    }

    private final DoorsFolder databaseRoot;
    private final DatabasePath<XmiDatabaseInterface> databasePath;

    public XmiDatabaseInterface(DatabasePath<XmiDatabaseInterface> databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }
        this.databasePath = databasePath;

        File databaseFile = new File(databasePath.getDatabasePath());
        if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !databaseFile.exists()) {
            this.databaseRoot = DoorsModelUtil.createFolder(null, FilenameUtils.getBaseName(databaseFile.getAbsolutePath()));
            this.flush();
        } else if (openFlag == OpenFlag.ERASE_IF_EXISTS) {
            if (databaseFile.exists()) {
                databaseFile.delete();
            }
            this.databaseRoot = DoorsModelUtil.createFolder(null, FilenameUtils.getBaseName(databaseFile.getAbsolutePath()));
            this.flush();
        } else if (openFlag == OpenFlag.OPEN_ONLY && databaseFile.isFile()) {
            final Resource resource = new ResourceSetImpl().getResource(URI.createFileURI(databaseFile.toString()), true);
            this.databaseRoot = (DoorsFolder) resource.getContents().get(0);
        } else {
            throw new FileNotFoundException(databaseFile.getAbsolutePath());
        }
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsTreeNode dbParent = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = (DoorsModule) dbParent.getChild(newModule.getName());

        if (dbModule != null) {
            dbModule.setParent(null);
        }

        return DoorsModelUtil.createCopy(newModule, dbParent.getParent());
    }

    @Override
    public final void flush() throws IOException {
        final Resource resource = new ResourceSetImpl().createResource(URI.createFileURI(new File(databasePath.getDatabasePath()).getAbsolutePath()));
        resource.getContents().add((DoorsFolderImpl) databaseRoot);
        resource.save(Collections.emptyMap());
    }

    @Override
    public DatabasePath<XmiDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public DoorsTreeNode getDatabaseRoot() {
        return databaseRoot;
    }

    private DoorsTreeNode ensureDatabasePath(final DoorsTreeNode path) {
        return ensureDatabasePath(databaseRoot, path.getFullNameSegments());
    }

    private static DoorsTreeNode ensureDatabasePath(final DoorsTreeNode parent, final List<String> path) {
        if (path.size() > 0) {
            DoorsTreeNode node = parent.getChild(path.get(0));
            if (node == null) {
                node = DoorsModelUtil.createFolder(parent, path.get(0));
            }
            if (path.size() > 1) {
                return ensureDatabasePath(parent.getChild(path.get(0)), path.subList(1, path.size()));
            } else {
                return node;
            }
        } else {
            return null;
        }
    }
}
