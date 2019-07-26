/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.application.Platform;
import javafx.scene.control.MultipleSelectionModel;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author fwiesweg
 */
public abstract class SelectionRetainingUpdateAction<T> implements UpdateAction<T> {

    private final Function<T, List<MultipleSelectionModel<?>>> selectionModelGetter;
    private List<List<Integer>> selectedIndices;

    public SelectionRetainingUpdateAction(Function<T, List<MultipleSelectionModel<?>>> selectionModelGetter) {
        this.selectionModelGetter = selectionModelGetter;
    }

    @Override
    public final void preUpdate(T ctrl) {
        this.selectedIndices = selectionModelGetter.apply(ctrl).stream()
                .map(model -> model.getSelectedIndices())
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    @Override
    public final void postUpdate(T ctrl) {
        List<MultipleSelectionModel<?>> selModels = selectionModelGetter.apply(ctrl);

        Platform.runLater(
                () -> IntStream.range(0, selectedIndices.size())
                        .mapToObj(i -> Pair.of(selModels.get(i), selectedIndices.get(i)))
                        .forEach(p -> p.getRight().forEach(p.getLeft()::select)));
    }

    public final UpdateAction<T> dontRetainSelection() {
        UpdateAction<T> currentAction = this;

        return new SelectionRetainingUpdateAction<T>(ctrl -> Collections.emptyList()) {
            @Override
            public void update(T ctrl) {
                currentAction.update(ctrl);
            }
        };
    }

}
