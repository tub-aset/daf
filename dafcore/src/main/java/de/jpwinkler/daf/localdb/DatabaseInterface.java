/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import net.harawata.appdirs.AppDirsFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    void flush() throws IOException;

    DoorsDatabase getDatabaseObject();

    List<DoorsModule> getModules(final SearchExpression e);

    DoorsModule importModule(final DoorsModule module);

    DoorsTreeNode getNode(final String path);
    
    void removeNode(DoorsTreeNode node);

    public static DatabaseInterface openFileDatabase() throws IOException {
        return openFileDatabase(getDefaultDatabaseDirectory(FileDatabaseInterface.class).resolve("db.DoorsDatabasemodel"));
    }

    public static DatabaseInterface openFileDatabase(Path databaseFile) throws IOException {
        DoorsDatabase db;

        databaseFile = databaseFile.toAbsolutePath();
        if (Files.exists(databaseFile)) {
            DoorsPackage.eINSTANCE.eClass();

            final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
            reg.getExtensionToFactoryMap().put("DoorsDatabasemodel", new XMIResourceFactoryImpl());
            final ResourceSet resourceSet = new ResourceSetImpl();
            final Resource resource = resourceSet.getResource(URI.createFileURI(databaseFile.toString()), true);
            db = (DoorsDatabase) resource.getContents().get(0);
        } else {
            db = DoorsFactory.eINSTANCE.createDoorsDatabase();
            db.setRoot(DoorsFactory.eINSTANCE.createDoorsTreeNode());
            final FileDatabaseInterface DoorsDatabaseInterface = new FileDatabaseInterface(databaseFile, db);
            DoorsDatabaseInterface.flush();
        }

        return new FileDatabaseInterface(databaseFile, db);
    }
    
    private static Path getDefaultDatabaseDirectory(Class<? extends DatabaseInterface> implCls) throws FileNotFoundException, IOException {
        String DoorsDatabasePath = System.getenv("DoorsDatabase_HOME");
        if(DoorsDatabasePath == null) {
            DoorsDatabasePath = AppDirsFactory.getInstance().getUserDataDir("dafcore", implCls.getSimpleName(), null);
        }
        
        Path path = Paths.get(DoorsDatabasePath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return path;
    }
}
