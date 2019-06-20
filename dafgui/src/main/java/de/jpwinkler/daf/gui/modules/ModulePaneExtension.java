/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.modules;

import de.jpwinkler.daf.gui.ApplicationPartExtension;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author fwiesweg
 */
public interface ModulePaneExtension extends ApplicationPartExtension {

    default List<ViewDefinition> getAdditionalViews() {
        return Collections.emptyList();
    }
    
    default List<Node> getBottomPanes() {
        return Collections.emptyList();
    }

    default List<Node> getSidePanes() {
        return Collections.emptyList();
    }
    
    default String getPaneName(Node node) {
        return (String) node.getUserData();
    }
}
