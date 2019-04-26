package de.jpwinkler.daf.csveditor.commands;

import de.jpwinkler.daf.csveditor.util.ColumnDefinition;
import de.jpwinkler.daf.csveditor.util.ColumnType;
import de.jpwinkler.daf.csveditor.util.ViewModel;
import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.DoorsCSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

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
        columnDefinition = new ColumnDefinition(ColumnType.ATTRIBUTE_COLUMN, newColumnName, newColumnName, 50, true);
        redo();
    }

    @Override
    public void undo() {
        viewModel.getDisplayedColumns().remove(columnDefinition);
        getModule().getAttributeDefinitions().remove(attributeDefinition);
    }

    @Override
    public void redo() {
        getModule().getAttributeDefinitions().add(attributeDefinition);
        viewModel.getDisplayedColumns().add(columnDefinition);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_COLUMNS };
    }

}
