package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Pair;
import javafx.util.StringConverter;

public class TagTableCell extends TextFieldTableCell<DoorsObject, Pair<String, String>> {

    public TagTableCell() {
        super(new StringConverter<Pair<String, String>>() {

            @Override
            public String toString(final Pair<String, String> object) {
                return object.getKey();
            }

            @Override
            public Pair<String, String> fromString(final String string) {
                return null;
            }
        });
    }

    @Override
    public void updateItem(final Pair<String, String> item, final boolean empty) {
        super.updateItem(item, empty);
        if (!empty && getTableRow() != null && item != null) {
            final DoorsObject o = getTableView().getItems().get(getTableRow().getIndex());
            if (item.getKey() != null && !item.getKey().isEmpty()) {
                String style = "";
                if (item.getKey().equals(item.getValue())) {
                    style += "-fx-background-color: #ccffcc";
                } else {
                    style += "-fx-background-color: #ffcccc";
                }
                setStyle(style);
            }
        }
    }
    //
    // @Override
    // public void updateItem(final String item, final boolean empty) {
    // super.updateItem(item, empty);
    // if (!empty && getTableRow() != null) {
    // final DoorsObject o =
    // getTableView().getItems().get(getTableRow().getIndex());
    // String style = "";
    // if (o.isHeading()) {
    // setText(o.getObjectNumber() + " " + o.getObjectHeading());
    // style += "-fx-font-weight: bold;";
    // if (o.getObjectLevel() <= 2) {
    // style += "-fx-font-size: 140%;";
    // } else if (o.getObjectLevel() == 3) {
    // style += "-fx-font-size: 130%;";
    // } else if (o.getObjectLevel() == 4) {
    // style += "-fx-font-size: 120%;";
    // } else if (o.getObjectLevel() == 5) {
    // style += "-fx-font-size: 110%;";
    // }
    // } else {
    // setText(o.getObjectText());
    // }
    // if ("requirement".equals(o.getAttributes().get("Object Type"))) {
    // style += "-fx-text-fill: #2E8B57;";
    // } else if ("information".equals(o.getAttributes().get("Object Type"))) {
    // style += "-fx-text-fill: #A52A2A;";
    // } else if ("predefinition".equals(o.getAttributes().get("Object Type")))
    // {
    // style += "-fx-text-fill: #000080;";
    // }
    // setPadding(new Insets(0, 0, 0, (o.getObjectLevel() - 1) * 10));
    // setStyle(style);
    // }
    // }

}
