package de.jpwinkler.daf.gui.modules;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.gui.AutoloadingPaneController;
import de.jpwinkler.daf.gui.modules.ViewDefinition.ColumnDefinition;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ModifiableObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public final class EditViewsPaneController extends AutoloadingPaneController<EditViewsPaneController> {

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
    private ChoiceBox<ViewDefinition.ColumnType> colTypeChoiceBox;
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
    private boolean dirtyColumnTitle = false;

    private final TreeSet<String> knownAttributes = new TreeSet<>();
    private static final String NEW_COLUMN_TEXT = "New column ";
    private static final String NEW_VIEW_TEXT = "New view ";

    public EditViewsPaneController(List<ViewDefinition> initialValue, Stream<String> knownAttributes) {
        Stream.concat(initialValue.stream().map(iv -> iv.copy())
                .peek(vd -> viewListView.getItems().add(vd))
                .flatMap(vd -> vd.getColumns().stream())
                .map(cd -> cd.getAttributeName()), knownAttributes)
                .distinct()
                .filter(an -> an != null)
                .forEach(an -> this.knownAttributes.add(an));

        colAttributeComboBox.setItems(new ModifiableObservableListBase<String>() {
            @Override
            public String get(int i) {
                return EditViewsPaneController.this.knownAttributes.stream().skip(i).findFirst().orElse(null);
            }

            @Override
            public int size() {
                return EditViewsPaneController.this.knownAttributes.size();
            }

            @Override
            protected void doAdd(int i, String e) {
                EditViewsPaneController.this.knownAttributes.add(e);
            }

            @Override
            protected String doSet(int i, String e) {
                throw new UnsupportedOperationException("Not supported");
            }

            @Override
            protected String doRemove(int i) {
                throw new UnsupportedOperationException("Not supported");
            }
        });

        colTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((ov, oldT, newT) -> {
            if (currentCol == null || newT == null || !newT.isUsesAttributeName()) {
                colAttributeComboBox.setDisable(true);
                colAttributeComboBox.getSelectionModel().clearSelection();
                return;
            }

            colAttributeComboBox.setDisable(false);
        });

        this.colTitleTextField.addEventFilter(KeyEvent.KEY_TYPED, (t) -> this.dirtyColumnTitle = true);

        colAttributeComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            if (this.dirtyColumnTitle) {
                return;
            }
            this.colTitleTextField.setText(newValue);
        });

        viewListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ViewDefinition> event) -> {
            setViewEnabled(event.getList().stream().findAny().orElse(null));
        });

        colListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ColumnDefinition> event) -> {
            setColEnabled(event.getList().stream().findAny().orElse(null));
        });
    }

    @Override
    protected void onClose(Optional<ButtonType> closeButton) {
        closeButton
                .filter(bt -> bt == ButtonType.OK)
                .ifPresent(bt -> {
                    this.setColEnabled(null);
                    this.setViewEnabled(null);
                });
    }

    private void setViewEnabled(ViewDefinition vd) {
        if (this.currentView != null) {
            currentView.setName(viewTitleTextField.getText());
            currentView.setDisplayRemainingColumns(viewAllAttributesCheckBox.isSelected());

            viewListView.refresh();
        }

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
        if (currentCol != null) {
            currentCol.setTitle(colTitleTextField.getText());
            currentCol.setVisible(colVisibleCheckBox.isSelected());
            currentCol.setColumnType(colTypeChoiceBox.getValue());
            colAttributeComboBox.commitValue();
            colAttributeComboBox.getItems().add(colAttributeComboBox.getValue());
            currentCol.setAttributeName(colAttributeComboBox.getValue());

            colListView.refresh();
        }

        this.currentCol = cd;
        if (cd != null) {
            colTypeChoiceBox.setDisable(false);
            colTypeChoiceBox.setItems(FXCollections.observableArrayList(ViewDefinition.ColumnType.values()));
            colTypeChoiceBox.getSelectionModel().select(cd.getColumnType());

            colPositionHbox.setDisable(false);

            colVisibleCheckBox.setDisable(false);
            colVisibleCheckBox.setSelected(cd.isVisible());

            colAttributeComboBox.setDisable(!currentCol.getColumnType().isUsesAttributeName());
            if (currentCol.getColumnType().isUsesAttributeName()) {
                colAttributeComboBox.getSelectionModel().select(currentCol.getAttributeName() == null ? "" : currentCol.getAttributeName());
            } else {
                colAttributeComboBox.getSelectionModel().clearSelection();
            }

            colDeleteButton.setDisable(false);

            // set title last -- dirtyColumnTitle == true && colAttributeComboBox.select may overwrite it
            colTitleTextField.setText(cd.getTitle());
            colTitleTextField.setDisable(false);

            this.dirtyColumnTitle = !cd.getTitle().trim().isEmpty() && !knownAttributes.contains(cd.getTitle());

        } else {
            colTitleTextField.setText("");
            colTitleTextField.setDisable(true);

            colTypeChoiceBox.setDisable(true);
            colTypeChoiceBox.setItems(FXCollections.emptyObservableList());

            colPositionHbox.setDisable(true);
            colVisibleCheckBox.setDisable(true);
            colVisibleCheckBox.setSelected(false);

            colAttributeComboBox.setDisable(true);
            colAttributeComboBox.getSelectionModel().clearSelection();

            colDeleteButton.setDisable(true);
        }
    }

    public List<ViewDefinition> getViews() {
        return viewListView.getItems();
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
        ViewDefinition vd = new ViewDefinition(NEW_VIEW_TEXT + newCounter++);
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
    public void copyViewButtonClicked() {
        this.viewListView.getSelectionModel().getSelectedItems().stream()
                .map(vd -> vd.copy())
                .forEach(this.viewListView.getItems()::add);
    }

    @FXML
    public void addColumnClicked() {
        ColumnDefinition cd = new ColumnDefinition(ViewDefinition.ColumnType.ATTRIBUTE, NEW_COLUMN_TEXT + newCounter++);
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
