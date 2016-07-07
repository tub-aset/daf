package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.Cluster;

public class UpdateObjectTypeTask {

    private static final String ATTRIBUTE_OBJECT_TYPE_CORRECTED = "Object Type Corrected";

    private static class Pass extends ObjectCSVPass {

        private final Map<String, String> objectTypeMap;

        public Pass() throws JsonSyntaxException, FileNotFoundException, IOException {
            objectTypeMap = new HashMap<>();
            final List<Cluster> clusters = Arrays.asList(new Gson().fromJson(IOUtils.toString(new FileInputStream("clusters-single-levenshtein-0.4-corrected.json")), Cluster[].class));
            for (final Cluster c : clusters) {
                for (final String example : c.getExamples()) {
                    objectTypeMap.put(example, c.getLabel());
                }
            }
            System.out.println(objectTypeMap.size());
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading()) {
                return;
            }
            final String srcID = object.getAttributes().get("SourceID");
            if (srcID == null || srcID.startsWith("STLH-") || srcID.startsWith("SB-")) {
                return;
            }

            String otNew = object.getAttributes().get("Object Type");
            if (objectTypeMap.containsKey(object.getText())) {
                otNew = objectTypeMap.get(object.getText());
            }
            object.getAttributes().put(ATTRIBUTE_OBJECT_TYPE_CORRECTED, otNew);
            if (getParsedModule().findAttributeDefinition(ATTRIBUTE_OBJECT_TYPE_CORRECTED) == null) {
                final AttributeDefinition ad = CSVFactory.eINSTANCE.createAttributeDefinition();
                ad.setName(ATTRIBUTE_OBJECT_TYPE_CORRECTED);
                getParsedModule().getAttributeDefinitions().add(ad);
            }
            saveModule();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body/B2.1 - Components/")).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
