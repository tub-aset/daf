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
import java.util.stream.Stream;

/**
 *
 * @author fwiesweg
 */
public class MultiCommand<T extends AbstractCommand> extends AbstractCommand {

    private final List<T> subCommands;

    public MultiCommand(List<T> subCommands) {
        this.subCommands = subCommands;
    }

    @Override
    public String getName() {
        return subCommands.get(0).getName();
    }

    @Override
    public void apply() {
        subCommands.forEach(sc -> sc.apply());
    }

    @Override
    public void redo() {
        subCommands.forEach(sc -> sc.redo());
    }

    @Override
    public void undo() {
        List<AbstractCommand> undoCommands = new ArrayList<>(subCommands);
        Collections.reverse(undoCommands);
        undoCommands.forEach(sc -> sc.undo());
    }

    @Override
    public boolean canUndo() {
        return subCommands.stream().map(sc -> sc.canUndo()).reduce((b1, b2) -> b1 && b2).orElse(super.canUndo());
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return subCommands.stream().flatMap(sc -> Stream.of(sc.getUpdateActions())).distinct().toArray(l -> new UpdateAction[l]);
    }

    @Override
    public boolean isApplicable() {
        return subCommands.stream().map(sc -> sc.isApplicable()).reduce((b1, b2) -> b1 && b2).orElse(super.isApplicable());
    }

}
