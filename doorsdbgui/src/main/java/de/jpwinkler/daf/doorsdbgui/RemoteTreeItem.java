package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class RemoteTreeItem extends TreeItem<ItemRef> {

    private boolean isFirstTimeChildren = true;

    public RemoteTreeItem() {
        super();
    }

    public RemoteTreeItem(final ItemRef value, final Node graphic) {
        super(value, graphic);
    }

    public RemoteTreeItem(final ItemRef value) {
        super(value);
    }

    @Override
    public ObservableList<TreeItem<ItemRef>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            try {
                super.getChildren().setAll(buildChildren());
            } catch (final DoorsException e) {
                throw new RuntimeException(e);
            }
        }
        return super.getChildren();
    }

    private ObservableList<TreeItem<ItemRef>> buildChildren() throws DoorsException {
        if (getValue() != null) {
            final ObservableList<TreeItem<ItemRef>> list = FXCollections.observableArrayList();
            for (final ItemRef c : getValue().getChildren()) {
                list.add(new RemoteTreeItem(c, imageForType(c.getType())));
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
        try {
            return !(getValue().getType() == DoorsItemType.FOLDER || getValue().getType() == DoorsItemType.PROJECT);
        } catch (final DoorsException e) {
            throw new RuntimeException(e);
        }
    }

}
