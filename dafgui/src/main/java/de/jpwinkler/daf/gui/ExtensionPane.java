/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import org.pf4j.PluginWrapper;
import de.jpwinkler.daf.gui.extensions.ApplicationPartExtension;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * @author fwiesweg
 */
public class ExtensionPane<T extends ApplicationPartExtension> extends AutoloadingPaneController<ExtensionPane> {

    public ExtensionPane(Supplier<List<T>> extensions, Function<T, List<Node>> paneGetter, String defaultSelection, Consumer<String> onSelected) {

        extensionChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            extensionPane.setContent(newValue.node);
            onSelected.accept(newValue.extensionPaneId);
        });

        extensionChoiceBox.getItems().stream()
                .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                .findAny().ifPresent(extensionChoiceBox.getSelectionModel()::select);

        this.extensions = extensions;
        this.defaultSelection = defaultSelection;
        this.paneGetter = paneGetter;
    }

    private final String defaultSelection;
    private final Supplier<List<T>> extensions;
    private final Function<T, List<Node>> paneGetter;

    public boolean hasPanes() {
        return !extensionChoiceBox.getItems().isEmpty();
    }

    public void addPlugin(PluginWrapper plugin) {
        extensions.get().stream()
                .filter(e -> e.getClass().getClassLoader() == plugin.getPluginClassLoader())
                .forEach(e -> {
                    List<Node> extensionNodes = paneGetter.apply(e);
                    for (int i = 0; i < extensionNodes.size(); i++) {
                        PaneChoice pc = new PaneChoice(e.getClass().getCanonicalName() + "__" + i, extensionNodes.get(i), plugin);
                        extensionChoiceBox.getItems().add(pc);
                    }
                });

        if (extensionChoiceBox.getSelectionModel().isEmpty()) {
            extensionChoiceBox.getSelectionModel().select(
                    extensionChoiceBox.getItems().stream()
                            .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                            .findAny().orElse(null));
        }
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
    }

    protected static final class PaneChoice {

        public PaneChoice(String extensionPaneId, Node node, PluginWrapper plugin) {
            this.extensionPaneId = extensionPaneId;
            this.node = node;
            this.plugin = plugin;
        }

        private final String extensionPaneId;
        private final Node node;
        private final PluginWrapper plugin;

        @Override
        public String toString() {
            return extensionPaneId;
        }
    }

    @FXML
    private ChoiceBox<PaneChoice> extensionChoiceBox;

    @FXML
    private ScrollPane extensionPane;

}
