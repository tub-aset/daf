package de.jpwinkler.daf.gui.controls;

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
import de.jpwinkler.daf.gui.ApplicationPartExtension;
import de.jpwinkler.daf.gui.AutoloadingPaneController;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public class ExtensionPane<T extends ApplicationPartExtension> extends AutoloadingPaneController<ExtensionPane> {

    public ExtensionPane(Supplier<List<T>> extensions, Function<T, List<? extends Node>> paneGetter, BiFunction<T, Node, String> paneNameGetter, String defaultSelection, Consumer<String> onSelected) {
        extensionChoiceBox.getItems().add(new PaneChoice(null, null, "Hide pane", null));

        extensionChoiceBox.getItems().stream()
                .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                .findAny().ifPresent(extensionChoiceBox.getSelectionModel()::select);

        this.selectionChangeListener = (obs, oldValue, newValue) -> {
            if (this.paneVBox.getChildren().size() == 2) {
                this.paneVBox.getChildren().remove(1);
            }

            if (this.paneVBox.getChildren().size() > 1) {
                throw new IllegalStateException();
            } else if (newValue != null && newValue.node != null) {
                this.paneVBox.getChildren().add(newValue.node);
                VBox.setVgrow(newValue.node, Priority.ALWAYS);
                VBox.setMargin(newValue.node, new Insets(5));
            }

            String newPaneId = updateVisibleProperty();
            if (defaultSelectionAllowUnset.isTrue() || newPaneId != null) {
                onSelected.accept(newPaneId);
            }
        };
        extensionChoiceBox.getSelectionModel().selectedItemProperty().addListener(this.selectionChangeListener);

        this.extensions = extensions;
        this.defaultSelection = defaultSelection;
        this.paneGetter = paneGetter;
        this.paneNameGetter = paneNameGetter;
    }

    private final String defaultSelection;
    private final MutableBoolean defaultSelectionAllowUnset = new MutableBoolean(true);

    private final Supplier<List<T>> extensions;
    private final Function<T, List<? extends Node>> paneGetter;
    private final BiFunction<T, Node, String> paneNameGetter;

    private final SimpleBooleanProperty visiblePanesProperty = new SimpleBooleanProperty(false);

    public final ObservableBooleanValue visiblePanesProperty() {
        return visiblePanesProperty;
    }

    private String updateVisibleProperty() {
        PaneChoice newValue = extensionChoiceBox.getSelectionModel().getSelectedItem();

        visiblePanesProperty.set(extensionChoiceBox.getItems().size() > 1 && newValue != null && newValue.node != null);
        return (newValue == null || newValue.node == null) ? null : newValue.extensionPaneId;
    }

    private final ChangeListener<PaneChoice> selectionChangeListener;

    public void addPlugin(PluginWrapper plugin) {
        extensions.get().stream()
                .filter(e -> e.getClass().getClassLoader() == plugin.getPluginClassLoader())
                .forEach(e -> {
                    List<? extends Node> extensionNodes = paneGetter.apply(e);
                    for (int i = 0; i < extensionNodes.size(); i++) {
                        PaneChoice pc = new PaneChoice(e.getClass().getCanonicalName() + "__" + i,
                                extensionNodes.get(i), paneNameGetter.apply(e, (Node) extensionNodes.get(i)), plugin);
                        extensionChoiceBox.getItems().add(pc);
                    }
                });

        if (extensionChoiceBox.getSelectionModel().isEmpty()) {
            extensionChoiceBox.getSelectionModel().select(
                    extensionChoiceBox.getItems().stream()
                            .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                            .findAny().orElse(null));
        }
        updateVisibleProperty();
    }

    public void removePlugin(PluginWrapper plugin) {
        List<PaneChoice> pcL = extensionChoiceBox.getItems().stream()
                .filter(pc -> pc.plugin == plugin)
                .collect(Collectors.toList());

        if (!pcL.isEmpty()) {
            extensionChoiceBox.getItems().removeAll(pcL);
            try {
                defaultSelectionAllowUnset.setFalse();
                extensionChoiceBox.getSelectionModel().select(
                        extensionChoiceBox.getItems().stream()
                                .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                                .findAny().orElse(null));
            } finally {
                defaultSelectionAllowUnset.setTrue();
            }
        }

        updateVisibleProperty();
    }

    public void shutdown() {
        extensionChoiceBox.getSelectionModel().selectedItemProperty().removeListener(this.selectionChangeListener);
        extensionChoiceBox.getItems().clear();
    }

    public void selectFirst() {
        if (extensionChoiceBox.getItems().size() > 1) {
            this.extensionChoiceBox.getSelectionModel().select(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No content has been registered for this pane by any plugin.");
            alert.setTitle("Cannot show pane");
            alert.setHeaderText("Cannot show pane");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    protected static final class PaneChoice {

        public PaneChoice(String extensionPaneId, Node node, String nodeName, PluginWrapper plugin) {
            this.extensionPaneId = extensionPaneId;
            this.node = node;
            this.plugin = plugin;
            this.nodeName = nodeName;
        }

        private final String extensionPaneId;
        private final Node node;
        private final String nodeName;

        private final PluginWrapper plugin;

        @Override
        public String toString() {
            return nodeName == null ? extensionPaneId : nodeName;
        }
    }

    @FXML
    private ChoiceBox<PaneChoice> extensionChoiceBox;

    @FXML
    private VBox paneVBox;

}
