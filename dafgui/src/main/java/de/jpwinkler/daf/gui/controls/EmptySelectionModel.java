/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

import javafx.scene.control.SelectionModel;

/**
 *
 * @author fwiesweg
 */
public class EmptySelectionModel<T> extends SelectionModel<T> {    

    @Override
    public void clearAndSelect(int i) {
    }

    @Override
    public void select(int i) {
    }

    @Override
    public void select(T t) {
    }

    @Override
    public void clearSelection(int i) {
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public boolean isSelected(int i) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void selectPrevious() {
    }

    @Override
    public void selectNext() {
    }

    @Override
    public void selectFirst() {
    }

    @Override
    public void selectLast() {
    }

}
