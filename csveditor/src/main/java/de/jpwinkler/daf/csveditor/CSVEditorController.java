package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.csveditor.background.BackgroundTask;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusMonitor;
import de.jpwinkler.daf.csveditor.massedit.CopyAttributeOperation;
import de.jpwinkler.daf.csveditor.massedit.MassEditOperation;
import de.jpwinkler.daf.csveditor.massedit.MassEditTarget;
import de.jpwinkler.daf.csveditor.massedit.SetAttributeOperation;
import de.jpwinkler.daf.csveditor.util.ExceptionDialog;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;
import de.jpwinkler.daf.doorscsv.util.CSVParseException;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CSVEditorController {


    private static final Logger LOGGER = Logger.getLogger(CSVEditorController.class.getName());

    private Stage primaryStage;

    private final Map<Tab, CSVEditorTabController> tabControllers = new HashMap<>();

    final FileChooser chooser = new FileChooser();

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox includeChildrenCheckbox;

    @FXML
    private CheckBox includeParentsCheckbox;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private Label statusBarLabel;

    final Map<DoorsTreeNode, Boolean> expanded = new WeakHashMap<>();

    private final BackgroundTaskStatusMonitor backgroundTaskStatusMonitor = new BackgroundTaskStatusMonitor();

    @FXML
    private ToggleButton filterExpressionToggleButton;

    @FXML
    private RadioButton massEditAllObjectsCheckBox;

    @FXML
    private RadioButton massEditSelectedObjectsCheckBox;
    @FXML
    private RadioButton massEditFilteredObjectsCheckBox;

    @FXML
    private RadioButton massEditSetAttributeCheckBox;

    @FXML
    private TextField massEditSetAttributeNameTextField;
    @FXML
    private TextField massEditSetAttributeValueTextField;

    @FXML
    private RadioButton massEditCopyAttributeCheckBox;

    @FXML
    private TextField massEditCopyAttributeFromTextField;

    @FXML
    private TextField massEditCopyAttributeToTextField;

    @FXML
    private Label backgroundTaskStatusLabel;

    @FXML
    private ProgressBar backgroundTaskStatusProgressBar;

    @FXML
    private ToolBar backgroundTaskStatusToolBar;

    @FXML
    private ListView<String> featureListView;

    @FXML
    private Menu recentMenu;

    @FXML
    private Tab objectTypeClassificationTab;


    private ChangeListener<TreeItem<OutlineTreeItem>> outlineListener;

    private final BooleanProperty simpleMode = new SimpleBooleanProperty(false);

    public boolean getSimpleMode() {
        return simpleMode.get();
    }

    public void setSimpleMode(final boolean value) {
        simpleMode.set(value);
    }

    @FXML
    public void initialize() {
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(newValue, includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
            }
        });
        includeChildrenCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), newValue, filterExpressionToggleButton.isSelected());
            }
        });
        includeParentsCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(filterTextField.getText(), newValue, includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(final ObservableValue<? extends Tab> observable, final Tab oldValue, final Tab newValue) {
                if (newValue != null && tabControllers.get(newValue) != null) {
                    populateOutlineTreeView(tabControllers.get(newValue).getModule());
                } else {
                    populateOutlineTreeView(null);
                }
            }
        });

        outlineListener = (ChangeListener<TreeItem<OutlineTreeItem>>) (observable, oldValue, newValue) -> {
            throw new UnsupportedOperationException("Not implemented");
        };

        outlineTreeView.getSelectionModel().selectedItemProperty().addListener(outlineListener);

        backgroundTaskStatusToolBar.setManaged(false);
        backgroundTaskStatusToolBar.setVisible(false);
        backgroundTaskStatusMonitor.addListener(new BackgroundTaskStatusListener() {

            @Override
            public void onUpdateStatus(final String taskName, final int current, final int max) {
                Platform.runLater(() -> {
                    backgroundTaskStatusToolBar.setManaged(true);
                    backgroundTaskStatusToolBar.setVisible(true);
                    backgroundTaskStatusLabel.setText(taskName);
                    backgroundTaskStatusProgressBar.setProgress((double) current / (double) max);
                });
            }

            @Override
            public void onDone() {
                Platform.runLater(() -> {
                    backgroundTaskStatusToolBar.setManaged(false);
                    backgroundTaskStatusToolBar.setVisible(false);
                });
            }

        });

    }

    public void populateOutlineTreeView(final DoorsModule module) {
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue().getTreeNode(), ti.isExpanded()));
        }

        if (module != null) {

            final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
            traverseTreeItem(wrappedModule, ti -> ti.setExpanded(expanded.containsKey(ti.getValue().getTreeNode()) && expanded.get(ti.getValue().getTreeNode())));
            outlineTreeView.setRoot(wrappedModule);
        } else {
            outlineTreeView.setRoot(null);
        }
    }

    private TreeItem<OutlineTreeItem> wrapModule(final DoorsTreeNode doorsTreeNode) {
        final TreeItem<OutlineTreeItem> treeItem = new TreeItem<>(new OutlineTreeItem(doorsTreeNode));

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
                ExceptionDialog.showExceptionDialog(e);
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

        }
    }

    public void newTabFromFile(final File selectedFile) throws IOException, CSVParseException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/csveditortab.fxml"));
        final Parent root = loader.load();
        final CSVEditorTabController controller = loader.getController();
        final Tab tab = new Tab(selectedFile != null ? selectedFile.getName() : "New Document", root);

        tab.setClosable(true);

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
            ExceptionDialog.showExceptionDialog(e);
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
    public void flattenClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).flatten();
        }
    }

    public void objectSelected(final DoorsObject newValue) {
        traverseTreeItem(outlineTreeView.getRoot(), item -> {
            if (item.getValue().getTreeNode().equals(newValue)) {
                outlineTreeView.getSelectionModel().selectedItemProperty().removeListener(outlineListener);
                outlineTreeView.getSelectionModel().select(item);
                outlineTreeView.getSelectionModel().selectedItemProperty().addListener(outlineListener);
            }
        });
    }

    @FXML
    public void filterExpressionToggleButtonClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
        }
    }

    public void setStatus(final String status) {
        statusBarLabel.setText(status);
    }

    @FXML
    public void massEditRunClicked() {
        MassEditTarget target;

        if (massEditAllObjectsCheckBox.isSelected()) {
            target = MassEditTarget.ALL_OBJECTS;
        } else if (massEditFilteredObjectsCheckBox.isSelected()) {
            target = MassEditTarget.FILTERED_OBJECTS;
        } else if (massEditSelectedObjectsCheckBox.isSelected()) {
            target = MassEditTarget.SELECTED_OBJECTS;
        } else {
            throw new RuntimeException("No viable value for mass edit target available.");
        }

        MassEditOperation operation;

        if (massEditSetAttributeCheckBox.isSelected()) {
            operation = new SetAttributeOperation(massEditSetAttributeNameTextField.getText(), massEditSetAttributeValueTextField.getText());
        } else if (massEditCopyAttributeCheckBox.isSelected()) {
            operation = new CopyAttributeOperation(massEditCopyAttributeFromTextField.getText(), massEditCopyAttributeToTextField.getText());
        } else {
            throw new RuntimeException("No viable operation for mass edit found.");
        }

        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).massEdit(target, operation);
        }
    }

    public void runBackgroundTask(final BackgroundTask backgroundTask) {
        backgroundTask.setMonitor(backgroundTaskStatusMonitor);
        new Thread(backgroundTask).start();
    }

    public void setFeatureList(final List<String> features) {
        featureListView.getItems().clear();
        featureListView.getItems().addAll(features);
    }

    @FXML
    public void splitLinesClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).splitLines();
        }
    }

    @FXML
    public void analyzeObjectTypeClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).analyzeObjectType();
        }
    }

    public CSVEditorTabController getCurrentTabController() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return tabControllers.get(selectedTab);
        } else {
            return null;
        }
    }

}