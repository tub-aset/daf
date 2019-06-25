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
import de.jpwinkler.daf.gui.ApplicationIcons;
import de.jpwinkler.daf.gui.BackgroundTask;
import de.jpwinkler.daf.gui.BackgroundTaskExecutorImpl;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    private boolean childrenLoadingStarted = false;
    private boolean childrenLoaded = false;

    private final BackgroundTaskExecutorImpl executor;
    private final BiConsumer<DoorsTreeItem, DoorsTreeNode> onLoad;
    private final Predicate<DoorsTreeNode> childFilter;

    public DoorsTreeItem(BackgroundTaskExecutorImpl executor, DoorsTreeNode value, BiConsumer<DoorsTreeItem, DoorsTreeNode> onLoad, Predicate<DoorsTreeNode> childFilter) {
        super(value, ApplicationIcons.getImage(value).toImageView());
        this.executor = executor;

        this.onLoad = onLoad;
        this.childFilter = childFilter;

        onLoad.accept(this, value);
    }

    @Override
    public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
        if (!childrenLoadingStarted) {
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
        childrenLoadingStarted = true;

        this.setGraphic(ApplicationIcons.LOADING.toImageView());
        CompletableFuture<List<DoorsTreeNode>> promise = getValue().getChildrenAsync(executor.withPriority(BackgroundTask.PRIORITY_FOLDERS));

        if (promise.isDone()) {
            try {
                this.loadChildren(promise.get());
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException();
            }
        } else {
            promise.exceptionally(t -> {
                Platform.runLater(() -> {
                    this.setGraphic(ApplicationIcons.ERROR.toImageView());
                    childrenLoadingStarted = false;
                });
                throw new RuntimeException(t);
            }).thenAccept((children) -> Platform.runLater(() -> {
                this.loadChildren(children);
                childrenLoaded = true;
            }));
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
