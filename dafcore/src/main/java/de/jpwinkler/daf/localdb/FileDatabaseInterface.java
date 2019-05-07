package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsModuleVersion;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.impl.DoorsDatabaseImpl;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

class FileDatabaseInterface implements DatabaseInterface {

    private final DoorsDatabase db;
    private final Path databaseFile;
    private final Path databaseRoot;

    FileDatabaseInterface(final Path databaseFile, final DoorsDatabase db) throws IOException {
        this.databaseFile = databaseFile.toAbsolutePath();
        databaseRoot = databaseFile.toAbsolutePath().getParent();
        this.db = db;
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsTreeNode dbNode = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = (DoorsModule) dbNode.getChild(newModule.getName());

        if (dbModule == null) {
            dbModule = DoorsFactory.eINSTANCE.createDoorsModule();
            dbModule.setParent(dbNode);
            dbModule.setName(newModule.getName());
        }

        DoorsModuleVersion currentVersion = newModule.getLatestVersion();

        Path csvPath = ensureCsvFilesystemPath(currentVersion);
        try ( ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(csvPath.toFile()))) {
            writer.writeModule(newModule);

            Path mmdPath = ensureMmdFilesystemPath(currentVersion);
            ModuleMetaDataParser.writeModuleMetaData(mmdPath.toFile(), newModule.getAttributes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return dbModule;
    }

    @Override
    public void flush() throws IOException {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("DoorsDatabasemodel", new XMIResourceFactoryImpl());
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createFileURI(databaseFile.toString()));
        resource.getContents().add((DoorsDatabaseImpl) db);
        resource.save(new HashMap<>());
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

    @Override
    public void removeNode(final DoorsTreeNode node) {
        if (node instanceof DoorsModule) {
            ((DoorsModule) node).setParent(null);
            ((DoorsModule) node).getVersions().forEach(v -> {
                try {
                    Files.deleteIfExists(ensureCsvFilesystemPath(v));
                    Files.deleteIfExists(ensureMmdFilesystemPath(v));
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            });
            ((DoorsModule) node).getVersions().clear();
        } else {
            final List<DoorsModule> markedForDeletion = new ArrayList<>();
            node.accept(new DoorsTreeNodeVisitor() {

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
        db.getRoot().accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (e.matches(module)) {
                    result.add(module);
                }
            }
        });
        return result;
    }

    @Override
    public DoorsTreeNode getNode(final String path) {
        final List<String> pathSegments = Arrays.asList(path.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsTreeNode current = getDatabaseObject().getRoot();
        for (final String segment : pathSegments.subList(0, pathSegments.size())) {
            current = current.getChild(segment);
            if (current == null) {
                return null;
            }
        }
        return current;
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
}
