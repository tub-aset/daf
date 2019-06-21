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

import java.util.function.Function;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;

/**
 *
 * @author fwiesweg
 */
public class ForwardingSingleSelectionModel<T, V> extends SelectionModel<T> {

    public ForwardingSingleSelectionModel(SingleSelectionModel<V> sourceModel, Function<T, V> transformation) {
        this.sourceModel = sourceModel;
        this.transformation = transformation;
    }

    protected final SelectionModel<V> sourceModel;
    protected final Function<T, V> transformation;

    @Override
    public void clearAndSelect(int i) {
        sourceModel.clearAndSelect(i);
    }

    @Override
    public void select(int i) {
        sourceModel.select(i);
    }

    @Override
    public void select(T t) {
        sourceModel.select(transformation.apply(t));
    }

    @Override
    public void clearSelection(int i) {
        sourceModel.clearSelection();
    }

    @Override
    public void clearSelection() {
        sourceModel.clearSelection();
    }

    @Override
    public boolean isSelected(int i) {
        return sourceModel.isSelected(i);
    }

    @Override
    public boolean isEmpty() {
        return sourceModel.isEmpty();
    }

    @Override
    public void selectPrevious() {
        sourceModel.selectPrevious();
    }

    @Override
    public void selectNext() {
        sourceModel.selectNext();
    }

    @Override
    public void selectFirst() {
        sourceModel.selectFirst();
    }

    @Override
    public void selectLast() {
        sourceModel.selectLast();
    }

}
