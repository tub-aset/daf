package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.documenttagging.ConfusionMatrix;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConfusionMatrixDialog {

    private final ConfusionMatrix<String> confusionMatrix;
    private final Window owner;

    public ConfusionMatrixDialog(final ConfusionMatrix<String> confusionMatrix, final Window owner) {
        this.confusionMatrix = confusionMatrix;
        this.owner = owner;
    }

    private Node getLabel(final String text, final double rotation) {
        final Label label = new Label();

        label.setText(text);

        label.setRotate(rotation);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);

        return new Group(label);
    }

    public void show() {
        final Stage dialogStage = new Stage();
        dialogStage.setTitle("Confusion Matrix");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(owner);

        final GridPane gridPane = new GridPane();

        gridPane.setVgap(3);
        gridPane.setHgap(3);
        final int numTags = confusionMatrix.getTags().size();

        Node n;

        n = getLabel("Predicted Tag", 0);
        gridPane.add(n, 2, 0, numTags, 1);
        GridPane.setHalignment(n, HPos.CENTER);
        GridPane.setValignment(n, VPos.CENTER);

        n = getLabel("Actual Tag", -90);
        gridPane.add(n, 0, 2, 1, numTags);
        GridPane.setHalignment(n, HPos.CENTER);
        GridPane.setValignment(n, VPos.CENTER);

        for (int i = 0; i < numTags; i++) {
            final String tag = confusionMatrix.getTags().get(i);

            n = getLabel(tag, 0);
            gridPane.add(n, 1, i + 2);
            GridPane.setHalignment(n, HPos.RIGHT);
            GridPane.setValignment(n, VPos.CENTER);

            n = getLabel(tag, -90);
            gridPane.add(n, i + 2, 1);
            GridPane.setHalignment(n, HPos.CENTER);
            GridPane.setValignment(n, VPos.BOTTOM);
        }

        for (int column = 0; column < numTags; column++) {
            final String predictedTag = confusionMatrix.getTags().get(column);
            for (int row = 0; row < numTags; row++) {
                final String actualTag = confusionMatrix.getTags().get(row);
                final int count = confusionMatrix.get(predictedTag, actualTag);
                if (count > 0) {
                    n = new Label(String.valueOf(count));
                    gridPane.add(n, column + 2, row + 2);
                    GridPane.setHalignment(n, HPos.CENTER);
                    GridPane.setValignment(n, VPos.CENTER);
                }
            }
        }

        dialogStage.setScene(new Scene(gridPane));

        dialogStage.show();
    }

}
