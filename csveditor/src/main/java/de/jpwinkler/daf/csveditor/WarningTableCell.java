package de.jpwinkler.daf.csveditor;

import java.util.Map;

import de.jpwinkler.daf.csveditor.otclassification.IgnoreList;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.libs.reqinfclassifier.ClassificationResult;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class WarningTableCell extends TextFieldTableCell<DoorsObject, String> {

    private final Map<DoorsObject, ClassificationResult> classificationResults;
    private final IgnoreList ignoreList;

    public WarningTableCell(final Map<DoorsObject, ClassificationResult> classificationResults, final IgnoreList ignoreList) {
        super(new DefaultStringConverter());
        this.classificationResults = classificationResults;
        this.ignoreList = ignoreList;
    }

    @Override
    public void updateItem(final String item, final boolean empty) {
        super.updateItem(item, empty);
        if (!empty && getTableRow() != null) {
            final DoorsObject o = getTableView().getItems().get(getTableRow().getIndex());
            setText("");
            final ClassificationResult r = classificationResults.get(o);
            if (!ignoreList.isIgnored(o) && r != null && !r.getObjectType().equals(o.getAttributes().get("Object Type"))) {
                switch (r.getReliability()) {
                case DEFINITELY_CORRECT:
                case MOST_LIKELY_CORRECT:
                    getTableRow().setStyle("-fx-control-inner-background: #ffaaaa");
                    setText("Error");
                    break;
                case MAYBE_CORRECT:
                    getTableRow().setStyle("-fx-control-inner-background: #ffffaa");
                    setText("Warning");
                    break;
                case AMBIGUOUS:
                case UNKNOWN:
                    setText("Remark");
                    getTableRow().setStyle("-fx-control-inner-background: #aaaaff");
                    break;
                }
            } else {
                getTableRow().setStyle("");
            }
        }
    }
}
