package de.jpwinkler.daf.doorsdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelFactory;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;
import de.jpwinkler.daf.doorsdb.util.DBUtils;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import de.jpwinkler.libs.doorsbridge.ModuleRef;

public class DoorsDBInterface {

    private final DoorsDB db;
    private final File file;

    private final DoorsApplication app = DoorsApplicationFactory.getDoorsApplication();

    public static DoorsDBInterface createDB(final File file) {
        final DoorsDB db = DoorsDBModelFactory.eINSTANCE.createDoorsDB();
        db.setDbLocation(file.getParentFile().getAbsolutePath());
        db.setRoot(DoorsDBModelFactory.eINSTANCE.createDBFolder());
        return new DoorsDBInterface(file, db);
    }

    public static DoorsDBInterface openDB(final File file) throws FileNotFoundException, IOException {

        DoorsDBModelPackage.eINSTANCE.eClass();

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());

        final ResourceSet resourceSet = new ResourceSetImpl();

        final Resource resource = resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
        final DoorsDB db = (DoorsDB) resource.getContents().get(0);
        return new DoorsDBInterface(file, db);
    }

    public static DoorsDBInterface createOrOpenDB(final File file) throws FileNotFoundException, IOException {
        if (file.exists()) {
            return openDB(file);
        } else {
            return createDB(file);
        }
    }

    private DoorsDBInterface(final File file, final DoorsDB db) {
        this.file = file;
        this.db = db;
    }

    public DBModule addModule(final String moduleName) throws DoorsException, IOException, CSVParseException, ParseException {
        final ItemRef i = app.getItem(moduleName);

        if (!i.exists()) {
            throw new RuntimeException(moduleName + " does not exist.");
        }

        if (!(i.getType() == DoorsItemType.FORMAL)) {
            throw new RuntimeException(moduleName + " is not a formal module.");
        }

        final DBFolder folder = DBUtils.mkdirs(db, i.getParent());

        DBModule module = folder.getModule(i.getItemName().getName());
        if (module == null) {
            module = DoorsDBModelFactory.eINSTANCE.createDBModule();
            module.setParent(folder);
            module.setName(i.getItemName().getName());
        }
        updateModule(module);
        return module;
    }

    public boolean updateModule(final DBModule module) throws DoorsException, IOException, ParseException {
        final ItemRef i = app.getItem(module.getFullName());
        final ModuleRef modRef = i.open();

        final Map<String, String> metaData = modRef.getModuleAttributes();
        final Date lastChangedOn = DoorsModuleUtil.parseDate(metaData.get("Last Modified On"));

        final String realFile = mapToFile(i, lastChangedOn);

        boolean result = false;

        if (module.getLatestVersion() == null || module.getLatestVersion().getDate().before(lastChangedOn)) {
            modRef.exportToCSV(new File(realFile + ".csv"));

            final DBVersion version = DoorsDBModelFactory.eINSTANCE.createDBVersion();
            version.setCsvLocation(realFile + ".csv");
            version.setDate(lastChangedOn);
            module.getVersions().add(version);
            result = true;
            saveDB();
        }
        modRef.close();
        return result;
    }

    private String mapToFile(final ItemRef i, final Date lastChangedOn) {
        File file = new File(db.getDbLocation());
        for (final String pathSegment : i.getParent().getItemName().getPathSegments()) {
            file = new File(file, pathSegment);
        }
        file.mkdirs();
        return new File(file, i.getItemName() + "_" + new SimpleDateFormat("yyyyMMdd").format(lastChangedOn)).getAbsolutePath();
    }

    public List<DBModule> updateAllModules() {
        final List<DBModule> updatedModules = new ArrayList<>();

        db.accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                try {
                    System.out.print("updating " + module.getFullName() + "... ");
                    if (updateModule(module)) {
                        System.out.println("added new version.");
                    } else {
                        System.out.println("up to date.");
                    }
                } catch (DoorsException | IOException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
        return updatedModules;
    }

    public void saveDB() throws IOException {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        reg.getExtensionToFactoryMap().put("doorsdbmodel", new XMIResourceFactoryImpl());
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createFileURI(file.getAbsolutePath()));
        resource.getContents().add(db);
        resource.save(new HashMap<>());
    }

    public DoorsDB getDB() {
        return db;
    }

    public void addTag(final DBModule module, final String tag) {
        DBTag tagObject = db.getTag(tag);
        if (tagObject == null) {
            tagObject = DoorsDBModelFactory.eINSTANCE.createDBTag();
            tagObject.setName(tag);
            db.getTags().add(tagObject);
        }
        if (!module.getTags().contains(tagObject)) {
            module.getTags().add(tagObject);
        }
    }

    public List<DBModule> getModulesWithTag(final String tag) {
        final DBTag tagObject = db.getTag(tag);
        if (tagObject != null) {
            return tagObject.getModules();
        } else {
            return Collections.emptyList();
        }
    }

    public void removeTag(final DBModule module, final String tag) {
        final DBTag tagObject = db.getTag(tag);
        module.getTags().remove(tagObject);
    }

    public List<String> getTags(final DBModule value) {
        return value.getTags().stream().map(t -> t.getName()).collect(Collectors.toList());
    }

    public void removeModule(final DBModule module) {
        module.setParent(null);
        module.getTags().clear();
        module.getVersions().forEach(v -> {
            new File(v.getCsvLocation()).delete();
            new File(v.getCsvLocation() + ".mmd").delete();
        });
        module.getVersions().clear();
    }

    public void removeFolder(final DBFolder folder) {
        final List<DBModule> markedForDeletion = new ArrayList<>();
        folder.accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                markedForDeletion.add(module);
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
        markedForDeletion.forEach(m -> removeModule(m));
        folder.setParent(null);
    }

}
