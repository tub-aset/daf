package de.jpwinkler.daf.doorsdbgui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import de.jpwinkler.daf.csveditor.CSVEditorApplication;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private TableView<Entry<String, String>> attributesTableView;

    @FXML
    private TableColumn<Entry<String, String>, String> attributeNameColumn;

    @FXML
    private TableColumn<Entry<String, String>, String> attributeValueColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<DBModule> searchResultListView;

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

        remoteTreeView.setOnMouseClicked(event -> {
            try {
                if (event.getClickCount() == 2) {
                    remoteDownloadOpenPressed();
                }
            } catch (final Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        remoteTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        localTreeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && localTreeView.getSelectionModel().getSelectedItem() != null && localTreeView.getSelectionModel().getSelectedItem().getValue() instanceof DBModule) {
                final DBModule item = (DBModule) localTreeView.getSelectionModel().getSelectedItem().getValue();
                openInCSVBrowser(item.getLatestVersion());

            }
        });

        versionsListView.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2 && versionsListView.getSelectionModel().getSelectedItem() != null) {
                openInCSVBrowser(versionsListView.getSelectionModel().getSelectedItem());
            }
        });

        localTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            final DBModule module = newValue != null && newValue.getValue() instanceof DBModule ? (DBModule) newValue.getValue() : null;
            updateTagsListView(module);
            updateVersionsListView(module);
            updateAttributesView(module);
        });

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSearch(newValue);
        });

        searchResultListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            final DBModule module = newValue instanceof DBModule ? (DBModule) newValue : null;
            updateTagsListView(module);
            updateVersionsListView(module);
            updateAttributesView(module);
        });

        searchResultListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && searchResultListView.getSelectionModel().getSelectedItem() != null && searchResultListView.getSelectionModel().getSelectedItem() instanceof DBModule) {
                final DBModule item = searchResultListView.getSelectionModel().getSelectedItem();
                openInCSVBrowser(item.getLatestVersion());

            }
        });

        attributeNameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getKey()));
        attributeValueColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue()));
    }

    private void openInCSVBrowser(final DBVersion dbVersion) {
        try {
            if (csvEditorApplication == null || !csvEditorApplication.getPrimaryStage().isShowing()) {
                csvEditorApplication = new CSVEditorApplication();
                csvEditorApplication.start(new Stage());
            }
            csvEditorApplication.openFile(new File(dbVersion.getCsvLocation()));
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            final DBModule module = (DBModule) selectedItem.getValue();
            db.addTag(module, newTagComboBox.getValue());
            db.saveDB();
            updateTagsListView(module);
            updateNewTagComboBox();
        }
    }

    @FXML
    public void removeTagsPressed() throws IOException {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            final DBModule module = (DBModule) selectedItem.getValue();
            for (final String tag : tagsListView.getSelectionModel().getSelectedItems()) {
                db.removeTag(module, tag);
            }
            updateTagsListView(module);
            db.saveDB();
        }
    }

    @FXML
    public void localDeletePressed() throws IOException {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            db.removeModule((DBModule) selectedItem.getValue());
            updateLocalTree();
            db.saveDB();
        } else if (selectedItem != null && selectedItem.getValue() instanceof DBFolder) {
            db.removeFolder((DBFolder) selectedItem.getValue());
            updateLocalTree();
            db.saveDB();
        }
    }

    @FXML
    public void localUpdatePressed() throws IOException, DoorsException, ParseException {
        final TreeItem<DBItem> selectedItem = localTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getValue() instanceof DBModule) {
            final DBModule module = (DBModule) selectedItem.getValue();
            db.updateModule(module);
            db.saveDB();
            updateVersionsListView(module);
        }

    }

    @FXML
    public void remoteAddToQueuePressed() throws DoorsException {
        for (final TreeItem<ItemRef> selectedItem : remoteTreeView.getSelectionModel().getSelectedItems()) {
            if (selectedItem.getValue().getType() == DoorsItemType.FORMAL) {
                final ItemRef item = selectedItem.getValue();
                downloadQueueListView.getItems().add(item.getItemName().getFullName());
            }
        }
    }

    @FXML
    public void remoteDownloadPressed() throws DoorsException, IOException, CSVParseException, ParseException {
        for (final TreeItem<ItemRef> selectedItem : remoteTreeView.getSelectionModel().getSelectedItems()) {
            if (selectedItem.getValue().getType() == DoorsItemType.FORMAL) {
                final ItemRef item = selectedItem.getValue();
                db.addModule(item.getItemName().getFullName());
                updateLocalTree();
            }
        }
    }

    @FXML
    public void remoteDownloadOpenPressed() throws DoorsException, IOException, CSVParseException, ParseException {
        for (final TreeItem<ItemRef> selectedItem : remoteTreeView.getSelectionModel().getSelectedItems()) {
            if (selectedItem.getValue().getType() == DoorsItemType.FORMAL) {
                final ItemRef item = selectedItem.getValue();
                final DBModule module = db.addModule(item.getItemName().getFullName());
                openInCSVBrowser(module.getLatestVersion());
                updateLocalTree();
            }
        }
    }

    @FXML
    public void cancelSearchPressed() {
        searchTextField.setText("");
        searchResultListView.setVisible(false);
        localTreeView.setVisible(true);
    }

    private void updateSearch(final String search) {
        searchResultListView.setVisible(true);
        localTreeView.setVisible(false);
        final DBSearchExpression searchExpression = DBSearchExpression.compile(search);
        if (searchExpression != null) {
            final List<DBModule> findModules = db.findModules(searchExpression);
            searchResultListView.getItems().clear();
            searchResultListView.getItems().addAll(findModules);
        }
    }

    private void updateLocalTree() {
        final HashMap<DBItem, Boolean> expanded = new HashMap<>();
        if (localTreeView.getRoot() != null) {
            traverseTreeItem(localTreeView.getRoot(), i -> expanded.put(i.getValue(), i.isExpanded()));
        }
        localTreeView.setRoot(new LocalTreeItem(db.getDB().getRoot(), new ImageView(Images.IMAGE_DB)));
        traverseTreeItem(localTreeView.getRoot(), i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
    }

    private void updateNewTagComboBox() {
        final String oldValue = newTagComboBox.getValue();
        newTagComboBox.getItems().clear();
        for (final DBTag tag : db.getDB().getTags()) {
            newTagComboBox.getItems().add(tag.getName());
            Collections.sort(newTagComboBox.getItems());
        }
        newTagComboBox.setValue(oldValue);
    }

    private void updateTagsListView(final DBModule module) {
        tagsListView.getItems().clear();
        if (module != null) {
            tagsListView.getItems().addAll(db.getTags(module));
        }
    }

    private void updateVersionsListView(final DBModule module) {
        versionsListView.getItems().clear();
        if (module != null) {
            versionsListView.getItems().addAll(module.getVersions());
        }
    }

    private void updateAttributesView(final DBModule module) {
        attributesTableView.getItems().clear();
        if (module != null) {
            final DBVersion latestVersion = module.getLatestVersion();
            if (latestVersion != null) {
                attributesTableView.setItems(FXCollections.observableArrayList(latestVersion.getAttributes().entrySet()));
            }
        }
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

}
