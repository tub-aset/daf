package de.jpwinkler.daf.gui.modules;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.filter.objects.CascadingFilter;
import de.jpwinkler.daf.filter.objects.DoorsObjectFilter;
import de.jpwinkler.daf.filter.objects.ObjectTextAndHeadingFilter;
import de.jpwinkler.daf.filter.objects.ReverseCascadingFilter;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationPreferences;
import de.jpwinkler.daf.gui.CommandStack;
import de.jpwinkler.daf.gui.MultiCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ViewDefinition.ColumnDefinition;
import de.jpwinkler.daf.gui.modules.commands.DeleteObjectCommand;
import de.jpwinkler.daf.gui.modules.commands.DemoteObjectCommand;
import de.jpwinkler.daf.gui.modules.commands.EditObjectAttributeCommand;
import de.jpwinkler.daf.gui.modules.commands.FlattenCommand;
import de.jpwinkler.daf.gui.modules.commands.NewObjectAfterCommand;
import de.jpwinkler.daf.gui.modules.commands.NewObjectBelowCommand;
import de.jpwinkler.daf.gui.modules.commands.PasteObjectsAfterCommand;
import de.jpwinkler.daf.gui.modules.commands.PasteObjectsBelowCommand;
import de.jpwinkler.daf.gui.modules.commands.PromoteObjectCommand;
import de.jpwinkler.daf.gui.modules.commands.ReduceToSelectionCommand;
import de.jpwinkler.daf.gui.modules.commands.SplitLinesCommand;
import de.jpwinkler.daf.gui.modules.commands.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.gui.modules.commands.UnwrapChildrenCommand;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsSystemAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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

public final class ModulePaneController extends ApplicationPartController<ModulePaneController> {

    private static final ViewDefinition STANDARD_VIEW = new ViewDefinition("Standard");

    static {
        ColumnDefinition columnDefinition = new ColumnDefinition("Object Heading/Text");
        columnDefinition.setWidth(700);
        columnDefinition.setVisible(true);
        STANDARD_VIEW.getColumns().add(columnDefinition);

        STANDARD_VIEW.setDisplayRemainingColumns(true);
    }
    
