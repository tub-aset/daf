package de.jpwinkler.daf.gui.modules;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.gui.ApplicationPartExtension;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author fwiesweg
 */
public interface ModulePaneExtension extends ApplicationPartExtension {

    default List<? extends ViewDefinition> getAdditionalViews() {
        return Collections.emptyList();
    }
    
    default List<? extends Node> getBottomPanes() {
        return Collections.emptyList();
    }

    default List<? extends Node> getSidePanes() {
        return Collections.emptyList();
    }
    
    default String getPaneName(Node node) {
        if(node.getUserData() == null) {
            throw new RuntimeException("No pane name associated with this extension pane. "
                    + "Either associate it with node.setUserData() or override getPaneName in your extension.");
        }
        
        return (String) node.getUserData();
    }
}
