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
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
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

    public void updateChildren() {
        childrenLoaded = true;
        this.setGraphic(ApplicationIcons.LOADING.toImageView());
        getValue().getChildrenAsync(executor).thenAccept((children) -> Platform.runLater(() -> {
            this.setGraphic(ApplicationIcons.getImage(getValue()).toImageView());
            final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
            list.clear();
            children.stream().filter(childFilter).map(n -> this.construct((DoorsTreeNode) n)).forEach(list::add);
            list.sort(Comparator.naturalOrder());
            super.getChildren().setAll(list);
        }));
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
