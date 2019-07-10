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
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.filter.ExpressionFilter;
import de.jpwinkler.daf.filter.model.FilteredDoorsTreeNode;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.BackgroundTask;
import de.jpwinkler.daf.gui.commands.MultiCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTableCell;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTreeCell;
import de.jpwinkler.daf.gui.controls.DoorsTreeItem;
import de.jpwinkler.daf.gui.controls.ExtensionPane;
import de.jpwinkler.daf.gui.controls.FixedSingleSelectionModel;
import de.jpwinkler.daf.gui.controls.ForwardingMultipleSelectionModel;
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
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.pf4j.PluginWrapper;

public final class ModulePaneController extends ApplicationPartController<ModulePaneController> {

    private static final ViewDefinition STANDARD_VIEW = new ViewDefinition("Standard");

    static {
        ColumnDefinition columnDefinition = new ColumnDefinition("Object Heading/Text");
        columnDefinition.setWidth(700);
        columnDefinition.setVisible(true);
        STANDARD_VIEW.getColumns().add(columnDefinition);

        STANDARD_VIEW.setDisplayRemainingColumns(false);
    }

    public ModulePaneController(ApplicationPaneController applicationController, ApplicationPart part) {
        super(applicationController, part, ModulePaneExtension.class);

        if (super.getDatabaseInterface().isReadOnly()) {
            outlineTreeView.setEditable(false);
            contentTableView.setEditable(false);
        }

        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        contentTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<DoorsObject>) (observable, oldValue, newValue) -> {
            traverseTreeItem(outlineTreeView.getRoot(), item -> {
                if (item != null && item.getValue() != null && Objects.equals(item.getValue(), newValue)) {
                    outlineTreeView.getSelectionModel().select(item);
                    outlineTreeView.scrollTo(outlineTreeView.getSelectionModel().getSelectedIndex());
                }
            });
        });

        outlineTreeView.setCellFactory(tv -> new CustomTextFieldTreeCell<>(
                treeNode -> {
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
                    } else if (treeNode != null) {
                        return treeNode.toString();
                    } else {
                        return "";
                    }
                },
                (it, newName) -> {
                    throw new UnsupportedOperationException();
                },
                it -> {
                }
        ));
        outlineTreeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<DoorsTreeNode>>) (observable, oldValue, newValue) -> {
            contentTableView.getItems().forEach(c -> {
                if (newValue.getValue() != null && newValue.getValue() == c) {
                    contentTableView.scrollTo(c);
                }
            });

        });

        setupDividerStorage(mainSplitPane, ModulePanePreferences.SPLITPOS, sideExtensionPane);
        setupDividerStorage(bottomSplitPane, ModulePanePreferences.BOTTOM_SPLITPOS, bottomExtensionPane);

        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            this.updateFilter(newValue, filterExpressionCheckBox.isSelected(), caseSensitiveCheckbox.isSelected());
        });
        filterExpressionCheckBox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), newValue, caseSensitiveCheckbox.isSelected());
        });
        caseSensitiveCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), filterExpressionCheckBox.isSelected(), newValue);
        });

        this.loadContent();
    }

    private void loadContent() {
        this.contentTableView.setPlaceholder(new ProgressBar());

        super.getDatabaseInterface().getDatabaseRoot().getChildAsync(super.getBackgroundTaskExecutor().withPriority(BackgroundTask.PRIORITY_MODULE_CONTENT), super.getApplicationPart().getDatabasePath().getPath())
                .thenCompose(module -> {
                    DoorsModule dm = (DoorsModule) module;
                    return dm.getObjectAttributesAsync(super.getBackgroundTaskExecutor().withPriority(BackgroundTask.PRIORITY_MODULE_CONTENT))
                            .thenApply(attrs -> dm);
                }).thenAccept(dm -> {
            Platform.runLater(() -> {
                this.contentTableView.setPlaceholder(null);

                this.actualModule = dm;
                if (this.actualModule == null) {
                    throw new RuntimeException("No such module: " + super.getApplicationPart().getDatabasePath().toString());
                }

                this.filteredModule = actualModule;
                updateGui(ModuleUpdateAction.UPDATE_VIEWS, ModuleUpdateAction.UPDATE_COLUMNS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW);
                traverseTreeItem(outlineTreeView.getRoot(), ti -> ti.setExpanded(true));
            });
        }).exceptionally(t -> {
            Platform.runLater(() -> {
                Button retryButton = new Button("Retry");
                retryButton.setOnAction(ev -> this.loadContent());
                this.contentTableView.setPlaceholder(retryButton);
            });
            return null;
        });
    }

    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ArrayList<ViewDefinition> views = ModulePanePreferences.VIEWS.retrieve();
    private final Map<DoorsTreeNode, Boolean> expanded = new WeakHashMap<>();
    private DoorsModule filteredModule;
    private DoorsModule actualModule;

    private ViewDefinition currentView;

    private final ExtensionPane<ModulePaneExtension> sideExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(ModulePaneExtension.class), e -> e.getSidePanes(), (e, n) -> e.getPaneName(n),
            ModulePanePreferences.SIDE_EXTENSION.retrieve(), ModulePanePreferences.SIDE_EXTENSION::store);
    private final ExtensionPane<ModulePaneExtension> bottomExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(ModulePaneExtension.class), e -> e.getBottomPanes(), (e, n) -> e.getPaneName(n),
            ModulePanePreferences.BOTTOM_EXTENSION.retrieve(), ModulePanePreferences.BOTTOM_EXTENSION::store);

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private SplitPane bottomSplitPane;

    @FXML
    private TreeView<DoorsTreeNode> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox filterExpressionCheckBox;

    @FXML
    private CheckBox caseSensitiveCheckbox;

    @FXML
    private MenuButton viewsMenuButton;

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    @FXML
    public void reduceToSelectionClicked() {
        executeCommand(new ReduceToSelectionCommand(filteredModule, getCurrentObjects()));
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        executeCommand(new MultiCommand(getCurrentObjects().stream().map(o -> new SwapObjectHeadingAndTextCommand(o)).collect(Collectors.toList())));
    }

    private void updateOutlineView(final DoorsModule module) {
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue(), ti.isExpanded()));
        }

        if (module != null) {
            outlineTreeView.setRoot(new DoorsTreeItem(super.getBackgroundTaskExecutor(), module, a -> true, null));
            traverseTreeItem(outlineTreeView.getRoot(), ti -> ti.setExpanded(expanded.containsKey(ti.getValue()) && expanded.get(ti.getValue())));
        } else {
            outlineTreeView.setRoot(null);
        }
    }

    private void updateContentView() {
        final TablePosition<?, ?> focusedCell = contentTableView.getFocusModel().getFocusedCell();
        final List<Integer> selected = new ArrayList<>(contentTableView.getSelectionModel().getSelectedIndices());
        contentTableView.getItems().clear();
        filteredModule.accept(new DoorsTreeNodeVisitor<DoorsObject>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                Platform.runLater(() -> {
                    contentTableView.getItems().add(object);
                });
                return true;
            }
        });
        if (focusedCell != null) {
            Platform.runLater(() -> {
                contentTableView.getFocusModel().focus(focusedCell);
                if (!selected.isEmpty()) {
                    contentTableView.getSelectionModel().selectIndices(selected.get(0), selected.stream().skip(1).mapToInt(a -> a).toArray());
                }
            });

        }
    }

    private void updateColumns() {
        this.filteredModule.getObjectAttributesAsync(super.getBackgroundTaskExecutor())
                .thenAccept(cols -> Platform.runLater(() -> {
            contentTableView.getColumns().clear();
            Set<String> displayedAttributes = new HashSet<>();
            BiFunction<String, String, TableColumn<DoorsObject, DoorsObject>> columnFactory = (attributeName, title) -> {
                final TableColumn<DoorsObject, DoorsObject> c = new TableColumn<>(title);
                c.setSortable(false);
                BiConsumer<DoorsObject, String> edit = (it, newValue) -> {
                    executeCommand(new EditObjectAttributeCommand(it, attributeName, newValue));
                    contentTableView.requestFocus();
                    contentTableView.getFocusModel().focusNext();
                };
                if (attributeName == null) {
                    c.setCellFactory(tc -> new CombinedTextHeadingCell(tc,
                            it -> it.getText(), edit));
                } else {
                    c.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                            it -> it.getAttributes().get(attributeName), edit));
                }
                displayedAttributes.add(attributeName);
                return c;
            };

            for (final ColumnDefinition columnDefinition : currentView.getColumns()) {
                if (!columnDefinition.isVisible()) {
                    continue;
                }

                TableColumn<DoorsObject, DoorsObject> c = columnFactory.apply(columnDefinition.getAttributeName(), columnDefinition.getTitle());
                c.setPrefWidth(columnDefinition.getWidth());
                c.widthProperty().addListener((obs, oldValue, newValue) -> {
                    columnDefinition.setWidth(newValue.doubleValue());
                    ModulePanePreferences.VIEWS.store(this.views);
                });
                contentTableView.getColumns().add(c);
            }

            cols.stream()
                    .distinct()
                    .filter(an -> !displayedAttributes.contains(an))
                    .peek(displayedAttributes::add)
                    .map(an -> columnFactory.apply(an, an))
                    .peek(c -> c.setPrefWidth(150))
                    .forEach(contentTableView.getColumns()::add);
        }));
    }

    private void fixObjectLevel(final DoorsTreeNode object) {
        if (object instanceof DoorsObject) {
            DoorsAttributes.OBJECT_LEVEL.setValue(Integer.class, object, ((DoorsObject) object).getObjectLevel());
        }
        
        object.getChildren().stream()
                .filter(c -> c instanceof DoorsObject)
                .forEach(this::fixObjectLevel);
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
            if (newValue == null) {
                return;
            }

            this.currentView = (ViewDefinition) newValue.getUserData();

            Set<String> moduleAttrs = new HashSet<>(this.filteredModule.getObjectAttributes());
            moduleAttrs.add(DoorsAttributes.OBJECT_LEVEL.getKey());
            this.currentView.getColumns().stream().filter(cd -> cd != null).forEach(cd -> moduleAttrs.add(cd.getAttributeName()));
            this.filteredModule.setObjectAttributes(new ArrayList<>(moduleAttrs));

            ModulePanePreferences.CURRENT_VIEW.store(viewsToggleGroup.getToggles().indexOf(viewsToggleGroup.getSelectedToggle()));
            updateGui(ModuleUpdateAction.UPDATE_COLUMNS);
        });

        RadioMenuItem standardRmi = new RadioMenuItem(STANDARD_VIEW.getName());
        this.viewsMenuButton.getItems().add(0, standardRmi);
        standardRmi.setUserData(STANDARD_VIEW);
        standardRmi.setToggleGroup(viewsToggleGroup);

        Stream.concat(views.stream(), super.getExtensions(ModulePaneExtension.class).stream()
                .flatMap(e -> e.getAdditionalViews().stream()))
                .forEach(vd -> {

                    RadioMenuItem rmi = new RadioMenuItem(vd.getName());
                    rmi.setUserData(vd);
                    rmi.setToggleGroup(viewsToggleGroup);
                    this.viewsMenuButton.getItems().add(this.viewsMenuButton.getItems().size() - 2, rmi);
                });

        int currentViewIdx = ModulePanePreferences.CURRENT_VIEW.retrieve();
        if (0 <= currentViewIdx && currentViewIdx < viewsToggleGroup.getToggles().size()) {
            viewsToggleGroup.getToggles().get(currentViewIdx).setSelected(true);
        } else {
            viewsToggleGroup.getToggles().get(0).setSelected(true);
        }
    }

    private void updateFilter(String text, boolean isExpression, boolean caseSensitive) {
        if (text == null || text.trim().isEmpty()) {
            this.filteredModule = this.actualModule;
        } else if (isExpression) {
            try {
                Predicate<DoorsTreeNode> filter = ExpressionFilter.compileExpression(text, caseSensitive, Pattern.CASE_INSENSITIVE);
                this.filteredModule = (DoorsModule) FilteredDoorsTreeNode.createFilteredTree(actualModule, filter, true);
            } catch (ParseCancellationException ex) {
                setStatus("Invalid filter: " + ApplicationPaneController.getMessage(ex));
                return;
            }
        } else {
            Predicate<DoorsTreeNode> filter = ExpressionFilter.compileSimple(text, caseSensitive);
            this.filteredModule = (DoorsModule) FilteredDoorsTreeNode.createFilteredTree(actualModule, filter, true);
        }

        setStatus("Filter applied");

        updateGui(ModuleUpdateAction.UPDATE_VIEWS, ModuleUpdateAction.UPDATE_COLUMNS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW);
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
        executeCommand(new NewObjectBelowCommand(super.getDatabaseInterface().getFactory(), getCurrentObject() != null ? this.getCurrentObject() : this.filteredModule));
    }

    @FXML
    public void newObjectAfterClicked() {
        executeCommand(new NewObjectAfterCommand(super.getDatabaseInterface().getFactory(), getCurrentObject()));
    }

    @FXML
    public void editViewsClicked() {
        new EditViewsPaneController(this.views, filteredModule.getObjectAttributes().stream())
                .showDialog(outlineTreeView.getScene().getWindow(), "Edit views", ButtonType.CANCEL, ButtonType.OK)
                .filter(r -> r.buttonType == ButtonType.OK)
                .map(r -> r.result.getViews())
                .ifPresent(r -> {
                    this.views.clear();
                    this.views.addAll(r);
                    ModulePanePreferences.VIEWS.store(this.views);
                    this.updateGui(ModuleUpdateAction.UPDATE_VIEWS, ModuleUpdateAction.UPDATE_COLUMNS);
                });
    }

    @FXML
    public void pasteBelowClicked() {
        executeCommand(new PasteObjectsBelowCommand(super.getDatabaseInterface().getFactory(), contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void pasteAfterClicked() {
        executeCommand(new PasteObjectsAfterCommand(super.getDatabaseInterface().getFactory(), contentTableView.getSelectionModel().getSelectedItem(), clipboard));
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
        executeCommand(new FlattenCommand(filteredModule));
    }

    @FXML
    public void splitLinesClicked() {
        executeCommand(new SplitLinesCommand(super.getDatabaseInterface().getFactory(), filteredModule));
    }

    @FXML
    public void analyzeObjectTypeClicked() {
        contentTableView.refresh();
    }

    @Override
    public SelectionModel<DoorsFolder> getCurrentFolderSelectionModel() {
        return new FixedSingleSelectionModel<>((DoorsFolder) this.filteredModule.getParent());
    }

    @Override
    public SelectionModel<DoorsModule> getCurrentModuleSelectionModel() {
        return new FixedSingleSelectionModel<>(this.filteredModule);
    }

    @Override
    public SelectionModel<DoorsObject> getCurrentObjectSelectionModel() {
        return new ForwardingMultipleSelectionModel<>(this.contentTableView.getSelectionModel(), x -> x, y -> y);
    }

    @FXML
    public void showSidePaneClicked() {
        sideExtensionPane.selectFirst();
    }

    @FXML
    public void showBottomPaneClicked() {
        bottomExtensionPane.selectFirst();
    }

    @FXML
    public void showDatabaseClicked() {
        this.open(this.getPath().withPath(""), DatabaseInterface.OpenFlag.OPEN_ONLY);
    }

    @Override
    public void shutdown() {
        super.shutdown();

        sideExtensionPane.shutdown();
        bottomExtensionPane.shutdown();
    }

    @Override
    public void removePlugin(PluginWrapper plugin) {
        super.removePlugin(plugin);

        sideExtensionPane.removePlugin(plugin);
        bottomExtensionPane.removePlugin(plugin);
    }

    @Override
    public void addPlugin(PluginWrapper plugin) {
        super.addPlugin(plugin);

        sideExtensionPane.addPlugin(plugin);
        bottomExtensionPane.addPlugin(plugin);
    }

    public static enum ModuleUpdateAction implements UpdateAction<ModulePaneController> {
        FIX_OBJECT_LEVELS(t -> t.fixObjectLevel(t.filteredModule)),
        FIX_OBJECT_NUMBERS(t -> t.fixObjectNumbers(t.filteredModule, "")),
        UPDATE_CONTENT_VIEW(t -> t.updateContentView()),
        UPDATE_OUTLINE_VIEW(t -> t.updateOutlineView(t.filteredModule)),
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

    private class CombinedTextHeadingCell extends CustomTextFieldTableCell<DoorsObject> {

        public CombinedTextHeadingCell(TableColumn<DoorsObject, DoorsObject> tc, Function<DoorsObject, String> toString, BiConsumer<DoorsObject, String> editCommand) {
            super(tc, toString, editCommand);
        }

        @Override
        public void updateItem(final DoorsObject item, final boolean empty) {
            super.updateItem(item, empty);
            String style = "";
            if (!empty && getTableRow() != null) {
                if (item.isHeading()) {
                    style += "-fx-font-weight: bold;";
                    if (item.getObjectLevel() <= 2) {
                        style += "-fx-font-size: 140%;";
                    } else if (item.getObjectLevel() == 3) {
                        style += "-fx-font-size: 130%;";
                    } else if (item.getObjectLevel() == 4) {
                        style += "-fx-font-size: 120%;";
                    } else if (item.getObjectLevel() == 5) {
                        style += "-fx-font-size: 110%;";
                    }

                    setText(item.getObjectNumber() + " " + item.getObjectHeading());
                } else {
                    setText(item.getObjectText());
                }

                setPadding(new Insets(0, 0, 0, (item.getObjectLevel() - 1) * 10));
            }
            setStyle(style);
        }

    }
}
