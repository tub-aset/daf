package de.jpwinkler.daf.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Window;

public class EditViewsPaneController {

    public static Dialog<List<ViewDefinition>> asDialog(Window owner, List<ViewDefinition> initialValue, Stream<String> knownAttributes) {
        try {
            var dialog = new Dialog();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);
            dialog.setResizable(true);

            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("EditViewsPane.fxml"));
            dialog.getDialogPane().setContent(loader.load());
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            final EditViewsPaneController editViewsController = loader.getController();
            editViewsController.initialize(initialValue, knownAttributes);
            dialog.setResultConverter(bt -> bt == ButtonType.OK ? editViewsController.viewListView.getItems() : null);
            return dialog;
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private ListView<ViewDefinition> viewListView;

    @FXML
    private ListView<ColumnDefinition> colListView;

    @FXML
    private TextField viewTitleTextField;
    @FXML
    private HBox viewPositionHbox;
    @FXML
    private CheckBox viewAllAttributesCheckBox;
    @FXML
    private Button viewDeleteButton;

    @FXML
    private TextField colTitleTextField;
    @FXML
    private ComboBox<String> colAttributeComboBox;
    @FXML
    private HBox colPositionHbox;
    @FXML
    private CheckBox colVisibleCheckBox;
    @FXML
    private Button colDeleteButton;
    @FXML
    private Button colAddButton;

    private ViewDefinition currentView;
    private ColumnDefinition currentCol;
    private int newCounter = 1;

    private final SortedSet<String> knownAttributes = new TreeSet<>();

    @FXML
    public void initialize() {
    }

    public void initialize(List<ViewDefinition> initialValue, Stream<String> knownAttributes) {
        initialValue.forEach(vd -> viewListView.getItems().add(vd));
        Stream.concat(knownAttributes, initialValue.stream()
                .flatMap(vd -> vd.getColumns().stream())
                .map(cd -> cd.getAttributeName()))
                .distinct()
                .forEach(an -> this.knownAttributes.add(an == null ? "" : an));
        this.knownAttributes.add("");

        viewTitleTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (currentView != null) {
                currentView.setName(newValue);
                viewListView.refresh();
            }
        });
        viewAllAttributesCheckBox.selectedProperty().addListener((obs, oldValue, newValue) -> {
            if (currentView != null) {
                currentView.setDisplayRemainingColumns(newValue);
            }
        });

        colTitleTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (currentCol != null) {
                currentCol.setTitle(newValue);
                colListView.refresh();
            }
        });
        colAttributeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (currentCol != null) {
                currentCol.setAttributeName((newValue == null || newValue.isEmpty()) ? null : newValue);
                if (newValue != null) {
                    this.knownAttributes.add(newValue);
                }
            }
        });
        colVisibleCheckBox.selectedProperty().addListener((obs, oldValue, newValue) -> {
            if (currentCol != null) {
                currentCol.setVisible(newValue);
            }
        });

        viewListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ViewDefinition> event) -> {
            setViewEnabled(event.getList().stream().findAny().orElse(null));
        });

        colListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ColumnDefinition> event) -> {
            setColEnabled(event.getList().stream().findAny().orElse(null));
        });
    }

    private void setViewEnabled(ViewDefinition vd) {
        this.currentView = vd;
        if (vd != null) {
            colListView.setItems(FXCollections.observableList(vd.getColumns()));

            viewTitleTextField.setText(vd.getName());
            viewTitleTextField.setDisable(false);
            viewPositionHbox.setDisable(false);

            viewAllAttributesCheckBox.setDisable(false);
            viewAllAttributesCheckBox.setSelected(vd.isDisplayRemainingColumns());

            viewDeleteButton.setDisable(false);
            colAddButton.setDisable(false);
        } else {
            colListView.setItems(FXCollections.emptyObservableList());

            viewTitleTextField.setText("");
            viewTitleTextField.setDisable(true);
            viewPositionHbox.setDisable(true);
            viewAllAttributesCheckBox.setSelected(false);

            viewDeleteButton.setDisable(true);
            colAddButton.setDisable(true);
        }
    }

    private void setColEnabled(ColumnDefinition cd) {
        this.currentCol = cd;
        if (cd != null) {
            colTitleTextField.setText(cd.getTitle());
            colTitleTextField.setDisable(false);

            colAttributeComboBox.setDisable(false);
            
            String attributeName = cd.getAttributeName();
            colAttributeComboBox.setItems(FXCollections.observableArrayList(knownAttributes));
            colAttributeComboBox.getSelectionModel().select(attributeName);
            colPositionHbox.setDisable(false);

            colVisibleCheckBox.setDisable(false);
            colVisibleCheckBox.setSelected(cd.isVisible());

            colDeleteButton.setDisable(false);
        } else {
            colTitleTextField.setText("");
            colTitleTextField.setDisable(true);

            colAttributeComboBox.setDisable(true);
            colAttributeComboBox.setItems(FXCollections.observableArrayList(Collections.emptyList()));

            colPositionHbox.setDisable(true);
            colVisibleCheckBox.setDisable(true);
            colVisibleCheckBox.setSelected(false);

            colDeleteButton.setDisable(true);
        }
    }

    @FXML
    public void colTopClicked() {
        moveSelectedTo(colListView, Integer.MIN_VALUE);
    }

    @FXML
    public void colUpClicked() {
        moveSelectedTo(colListView, -1);
    }

    @FXML
    public void colDownClicked() {
        moveSelectedTo(colListView, 1);
    }

    @FXML
    public void colBottomClicked() {
        moveSelectedTo(colListView, Integer.MAX_VALUE);
    }

    @FXML
    public void viewTopClicked() {
        moveSelectedTo(viewListView, Integer.MIN_VALUE);
    }

    @FXML
    public void viewUpClicked() {
        moveSelectedTo(viewListView, -1);
    }

    @FXML
    public void viewDownClicked() {
        moveSelectedTo(viewListView, 1);
    }

    @FXML
    public void viewBottomClicked() {
        moveSelectedTo(viewListView, Integer.MAX_VALUE);
    }

    private <T> void moveSelectedTo(ListView<T> lv, int relativePosition) {
        if (lv.getSelectionModel().isEmpty()) {
            return;
        }

        final int selectedIndex = lv.getSelectionModel().getSelectedIndex();
        final T obj = lv.getItems().remove(selectedIndex);

        long newIndex = (long) selectedIndex + (long) relativePosition;
        if (newIndex < 0) {
            newIndex = 0;
        }
        if (newIndex > lv.getItems().size()) {
            newIndex = lv.getItems().size();
        }

        lv.getItems().add((int) newIndex, obj);
        lv.getSelectionModel().select(obj);
    }

    @FXML
    public void addViewClicked() {
        ViewDefinition vd = new ViewDefinition("New view " + newCounter++);
        this.viewListView.getItems().add(vd);
        this.viewListView.getSelectionModel().select(vd);
    }

    @FXML
    public void deleteViewClicked() {
        ViewDefinition vd = this.viewListView.getSelectionModel().getSelectedItem();
        if (vd != null) {
            this.viewListView.getItems().remove(vd);
        }
    }

    @FXML
    public void addColumnClicked() {
        ColumnDefinition cd = new ColumnDefinition("New column " + newCounter++);
        this.colListView.getItems().add(cd);
        this.colListView.getSelectionModel().select(cd);
    }

    @FXML
    public void deleteColumnClicked() {
        ColumnDefinition cd = this.colListView.getSelectionModel().getSelectedItem();
        if (cd != null) {
            this.colListView.getItems().remove(cd);
        }
    }
}
