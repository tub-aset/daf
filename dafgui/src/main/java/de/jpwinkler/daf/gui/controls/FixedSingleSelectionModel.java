package de.jpwinkler.daf.gui.controls;

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

import javafx.scene.control.SelectionModel;

/**
 *
 * @author fwiesweg
 */
public class FixedSingleSelectionModel<T> extends SelectionModel<T> {

    public FixedSingleSelectionModel(T selectedValue) {
        this.selectedValue = selectedValue;
        super.setSelectedItem(selectedValue);

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
