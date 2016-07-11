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

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.Cluster;

public class UpdateObjectTypeTask {

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
        protected void preprocessParsedModule(final DoorsModule module) {
            DoorsModuleUtil.ensureAttributeDefinition(module, Attributes.OBJECT_TYPE_CORRECT);
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading()) {
                return;
            }
            final String srcID = object.getAttributes().get(Attributes.SOURCE_ID);
            if (srcID == null || srcID.startsWith("STLH-") || srcID.startsWith("SB-")) {
                return;
            }

            String otNew = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            if (objectTypeMap.containsKey(object.getText())) {
                otNew = objectTypeMap.get(object.getText());
            }
            object.getAttributes().put(Attributes.OBJECT_TYPE_CORRECT, otNew);
            saveModule();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body/B2.1 - Components/")).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
