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
public class FixedSingleSelectionModel<T> extends SelectionModel<T> {

    public FixedSingleSelectionModel(T selectedValue) {
        this.selectedValue = selectedValue;

    }

    protected final T selectedValue;

    @Override
    public void clearAndSelect(int i) {
        if (selectedValue != null && i != 0) {
            throw new UnsupportedOperationException("Fixed selection");
        }
    }

    @Override
    public void select(int i) {
        if (selectedValue != null && i != 0) {
            throw new UnsupportedOperationException("Fixed selection");
        }
    }

    @Override
    public void select(T t) {
        if (selectedValue != null && t != selectedValue) {
            throw new UnsupportedOperationException("Fixed selection");
        }
    }

    @Override
    public void clearSelection(int i) {
        throw new UnsupportedOperationException("Fixed selection");
    }

    @Override
    public void clearSelection() {
        throw new UnsupportedOperationException("Fixed selection");
    }

    @Override
    public boolean isSelected(int i) {
        return selectedValue != null && i == 0;
    }

    @Override
    public boolean isEmpty() {
        return selectedValue != null;
    }

    @Override
    public void selectPrevious() {
        throw new UnsupportedOperationException("Fixed selection");
    }

    @Override
    public void selectNext() {
        throw new UnsupportedOperationException("Fixed selection");
    }

    @Override
    public void selectFirst() {
        throw new UnsupportedOperationException("Fixed selection");
    }

    @Override
    public void selectLast() {
        throw new UnsupportedOperationException("Fixed selection");
    }

}
