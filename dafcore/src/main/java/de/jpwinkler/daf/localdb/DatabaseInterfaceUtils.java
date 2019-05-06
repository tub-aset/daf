/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.localdb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.harawata.appdirs.AppDirsFactory;

/**
 *
 * @author fwiesweg
 */
class DatabaseInterfaceUtils {
    
    static Path getDefaultDatabaseDirectory(Class<? extends DatabaseInterface> implCls) throws FileNotFoundException, IOException {
        String doorsdbPath = System.getenv("DOORSDB_HOME");
        if(doorsdbPath == null) {
            doorsdbPath = AppDirsFactory.getInstance().getUserDataDir("dafcore", implCls.getSimpleName(), null);
        }
        
        Path path = Paths.get(doorsdbPath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return path;
    }
}
