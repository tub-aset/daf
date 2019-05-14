package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.csv.ModuleCSVParser;
import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.csveditor.CommandStack.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.module.FlattenCommand;
import de.jpwinkler.daf.csveditor.commands.module.ReduceToSelectionCommand;
import de.jpwinkler.daf.csveditor.commands.module.SplitLinesCommand;
import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.csveditor.commands.object.DeleteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.object.DemoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.object.EditObjectAttributeCommand;
import de.jpwinkler.daf.csveditor.commands.object.NewObjectAfterCommand;
import de.jpwinkler.daf.csveditor.commands.object.NewObjectBelowCommand;
import de.jpwinkler.daf.csveditor.commands.object.PasteObjectsAfterCommand;
import de.jpwinkler.daf.csveditor.commands.object.PasteObjectsBelowCommand;
import de.jpwinkler.daf.csveditor.commands.object.PromoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.object.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.csveditor.commands.object.UnwrapChildrenCommand;
import de.jpwinkler.daf.csveditor.filter.CascadingFilter;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilter;
import de.jpwinkler.daf.csveditor.filter.ObjectTextAndHeadingFilter;
import de.jpwinkler.daf.csveditor.filter.ReverseCascadingFilter;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsModuleUtil;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class FilePaneController implements FileStateController {

    private static final ViewDefinition STANDARD_VIEW = new ViewDefinition("Standard");

    static {
        ColumnDefinition columnDefinition = new ColumnDefinition("Object Heading/Text");
        columnDefinition.setWidth(700);
        columnDefinition.setVisible(true);
        STANDARD_VIEW.getColumns().add(columnDefinition);

        STANDARD_VIEW.setDisplayRemainingColumns(true);
    }

    private final CommandStack commandStack = new CommandStack();
    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ArrayList<ViewDefinition> views = ApplicationPreferences.FILE_PANE_VIEWS.retrieve();
    private final Map<DoorsTreeNode, Boolean> expanded = new WeakHashMap<>();

    private ApplicationStateController applicationStateController;

    private File file;
    private DoorsModule module;
    private List<Menu> menus;
    private ViewDefinition currentView;

    private final Set<DoorsObject> filteredObjects = new HashSet<>();

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox filterExpressionCheckBox;

    @FXML
    private CheckBox includeChildrenCheckbox;

    @FXML
    private CheckBox includeParentsCheckbox;

    @FXML
    private MenuButton viewsMenuButton;

    @FXML
    public void initialize() {
        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        contentTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<DoorsObject>) (observable, oldValue, newValue) -> {
            traverseTreeItem(outlineTreeView.getRoot(), item -> {
                if (item != null && item.getValue() != null && Objects.equals(item.getValue().treeNode, newValue)) {
                    outlineTreeView.getSelectionModel().select(item);
                }
            });
        });

        outlineTreeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<OutlineTreeItem>>) (observable, oldValue, newValue) -> {
            contentTableView.getItems().forEach(c -> {
                if (newValue != null && newValue.getValue() != null && newValue.getValue().treeNode == c) {
                    contentTableView.scrollTo(c);
                }
            });

        });

        mainSplitPane.setDividerPositions((double) ApplicationPreferences.FILE_PANE_SPLITPOS.retrieve());
        mainSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                ApplicationPreferences.FILE_PANE_SPLITPOS.store(newValue.doubleValue());
            });
        });

        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            this.updateFilter(newValue, includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), filterExpressionCheckBox.isSelected());
        });
        filterExpressionCheckBox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), newValue);
        });
        includeChildrenCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), newValue, filterExpressionCheckBox.isSelected());
        });
        includeParentsCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), newValue, includeChildrenCheckbox.isSelected(), filterExpressionCheckBox.isSelected());
        });

        int currentViewIdx = ApplicationPreferences.FILE_PANE_CURRENT_VIEW.retrieve();
        if (currentViewIdx < 0 || currentViewIdx >= views.size()) {
            currentView = STANDARD_VIEW;
        } else {
            currentView = views.get(currentViewIdx);
        }
    }

    @Override
    public void initialize(ApplicationStateController applicationStateController, File file) throws IOException {
        this.applicationStateController = applicationStateController;

        this.file = file;
        this.module = file == null ? DoorsFactory.eINSTANCE.createDoorsModule() : new ModuleCSVParser().parseCSV(file);

        updateGui(UpdateAction.UPDATE_VIEWS, UpdateAction.UPDATE_COLUMNS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW);
        traverseTreeItem(outlineTreeView.getRoot(), ti -> ti.setExpanded(true));

        try {
            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("FilePaneMenu.fxml"));
            loader.setController(this);
            final Menu rootMenu = loader.load();
            this.menus = rootMenu.getItems().stream()
                    .filter(m -> m instanceof Menu)
                    .map(m -> (Menu) m)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    @Override
    public Collection<Menu> getMenus() {
        return menus;
    }

    @Override
    public void save(OutputStream os) throws IOException {
        try ( ModuleCSVWriter writer = new ModuleCSVWriter(os)) {
            writer.writeModule(module);
            commandStack.setSavePoint();
        }
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean isDirty() {
        return commandStack.isDirty();
    }

    @FXML
    public void reduceToSelectionClicked() {
        executeCommand(new ReduceToSelectionCommand(module, getCurrentObject()));
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        executeCommand(new SwapObjectHeadingAndTextCommand(module, getCurrentObject()));
    }

    private void executeCommand(final AbstractCommand command) {
        if (!command.isApplicable()) {
            applicationStateController.setStatus(command.getName() + ": Command is not appicable for this selection.");
            return;
        }

        command.apply();
        commandStack.addCommand(command);
        updateGui(command.getUpdateActions());
    }

    private void updateGui(final UpdateAction... updateActions) {
        for (final UpdateAction action : updateActions) {
            switch (action) {
                case FIX_OBJECT_LEVELS:
                    fixObjectLevel(module, 0);
                    break;
                case FIX_OBJECT_NUMBERS:
                    fixObjectNumbers(module, "");
                    break;
                case UPDATE_CONTENT_VIEW:
                    updateContentView();
                    break;
                case UPDATE_OUTLINE_VIEW:
                    updateOutlineView(module);
                    break;
                case UPDATE_COLUMNS:
                    updateColumns();
                    break;
                case UPDATE_VIEWS:
                    updateViews();
                    break;
                default:
                    break;
            }
        }
    }

    private void updateOutlineView(final DoorsModule module) {
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue().treeNode, ti.isExpanded()));
        }

        if (module != null) {

            final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
            traverseTreeItem(wrappedModule, ti -> ti.setExpanded(expanded.containsKey(ti.getValue().treeNode) && expanded.get(ti.getValue().treeNode)));
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

    private void updateContentView() {
        final TablePosition<?, ?> focusedCell = contentTableView.getFocusModel().getFocusedCell();
        contentTableView.getItems().clear();
        module.accept(new DoorsTreeNodeVisitor<>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (!filteredObjects.contains(object)) {
                    contentTableView.getItems().add(object);
                }
                return true;
            }

        });
        if (focusedCell != null) {
            contentTableView.getFocusModel().focus(focusedCell);
        }
    }

    private void updateColumns() {
        contentTableView.getColumns().clear();
        for (final ColumnDefinition columnDefinition : currentView.getColumns()) {
            if (!columnDefinition.isVisible()) {
                continue;
            }

            final TableColumn<DoorsObject, String> c = new TableColumn<>(columnDefinition.getTitle());
            c.setSortable(false);
            c.setPrefWidth(columnDefinition.getWidth());
            c.widthProperty().addListener((obs, oldValue, newValue) -> {
                columnDefinition.setWidth(newValue.doubleValue());
                ApplicationPreferences.FILE_PANE_VIEWS.store(this.views);
            });

            c.setCellFactory(param -> new CustomTableCell());
            c.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                    columnDefinition.getAttributeName() == null ? param.getValue().getText()
                    : param.getValue().getAttributes().get(columnDefinition.getAttributeName())));
            c.setOnEditCommit(event -> {
                executeCommand(new EditObjectAttributeCommand(module, event.getRowValue(), columnDefinition.getAttributeName(), event.getNewValue()));
                contentTableView.requestFocus();
                contentTableView.getFocusModel().focusNext();
            });
            contentTableView.getColumns().add(c);

        }
    }

    private void fixObjectLevel(final DoorsTreeNode object, final int level) {
        if (object instanceof DoorsObject) {
            ((DoorsObject) object).setObjectLevel(level);
        }

        for (final DoorsTreeNode child : object.getChildren()) {
            if (child instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) child, level + 1);
            }
        }
    }

    private void fixObjectNumbers(final DoorsTreeNode object, final String parentObjectNumber) {
        int headingCount = 0;
        int objectCount = 0;
        for (final DoorsTreeNode child : object.getChildren()) {
            if (child instanceof DoorsObject) {
                final DoorsObject childObject = (DoorsObject) child;
                String objectNumber = "";
                if (childObject.isHeading()) {
                    headingCount++;
                    objectCount = 0;
                    objectNumber = (!"".equals(parentObjectNumber) ? parentObjectNumber + "." : "") + String.valueOf(headingCount);
                    childObject.setObjectNumber(objectNumber);
                } else {
                    objectCount++;
                    objectNumber = (!"".equals(parentObjectNumber) ? (parentObjectNumber + ".") : "") + String.valueOf(headingCount) + "-" + String.valueOf(objectCount);
                    childObject.setObjectNumber("");
                }
                fixObjectNumbers(childObject, objectNumber);
            }

        }
    }

    private void updateViews() {
        this.viewsMenuButton.getItems().removeIf(mi -> mi instanceof RadioMenuItem);
        ToggleGroup viewsToggleGroup = new ToggleGroup();
        viewsToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            this.currentView = (ViewDefinition) newValue.getUserData();
            ApplicationPreferences.FILE_PANE_CURRENT_VIEW.store(views.indexOf(this.currentView));
            updateGui(UpdateAction.UPDATE_COLUMNS);
        });

        boolean selected = false;
        for (int i = views.size() - 1; i >= 0; i--) {
            ViewDefinition vd = views.get(i);
            RadioMenuItem rmi = new RadioMenuItem(vd.getName());
            rmi.setUserData(vd);
            rmi.setToggleGroup(viewsToggleGroup);
            this.viewsMenuButton.getItems().add(0, rmi);

            if (vd == currentView) {
                rmi.setSelected(true);
                selected = true;
            }
        }

        RadioMenuItem standardRmi = new RadioMenuItem(STANDARD_VIEW.getName());
        standardRmi.setUserData(STANDARD_VIEW);
        standardRmi.setToggleGroup(viewsToggleGroup);
        this.viewsMenuButton.getItems().add(0, standardRmi);
        if (!selected) {
            standardRmi.setSelected(true);
        }
    }

    private void updateFilter(final String text, final boolean includeParents, final boolean includeChildren, final boolean isExpression) {

        DoorsObjectFilter filter = isExpression ? DoorsObjectFilter.compile(text) : new ObjectTextAndHeadingFilter(text, false, false);
        if (includeChildren) {
            filter = new CascadingFilter(filter);
        }

        if (includeParents) {
            filter = new ReverseCascadingFilter(filter);
        }

        final DoorsObjectFilter filterFinal = filter;

        filteredObjects.clear();
        module.accept(new DoorsTreeNodeVisitor<>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (!filterFinal.checkObject(object)) {
                    filteredObjects.add(object);
                }
                return true;
            }
        });

        updateGui(UpdateAction.UPDATE_CONTENT_VIEW);

        final int totalObjects = DoorsModuleUtil.countObjects(module);
        final int visibleObjects = totalObjects - filteredObjects.size();

        applicationStateController.setStatus(visibleObjects < totalObjects
                ? String.format("Showing %d out of %d objects.", visibleObjects, totalObjects) : "");
    }

    private DoorsObject getCurrentObject() {
        return contentTableView.getSelectionModel().getSelectedItem();
    }

    private List<DoorsObject> getCurrentObjects() {
        return contentTableView.getSelectionModel().getSelectedItems();
    }

    @FXML
    public void deleteObjectClicked() {
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
    }

    @FXML
    public void unwrapChildrenClicked() {
        executeCommand(new UnwrapChildrenCommand(module, getCurrentObject()));
    }

    @FXML
    public void demoteObjectClicked() {
        executeCommand(new DemoteObjectCommand(module, getCurrentObject()));
    }

    @FXML
    public void promoteObjectClicked() {
        executeCommand(new PromoteObjectCommand(module, getCurrentObject()));
    }

    @FXML
    public void newObjectBelowClicked() {
        executeCommand(new NewObjectBelowCommand(module, getCurrentObject()));
    }

    @FXML
    public void newObjectAfterClicked() {
        executeCommand(new NewObjectAfterCommand(module, getCurrentObject()));
    }

    @FXML
    public void redoClicked() {
        final AbstractCommand commandToRedo = commandStack.redo();
        if (commandToRedo == null) {
            applicationStateController.setStatus("Cannot redo.");
        } else {
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    @FXML
    public void undoClicked() {
        final AbstractCommand commandToUndo = commandStack.undo();
        if (commandToUndo == null) {
            applicationStateController.setStatus("Cannot undo.");
        } else {
            updateGui(commandToUndo.getUpdateActions());
        }
    }

    @FXML
    public void editViewsClicked() {
        EditViewsPaneController.asDialog(outlineTreeView.getScene().getWindow(), this.views, module.getObjectAttributes().stream())
                .showAndWait().ifPresent(r -> {
                    this.views.clear();
                    this.views.addAll(r);
                    ApplicationPreferences.FILE_PANE_VIEWS.store(this.views);
                    this.updateGui(UpdateAction.UPDATE_VIEWS, UpdateAction.UPDATE_COLUMNS);
                });
    }

    @FXML
    public void pasteBelowClicked() {
        executeCommand(new PasteObjectsBelowCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void pasteAfterClicked() {
        executeCommand(new PasteObjectsAfterCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void copyClicked() {
        clipboard.clear();
        clipboard.addAll(contentTableView.getSelectionModel().getSelectedItems());
    }

    @FXML
    public void cutClicked() {
        copyClicked();
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
    }

    @FXML
    public void flattenClicked() {
        executeCommand(new FlattenCommand(module));
    }

    @FXML
    public void splitLinesClicked() {
        executeCommand(new SplitLinesCommand(module));
    }

    @FXML
    public void analyzeObjectTypeClicked() {
        contentTableView.refresh();
    }

    private class CustomTableCell extends TextFieldTableCell<DoorsObject, String> {

        public CustomTableCell() {
            super(new DefaultStringConverter());
        }

        @Override
        public void updateItem(final String item, final boolean empty) {
            super.updateItem(item, empty);
            String style = "";
            if (!empty && getTableRow() != null) {
                final DoorsObject o = getTableView().getItems().get(getTableRow().getIndex());
                if (o.isHeading()) {
                    style += "-fx-font-weight: bold;";
                    if (o.getObjectLevel() <= 2) {
                        style += "-fx-font-size: 140%;";
                    } else if (o.getObjectLevel() == 3) {
                        style += "-fx-font-size: 130%;";
                    } else if (o.getObjectLevel() == 4) {
                        style += "-fx-font-size: 120%;";
                    } else if (o.getObjectLevel() == 5) {
                        style += "-fx-font-size: 110%;";
                    }

                    setText(o.getObjectNumber() + " " + o.getObjectHeading());
                } else {
                    setText(o.getObjectText());
                }

                setPadding(new Insets(0, 0, 0, (o.getObjectLevel() - 1) * 10));
            }
            setStyle(style);
        }

    }

    private static class OutlineTreeItem {

        public final DoorsTreeNode treeNode;

        public OutlineTreeItem(final DoorsTreeNode treeNode) {
            this.treeNode = treeNode;
        }

        @Override
        public String toString() {
            if (treeNode instanceof DoorsModule) {
                return ((DoorsModule) treeNode).getName();
            } else if (treeNode instanceof DoorsObject) {

                String truncatedText = ((DoorsObject) treeNode).getText().replace("\n", "");
                if (truncatedText.length() > 50) {
                    truncatedText = truncatedText.substring(0, 47) + "...";
                }

                if (((DoorsObject) treeNode).isHeading()) {
                    return ((DoorsObject) treeNode).getObjectNumber() + " " + truncatedText;
                } else {
                    return truncatedText;
                }
            } else {
                return treeNode.toString();
            }
        }
    }

}
