/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

import java.util.function.Function;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import javafx.scene.control.MultipleSelectionModel;

/**
 *
 * @author fwiesweg
 */
public class ForwardingMultipleSelectionModel<T, V> extends MultipleSelectionModel<T> {

    public ForwardingMultipleSelectionModel(MultipleSelectionModel<V> sourceModel, Function<T, V> transformation, Function<V, T> inverseTransformation) {
        this.sourceModel = sourceModel;
        this.transformation = transformation;
        this.inverseTransformation = inverseTransformation;
    }

    private final MultipleSelectionModel<V> sourceModel;
    private final Function<T, V> transformation;
    private final Function<V, T> inverseTransformation;

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return sourceModel.getSelectedIndices();
    }

    @Override
    public ObservableList<T> getSelectedItems() {
        return new TransformationList<T, V>(sourceModel.getSelectedItems()) {
            @Override
            protected void sourceChanged(ListChangeListener.Change<? extends V> change) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getSourceIndex(int i) {
                return i;
            }
            
            /**
             * This should have an @Override here for Java 11, but Java 8 does not
             * have this method, causing a compiler error. Just leaving the @Override
             * out makes sure it compiles with both JDKs.
             * @param i
             * @return 
             */
            public int getViewIndex(int i) {
                return i;
            }

            @Override
            public T get(int arg0) {
                return inverseTransformation.apply(super.getSource().get(arg0));
            }

            @Override
            public int size() {
                return super.getSource().size();
            }
        };
    }

    @Override
    public void selectIndices(int i, int... ints) {
        sourceModel.selectIndices(i, ints);
    }

    @Override
    public void selectAll() {
        sourceModel.selectAll();
    }

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
