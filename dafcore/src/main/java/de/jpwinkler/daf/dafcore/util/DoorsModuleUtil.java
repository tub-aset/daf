package de.jpwinkler.daf.dafcore.util;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class DoorsModuleUtil {

    public static DoorsObject getPreviousObject(final DoorsObject o) {
        if (o.getParent() != null && o.getParent().getChildren().indexOf(o) > 0) {
            return (DoorsObject) o.getParent().getChildren().get(o.getParent().getChildren().indexOf(o) - 1);
        } else {
            return null;
        }
    }

    public static DoorsObject getNextObject(final DoorsObject o) {
        if (o.getParent() != null && o.getParent().getChildren().indexOf(o) < o.getParent().getChildren().size() - 1) {
            return (DoorsObject) o.getParent().getChildren().get(o.getParent().getChildren().indexOf(o) + 1);
        } else {
            return null;
        }
    }

    public static int countObjects(final DoorsTreeNode module) {

        return 1 + module.getChildren().stream().mapToInt(n -> countObjects(n)).sum();

    }

}
