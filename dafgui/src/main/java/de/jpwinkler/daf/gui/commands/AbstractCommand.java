package de.jpwinkler.daf.gui.commands;

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

import de.jpwinkler.daf.gui.ApplicationPartInterface;
import java.lang.ref.WeakReference;

/**
 *
 * @author fwiesweg
 */
public abstract class AbstractCommand {

    public boolean isApplicable() {
        return true;
    }

    public abstract String getName();

    public abstract void apply();

    public abstract void redo();

    public abstract void undo();

    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[0];
    }

    public boolean canUndo() {
        return true;
    }

    private WeakReference<ApplicationPartInterface> originController;
    private AbstractCommand previous;
    private AbstractCommand next;

    public ApplicationPartInterface getOriginController() {
        return originController.get();
    }

    public void setOriginController(ApplicationPartInterface originController) {
        this.originController = new WeakReference<>(originController);
    }

    public AbstractCommand getPrevious() {
        return previous;
    }

    public void setPrevious(AbstractCommand previous) {
        this.previous = previous;
    }

    public AbstractCommand getNext() {
        return next;
    }

    public void setNext(AbstractCommand next) {
        this.next = next;
    }
}
