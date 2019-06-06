/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.extensions;

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
