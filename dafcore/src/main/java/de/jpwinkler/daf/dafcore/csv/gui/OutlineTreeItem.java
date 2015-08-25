package de.jpwinkler.daf.dafcore.csv.gui;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class OutlineTreeItem {

    private final DoorsTreeNode treeNode;

    public OutlineTreeItem(final DoorsTreeNode treeNode) {
        super();
        this.treeNode = treeNode;
    }

    @Override
    public String toString() {
        if (treeNode instanceof DoorsModule) {
            return ((DoorsModule) treeNode).getName();
        } else if (treeNode instanceof DoorsObject) {
            if (((DoorsObject) treeNode).isHeading()) {
                return ((DoorsObject) treeNode).getObjectNumber() + " " + getTruncatedText();
            } else {
                return getTruncatedText();
            }
        } else {
            return treeNode.toString();
        }
    }

    private String getTruncatedText() {
        final String temp = ((DoorsObject) treeNode).getText().replace("\n", "");
        if (temp.length() > 50) {
            return temp.substring(0, 47) + "...";
        } else {
            return temp;
        }
    }

    public DoorsTreeNode getTreeNode() {
        return treeNode;
    }
}
