package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class NewObjectAfterCommand extends AbstractCommand {

    private final DoorsObject object;

    public NewObjectAfterCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        final DoorsObject createDoorsObject = CSVFactory.eINSTANCE.createDoorsObject();
        createDoorsObject.setObjectText("");
        createDoorsObject.setObjectHeading("");
        createDoorsObject.setObjectLevel(object.getObjectLevel());
        object.getParent().getChildren().add(object.getParent().getChildren().indexOf(object) + 1, createDoorsObject);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
