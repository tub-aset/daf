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

    void initialize(ApplicationStateController applicationStateController, File file) throws IOException;

    boolean isDirty();

    boolean save();

    boolean saveAs();

    void updateFilter(final String text, final boolean includeParents, final boolean includeChildren, final boolean isExpression);

    Collection<Menu> getMenus();

    public static FileStateController empty() {
        return new FileStateController() {
            @Override
            public File getFile() {
                return null;
            }

            @Override
            public void initialize(ApplicationStateController applicationStateController, File file) throws IOException {
            }

            @Override
            public boolean isDirty() {
                return false;
            }

            @Override
            public boolean save() {
                return true;
            }

            @Override
            public boolean saveAs() {
                return false;
            }

            @Override
            public void updateFilter(String text, boolean includeParents, boolean includeChildren, boolean isExpression) {
            }

            @Override
            public Collection<Menu> getMenus() {
                return Collections.emptySet();
            }
        };
    }
}
