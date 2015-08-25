package de.jpwinkler.daf.dafcore.csv.gui;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import javafx.geometry.Insets;
import javafx.scene.control.cell.TextFieldTableCell;

public class ObjectHeadingAndObjectTextTableCell extends TextFieldTableCell<DoorsObject, DoorsObject> {

    @Override
    public void updateItem(final DoorsObject item, final boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            String style = "";
            if (item.isHeading()) {
                setText(item.getObjectNumber() + " " + item.getObjectHeading());
                style += "-fx-font-weight: bold;";
                if (item.getObjectLevel() <= 2) {
                    style += "-fx-font-size: 140%;";
                } else if (item.getObjectLevel() == 3) {
                    style += "-fx-font-size: 130%;";
                } else if (item.getObjectLevel() == 4) {
                    style += "-fx-font-size: 120%;";
                } else if (item.getObjectLevel() == 5) {
                    style += "-fx-font-size: 110%;";
                }
            } else {
                setText(item.getObjectText());
            }
            if ("requirement".equals(item.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #2E8B57;";
            } else if ("information".equals(item.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #A52A2A;";
            } else if ("predefinition".equals(item.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #000080;";
            }
            setPadding(new Insets(0, 0, 0, (item.getObjectLevel() - 1) * 10));
            setStyle(style);
        } else {
            setText("empty");
        }
    }

}
