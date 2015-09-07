package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class NewObjectBelowCommand extends AbstractCommand {

    private final DoorsObject object;

    public NewObjectBelowCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
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
        createDoorsObject.setObjectLevel(object.getObjectLevel() + 1);
        object.getChildren().add(createDoorsObject);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
