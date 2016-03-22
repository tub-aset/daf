package de.jpwinkler.daf.doorsdb.util.browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.function.Consumer;

import de.jpwinkler.daf.csveditor.CSVEditorApplication;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BrowserController {

    private CSVEditorApplication csvEditorApplication;

    private Stage primaryStage;

    private DoorsApplication app;

    private DoorsDBInterface db;

    @FXML
    private TreeView<ItemRef> remoteTreeView;

    @FXML
    private TreeView<DBItem> localTreeView;

    @FXML
    private ListView<String> downloadQueueListView;

    @FXML
    private ListView<DBVersion> versionsListView;

    @FXML
    private ListView<String> tagsListView;

    @FXML
    private ComboBox<String> newTagComboBox;

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() throws FileNotFoundException, IOException {
        app = DoorsApplicationFactory.getDoorsApplication();
        db = DoorsDBInterface.createOrOpenDB(new File("C:/WORK/DoorsDB/db.doorsdbmodel"));
        updateLocalTree();
        updateNewTagComboBox();
        remoteTreeView.setRoot(new RemoteTreeItem(app.getRoot(), new ImageView(new Image(getClass().getResourceAsStream("/icons/doors_db.png")))));

        remoteTreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                try {
                    if (event.getClickCount() == 2 && remoteTreeView.getSelectionModel().getSelectedItem() != null && remoteTreeView.getSelectionModel().getSelectedItem().getValue().getType() == DoorsItemType.FORMAL) {
                        final ItemRef item = remoteTreeView.getSelectionModel().getSelectedItem().getValue();
                        downloadQueueListView.getItems().add(item.getFullName());
                    }
                } catch (final DoorsException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        localTreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                if (event.getClickCount() == 2 && localTreeView.getSelectionModel().getSelectedItem() != null && localTreeView.getSelectionModel().getSelectedItem().getValue() instanceof DBModule) {
                    final DBModule item = (DBModule) localTreeView.getSelectionModel().getSelectedItem().getValue();
                    try {
                        if (csvEditorApplication == null) {
                            csvEditorApplication = new CSVEditorApplication();
                            csvEditorApplication.start(new Stage());
                        }
                        csvEditorApplication.openFile(new File(item.getLatestVersion().getCsvLocation()));
                    } catch (final Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });

        localTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateTagsListView();
            updateVersionsListView();
        });

    }

    @FXML
    public void downloadAllPressed() {
        try {
            for (final String item : downloadQueueListView.getItems()) {
                db.addModule(item);
            }
            downloadQueueListView.getItems().clear();
        } catch (DoorsException | IOException | CSVParseException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        updateLocalTree();
    }

    @FXML
    public void addTagPressed() throws IOException {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            db.addTag((DBModule) selectedItem.getValue(), newTagComboBox.getValue());
            db.saveDB();
            updateTagsListView();
            updateNewTagComboBox();
        }
    }

    @FXML
    public void removeTagsPressed() throws IOException {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            for (final String tag : tagsListView.getSelectionModel().getSelectedItems()) {
                db.removeTag((DBModule) selectedItem.getValue(), tag);
            }
            updateTagsListView();
            db.saveDB();
        }
    }

    private void updateLocalTree() {
        final HashMap<DBItem, Boolean> expanded = new HashMap<>();
        if (localTreeView.getRoot() != null) {
            traverseTreeItem(localTreeView.getRoot(), i -> expanded.put(i.getValue(), i.isExpanded()));
        }
        localTreeView.setRoot(new LocalTreeItem(db.getDB().getRoot()));
        traverseTreeItem(localTreeView.getRoot(), i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
    }

    private void updateNewTagComboBox() {
        final String oldValue = newTagComboBox.getValue();
        newTagComboBox.getItems().clear();
        for (final DBTag tag : db.getDB().getTags()) {
            newTagComboBox.getItems().add(tag.getName());
        }
        newTagComboBox.setValue(oldValue);
    }

    private void updateTagsListView() {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        tagsListView.getItems().clear();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            tagsListView.getItems().addAll(db.getTags((DBModule) selectedItem.getValue()));
        }
    }

    private void updateVersionsListView() {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        versionsListView.getItems().clear();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            versionsListView.getItems().addAll(((DBModule) selectedItem.getValue()).getVersions());
        }
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

}
