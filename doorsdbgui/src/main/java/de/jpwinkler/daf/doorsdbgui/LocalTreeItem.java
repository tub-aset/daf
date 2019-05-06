package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class LocalTreeItem extends TreeItem<DoorsTreeNode> {

    private boolean isFirstTimeChildren = true;

    public LocalTreeItem() {
        super();
    }

    public LocalTreeItem(final DoorsTreeNode value, final Node graphic) {
        super(value, graphic);
    }

    public LocalTreeItem(final DoorsTreeNode value) {
        super(value);
    }

    @Override
    public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren());
        }
        return super.getChildren();
    }

    private ObservableList<TreeItem<DoorsTreeNode>> buildChildren() {
        if (getValue() != null) {
            final ObservableList<TreeItem<DoorsTreeNode>> list = FXCollections.observableArrayList();
            for (final DoorsTreeNode c : getValue().getChildren()) {
                list.add(new LocalTreeItem(c, imageForItem(c)));
            }
            list.sort(new DBItemComparator());
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    private ImageView imageForItem(final DoorsTreeNode item) {
        if (item instanceof DoorsFolder) {
            return new ImageView(Images.IMAGE_FOLDER);
        } else if (item instanceof DoorsModule) {
            return new ImageView(Images.IMAGE_FORMAL);
        } else {
            return new ImageView();
        }
    }

    @Override
    public boolean isLeaf() {
        return getValue() instanceof DoorsModule;
    }

}
