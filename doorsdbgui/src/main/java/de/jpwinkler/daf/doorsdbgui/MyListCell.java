package de.jpwinkler.daf.doorsdbgui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.apache.lucene.document.Document;

public class MyListCell extends ListCell<Document> {

    private final Label row1;
    private final Label row2;

    public MyListCell() {
        final GridPane gridPane = new GridPane();
        row1 = new Label();
        row2 = new Label();

        gridPane.add(row1, 0, 0);
        gridPane.add(row2, 0, 1);
        row2.setTextFill(Color.gray(0.4));
        setGraphic(gridPane);
        setText(null);
    }

    @Override
    protected void updateItem(final Document item, final boolean empty) {
        if (empty) {
            row1.setText("");
            row2.setText("");
        } else {
            row1.setText(item.get("text"));
            row2.setText(item.get("type") + " - " + item.get("source"));
        }
    }

}
