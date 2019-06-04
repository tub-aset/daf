package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.impl.DoorsDatabaseImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class FileDatabaseInterface implements DatabaseInterface {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        DoorsPackage.eINSTANCE.eClass();
    }

    private static final String DATABASE_ITEMNAME = "DoorsDatabasemodel";
    private static final String DATABASE_FILENAME = "db." + DATABASE_ITEMNAME;

    private final DoorsDatabase db;
    private DatabasePath<FileDatabaseInterface> databasePath;

    public FileDatabaseInterface(DatabasePath<FileDatabaseInterface> databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;
        Path databaseRoot = Paths.get(databasePath.getDatabasePath());

        if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !databaseRoot.toFile().isDirectory()) {
            Files.createDirectories(databaseRoot);
        } else if (openFlag == OpenFlag.ERASE_IF_EXISTS && databaseRoot.toFile().exists()) {
            FileUtils.deleteDirectory(databaseRoot.toFile());
            Files.createDirectories(databaseRoot);
        } else if (openFlag == OpenFlag.OPEN_ONLY && (!databaseRoot.toFile().isDirectory() || !databaseRoot.resolve(DATABASE_FILENAME).toFile().isFile())) {
            throw new FileNotFoundException(databaseRoot.toFile().getAbsolutePath());
        }
        
        if(databaseRoot.resolve(DATABASE_FILENAME).toFile().isFile()) {
            final Resource resource = new ResourceSetImpl().getResource(URI.createFileURI(databaseRoot.resolve(DATABASE_FILENAME).toString()), true);
            this.db = (DoorsDatabase) resource.getContents().get(0);
        } else {
            this.db = DoorsFactory.eINSTANCE.createDoorsDatabase();
            this.db.setRoot(DoorsModelUtil.createFolder(null, FilenameUtils.getBaseName(databaseRoot.toString())));
            this.flush();
        }
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsTreeNode dbParent = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = (DoorsModule) dbParent.getChild(newModule.getName());

        if (dbModule != null) {
            removeNode(dbModule);
        }

        return DoorsModelUtil.createCopy(newModule, dbParent.getParent());
    }

    @Override
    public final void flush() throws IOException {
        if (databasePath.getDatabasePath() == null) {
            throw new IllegalStateException("No database path set");
        }

        final Resource resource = new ResourceSetImpl().createResource(URI.createFileURI(Paths.get(databasePath.getDatabasePath()).resolve(DATABASE_FILENAME).toString()));
        resource.getContents().add((DoorsDatabaseImpl) db);
        resource.save(Collections.emptyMap());

        db.getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            protected boolean visitPreTraverse(DoorsModule m) {
                Path modulePath = ensureModuleFolderPath(m);
                try {
                    ModuleCSV.write(
                            modulePath.resolve(m.getName() + ".csv").toFile(),
                            modulePath.resolve(m.getName() + ".mmd").toFile(), m);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                return super.visitPreTraverse(m);
            }

        });
    }

    @Override
    public DatabasePath<FileDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

    @Override
    public void removeNode(final DoorsTreeNode node) {
        if (node instanceof DoorsObject) {
            node.getParent().getChildren().remove(node);
        } else if (node instanceof DoorsModule) {
            ((DoorsModule) node).setParent(null);
            try {
                Path p = ensureModuleFolderPath(((DoorsModule) node));
                Files.deleteIfExists(p.resolve(node.getName() + ".csv"));
                Files.deleteIfExists(p.resolve(node.getName() + ".mmd"));
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        } else if (node instanceof DoorsFolder) {
            final List<DoorsModule> markedForDeletion = new ArrayList<>();
            node.accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

                @Override
                public void visitPostTraverse(final DoorsModule module) {
                    markedForDeletion.add(module);
                }

            });
            markedForDeletion.forEach(m -> removeNode(m));
            node.setParent(null);
        }
    }

    private Path ensureModuleFolderPath(final DoorsModule m) {
        try {
            Path p = Paths.get(databasePath.getDatabasePath(), m.getFullNameSegments().toArray(new String[0])).getParent();
            Files.createDirectories(p);
            return p;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private DoorsTreeNode ensureDatabasePath(final DoorsTreeNode path) {
        return ensureDatabasePath(db.getRoot(), path.getFullNameSegments());
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
