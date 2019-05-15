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
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import net.harawata.appdirs.AppDirsFactory;
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
    
    void removeNode(DoorsTreeNode node);
    
    URI getURI();

    public static DatabaseInterface openFileDatabase() throws IOException {
        return new FileDatabaseInterface(getDefaultDatabaseDirectory(FileDatabaseInterface.class).resolve("db.DoorsDatabasemodel"));
    }
    
    public static DatabaseInterface openDoorsApplicationDatabase() throws IOException {
        return new DoorsApplicationDatabaseInterface();
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
