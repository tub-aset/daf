package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsApplicationFactory;
import de.jpwinkler.daf.bridge.DoorsException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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

public class DoorsDBInterface {

    private final DoorsDB db;
    private final Path databaseFile;
    private final Path databaseRoot;

    private final DoorsApplication app = DoorsApplicationFactory.getDoorsApplication();

    public static DoorsDBInterface createDB(final Path databaseFile) throws IOException {
        final DoorsDB db = DoorsCSVFactory.eINSTANCE.createDoorsDB();
        db.setRoot(DoorsCSVFactory.eINSTANCE.createDoorsFolder());
        final DoorsDBInterface doorsDBInterface = new DoorsDBInterface(databaseFile, db);
        doorsDBInterface.saveDB();
        return doorsDBInterface;
    }

    public static DoorsDBInterface openDB(final Path databaseFile) throws IOException {

        DoorsCSVPackage.eINSTANCE.eClass();

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());

        final ResourceSet resourceSet = new ResourceSetImpl();

        // TODO verify
        final Resource resource = resourceSet.getResource(URI.createFileURI(databaseFile.toString()), true);
        final DoorsDB db = (DoorsDB) resource.getContents().get(0);
        return new DoorsDBInterface(databaseFile, db);
    }

    public static DoorsDBInterface createOrOpenDB(final Path databaseFile) throws FileNotFoundException, IOException {
        if (Files.exists(databaseFile)) {
            return openDB(databaseFile);
        } else {
            return createDB(databaseFile);
        }
    }

    private DoorsDBInterface(final Path databaseFile, final DoorsDB db) throws IOException {
        this.databaseFile = databaseFile.toAbsolutePath();
        databaseRoot = databaseFile.toAbsolutePath().getParent();
        this.db = db;
    }

    public Path getCSVLocation(final DoorsDatabaseVersion v) {
        return Paths.get(databaseRoot.toString(), v.getModule().getParent().getFullName(), v.getModule().getName() + "_" + new SimpleDateFormat("yyyyMMdd").format(v.getDate()) + ".csv");
    }

    private Path getMMDLocation(final DoorsDatabaseVersion v) {
        return Paths.get(getCSVLocation(v).toString() + ".mmd");
    }

    public DoorsModule addModule(final String moduleName) throws DoorsException, IOException {
        final ItemRef i = app.getItem(moduleName);

        if (!i.exists()) {
            throw new RuntimeException(moduleName + " does not exist.");
        }

        if (!(i.getType() == DoorsItemType.FORMAL)) {
            throw new RuntimeException(moduleName + " is not a formal module.");
        }

        final DoorsFolder folder = DBUtils.mkdirs(db, i.getParent());

        DoorsModule module = folder.getModule(i.getItemName().getName());
        if (module == null) {
            module = DoorsCSVFactory.eINSTANCE.createDoorsModule();
            module.setParent(folder);
            module.setName(i.getItemName().getName());
        }
        updateModule(module);
        return module;
    }

    public boolean updateModule(final DoorsModule module) throws DoorsException, IOException {
        final ItemRef i = app.getItem(module.getFullName());
        final ModuleRef modRef = i.open();

        final Map<String, String> metaData = modRef.getModuleAttributes();
        Date lastChangedOn;
        try {
            lastChangedOn = DoorsModuleUtil.parseDate(metaData.get("Last Modified On"));
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }

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
            saveDB();
        }
        modRef.close();
        return result;
    }

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
                try {
                    System.out.print("updating " + module.getFullName() + "... ");
                    if (updateModule(module)) {
                        System.out.println("added new version.");
                    } else {
                        System.out.println("up to date.");
                    }
                } catch (DoorsException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return updatedModules;
    }

    public void saveDB() throws IOException {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createFileURI(databaseFile.toString()));
        resource.getContents().add(db);
        resource.save(new HashMap<>());
    }

    public DoorsDB getDB() {
        return db;
    }

    public void removeModule(final DoorsModule module) {
        module.setParent(null);
        module.getVersions().forEach(v -> {
            try {
                Files.deleteIfExists(getCSVLocation(v));
                Files.deleteIfExists(getMMDLocation(v));
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        module.getVersions().clear();
    }

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

    public DoorsFolder getFolder(final String path) {
        final List<String> pathSegments = Arrays.asList(path.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsFolder current = getDB().getRoot();
        for (final String segment : pathSegments.subList(0, pathSegments.size())) {
            current = current.getFolder(segment);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public DoorsModule getModule(final String path) {
        final List<String> pathSegments = Arrays.asList(path.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsFolder current = getDB().getRoot();
        for (final String segment : pathSegments.subList(0, pathSegments.size() - 1)) {
            current = current.getFolder(segment);
            if (current == null) {
                return null;
            }
        }
        return current.getModule(pathSegments.get(pathSegments.size() - 1));
    }

    public DoorsModule importModule(final Path src, final Path dst) throws IOException {
        final DoorsFolder folder = DBUtils.mkdirs(db, dst);

        final String moduleName = FilenameUtils.removeExtension(src.getFileName().toString());
        DoorsModule module = folder.getModule(moduleName);
        if (module == null) {
            Date lastChangedOn;
            Map<String, String> attributes;
            try {
                attributes = new ModuleMetaDataParser().parseModuleMetaData(new File(src + ".mmd"));
                lastChangedOn = DoorsModuleUtil.parseDate(attributes.get("Last Modified On"));
            } catch (final FileNotFoundException e) {
                lastChangedOn = new Date();
                attributes = Collections.emptyMap();
            } catch (final ParseException e) {
                throw new RuntimeException(e);
            }

            module = DoorsCSVFactory.eINSTANCE.createDoorsModule();
            module.setParent(folder);
            module.setName(moduleName);

            final DoorsDatabaseVersion version = DoorsCSVFactory.eINSTANCE.createDoorsDatabaseVersion();
            version.setDate(lastChangedOn);
            version.setModule(module);

            Files.createDirectories(getCSVLocation(version).getParent());

            FileUtils.copyFile(src.toFile(), getCSVLocation(version).toFile());

            for (final Entry<String, String> e : attributes.entrySet()) {
                version.getAttributes().put(e.getKey(), e.getValue());
            }
        } else {
            System.out.println("Skipping " + src + ", already exists");
        }

        return module;
    }

    public static DoorsDBInterface getDefaultDatabase() throws FileNotFoundException, IOException {
        final String doorsdb_home = System.getenv("DOORSDB_HOME");
        Path path;
        if (doorsdb_home != null) {
            path = Paths.get(doorsdb_home);
        } else {
            path = Paths.get(System.getenv("LOCALAPPDATA"), "DoorsDB");
        }
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return createOrOpenDB(path.resolve("db.doorsdbmodel"));
    }

}
