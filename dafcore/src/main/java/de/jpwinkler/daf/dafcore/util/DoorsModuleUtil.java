package de.jpwinkler.daf.dafcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
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

    public static void ensureAttributeDefinition(final DoorsModule module, final String attributeName) {
        if (module.findAttributeDefinition(attributeName) == null) {
            final AttributeDefinition ad = CSVFactory.eINSTANCE.createAttributeDefinition();
            ad.setName(attributeName);
            module.getAttributeDefinitions().add(ad);
        }
    }

    public static int countObjects(final DoorsTreeNode module) {

        return 1 + module.getChildren().stream().mapToInt(n -> countObjects(n)).sum();

    }

    public static Date parseDate(final String doorsDateString) throws ParseException {
        return new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).parse(doorsDateString);
    }

    public static void compareModules(final DoorsModule left, final DoorsModule right) {
        final Map<String, DoorsObject> leftObjects = new HashMap<>();
        final Map<String, DoorsObject> rightObjects = new HashMap<>();

        left.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                leftObjects.put(object.getObjectIdentifier(), object);
                return true;
            }
        });

        right.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                rightObjects.put(object.getObjectIdentifier(), object);
                return true;
            }
        });

        // search for added and changed objects
        for (final Entry<String, DoorsObject> e : rightObjects.entrySet()) {
            if (!leftObjects.containsKey(e.getKey())) {
                System.out.println("added: " + e.getValue());
            } else {
                final DoorsObject old = leftObjects.get(e.getKey());
                if (!old.getText().equals(e.getValue().getText())) {
                    System.out.println("changed: " + e.getValue());
                }
            }
        }

        // search for deleted objects
        for (final Entry<String, DoorsObject> e : leftObjects.entrySet()) {
            if (!rightObjects.containsKey(e.getKey())) {
                System.out.println("added: " + e.getValue());
            }
        }
    }

    public static String getTemplateName(final DoorsModule module) {
        String moduleType = "";
        final String templateShortName = module.getAttributes().get("IN_TemplateShortName");
        if ("STLH".equals(templateShortName)) {
            moduleType = "stlh";
        } else if ("SB".equals(templateShortName)) {
            moduleType = "slh";
        } else {
            return null;
        }
        final String templateVersion = module.getAttributes().get("Template Version");
        if (templateVersion != null) {
            return moduleType + "-" + templateVersion;
        } else {
            return null;
        }

    }
}

