package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.model.DoorsCSVFactory;
import de.jpwinkler.daf.model.DoorsCSVPackage;
import de.jpwinkler.daf.model.DoorsDB;
import de.jpwinkler.daf.model.DoorsModuleVersion;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class FileDatabaseInterface implements DatabaseInterface {

    private final DoorsDB db;
    private final Path databaseFile;
    private final Path databaseRoot;

    public static FileDatabaseInterface createOrOpenDB() throws IOException {
        return createOrOpenDB(DatabaseInterfaceUtils.getDefaultDatabaseDirectory(FileDatabaseInterface.class).resolve("db.doorsdbmodel"));
    }

    public static FileDatabaseInterface createOrOpenDB(Path databaseFile) throws IOException {
        DoorsDB db;

        databaseFile = databaseFile.toAbsolutePath();
        if (Files.exists(databaseFile)) {
            DoorsCSVPackage.eINSTANCE.eClass();

            final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
            reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());
            final ResourceSet resourceSet = new ResourceSetImpl();
            final Resource resource = resourceSet.getResource(URI.createFileURI(databaseFile.toString()), true);
            db = (DoorsDB) resource.getContents().get(0);
        } else {
            db = DoorsCSVFactory.eINSTANCE.createDoorsDB();
            db.setRoot(DoorsCSVFactory.eINSTANCE.createDoorsFolder());
            final FileDatabaseInterface doorsDBInterface = new FileDatabaseInterface(databaseFile, db);
            doorsDBInterface.flush();
        }

        return new FileDatabaseInterface(databaseFile, db);
    }

    private FileDatabaseInterface(final Path databaseFile, final DoorsDB db) throws IOException {
        this.databaseFile = databaseFile.toAbsolutePath();
        databaseRoot = databaseFile.toAbsolutePath().getParent();
        this.db = db;
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsFolder folder = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = folder.getModule(newModule.getName());

        if (dbModule == null) {
            dbModule = DoorsCSVFactory.eINSTANCE.createDoorsModule();
            dbModule.setParent(folder);
            dbModule.setName(newModule.getName());
        }

        DoorsModuleVersion currentVersion = newModule.getLatestVersion();

        Path csvPath = ensureCsvFilesystemPath(currentVersion);
        try ( ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(csvPath.toFile()))) {
            writer.writeModule(newModule);

            Path mmdPath = ensureMmdFilesystemPath(currentVersion);
            ModuleMetaDataParser.writeModuleMetaData(mmdPath.toFile(), newModule.getAttributes().map());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        return dbModule;
    }

    @Override
    public void flush() throws IOException {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createFileURI(databaseFile.toString()));
        resource.getContents().add(db);
        resource.save(new HashMap<>());
    }

    @Override
    public DoorsDB getDatabaseObject() {
        return db;
    }

    @Override
    public void removeModule(final DoorsModule module) {
        module.setParent(null);
        module.getVersions().forEach(v -> {
            try {
                Files.deleteIfExists(ensureCsvFilesystemPath(v));
                Files.deleteIfExists(ensureMmdFilesystemPath(v));
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        });
        module.getVersions().clear();
    }

    @Override
    public void removeFolder(final DoorsFolder folder) {
        final List<DoorsModule> markedForDeletion = new ArrayList<>();
        folder.accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                markedForDeletion.add(module);
            }

        });
        markedForDeletion.forEach(m -> removeModule(m));
        folder.setParent(null);
    }

    @Override
    public List<DoorsModule> getModules() {
        final List<DoorsModule> result = new ArrayList<>();
        db.getRoot().accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                result.add(module);
            }
        });
        return result;
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
    public DoorsFolder getFolder(final String path) {
        final List<String> pathSegments = Arrays.asList(path.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsFolder current = getDatabaseObject().getRoot();
        for (final String segment : pathSegments.subList(0, pathSegments.size())) {
            current = current.getFolder(segment);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    @Override
    public DoorsModule getModule(final String path) {
        final List<String> pathSegments = Arrays.asList(path.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsFolder current = getDatabaseObject().getRoot();
        for (final String segment : pathSegments.subList(0, pathSegments.size() - 1)) {
            current = current.getFolder(segment);
            if (current == null) {
                return null;
            }
        }
        return current.getModule(pathSegments.get(pathSegments.size() - 1));
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

    private DoorsFolder ensureDatabasePath(final DoorsTreeNode path) {
        return ensureDatabasePath(db.getRoot(), path.getFullNameSegments());
    }

    private static DoorsFolder ensureDatabasePath(final DoorsFolder parent, final List<String> path) {
        if (path.size() > 0) {
            DoorsFolder folder = parent.getFolder(path.get(0));
            if (folder == null) {
                folder = DoorsCSVFactory.eINSTANCE.createDoorsFolder();
                folder.setName(path.get(0));
                parent.getChildren().add(folder);
            }
            if (path.size() > 1) {
                return ensureDatabasePath(parent.getFolder(path.get(0)), path.subList(1, path.size()));
            } else {
                return folder;
            }
        } else {
            return null;
        }
    }

    @Override
    public void removeTag(DoorsModule module, String tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTag(DoorsModule module, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getTags(DoorsModule doorsModule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
