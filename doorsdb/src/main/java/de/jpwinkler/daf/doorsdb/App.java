package de.jpwinkler.daf.doorsdb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

public class App {

    private final DoorsDBInterface dbInterface;
    private DBTag englishTag;
    private DBTag germanTag;

    public App(final DoorsDBInterface dbInterface) {
        super();
        this.dbInterface = dbInterface;
    }

    public static void main(final String[] args) throws Exception {
        final DoorsDBInterface dbInterface = DoorsDBInterface.createOrOpenDB(new File("C:/WORK/DoorsDB/db.doorsdbmodel"));
        // dbInterface.updateAllModules();
        // dbInterface.saveDB();
        // new App(dbInterface).updateObjectType();
        // final List<DBModule> updateAllModules =
        // dbInterface.updateAllModules();
        // System.out.println("Updated modules:");
        // final List<DBModule> updateAllModules =
        // dbInterface.getModulesWithTag("deutsch");
        // updateAllModules.forEach(m -> System.out.println(m.getFullName()));
        // new App(dbInterface).addDocumentVersionTags();
        new App(dbInterface).integrateAttributes();
    }

    private void integrateAttributes() throws IOException {
        dbInterface.getDB().accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                for (final DBVersion version : module.getVersions()) {
                    new File(version.getCsvLocation() + ".mmd").delete();
                }
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
        dbInterface.saveDB();
    }

    private void addDocumentVersionTags() throws IOException {
        final Pattern p = Pattern.compile("[0-9.]*");
        dbInterface.getDB().accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                final String csvLocation = module.getLatestVersion().getCsvLocation();
                try {
                    final Map<String, String> moduleMetaData = new ModuleMetaDataParser().parseModuleMetaData(new File(csvLocation + ".mmd"));
                    final String tempVer = moduleMetaData.get("Template Version");
                    if (tempVer != null && p.matcher(tempVer).matches()) {
                        System.out.println(tempVer);
                        dbInterface.addTag(module, "template:" + tempVer);
                    } else {
                        System.out.println("Template Version missing!");
                    }
                } catch (final IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
        dbInterface.saveDB();
    }

    private void updateObjectType() throws IOException {
        dbInterface.getDB().accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                guessObjectTypePresence(module);
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
    }

    private void guessObjectTypePresence(final DBModule module) {
        try {
            DoorsModule parsedModule;
            parsedModule = new ModuleCSVParser().parseCSV(new File(module.getLatestVersion().getCsvLocation()));
            final List<DoorsObject> objectsWithoutOT = new ArrayList<>();
            final List<DoorsObject> objectsWithOT = new ArrayList<>();
            parsedModule.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    if (!object.getAttributes().containsKey("Object Type") || object.getAttributes().get("Object Type").isEmpty()) {
                        objectsWithoutOT.add(object);
                    } else {
                        objectsWithOT.add(object);
                    }
                    return true;
                }
            });
            if ((double) objectsWithoutOT.size() / (objectsWithOT.size() + objectsWithoutOT.size()) > 0.8) {
                System.out.println(module.getFullName() + " has no OT info");
                dbInterface.addTag(module, "no_objecttype");
            } else {
                System.out.println(module.getFullName() + " LOOKS GOOD!");
            }
        } catch (IOException | CSVParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}