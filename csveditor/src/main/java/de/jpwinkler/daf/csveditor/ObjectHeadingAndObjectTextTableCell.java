package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import javafx.geometry.Insets;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class ObjectHeadingAndObjectTextTableCell extends TextFieldTableCell<DoorsObject, String> {

    public ObjectHeadingAndObjectTextTableCell() {
        super(new DefaultStringConverter());
    }

    @Override
    public void updateItem(final String item, final boolean empty) {
        super.updateItem(item, empty);
        String style = "";
        if (!empty && getTableRow() != null) {
            final DoorsObject o = getTableView().getItems().get(getTableRow().getIndex());
            if (o.isHeading()) {
                setText(o.getObjectNumber() + " " + o.getObjectHeading());
                style += "-fx-font-weight: bold;";
                if (o.getObjectLevel() <= 2) {
                    style += "-fx-font-size: 140%;";
                } else if (o.getObjectLevel() == 3) {
                    style += "-fx-font-size: 130%;";
                } else if (o.getObjectLevel() == 4) {
                    style += "-fx-font-size: 120%;";
                } else if (o.getObjectLevel() == 5) {
                    style += "-fx-font-size: 110%;";
                }
            } else {
                setText(o.getObjectText());
            }
            if ("requirement".equals(o.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #2E8B57;";
            } else if ("information".equals(o.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #A52A2A;";
            } else if ("predefinition".equals(o.getAttributes().get("Object Type"))) {
                style += "-fx-text-fill: #000080;";
            }
            setPadding(new Insets(0, 0, 0, (o.getObjectLevel() - 1) * 10));
        }
        setStyle(style);
    }

}
