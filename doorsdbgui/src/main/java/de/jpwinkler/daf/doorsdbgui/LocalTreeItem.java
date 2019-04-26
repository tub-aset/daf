package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.bridge.DoorsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class LocalTreeItem extends TreeItem<DBItem> {

    private boolean isFirstTimeChildren = true;

    public LocalTreeItem() {
        super();
    }

    public LocalTreeItem(final DBItem value, final Node graphic) {
        super(value, graphic);
    }

    public LocalTreeItem(final DBItem value) {
        super(value);
    }

    @Override
    public ObservableList<TreeItem<DBItem>> getChildren() {
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

    private ObservableList<TreeItem<DBItem>> buildChildren() throws DoorsException {
        if (getValue() != null) {
            final ObservableList<TreeItem<DBItem>> list = FXCollections.observableArrayList();
            for (final DBItem c : getValue().getChildren()) {
                list.add(new LocalTreeItem(c, imageForItem(c)));
            }
            list.sort(new DBItemComparator());
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    private ImageView imageForItem(final DBItem item) {
        if (item instanceof DBFolder) {
            return new ImageView(Images.IMAGE_FOLDER);
        } else if (item instanceof DBModule) {
            return new ImageView(Images.IMAGE_FORMAL);
        } else {
            return new ImageView();
        }
    }

    @Override
    public boolean isLeaf() {
        return getValue() instanceof DBModule;
    }

}
