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
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableListBase;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public final class EditViewsPaneController extends AutoloadingPaneController<EditViewsPaneController> {

    @FXML
    private ListView<ViewDefinition> viewListView;

    @FXML
    private ListView<ViewDefinition.ColumnDefinition> colListView;

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
    private ComboBox<ColumnAttribute> colAttributeComboBox;
    @FXML
    private HBox colPositionHbox;
    @FXML
    private CheckBox colVisibleCheckBox;
    @FXML
    private Button colDeleteButton;
    @FXML
    private Button colAddButton;

    private ViewDefinition currentView;
    private ViewDefinition.ColumnDefinition currentCol;
    private int newCounter = 1;

    private final TreeMap<String, ColumnAttribute> knownAttributesTreeMap = new TreeMap<>();
    private final ObservableMap<String, ColumnAttribute> knownAttributes = FXCollections.observableMap(knownAttributesTreeMap);
    private static final String NEW_COLUMN_TEXT = "New column ";
    private static final String NEW_VIEW_TEXT = "New view ";

    private static final class ColumnAttribute {

        private final String columnAttribute;

        public ColumnAttribute(String columnAttribute) {
            this.columnAttribute = columnAttribute;
        }

        public String getColumnAttribute() {
            return columnAttribute;
        }

        public String getAttributeName() {
            return columnAttribute.isEmpty() ? null : columnAttribute;
        }

        @Override
        public String toString() {
            return columnAttribute.isEmpty() ? "Combined Object Heading/Text" : columnAttribute;
        }
    }

    public EditViewsPaneController(List<ViewDefinition> initialValue, Stream<String> knownAttributes) {
        initialValue.forEach(vd -> viewListView.getItems().add(vd));
        Stream.concat(knownAttributes, initialValue.stream()
                .flatMap(vd -> vd.getColumns().stream())
                .map(cd -> cd.getAttributeName()))
                .distinct()
                .filter(an -> an != null)
                .forEach(an -> this.knownAttributes.put(an, new ColumnAttribute(an)));
        this.knownAttributes.put("", new ColumnAttribute(""));

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

        colAttributeComboBox.setConverter(new StringConverter<ColumnAttribute>() {
            @Override
            public String toString(ColumnAttribute t) {
                return t == null ? null : t.toString();
            }

            @Override
            public ColumnAttribute fromString(String string) {
                return new ColumnAttribute(string);
            }
        });
        colAttributeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (currentCol != null) {
                if (newValue == null) {
                    newValue = this.knownAttributes.get("");
                } else if (!this.knownAttributes.containsKey(newValue.getColumnAttribute())) {
                    this.knownAttributes.put(newValue.getColumnAttribute(), newValue);
                }

                currentCol.setAttributeName(newValue.getAttributeName());

                if (colTitleTextField.getText().startsWith(NEW_COLUMN_TEXT)
                        || this.knownAttributes.containsKey(colTitleTextField.getText())
                        || colTitleTextField.getText().equals(this.knownAttributes.get("").toString())) {
                    colTitleTextField.setText(newValue.toString());
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

        colListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ViewDefinition.ColumnDefinition> event) -> {
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

    private void setColEnabled(ViewDefinition.ColumnDefinition cd) {
        this.currentCol = cd;
        if (cd != null) {
            colTitleTextField.setText(cd.getTitle());
            colTitleTextField.setDisable(false);

            colAttributeComboBox.setDisable(false);

            String columnAttribute = cd.getAttributeName() == null ? "" : cd.getAttributeName();
            if (!knownAttributes.containsKey(columnAttribute)) {
                knownAttributes.put(columnAttribute, new ColumnAttribute(columnAttribute));
            }

            colAttributeComboBox.setItems(new ObservableListBase<ColumnAttribute>() {
                @Override
                public ColumnAttribute get(int i) {
                    return knownAttributesTreeMap.values().stream().skip(i).findFirst().orElse(null);
                }

                @Override
                public int size() {
                    return knownAttributes.size();
                }
            });
            colAttributeComboBox.getSelectionModel().select(knownAttributes.get(columnAttribute));
            colPositionHbox.setDisable(false);

            colVisibleCheckBox.setDisable(false);
            colVisibleCheckBox.setSelected(cd.isVisible());

            colDeleteButton.setDisable(false);
        } else {
            colTitleTextField.setText("");
            colTitleTextField.setDisable(true);

            colAttributeComboBox.setDisable(true);
            colAttributeComboBox.setItems(FXCollections.emptyObservableList());

            colPositionHbox.setDisable(true);
            colVisibleCheckBox.setDisable(true);
            colVisibleCheckBox.setSelected(false);

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
    public void addColumnClicked() {
        ViewDefinition.ColumnDefinition cd = new ViewDefinition.ColumnDefinition(NEW_COLUMN_TEXT + newCounter++);
        this.colListView.getItems().add(cd);
        this.colListView.getSelectionModel().select(cd);
    }

    @FXML
    public void deleteColumnClicked() {
        ViewDefinition.ColumnDefinition cd = this.colListView.getSelectionModel().getSelectedItem();
        if (cd != null) {
            this.colListView.getItems().remove(cd);
        }
    }
}
