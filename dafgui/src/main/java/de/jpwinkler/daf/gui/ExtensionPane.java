/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.gui.extensions.ApplicationPartExtensionPoint;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author fwiesweg
 */
public class ExtensionPane extends AutoloadingPaneController<ExtensionPane> {

    public ExtensionPane(List<ApplicationPartExtensionPoint> extensions, Function<ApplicationPartExtensionPoint, List<Node>> paneGetter, String defaultSelection, Consumer<String> onSelected) {

        extensionChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            extensionPane.setContent(newValue.node);
            onSelected.accept(newValue.extensionPaneId);
        });

        extensions.forEach(e -> {
            List<Node> extensionNodes = paneGetter.apply(e);
            for (int i = 0; i < extensionNodes.size(); i++) {
                PaneChoice pc = new PaneChoice(e.getClass().getCanonicalName() + "__" + i, extensionNodes.get(i));
                extensionChoiceBox.getItems().add(pc);
            }
        });

        extensionChoiceBox.getItems().stream()
                .filter(it -> Objects.equals(it.extensionPaneId, defaultSelection))
                .findAny().ifPresent(extensionChoiceBox.getSelectionModel()::select);
    }

    public boolean hasPanes() {
        return !extensionChoiceBox.getItems().isEmpty();
    }

    protected static final class PaneChoice {

        public PaneChoice(String extensionPaneId, Node node) {
            this.extensionPaneId = extensionPaneId;
            this.node = node;
        }

        private final String extensionPaneId;
        private final Node node;

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
