package de.jpwinkler.daf.doorsdb.util.browser;

import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsItemType;
import de.jpwinkler.libs.doorsbridge.ItemRef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
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
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    private ImageView imageForType(final DoorsItemType type) {
        String imageFile = null;
        switch (type) {
        case FOLDER:
            imageFile = "/icons/doors_folder.png";
            break;
        case FORMAL:
            imageFile = "/icons/doors_formal.png";
            break;
        case LINK:
            imageFile = "/icons/doors_link.png";
            break;
        case PROJECT:
            imageFile = "/icons/doors_project.png";
            break;
        default:
            break;
        }
        return new ImageView(new Image(getClass().getResourceAsStream(imageFile)));
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
