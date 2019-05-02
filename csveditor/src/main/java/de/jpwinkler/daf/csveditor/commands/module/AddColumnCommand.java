package de.jpwinkler.daf.csveditor.commands.module;

import de.jpwinkler.daf.csveditor.CommandStack.AbstractCommand;
import de.jpwinkler.daf.csveditor.views.ColumnDefinition;
import de.jpwinkler.daf.csveditor.views.ViewModel;
import de.jpwinkler.daf.doorscsv.model.AttributeDefinition;
import de.jpwinkler.daf.doorscsv.model.DoorsCSVFactory;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;

public class AddColumnCommand extends AbstractCommand {

    private final String newColumnName;
    private final ViewModel viewModel;

    private AttributeDefinition attributeDefinition;
    private ColumnDefinition columnDefinition;

    public AddColumnCommand(final DoorsModule module, final ViewModel viewModel, final String newColumnName) {
        super(module);
        this.viewModel = viewModel;
        this.newColumnName = newColumnName;
    }

    @Override
    public String getName() {
        return "Add Column";
    }

    @Override
    public void apply() {
        attributeDefinition = DoorsCSVFactory.eINSTANCE.createAttributeDefinition();
        attributeDefinition.setName(newColumnName);

        columnDefinition = new ColumnDefinition();
        columnDefinition.setWidth(50);
        columnDefinition.setColumnTitle(newColumnName);
        columnDefinition.setAttributeName(newColumnName);
        columnDefinition.setVisible(true);
        redo();
    }

    @Override
    public void undo() {
        viewModel.getColumns().remove(columnDefinition);
        getModule().getAttributeDefinitions().remove(attributeDefinition);
    }

    @Override
    public void redo() {
        getModule().getAttributeDefinitions().add(attributeDefinition);
        viewModel.getColumns().add(columnDefinition);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{UpdateAction.UPDATE_COLUMNS};
    }

}
