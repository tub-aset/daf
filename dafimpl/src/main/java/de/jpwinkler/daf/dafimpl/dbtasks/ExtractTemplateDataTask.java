package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.GsonBuilder;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.tasks.ModuleListSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class ExtractTemplateDataTask {

    private static final String TEMPLATE_VER = "2.0";
    private static final List<String> IGNORE_LIST = Arrays.asList("", "»...«");

    private static final String TEMPLATE2 = "/Templates/SLH/Vorlagen/SB " + TEMPLATE_VER + "/SB " + TEMPLATE_VER + " Content";

    private static class Pass extends ObjectCSVPass {
        private final Map<String, String> types = new HashMap<>();
        private final Map<String, String> texts = new HashMap<>();

        @Override
        protected void processObject(final DoorsObject object) {
            final String srcID = object.getAttributes().get(Attributes.SOURCE_ID);
            final String ot = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            final String text = object.getText();
            if (IGNORE_LIST.contains(text.trim())) {
                return;
            }
            types.put(srcID, ot);
            if (texts.containsKey(text) && !ot.equals(texts.get(text))) {
                System.out.println("template panic!");
                System.out.println(text + " (" + ot + "/" + texts.get(text) + ")");
            }
            texts.put(text, ot);
        }

        @Override
        public void postprocess() {
            try (FileOutputStream fos = new FileOutputStream("slh-" + TEMPLATE_VER + ".json")) {
                IOUtils.write(new GsonBuilder().setPrettyPrinting().create().toJson(types), fos);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            try (FileOutputStream fos = new FileOutputStream("slh-" + TEMPLATE_VER + "-text.json")) {
                IOUtils.write(new GsonBuilder().setPrettyPrinting().create().toJson(texts), fos);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new ModuleListSource(Arrays.asList(TEMPLATE2))).buildAndRun();
    }

}
