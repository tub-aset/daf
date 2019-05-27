package de.jpwinkler.daf.db;

import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.filter.modules.SearchExpression;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

class FileDatabaseInterface implements DatabaseInterface {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        DoorsPackage.eINSTANCE.eClass();
    }

    private static final String DATABASE_ITEMNAME = "DoorsDatabasemodel";
    private static final String DATABASE_FILENAME = "db." + DATABASE_ITEMNAME;

    private final DoorsDatabase db;
    private Path databaseRoot;

    FileDatabaseInterface(final Path databaseRoot) throws IOException {
        this.databaseRoot = databaseRoot;

        if (databaseRoot != null) {
            Files.createDirectories(databaseRoot);

            final Resource resource = new ResourceSetImpl().getResource(URI.createFileURI(databaseRoot.resolve(DATABASE_FILENAME).toString()), true);
            this.db = (DoorsDatabase) resource.getContents().get(0);
        } else {
            this.db = DoorsFactory.eINSTANCE.createDoorsDatabase();
            this.db.setRoot(DoorsModelUtil.createFolder(null));
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
        if (databaseRoot == null) {
            throw new IllegalStateException("No databaseRoot set");
        }

        final Resource resource = new ResourceSetImpl().createResource(URI.createFileURI(databaseRoot.resolve(DATABASE_FILENAME).toString()));
        resource.getContents().add((DoorsDatabaseImpl) db);
        resource.save(Collections.emptyMap());

        db.getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            protected boolean visitPreTraverse(DoorsModule m) {
                Path csvPath = ensureCsvFilesystemPath(m);
                try ( ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(csvPath.toFile()))) {
                    writer.writeModule(m);

                    Path mmdPath = ensureMmdFilesystemPath(m);
                    ModuleMetaDataParser.writeModuleMetaData(mmdPath.toFile(), m.getAttributes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                return super.visitPreTraverse(m);
            }

        });
    }

    @Override
    public void setPath(String path) {
        this.databaseRoot = new File(path).toPath();
    }

    @Override
    public String getPath() {
        return this.databaseRoot == null ? null : this.databaseRoot.toAbsolutePath().toString();
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

    @Override
    public void removeNode(final DoorsTreeNode node) {
        if(node instanceof DoorsObject) {
            node.getParent().getChildren().remove(node);
        } else if (node instanceof DoorsModule) {
            ((DoorsModule) node).setParent(null);
            try {
                Files.deleteIfExists(ensureCsvFilesystemPath(((DoorsModule) node)));
                Files.deleteIfExists(ensureMmdFilesystemPath(((DoorsModule) node)));
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

    @Override
    public List<DoorsModule> getModules(final SearchExpression e) {
        final List<DoorsModule> result = new ArrayList<>();
        db.getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (e.matches(module)) {
                    result.add(module);
                }
            }
        });
        return result;
    }

    private Path ensureCsvFilesystemPath(final DoorsModule m) {
        try {
            Path p = Paths.get(databaseRoot.toString(), m.getFullName() + ".csv");
            Files.createDirectories(p.getParent());
            return p;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Path ensureMmdFilesystemPath(final DoorsModule m) {
        try {
            Path p = Paths.get(ensureCsvFilesystemPath(m).toString() + ".mmd");
            Files.createDirectories(p.getParent());
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
                node = DoorsModelUtil.createFolder(path.get(0));
                parent.getChildren().add(node);
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

    @Override
    public boolean isReadOnly() {
        return false;
    }
}
