package de.jpwinkler.daf.csveditor.views;

import de.jpwinkler.daf.csveditor.MainFX;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Modality;
import javafx.stage.Window;

public class EditViewsPaneController {

    public static Dialog<List<ViewModel>> asDialog(Window owner, List<ViewModel> initialValue) {
        try {
            var dialog = new Dialog();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("EditViewsPane.fxml"));
            dialog.getDialogPane().setContent(loader.load());
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            final EditViewsPaneController editViewsController = loader.getController();
            editViewsController.initialize(initialValue);
            dialog.setResultConverter(bt -> bt == ButtonType.OK ? editViewsController.listView.getItems() : null);
            return dialog;
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private ListView<ColumnDefinition> listView;
    private final Map<ColumnDefinition, Boolean> newVisibilities = new HashMap<>();

    @FXML
    public void initialize() {
        listView.setCellFactory(CheckBoxListCell.forListView(param -> {
            final SimpleBooleanProperty p = new SimpleBooleanProperty(newVisibilities.containsKey(param) ? newVisibilities.get(param) : param.isVisible());
            p.addListener((observable, oldValue, newValue) -> newVisibilities.put(param, newValue));
            return p;
        }));
    }

    public void initialize(List<ViewModel> initialValue) {
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
}
