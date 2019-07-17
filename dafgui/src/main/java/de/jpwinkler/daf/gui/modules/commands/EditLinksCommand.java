package de.jpwinkler.daf.gui.modules.commands;

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
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsObject;
import java.util.ArrayList;
import java.util.Collection;

public class EditLinksCommand extends AbstractCommand {

    private final DoorsObject doorsObject;
    private final Collection<DoorsLink> newLinks;
    private Collection<DoorsLink> oldLinks;

    public EditLinksCommand(DoorsObject doorsObject, Collection<DoorsLink> newLinks) {
        this.doorsObject = doorsObject;
        this.newLinks = newLinks;
    }

    @Override
    public String getName() {
        return "Edit links";
    }

    @Override
    public boolean isApplicable() {
        return doorsObject != null;
    }

    @Override
    public void apply() {
        this.oldLinks = new ArrayList<>(doorsObject.getOutgoingLinks());
        this.redo();
    }

    @Override
    public void redo() {
        this.doorsObject.getOutgoingLinks().clear();
        this.doorsObject.getOutgoingLinks().addAll(newLinks);
    }

    @Override
    public void undo() {
        this.doorsObject.getOutgoingLinks().clear();
        this.doorsObject.getOutgoingLinks().addAll(oldLinks);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(ModulePaneController.ModuleUpdateAction.UPDATE_CONTENT_VIEW);
    }

}