    public ModulePaneController(ApplicationPaneController applicationController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        super(applicationController, path, databaseInterface, databaseCommandStack);
        
        if(databaseInterface.isReadOnly()) {
            outlineTreeView.setEditable(false);
            contentTableView.setEditable(false);
        }

        this.module = (DoorsModule) databaseInterface.getNode(path.getPath());
        if(this.module == null) {
            throw new RuntimeException("No such module: " + path.getPath());
        }
        
        int currentViewIdx = ApplicationPreferences.MODULE_PANE_CURRENT_VIEW.retrieve();
        if (currentViewIdx < 0 || currentViewIdx >= views.size()) {
            currentView = STANDARD_VIEW;
        } else {
            currentView = views.get(currentViewIdx);
        }

        mergeObjectAttributes();

        updateGui(ModuleUpdateAction.UPDATE_VIEWS, ModuleUpdateAction.UPDATE_COLUMNS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW);
        traverseTreeItem(outlineTreeView.getRoot(), ti -> ti.setExpanded(true));

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

        mainSplitPane.setDividerPositions((double) ApplicationPreferences.MODULE_PANE_SPLITPOS.retrieve());
        mainSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                ApplicationPreferences.MODULE_PANE_SPLITPOS.store(newValue.doubleValue());
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
    }

    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ArrayList<ViewDefinition> views = ApplicationPreferences.MODULE_PANE_VIEWS.retrieve();
    private final Map<DoorsTreeNode, Boolean> expanded = new WeakHashMap<>();
    private final DoorsModule module;

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

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }
//
    @FXML
    public void reduceToSelectionClicked() {
        executeCommand(new ReduceToSelectionCommand(module, getCurrentObject()));
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        executeCommand(new MultiCommand(getCurrentObjects().stream().map(o -> new SwapObjectHeadingAndTextCommand(o)).collect(Collectors.toList())));
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
                ApplicationPreferences.MODULE_PANE_VIEWS.store(this.views);
            });

            c.setCellFactory(param -> new CustomTableCell());
            c.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                    columnDefinition.getAttributeName() == null ? param.getValue().getText()
                    : param.getValue().getAttributes().get(columnDefinition.getAttributeName())));
            c.setOnEditCommit(event -> {
                executeCommand(new EditObjectAttributeCommand(event.getRowValue(), columnDefinition.getAttributeName(), event.getNewValue()));
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

    private void mergeObjectAttributes() {
        Set<String> moduleAttrs = new HashSet<>(this.module.getObjectAttributes());
        moduleAttrs.add(DoorsSystemAttributes.OBJECT_LEVEL.getKey());
        this.currentView.getColumns().forEach(cd -> moduleAttrs.add(cd.getAttributeName()));
        this.module.setObjectAttributes(new ArrayList<>(moduleAttrs));
    }

    private void updateViews() {
        this.viewsMenuButton.getItems().removeIf(mi -> mi instanceof RadioMenuItem);
        ToggleGroup viewsToggleGroup = new ToggleGroup();
        viewsToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            this.currentView = (ViewDefinition) newValue.getUserData();
            mergeObjectAttributes();

            ApplicationPreferences.MODULE_PANE_CURRENT_VIEW.store(views.indexOf(this.currentView));
            updateGui(ModuleUpdateAction.UPDATE_COLUMNS);
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

        updateGui(ModuleUpdateAction.UPDATE_CONTENT_VIEW);

        final int totalObjects = DoorsModelUtil.countObjects(module);
        final int visibleObjects = totalObjects - filteredObjects.size();

        this.setStatus(visibleObjects < totalObjects
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
        executeCommand(new DeleteObjectCommand(getCurrentObjects()));
    }

    @FXML
    public void unwrapChildrenClicked() {
        executeCommand(new UnwrapChildrenCommand(getCurrentObject()));
    }

    @FXML
    public void demoteObjectClicked() {
        executeCommand(new MultiCommand(getCurrentObjects().stream().map(o -> new DemoteObjectCommand(o)).collect(Collectors.toList())));
    }

    @FXML
    public void promoteObjectClicked() {
        executeCommand(new MultiCommand(getCurrentObjects().stream().map(o -> new PromoteObjectCommand(o)).collect(Collectors.toList())));
    }

    @FXML
    public void newObjectBelowClicked() {
        executeCommand(new NewObjectBelowCommand(getCurrentObject() != null ? this.getCurrentObject() : this.module));
    }

    @FXML
    public void newObjectAfterClicked() {
        executeCommand(new NewObjectAfterCommand(getCurrentObject()));
    }

    @FXML
    public void editViewsClicked() {
        new EditViewsPaneController(this.views, module.getObjectAttributes().stream())
                .asDialog(outlineTreeView.getScene().getWindow(), ButtonType.CANCEL, ButtonType.OK)
                .filter(r -> r.buttonType == ButtonType.OK)
                .map(r -> r.result.getViews())
                .ifPresent(r -> {
                    this.views.clear();
                    this.views.addAll(r);
                    ApplicationPreferences.MODULE_PANE_VIEWS.store(this.views);
                    this.updateGui(ModuleUpdateAction.UPDATE_VIEWS, ModuleUpdateAction.UPDATE_COLUMNS);
                });
    }

    @FXML
    public void pasteBelowClicked() {
        executeCommand(new PasteObjectsBelowCommand(contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void pasteAfterClicked() {
        executeCommand(new PasteObjectsAfterCommand(contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void copyClicked() {
        clipboard.clear();
        clipboard.addAll(contentTableView.getSelectionModel().getSelectedItems());
    }

    @FXML
    public void cutClicked() {
        copyClicked();
        deleteObjectClicked();
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

    public static enum ModuleUpdateAction implements UpdateAction<ModulePaneController> {
        FIX_OBJECT_LEVELS(t -> t.fixObjectLevel(t.module, 0)),
        FIX_OBJECT_NUMBERS(t -> t.fixObjectNumbers(t.module, "")),
        UPDATE_CONTENT_VIEW(t -> t.updateContentView()),
        UPDATE_OUTLINE_VIEW(t -> t.updateOutlineView(t.module)),
        UPDATE_COLUMNS(t -> t.updateColumns()),
        UPDATE_VIEWS(t -> t.updateViews());

        private final Consumer<ModulePaneController> updateFun;

        ModuleUpdateAction(Consumer<ModulePaneController> updateFun) {
            this.updateFun = updateFun;
        }

        @Override
        public void update(ModulePaneController ctrl) {
            updateFun.accept(ctrl);
        }

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
