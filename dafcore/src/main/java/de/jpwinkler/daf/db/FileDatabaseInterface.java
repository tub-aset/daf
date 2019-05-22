package de.jpwinkler.daf.db;

import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsModuleVersion;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.impl.DoorsDatabaseImpl;
import de.jpwinkler.daf.filter.modules.SearchExpression;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

class FileDatabaseInterface implements DatabaseInterface {

    private static final String DATABASE_ITEMNAME = "DoorsDatabasemodel";
    private static final String DATABASE_FILENAME = "db." + DATABASE_ITEMNAME;

    private final DoorsDatabase db;
    private Path databaseRoot;

    FileDatabaseInterface(final Path databaseRoot) throws IOException {
        this.databaseRoot = databaseRoot;

        if (databaseRoot != null) {
            Files.createDirectories(databaseRoot);

            //DoorsPackage.eINSTANCE.eClass();
            final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
            reg.getExtensionToFactoryMap().put(DATABASE_ITEMNAME, new XMIResourceFactoryImpl());
            final ResourceSet resourceSet = new ResourceSetImpl();
            final Resource resource = resourceSet.getResource(
                    org.eclipse.emf.common.util.URI.createFileURI(databaseRoot.resolve(DATABASE_FILENAME).toString()), true);
            this.db = (DoorsDatabase) resource.getContents().get(0);
        } else {
            this.db = DoorsFactory.eINSTANCE.createDoorsDatabase();
            this.db.setRoot(DoorsFactory.eINSTANCE.createDoorsTreeNode());
        }
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsTreeNode dbNode = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = (DoorsModule) dbNode.getChild(newModule.getName());

        if (dbModule != null) {
            removeNode(dbModule);
        }

        dbModule = DoorsFactory.eINSTANCE.createDoorsModule();
        dbModule.copyFrom(newModule, dbNode);
        return dbModule;
    }

    @Override
    public final void flush() throws IOException {
        if (databaseRoot == null) {
            throw new IllegalStateException("No databaseRoot set");
        }

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put(DATABASE_ITEMNAME, new XMIResourceFactoryImpl());
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(
                org.eclipse.emf.common.util.URI.createFileURI(databaseRoot.resolve(databaseRoot).toString()));
        resource.getContents().add((DoorsDatabaseImpl) db);
        resource.save(new HashMap<>());

        db.getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            protected boolean visitPreTraverse(DoorsModule module) {
                if (databaseRoot != null) {
                    DoorsModuleVersion currentVersion = module.getLatestVersion();
                    Path csvPath = ensureCsvFilesystemPath(currentVersion);
                    try ( ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(csvPath.toFile()))) {
                        writer.writeModule(module);

                        Path mmdPath = ensureMmdFilesystemPath(currentVersion);
                        ModuleMetaDataParser.writeModuleMetaData(mmdPath.toFile(), module.getAttributes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                return super.visitPreTraverse(module);
            }

        });
    }

    @Override
    public void flushTo(String path) throws IOException {
        this.databaseRoot = new File(path).toPath();
        this.flush();
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

    @Override
    public void removeNode(final DoorsTreeNode node) {
        if (node instanceof DoorsModule) {
            ((DoorsModule) node).setParent(null);

            if (databaseRoot != null) {
                ((DoorsModule) node).getVersions().forEach(v -> {
                    try {
                        Files.deleteIfExists(ensureCsvFilesystemPath(v));
                        Files.deleteIfExists(ensureMmdFilesystemPath(v));
                    } catch (final IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            ((DoorsModule) node).getVersions().clear();
        } else {
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

    private Path ensureCsvFilesystemPath(final DoorsModuleVersion v) {
        try {
            Path p = Paths.get(databaseRoot.toString(), v.getModule().getParent().getFullName(), v.getModule().getName() + "_" + new SimpleDateFormat("yyyyMMdd").format(v.getDate()) + ".csv");
            Files.createDirectories(ensureCsvFilesystemPath(v).getParent());
            return p;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Path ensureMmdFilesystemPath(final DoorsModuleVersion version) {
        try {
            Path p = Paths.get(ensureCsvFilesystemPath(version).toString() + ".mmd");
            Files.createDirectories(ensureCsvFilesystemPath(version).getParent());
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
            DoorsTreeNode folder = parent.getChild(path.get(0));
            if (folder == null) {
                folder = DoorsFactory.eINSTANCE.createDoorsTreeNode();
                folder.setName(path.get(0));
                parent.getChildren().add(folder);
            }
            if (path.size() > 1) {
                return ensureDatabasePath(parent.getChild(path.get(0)), path.subList(1, path.size()));
            } else {
                return folder;
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
