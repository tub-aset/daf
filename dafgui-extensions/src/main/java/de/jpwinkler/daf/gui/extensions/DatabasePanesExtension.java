/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.extensions;

import java.util.Collections;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author fwiesweg
 */
public interface DatabasePanesExtension extends ApplicationPartExtension {

    default List<Node> getBottomPanes() {
        return Collections.emptyList();
    }

    default List<Node> getSidePanes() {
        return Collections.emptyList();
    }
}
