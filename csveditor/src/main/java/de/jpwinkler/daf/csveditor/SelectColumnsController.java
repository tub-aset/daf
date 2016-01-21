package de.jpwinkler.daf.csveditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.csveditor.commands.UpdateAction;
import de.jpwinkler.daf.csveditor.util.ColumnDefinition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;

public class SelectColumnsController {

    @FXML
    private ListView<ColumnDefinition> listView;

    private CSVEditorTabController tabController;
    private Stage dialogStage;

    private final Map<ColumnDefinition, Boolean> newVisibilities = new HashMap<>();

    @FXML
    public void initialize() {
        listView.setCellFactory(CheckBoxListCell.forListView(param -> {
            final SimpleBooleanProperty p = new SimpleBooleanProperty(newVisibilities.containsKey(param) ? newVisibilities.get(param) : param.isVisible());
            p.addListener((observable, oldValue, newValue) -> newVisibilities.put(param, newValue));
            return p;
        }));
    }

    public void setTabController(final CSVEditorTabController tabController) {
        this.tabController = tabController;
        listView.getItems().addAll(tabController.getViewModel().getDisplayedColumns());
    }

    @FXML
    public void okClicked() {
        tabController.getViewModel().getDisplayedColumns().clear();
        tabController.getViewModel().getDisplayedColumns().addAll(listView.getItems());
        for (final Entry<ColumnDefinition, Boolean> e : newVisibilities.entrySet()) {
            e.getKey().setVisible(e.getValue());
        }
        tabController.updateGui(UpdateAction.UPDATE_COLUMNS);
        dialogStage.close();
    }

    @FXML
    public void cancelClicked() {
        dialogStage.close();
    }

    @FXML
    public void topClicked() {
        final int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            final ColumnDefinition cd = listView.getItems().remove(selectedIndex);
            listView.getItems().add(0, cd);
            listView.getSelectionModel().select(cd);
        }
    }

    @FXML
    public void upClicked() {
        final int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            final ColumnDefinition cd = listView.getItems().remove(selectedIndex);
            listView.getItems().add(selectedIndex - 1, cd);
            listView.getSelectionModel().select(cd);
        }
    }

    @FXML
    public void downClicked() {
        final int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < listView.getItems().size() - 1) {
            final ColumnDefinition cd = listView.getItems().remove(selectedIndex);
            listView.getItems().add(selectedIndex + 1, cd);
            listView.getSelectionModel().select(cd);
        }
    }

    @FXML
    public void botClicked() {
        final int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < listView.getItems().size() - 1) {
            final ColumnDefinition cd = listView.getItems().remove(selectedIndex);
            listView.getItems().add(cd);
            listView.getSelectionModel().select(cd);
        }
    }

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
