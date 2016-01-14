package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.ConfusionMatrix;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CSVEditorController {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorController.class.getName());

    private static final List<SupportedAlgorithm> SUPPORTED_ALGORITHMS = Arrays.asList();

    private Stage primaryStage;

    private final Map<Tab, CSVEditorTabController> tabControllers = new HashMap<>();
    private AbstractAlgorithmConfigurationController algorithmConfigurationController;

    final FileChooser chooser = new FileChooser();

    final ToggleGroup algorithmToggleGroup = new ToggleGroup();

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox includeChildrenCheckbox;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private VBox algorithmSelectionContainer;

    @FXML
    private TitledPane algorithmConfigurationContainer;

    @FXML
    private TitledPane trainingDataContainer;

    @FXML
    private GridPane resultGridPane;

    private DocumentTaggingAlgorithm<DoorsTreeNode, String> algorithm;

    private TaggedDocument<DoorsTreeNode, String> lastResult;

    @FXML
    public void initialize() {
        chooser.setInitialDirectory(new File("C:/WORK/DOORS"));
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(newValue, includeChildrenCheckbox.isSelected());
            }
        });
        includeChildrenCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(filterTextField.getText(), newValue);
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(final ObservableValue<? extends Tab> observable, final Tab oldValue, final Tab newValue) {
                if (newValue != null && tabControllers.get(newValue) != null) {
                    populateOutlineTreeView(tabControllers.get(newValue).getModule());
                }
            }
        });

        outlineTreeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<OutlineTreeItem>>) (observable, oldValue, newValue) -> {
            final DoorsTreeNode treeNode = newValue.getValue().getTreeNode();
            if (treeNode instanceof DoorsObject) {

                // TODO
                // contentTableView.getSelectionModel().select((DoorsObject)
                // treeNode);
                // contentTableView.scrollTo((DoorsObject) treeNode);
            }
        });

        for (final SupportedAlgorithm algorithm : SUPPORTED_ALGORITHMS) {
            final RadioButton radioButton = new RadioButton(algorithm.getName());
            radioButton.setToggleGroup(algorithmToggleGroup);
            radioButton.getProperties().put("algorithm", algorithm);
            radioButton.setOnAction(this::algorithmChanged);
            algorithmSelectionContainer.getChildren().add(radioButton);
        }

    }

    private void algorithmChanged(final ActionEvent event) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            final String viewName = ((SupportedAlgorithm) algorithmToggleGroup.getSelectedToggle().getProperties().get("algorithm")).getConfigurationViewFile();
            loader.setLocation(new File(viewName).toURI().toURL());
            final Parent root = loader.load();
            algorithmConfigurationContainer.setContent(root);
            algorithmConfigurationController = loader.getController();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void populateOutlineTreeView(final DoorsModule module) {
        final Map<DoorsTreeNode, Boolean> expanded = new HashMap<>();
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue().getTreeNode(), ti.isExpanded()));
        }

        final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
        traverseTreeItem(wrappedModule, ti -> ti.setExpanded(expanded.containsKey(ti.getValue().getTreeNode()) && expanded.get(ti.getValue().getTreeNode())));
        outlineTreeView.setRoot(wrappedModule);
    }

    private TreeItem<OutlineTreeItem> wrapModule(final DoorsTreeNode doorsTreeNode) {
        final TreeItem<OutlineTreeItem> treeItem = new TreeItem<OutlineTreeItem>(new OutlineTreeItem(doorsTreeNode));

        for (final DoorsTreeNode childNode : doorsTreeNode.getChildren()) {
            treeItem.getChildren().add(wrapModule(childNode));
        }
        return treeItem;
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    @FXML
    public void openClicked() {
        final File selectedFile = chooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            try {
                newTabFromFile(selectedFile);
            } catch (IOException | CSVParseException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

        }
    }

    public void newTabFromFile(final File selectedFile) throws IOException, CSVParseException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("view/csveditortab.fxml").toURI().toURL());
        final Parent root = loader.load();
        final CSVEditorTabController controller = loader.getController();
        final Tab tab = new Tab(selectedFile != null ? selectedFile.getName() : "New Document", root);

        controller.setMainController(this);
        controller.setFile(selectedFile);
        controller.setStage(primaryStage);
        controller.setTab(tab);
        tabControllers.put(tab, controller);

        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    public void saveClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).save();
        }
    }

    @FXML
    public void saveAsClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).saveAs();
        }
    }

    @FXML
    public void reduceToSelectionClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).reduceToSelection();
        }
    }

    @FXML
    public void closeClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            closeTab(selectedTab);
        }
    }

    private boolean closeTab(final Tab selectedTab) {
        final CSVEditorTabController controller = tabControllers.get(selectedTab);
        if (controller.isDirty()) {
            final Optional<ButtonType> saveConfirm = new Alert(AlertType.CONFIRMATION, "There are unsaved changes, what shall we save those?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL).showAndWait();
            if (saveConfirm.isPresent() && saveConfirm.get() == ButtonType.YES) {
                if (!controller.save()) {
                    return false;
                }
            } else if (saveConfirm.isPresent() && saveConfirm.get() == ButtonType.CANCEL) {
                return false;
            } else if (!saveConfirm.isPresent()) {
                return false;
            }
        }
        tabPane.getTabs().remove(selectedTab);
        tabControllers.remove(selectedTab);
        return true;
    }

    @FXML
    public void exitClicked() {
        for (final Tab tab : tabControllers.keySet()) {
            if (!closeTab(tab)) {
                return;
            }
        }
        Platform.exit();
    }

    @FXML
    public void addColumnClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).addColumn();
        }
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).swapObjectHeadingAndText();
        }
    }

    @FXML
    public void deleteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).deleteObject();
        }
    }

    @FXML
    public void unwrapChildrenClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).unwrapChildren();
        }
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void newObjectAfterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).newObjectAfter();
        }
    }

    @FXML
    public void newObjectBelowClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).newObjectBelow();
        }
    }

    @FXML
    public void promoteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).promoteObject();
        }
    }

    @FXML
    public void demoteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).demoteObject();
        }
    }

    @FXML
    public void newClicked() {
        try {
            newTabFromFile(null);
        } catch (IOException | CSVParseException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    public void undoClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).undo();
        }
    }

    @FXML
    public void redoClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).redo();
        }
    }

    @FXML
    public void columnsClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).setupColumns();
        }
    }

    @FXML
    public void filterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).setupFilter();
        }

    }

    @FXML
    public void cutClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).cut();
        }
    }

    @FXML
    public void copyClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).copy();
        }
    }

    @FXML
    public void pasteAfterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).pasteAfter();
        }
    }

    @FXML
    public void pasteBelowClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).pasteBelow();
        }
    }

    @FXML
    public void trainButtonClicked() {
        if (algorithmConfigurationController != null) {
            final List<DocumentAccessor<DoorsTreeNode>> trainingDocuments = new ArrayList<>();

            for (final Tab tab : tabPane.getTabs()) {
                if (!tab.isSelected()) {
                    trainingDocuments.add(new DoorsDocumentAccessor(tabControllers.get(tab).getModule()));
                    System.out.println(tabControllers.get(tab).getModule());
                }
            }

            algorithm = algorithmConfigurationController.getTrainedAlgorithm(trainingDocuments);
        }
    }

    @FXML
    public void flattenClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).flatten();
        }
    }

    @FXML
    public void tagButtonClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (algorithm != null && selectedTab != null) {
            final CSVEditorTabController curentTabController = tabControllers.get(selectedTab);
            final TaggedDocument<DoorsTreeNode, String> result = algorithm.tagDocument(new DoorsDocumentAccessor(curentTabController.getModule()));
            curentTabController.setTaggingResult(result);
            lastResult = result;
            buildResultGridPane();
        }
    }

    private void buildResultGridPane() {
        resultGridPane.getChildren().clear();
        final ConfusionMatrix<String> confusionMatrix = new ConfusionMatrix<>(lastResult);
        resultGridPane.add(new Label("Tag"), 0, 0);
        resultGridPane.add(new Label("Precision"), 1, 0);
        resultGridPane.add(new Label("Recall"), 2, 0);
        resultGridPane.add(new Label("F1"), 3, 0);

        for (int i = 0; i < confusionMatrix.getTags().size(); i++) {
            final String tag = confusionMatrix.getTags().get(i);

            resultGridPane.add(new Label(tag), 0, i + 1);
            resultGridPane.add(new Label(String.valueOf(confusionMatrix.getPrecision(tag))), 1, i + 1);
            resultGridPane.add(new Label(String.valueOf(confusionMatrix.getRecall(tag))), 2, i + 1);
            resultGridPane.add(new Label(String.valueOf(confusionMatrix.getF1Score(tag))), 3, i + 1);
        }

        resultGridPane.add(new Label("micro"), 0, confusionMatrix.getTags().size() + 1);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMicroPrecision())), 1, confusionMatrix.getTags().size() + 1);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMicroRecall())), 2, confusionMatrix.getTags().size() + 1);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMicroF1Score())), 3, confusionMatrix.getTags().size() + 1);

        resultGridPane.add(new Label("macro"), 0, confusionMatrix.getTags().size() + 2);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMacroPrecision())), 1, confusionMatrix.getTags().size() + 2);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMacroRecall())), 2, confusionMatrix.getTags().size() + 2);
        resultGridPane.add(new Label(String.valueOf(confusionMatrix.getMacroF1Score())), 3, confusionMatrix.getTags().size() + 2);
    }

    @FXML
    public void showConfusionMatrixDialog() {
        if (lastResult != null) {
            new ConfusionMatrixDialog(new ConfusionMatrix<>(lastResult), primaryStage).show();
        }
    }
}