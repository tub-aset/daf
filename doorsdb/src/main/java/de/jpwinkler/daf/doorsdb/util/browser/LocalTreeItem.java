package de.jpwinkler.daf.doorsdb.util.browser;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

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
                list.add(new LocalTreeItem(c));
            }
            list.sort(new DBItemComparator());
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    @Override
    public boolean isLeaf() {
        return getValue() instanceof DBModule;
    }

}
