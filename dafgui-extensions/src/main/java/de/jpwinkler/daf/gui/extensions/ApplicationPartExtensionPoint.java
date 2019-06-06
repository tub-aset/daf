/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.extensions;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import org.pf4j.ExtensionPoint;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPartExtensionPoint extends ExtensionPoint {
    
    default void initialise(ApplicationPartInterface applicationPart) {
    }
    
    default List<Menu> getMenus() {
        return Collections.emptyList();
    }
    
    default List<Node> getBottomPanes() {
        return Collections.emptyList();
    }
    
    default List<Node> getSidePanes() {
        return Collections.emptyList();
    }
    
}
