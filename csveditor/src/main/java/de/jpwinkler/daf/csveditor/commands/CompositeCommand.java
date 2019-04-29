package de.jpwinkler.daf.csveditor.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.doorscsv.model.DoorsModule;

public class CompositeCommand extends AbstractCommand {

    private final List<? extends AbstractCommand> commands;

    public CompositeCommand(final DoorsModule module, final List<? extends AbstractCommand> commands) {
        super(module);
        this.commands = commands;
    }

    @Override
    public boolean isApplicable() {
        return commands.stream().allMatch(c -> c.isApplicable());
    }

    @Override
    public String getName() {
        return "Composite Command";
    }

    @Override
    public void apply() {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).apply();
        }
    }

    @Override
    public void redo() {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).redo();
        }
    }

    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        final List<UpdateAction> collect = commands.stream().map(c -> c.getUpdateActions()).map(updateActions -> Arrays.asList(updateActions)).flatMap(List::stream).distinct().collect(Collectors.toList());
        return collect.toArray(new UpdateAction[collect.size()]);
    }

    @Override
    public boolean canUndo() {
        return commands.stream().allMatch(c -> c.canUndo());
    }

}
