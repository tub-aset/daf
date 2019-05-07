package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.daf.bridge.DoorsItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;
import de.jpwinkler.daf.model.DoorsTreeNode;

public class RemoteTreeItem extends TreeItem<DoorsTreeNodeRef> {

    private boolean isFirstTimeChildren = true;

    public RemoteTreeItem() {
        super();
    }

    public RemoteTreeItem(final DoorsTreeNodeRef value, final Node graphic) {
        super(value, graphic);
    }

    public RemoteTreeItem(final DoorsTreeNodeRef value) {
        super(value);
    }

    @Override
    public ObservableList<TreeItem<DoorsTreeNodeRef>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren());
        }
        return super.getChildren();
    }

    private ObservableList<TreeItem<DoorsTreeNodeRef>> buildChildren() {
        if (getValue() != null) {
            final ObservableList<TreeItem<DoorsTreeNodeRef>> list = FXCollections.observableArrayList();
            for (final DoorsTreeNode c : getValue().getChildren()) {
                list.add(new RemoteTreeItem((DoorsTreeNodeRef) c, imageForType(((DoorsTreeNodeRef) c).getType())));
            }
            list.sort(new ItemRefComparator());
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    private ImageView imageForType(final DoorsItemType type) {
        switch (type) {
            case FOLDER:
                return new ImageView(Images.IMAGE_FOLDER);
            case FORMAL:
                return new ImageView(Images.IMAGE_FORMAL);
            case LINK:
                return new ImageView(Images.IMAGE_LINK);
            case PROJECT:
                return new ImageView(Images.IMAGE_PROJECT);
            default:
                return new ImageView();
        }

    }

    @Override
    public boolean isLeaf() {
        return !(getValue().getType() == DoorsItemType.FOLDER || getValue().getType() == DoorsItemType.PROJECT);
    }

}
