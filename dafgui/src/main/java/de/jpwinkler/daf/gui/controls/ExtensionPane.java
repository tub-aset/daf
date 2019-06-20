/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

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
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import org.pf4j.PluginWrapper;

/**
 *
 * @author fwiesweg
 */
public class ExtensionPane<T extends ApplicationPartExtension> extends AutoloadingPaneController<ExtensionPane> {

    public ExtensionPane(Supplier<List<T>> extensions, Function<T, List<Node>> paneGetter, BiFunction<T, Node, String> paneNameGetter, String defaultSelection, Consumer<String> onSelected) {
        extensionChoiceBox.getItems().add(new PaneChoice(null, null, "Hide pane", null));

        extensionChoiceBox.getItems().stream()
                .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                .findAny().ifPresent(extensionChoiceBox.getSelectionModel()::select);

        this.selectionChangeListener = (obs, oldValue, newValue) -> {
            this.extensionPane.setContent(newValue == null ? null : newValue.node);
            updateVisibleProperty();
            onSelected.accept(newValue == null ? null : newValue.extensionPaneId);
        };
        extensionChoiceBox.getSelectionModel().selectedItemProperty().addListener(this.selectionChangeListener);

        this.extensions = extensions;
        this.defaultSelection = defaultSelection;
        this.paneGetter = paneGetter;
        this.paneNameGetter = paneNameGetter;
    }

    private final String defaultSelection;
    private final Supplier<List<T>> extensions;
    private final Function<T, List<Node>> paneGetter;
    private final BiFunction<T, Node, String> paneNameGetter;

    private final SimpleBooleanProperty visiblePanesProperty = new SimpleBooleanProperty(false);

    public final ObservableBooleanValue visiblePanesProperty() {
        return visiblePanesProperty;
    }

    private void updateVisibleProperty() {
        visiblePanesProperty.set(extensionChoiceBox.getItems().size() > 1
                && !extensionChoiceBox.getSelectionModel().isEmpty() && extensionChoiceBox.getSelectionModel().getSelectedItem().node != null);
    }

    private final ChangeListener<PaneChoice> selectionChangeListener;

    public void addPlugin(PluginWrapper plugin) {
        extensions.get().stream()
                .filter(e -> e.getClass().getClassLoader() == plugin.getPluginClassLoader())
                .forEach(e -> {
                    List<Node> extensionNodes = paneGetter.apply(e);
                    for (int i = 0; i < extensionNodes.size(); i++) {
                        PaneChoice pc = new PaneChoice(e.getClass().getCanonicalName() + "__" + i,
                                extensionNodes.get(i), paneNameGetter.apply(e, extensionNodes.get(i)), plugin);
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
            extensionChoiceBox.getSelectionModel().select(
                    extensionChoiceBox.getItems().stream()
                            .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                            .findAny().orElse(null));
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
    private ScrollPane extensionPane;

}
