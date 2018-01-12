package de.jpwinkler.daf.csveditor.otclassification;

import de.jpwinkler.daf.csveditor.CSVEditorController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.SentenceMarkup;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.SentenceMarkupRange;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ObjectTypeClassificationController {

    @FXML
    private Label explanation1Label;
    @FXML
    private FlowPane classifiedSentenceContainer;
    @FXML
    private Label explanation2Label;

    @FXML
    private HBox fixButtonGroup;

    @FXML
    private HBox issueButtonGroup;

    @FXML
    private HBox ignoreButtonGroup;

    @FXML
    private HBox unIgnoreButtonGroup;

    private DoorsObject object;
    private ClassificationResult classificationResult;

    private CSVEditorController csvEditorController;

    @FXML
    public void initialize() {
        clearUI();

    }

    private void clearUI() {
        explanation1Label.setText("");
        classifiedSentenceContainer.getChildren().clear();
        explanation2Label.setText("");
        fixButtonGroup.managedProperty().bind(fixButtonGroup.visibleProperty());
        issueButtonGroup.managedProperty().bind(issueButtonGroup.visibleProperty());
        ignoreButtonGroup.managedProperty().bind(ignoreButtonGroup.visibleProperty());
        unIgnoreButtonGroup.managedProperty().bind(unIgnoreButtonGroup.visibleProperty());
        fixButtonGroup.setVisible(false);
        issueButtonGroup.setVisible(false);
        ignoreButtonGroup.setVisible(false);
        unIgnoreButtonGroup.setVisible(false);
    }

    public void updatePanels(final DoorsObject object, final ClassificationResult classificationResult) {
        this.object = object;
        this.classificationResult = classificationResult;

        clearUI();

        if (object == null) {
            return;
        }

        final String actualLabel = object.getAttributes().get("Object Type");
        final String suggestedLabel = classificationResult != null ? classificationResult.getObjectType() : null;

        if (csvEditorController.getIgnoreList().isIgnoredOnce(object)) {
            explanation1Label.setText("This item is set to be ignored once.");
            unIgnoreButtonGroup.setVisible(true);
        } else if (csvEditorController.getIgnoreList().isAlwaysIgnored(object)) {
            explanation1Label.setText("This item is set to be always ignored.");
            unIgnoreButtonGroup.setVisible(true);
        } else {
            ignoreButtonGroup.setVisible(true);
            if (suggestedLabel == null) {
                explanation1Label.setText("The classifier was not able to determine a label for this item.");
            } else if (!suggestedLabel.equals(actualLabel)) {
                switch (classificationResult.getReliability()) {
                case DEFINITELY_CORRECT:
                    explanation1Label.setText("This item is classified incorrectly as \"" + actualLabel + "\". This item must be classified as \"" + suggestedLabel + "\".");
                    break;
                case AMBIGUOUS:
                    explanation1Label.setText("This item is ambiguous. Maybe it should be rephrased.");
                    break;
                case MAYBE_CORRECT:
                    explanation1Label.setText("This object is classified as \"" + actualLabel + "\". Analysis has shown that object type \"" + suggestedLabel + "\" might be more appropriate.");
                    break;
                case MOST_LIKELY_CORRECT:
                    explanation1Label.setText("This object is classified as \"" + actualLabel + "\", but should be classified as \"" + suggestedLabel + "\".");
                    break;
                case UNKNOWN:
                    explanation1Label.setText("You have a strange feeling for a moment, then it passes.");
                    break;
                default:
                }
                switch (classificationResult.getClassifiedBy()) {
                case CLUSTER_CLASSIFIER:
                    explanation2Label.setText("Similar items were also classified as \"" + suggestedLabel + "\".");
                    break;
                case CONVNET_CLASSIFIER:
                    explanation2Label.setText("This requirement contains words and/or word sequences often used with object type \"" + suggestedLabel + "\". These are highlighted below.");
                    updateSentenceMarkup(((ConvNetClassificationResult) classificationResult).getSentenceMarkup());
                    break;
                case HEADING_CLASSIFIER:
                    explanation2Label.setText("This item is a heading and should therefore be classified as \"" + suggestedLabel + "\".");
                    break;
                case PARSE_TREE_CLASSIFIER:
                    explanation2Label.setText("This item contains sentence patterns often used with object type \"" + suggestedLabel + "\".");
                    break;
                case TEMPLATE_CLASSIFIER:
                    explanation2Label.setText("This item was copied from a template and should therefore be classified as \"" + suggestedLabel + "\", according to the template.");
                    break;
                default:
                }
                fixButtonGroup.setVisible(true);
            } else {
                explanation1Label.setText("This item is correctly classified.");
                issueButtonGroup.setVisible(true);
            }

        }

    }

    private Paint getColor(final SentenceMarkupRange range) {
        final Color baseColor = range.getStrongestIndex() == 0 ? Color.RED : Color.GREEN;

        final Color c = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), range.getStrongest());

        return c;
    }

    private void updateSentenceMarkup(final SentenceMarkup sentenceMarkup) {
        int lastEnd = 0;
        for (final SentenceMarkupRange range : sentenceMarkup.getRanges()) {
            if (lastEnd < range.getStart()) {
                classifiedSentenceContainer.getChildren().add(new Label(object.getText().substring(lastEnd, range.getStart())));
            }
            lastEnd = range.getStart() + range.getLength();
            final Label coloredLabel = new Label(object.getText().substring(range.getStart(), lastEnd));
            coloredLabel.setBackground(new Background(new BackgroundFill(getColor(range), null, null)));
            classifiedSentenceContainer.getChildren().add(coloredLabel);
        }
        if (lastEnd < object.getText().length()) {
            classifiedSentenceContainer.getChildren().add(new Label(object.getText().substring(lastEnd)));
        }
    }

    @FXML
    public void ignoreOnce() {
        csvEditorController.getIgnoreList().ignoreOnce(object);
        updatePanels(object, classificationResult);
        csvEditorController.getCurrentTabController().refreshTable();
    }

    @FXML
    public void ignoreAlways() {
        csvEditorController.getIgnoreList().ignoreAlways(object);
        updatePanels(object, classificationResult);
        csvEditorController.getCurrentTabController().refreshTable();
    }

    @FXML
    public void unIgnore() {
        csvEditorController.getIgnoreList().unIgnore(object);
        updatePanels(object, classificationResult);
        csvEditorController.getCurrentTabController().refreshTable();
    }

    @FXML
    public void fix() {
        object.getAttributes().put("Object Type", classificationResult.getObjectType());
        updatePanels(object, classificationResult);
        csvEditorController.getCurrentTabController().refreshTable();
    }

    @FXML
    public void notAnIssue() {

    }

    @FXML
    public void thisIsAnIssue() {

    }

    public CSVEditorController getCsvEditorController() {
        return csvEditorController;
    }

    public void setCsvEditorController(final CSVEditorController csvEditorController) {
        this.csvEditorController = csvEditorController;
    }
}
