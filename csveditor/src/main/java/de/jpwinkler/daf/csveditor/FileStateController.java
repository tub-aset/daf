/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import javafx.scene.control.Menu;

/**
 *
 * @author fwiesweg
 */
public interface FileStateController {

    File getFile();
    void setFile(File file);

    void initialize(ApplicationStateController applicationStateController, File file) throws IOException;
    void save(File file) throws IOException;
    
    CommandStack getCommandStack();

    Collection<Menu> getMenus();

    public static FileStateController empty() {
        return new FileStateController() {
            @Override
            public File getFile() {
                return null;
            }
            
            @Override
            public void setFile(File file) {
            }

            @Override
            public void initialize(ApplicationStateController applicationStateController, File file) throws IOException {
            }

            @Override
            public void save(File file) throws IOException {
            }

            @Override
            public Collection<Menu> getMenus() {
                return Collections.emptySet();
            }

            @Override
            public CommandStack getCommandStack() {
                return new CommandStack();
            }
        };
    }
}
