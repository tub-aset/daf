/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.gui.ApplicationIcons;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author fwiesweg
 */
public class DoorsTreeItem extends TreeItem<DoorsTreeNode> implements Comparable<DoorsTreeItem> {

    private boolean childrenLoaded = false;

    protected final BackgroundTaskExecutor executor;
    protected final BiConsumer<DoorsTreeItem, DoorsTreeNode> onLoad;
    protected final Predicate<DoorsTreeNode> childFilter;

    public DoorsTreeItem(BackgroundTaskExecutor executor, DoorsTreeNode value, BiConsumer<DoorsTreeItem, DoorsTreeNode> onLoad, Predicate<DoorsTreeNode> childFilter) {
        super(value, ApplicationIcons.getImage(value).toImageView());
        this.executor = executor;

        this.onLoad = onLoad;
        this.childFilter = childFilter;

        onLoad.accept(this, value);
    }

    @Override
    public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
        if (!childrenLoaded) {
            updateChildren();
        }
        return super.getChildren();
    }

    private void loadChildren(List<DoorsTreeNode> children) {
        this.setGraphic(ApplicationIcons.getImage(getValue()).toImageView());
        final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
        children.stream().filter(childFilter).map(n -> this.construct((DoorsTreeNode) n)).forEach(list::add);
        list.sort(Comparator.naturalOrder());
        super.getChildren().setAll(list);
    }

    public void updateChildren() {
        childrenLoaded = true;

        this.setGraphic(ApplicationIcons.LOADING.toImageView());
        CompletableFuture<List<DoorsTreeNode>> promise = getValue().getChildrenAsync(executor).exceptionally(t -> {
            childrenLoaded = false;
            throw new RuntimeException(t);
        });
        if (promise.isDone()) {
            try {
                this.loadChildren(promise.get());
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException();
            }
        } else {
            promise.thenAccept((children) -> Platform.runLater(() -> this.loadChildren(children)));
        }
    }

    protected DoorsTreeItem construct(DoorsTreeNode node) {
        return new DoorsTreeItem(executor, node, onLoad, childFilter);
    }

    @Override
    public boolean isLeaf() {
        return childrenLoaded && getChildren().isEmpty();
    }

    @Override
    public int compareTo(DoorsTreeItem o2) {
        return Objects.compare(this.getValue().getName(), o2.getValue().getName(), Comparator.naturalOrder());
    }
}
