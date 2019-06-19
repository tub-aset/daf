/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.util.Collections;
import java.util.List;
import javafx.scene.control.Menu;
import org.pf4j.ExtensionPoint;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPaneExtension extends ExtensionPoint {
    
    default void initialise(ApplicationPaneInterface applicationPart) {
    }

    default List<ApplicationPart> getApplicationParts() {
        return Collections.emptyList();
    }
    
    default List<Menu> getApplicationMenus() {
        return Collections.emptyList();
    }
}
