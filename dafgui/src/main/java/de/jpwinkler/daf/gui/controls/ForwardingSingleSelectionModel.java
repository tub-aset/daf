/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

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
