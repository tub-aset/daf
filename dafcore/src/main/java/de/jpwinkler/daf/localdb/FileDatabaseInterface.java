package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsApplicationFactory;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.ItemRef;
import de.jpwinkler.daf.bridge.ModuleRef;
import de.jpwinkler.daf.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.model.DoorsCSVFactory;
import de.jpwinkler.daf.model.DoorsCSVPackage;
import de.jpwinkler.daf.model.DoorsDB;
import de.jpwinkler.daf.model.DoorsDatabaseVersion;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsModuleUtil;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class FileDatabaseInterface implements DatabaseInterface {

    private final DoorsDB db;
    private final Path databaseFile;
    private final Path databaseRoot;

    private final DoorsApplication app = DoorsApplicationFactory.getDoorsApplication();

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

    private Path getCSVLocation(final DoorsDatabaseVersion v) {
        return Paths.get(databaseRoot.toString(), v.getModule().getParent().getFullName(), v.getModule().getName() + "_" + new SimpleDateFormat("yyyyMMdd").format(v.getDate()) + ".csv");
    }

    private Path getMMDLocation(final DoorsDatabaseVersion v) {
        return Paths.get(getCSVLocation(v).toString() + ".mmd");
    }

    @Override
    public DoorsModule addModule(final String moduleName) {
        final ItemRef i = app.getItem(moduleName);

        if (!i.exists()) {
            throw new RuntimeException(moduleName + " does not exist.");
        }

        if (!(i.getType() == DoorsItemType.FORMAL)) {
            throw new RuntimeException(moduleName + " is not a formal module.");
        }

        final DoorsFolder folder = this.mkdirs(i.getParent());

        DoorsModule module = folder.getModule(i.getItemName().getName());
        if (module == null) {
            module = DoorsCSVFactory.eINSTANCE.createDoorsModule();
            module.setParent(folder);
            module.setName(i.getItemName().getName());
        }
        updateModule(module);
        return module;
    }

    @Override
    public boolean updateModule(final DoorsModule module) {
        try {
            final ItemRef i = app.getItem(module.getFullName());
            final ModuleRef modRef = i.open();

            final Map<String, String> metaData = modRef.getModuleAttributes();
            Date lastChangedOn = DoorsModuleUtil.parseDate(metaData.get("Last Modified On"));

            boolean result = false;

            if (module.getLatestVersion() == null || module.getLatestVersion().getDate().before(lastChangedOn)) {
                final DoorsDatabaseVersion version = DoorsCSVFactory.eINSTANCE.createDoorsDatabaseVersion();
                version.setDate(lastChangedOn);
                version.setModule(module);

                Files.createDirectories(getCSVLocation(version).getParent());
                modRef.exportToCSV(getCSVLocation(version).toFile());

                final Map<String, String> attributes = new ModuleMetaDataParser().parseModuleMetaData(getMMDLocation(version).toFile());
                for (final Entry<String, String> e : attributes.entrySet()) {
                    version.getAttributes().put(e.getKey(), e.getValue());
                }
                Files.delete(getMMDLocation(version));
                result = true;
                flush();

            }
            modRef.close();
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<DoorsModule> updateAllModules() {
        final List<DoorsModule> updatedModules = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        final Date cutoff = c.getTime();

        db.accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (module.hasTag("sys:keepversion") || (module.getLatestVersion() != null && module.getLatestVersion().getDate().before(cutoff))) {
                    System.out.println("skipping " + module.getFullName());
                    return;
                }
                System.out.print("updating " + module.getFullName() + "... ");
                if (updateModule(module)) {
                    System.out.println("added new version.");
                } else {
                    System.out.println("up to date.");
                }
            }
        });
        return updatedModules;
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
                Files.deleteIfExists(getCSVLocation(v));
                Files.deleteIfExists(getMMDLocation(v));
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
    public List<DoorsModule> getAllModules() {
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
    public List<DoorsModule> findModules(final SearchExpression e) {
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

    @Override
    public DoorsModule importModule(final Path src, final Path dst) {
        final DoorsFolder folder = this.mkdirs(dst);

        final String moduleName = FilenameUtils.removeExtension(src.getFileName().toString());
        DoorsModule module = folder.getModule(moduleName);
        if (module != null) {
            System.out.println("Skipping " + src + ", already exists");
            return module;
        }

        Date lastChangedOn;
        Map<String, String> attributes;
        try {
            attributes = new ModuleMetaDataParser().parseModuleMetaData(new File(src + ".mmd"));
            lastChangedOn = DoorsModuleUtil.parseDate(attributes.get("Last Modified On"));
        } catch (final FileNotFoundException e) {
            lastChangedOn = new Date();
            attributes = Collections.emptyMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        module = DoorsCSVFactory.eINSTANCE.createDoorsModule();
        module.setParent(folder);
        module.setName(moduleName);

        final DoorsDatabaseVersion version = DoorsCSVFactory.eINSTANCE.createDoorsDatabaseVersion();
        version.setDate(lastChangedOn);
        version.setModule(module);

        try {
            Files.createDirectories(getCSVLocation(version).getParent());
            FileUtils.copyFile(src.toFile(), getCSVLocation(version).toFile());

            for (final Entry<String, String> e : attributes.entrySet()) {
                version.getAttributes().put(e.getKey(), e.getValue());
            }

            return module;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DoorsFolder mkdirs(final ItemRef path) {
        return ensurePath(db.getRoot(), path.getItemName().getPathSegments());
    }

    private DoorsFolder mkdirs(final Path path) {
        System.out.println(path);
        final List<String> segments = new ArrayList<>();
        final Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()) {
            segments.add(iterator.next().toString());
        }
        return ensurePath(db.getRoot(), segments);
    }

    private static DoorsFolder ensurePath(final DoorsFolder parent, final List<String> path) {
        if (path.size() > 0) {
            DoorsFolder folder = parent.getFolder(path.get(0));
            if (folder == null) {
                folder = DoorsCSVFactory.eINSTANCE.createDoorsFolder();
                folder.setName(path.get(0));
                parent.getChildren().add(folder);
            }
            if (path.size() > 1) {
                return ensurePath(parent.getFolder(path.get(0)), path.subList(1, path.size()));
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
