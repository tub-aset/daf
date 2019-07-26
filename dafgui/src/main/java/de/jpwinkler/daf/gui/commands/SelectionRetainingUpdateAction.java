/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
