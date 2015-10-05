package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import javafx.scene.control.cell.TextFieldTableCell;

public class AttributeTableCell extends TextFieldTableCell<DoorsObject, DoorsObject> {

    private final String attribute;

    public AttributeTableCell(final String attribute) {
        super();
        this.attribute = attribute;
    }

    @Override
    public void updateItem(final DoorsObject item, final boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setText(item.getAttributes().get(attribute));
        }
    }

}
