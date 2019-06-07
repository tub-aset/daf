/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.commands;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author fwiesweg
 */
public class MultiCommand extends AbstractCommand {
    
    private final List<AbstractCommand> subCommands;

    public MultiCommand(List<AbstractCommand> subCommands) {
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
        subCommands.forEach(sc -> sc.undo());
    }

    @Override
    public boolean canUndo() {
        return subCommands.stream().map(sc -> sc.canUndo()).reduce( (b1, b2) -> b1 && b2).orElse(super.canUndo());
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return subCommands.stream().flatMap(sc -> Stream.of(sc.getUpdateActions())).distinct().toArray(l -> new UpdateAction[l]);
    }

    @Override
    public boolean isApplicable() {
        return subCommands.stream().map(sc -> sc.isApplicable()).reduce( (b1, b2) -> b1 && b2).orElse(super.isApplicable());
    }
    
    
    
}
