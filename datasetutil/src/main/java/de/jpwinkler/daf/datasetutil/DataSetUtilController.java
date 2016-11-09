package de.jpwinkler.daf.datasetutil;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DataSetUtilController implements ClipboardOwner {

    @FXML
    private Label parentLabel;
    @FXML
    private Label previousLabel;
    @FXML
    private Label currentLabel;
    @FXML
    private Label child1Label;
    @FXML
    private Label child2Label;
    @FXML
    private Label nextLabel;
    @FXML
    private CheckBox unknownStructuralTypeCheckBox;
    @FXML
    private CheckBox combinedStructuralTypeCheckBox;
    @FXML
    private CheckBox ambiguousObjectTypeCheckBox;
    @FXML
    private RadioButton structuralTypeHeadingRadioButton;
    @FXML
    private ToggleGroup structuralTypeGroup;
    @FXML
    private RadioButton structuralTypeIdentifierRadioButton;
    @FXML
    private RadioButton structuralTypeItemizationRadioButton;
    @FXML
    private RadioButton structuralTypeGroupOfWordsRadioButton;
    @FXML
    private RadioButton structuralTypeEnumerationRadioButton;
    @FXML
    private RadioButton structuralTypeDefinitionRadioButton;
    @FXML
    private RadioButton structuralTypeBooleanExpressionRadioButton;
    @FXML
    private RadioButton structuralTypeSentenceRadioButton;
    @FXML
    private RadioButton structuralTypeMathExpressionRadioButton;
    @FXML
    private RadioButton objectTypeInformationRadioButton;
    @FXML
    private ToggleGroup objectTypeGroup;
    @FXML
    private RadioButton objectTypeRequirementRadioButton;
    @FXML
    private RadioButton objectTypePredefinitionRadioButton;
    @FXML
    private RadioButton objectTypeHeadingRadioButton;
    @FXML
    private RadioButton objectTypeProcessRequirementRadioButton;
    @FXML
    private TableView<Pair<String, String>> taskInfoTableView;
    @FXML
    private TableColumn<Pair<String, String>, String> taskInfoKeyColumn;
    @FXML
    private TableColumn<Pair<String, String>, String> taskInfoValueColumn;
    @FXML
    private Label statusLabel;

    private Stage primaryStage;

    private final DataSetStorage storage = new DataSetStorage();

    private final SynchronizedQueue<Task> taskQueue = new SynchronizedQueue<>(5);
    private final SynchronizedQueue<DoorsObject> objectQueue = new SynchronizedQueue<>(10000);

    private Task task;

    private void newTask() {
        task = taskQueue.dequeue();
        currentLabel.setText(task.getBody());
        parentLabel.setText(task.getParentBody());
        previousLabel.setText(task.getPreviousBody());
        nextLabel.setText(task.getNextBody());
        child1Label.setText(task.getChildBodies().size() > 0 ? task.getChildBodies().get(0) : null);
        child2Label.setText(task.getChildBodies().size() > 1 ? "..." : "");
        final List<Pair<String, String>> taskInfo = new ArrayList<>();
        taskInfo.add(Pair.of("Structural", task.getStructuralType()));
        taskInfo.add(Pair.of("", ""));
        taskInfo.add(Pair.of("Similiarity OT", task.getSimiliarityAnalysisObjectType()));
        taskInfo.add(Pair.of("Similiarity", Double.toString(task.getGreatestSimiliarity())));
        taskInfo.add(Pair.of("", ""));
        taskInfo.add(Pair.of("Original OT", task.getOriginalObjectType()));
        taskInfo.add(Pair.of("", ""));
        taskInfo.add(Pair.of("Cluster OT", task.getClusterAnalysisObjectType()));
        taskInfo.add(Pair.of("", ""));
        taskInfo.add(Pair.of("ConvNet OT", task.getConvNetClassification()));
        taskInfo.add(Pair.of("ConvNet Info", Double.toString(task.getConvNetInf())));
        taskInfo.add(Pair.of("ConvNet Req", Double.toString(task.getConvNetReq())));
        taskInfoTableView.setItems(FXCollections.observableArrayList(taskInfo));
        objectTypeGroup.selectToggle(null);
        structuralTypeGroup.selectToggle(null);
        ambiguousObjectTypeCheckBox.setSelected(false);
        unknownStructuralTypeCheckBox.setSelected(false);
        combinedStructuralTypeCheckBox.setSelected(false);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(task.getBody()), this);
        autofill();
        updateStatus();
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() throws IOException {
        if (new File("queue.json.gz").exists()) {
            taskQueue.load("queue.json.gz");
        }
        if (new File("dataset.json.gz").exists()) {
            storage.load("dataset.json.gz");
        }
        new ObjectQueueFillThread(objectQueue).start();
        new TaskQueueFillThread(objectQueue, taskQueue, storage).start();
        taskInfoKeyColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getKey()));
        taskInfoValueColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue()));
        newTask();
    }

    @FXML
    public void keyPressed(final KeyEvent event) throws IOException {
        switch (event.getCode()) {
        case I:
            objectTypeInformationRadioButton.setSelected(true);
            break;
        case R:
            objectTypeRequirementRadioButton.setSelected(true);
            break;
        case P:
            objectTypePredefinitionRadioButton.setSelected(true);
            break;
        case H:
            objectTypeHeadingRadioButton.setSelected(true);
            break;
        case T:
            objectTypeProcessRequirementRadioButton.setSelected(true);
            break;
        case DIGIT1:
            structuralTypeHeadingRadioButton.setSelected(true);
            break;
        case DIGIT2:
            structuralTypeSentenceRadioButton.setSelected(true);
            break;
        case DIGIT3:
            structuralTypeGroupOfWordsRadioButton.setSelected(true);
            break;
        case DIGIT4:
            structuralTypeDefinitionRadioButton.setSelected(true);
            break;
        case DIGIT5:
            structuralTypeIdentifierRadioButton.setSelected(true);
            break;
        case DIGIT6:
            structuralTypeItemizationRadioButton.setSelected(true);
            break;
        case DIGIT7:
            structuralTypeEnumerationRadioButton.setSelected(true);
            break;
        case DIGIT8:
            structuralTypeBooleanExpressionRadioButton.setSelected(true);
            break;
        case DIGIT9:
            structuralTypeMathExpressionRadioButton.setSelected(true);
            break;
        case COMMA:
            unknownStructuralTypeCheckBox.setSelected(!unknownStructuralTypeCheckBox.isSelected());
            structuralTypeGroup.getToggles().forEach(t -> ((RadioButton) t).setDisable(unknownStructuralTypeCheckBox.isSelected() || combinedStructuralTypeCheckBox.isSelected()));
            break;
        case PERIOD:
            combinedStructuralTypeCheckBox.setSelected(!combinedStructuralTypeCheckBox.isSelected());
            structuralTypeGroup.getToggles().forEach(t -> ((RadioButton) t).setDisable(unknownStructuralTypeCheckBox.isSelected() || combinedStructuralTypeCheckBox.isSelected()));
            break;
        case MINUS:
            ambiguousObjectTypeCheckBox.setSelected(!ambiguousObjectTypeCheckBox.isSelected());
            objectTypeGroup.getToggles().forEach(t -> ((RadioButton) t).setDisable(ambiguousObjectTypeCheckBox.isSelected()));
            break;
        case SHIFT:
            // accept
            createRecord();
            newTask();
            break;
        case S:
            // queue.save("queue.json.gz");
            storage.save("dataset.json.gz");
            break;
        case O:
            storage.export("Y:/git/nn-object-classification/data/confidential/reqinf-online.txt");
            break;
        case CONTROL:
            // ignore
            newTask();
        default:
            break;
        }

    }

    private void autofill() {
        selectStructuralType(task.getStructuralType());
        if ("heading".equals(task.getOriginalObjectType())) {
            selectObjectType("heading");
            selectStructuralType("heading");
        } else if (task.getGreatestSimiliarity() > 0.95) {
            selectObjectType(task.getSimiliarityAnalysisObjectType());
            selectStructuralType(task.getSimiliarityAnalysisStructuralType());
        } else if (task.getClusterAnalysisObjectType() != null) {
            selectObjectType(task.getClusterAnalysisObjectType());
        }
    }

    private void selectObjectType(final String ot) {
        switch (ot) {
        case "requirement":
            objectTypeRequirementRadioButton.setSelected(true);
            break;
        case "information":
            objectTypeInformationRadioButton.setSelected(true);
            break;
        case "predefinition":
            objectTypePredefinitionRadioButton.setSelected(true);
            break;
        case "process_requirement":
            objectTypeProcessRequirementRadioButton.setSelected(true);
            break;
        case "heading":
            objectTypeHeadingRadioButton.setSelected(true);
            break;
        default:
            objectTypeGroup.selectToggle(null);
            break;
        }
    }

    private void selectStructuralType(final String st) {
        switch (st) {
        case "heading":
            structuralTypeHeadingRadioButton.setSelected(true);
            break;
        case "sentence":
        case "sentence(single)":
        case "sentence(multi)":
        case "sentence(single_wrapped)":
        case "sentence(multi_wrapped)":
            structuralTypeSentenceRadioButton.setSelected(true);
            break;
        case "group_of_words":
            structuralTypeGroupOfWordsRadioButton.setSelected(true);
            break;
        case "itemization":
            structuralTypeItemizationRadioButton.setSelected(true);
            break;
        case "enumeration":
            structuralTypeEnumerationRadioButton.setSelected(true);
            break;
        case "definition":
            structuralTypeDefinitionRadioButton.setSelected(true);
            break;
        case "identifier":
            structuralTypeIdentifierRadioButton.setSelected(true);
            break;
        case "boolean_expression":
            structuralTypeBooleanExpressionRadioButton.setSelected(true);
            break;
        case "math_expression":
            structuralTypeMathExpressionRadioButton.setSelected(true);
            break;
        default:

        }
    }

    private void createRecord() {
        final DataSetRecord r = new DataSetRecord();
        r.setBody(task.getBody());
        r.setParentBody(task.getParentBody());
        r.setPreviousBody(task.getPreviousBody());
        r.setNextBody(task.getNextBody());
        r.setOriginalObjectType(task.getOriginalObjectType());
        r.setAmbiguousObjectType(ambiguousObjectTypeCheckBox.isSelected());
        r.setCombinedStructuralType(combinedStructuralTypeCheckBox.isSelected());
        r.setUnknownStructuralType(unknownStructuralTypeCheckBox.isSelected());
        r.setDateAdded(new Date());
        if (!ambiguousObjectTypeCheckBox.isSelected()) {
            r.setCorrectedObjectType(((RadioButton) objectTypeGroup.getSelectedToggle()).getText());
        }
        if (!combinedStructuralTypeCheckBox.isSelected() && !unknownStructuralTypeCheckBox.isSelected()) {
            r.setStructualType(((RadioButton) structuralTypeGroup.getSelectedToggle()).getText());
        }
        storage.add(r);
        updateStatus();
    }

    private void updateStatus() {
        statusLabel.setText("Storage size: " + storage.size() + ", Object Queue Size: " + objectQueue.size() + ", Task Queue size: " + taskQueue.size());
    }

    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
    }

}
