package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class FindCommonObjectsTask {

    private static class Pass extends ObjectCSVPass {
        private final Map<String, Map<String, Counter>> objectTypeMap = new HashMap<>();
        private final Map<String, Map<String, Counter>> objectSourceMap = new HashMap<>();

        public double foo(final String s) {
            final int total = objectSourceMap.get(s).values().stream().mapToInt(c -> c.get()).sum();
            return objectSourceMap.get(s).values().stream().mapToDouble(c -> (double) c.get() / total).max().getAsDouble();
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading() || object.getText().trim().isEmpty()) {
                return;
            }
            final String srcId = object.getAttributes().get("SourceID");
            if (srcId == null || srcId.startsWith("STLH-") || srcId.startsWith("SB-") || srcId.isEmpty()) {
                return;
            }
            final String ot = object.getAttributes().get("Object Type");
            if (ot == null) {
                return;
            }
            Map<String, Counter> typeMap = objectTypeMap.get(object.getText());
            if (typeMap == null) {
                typeMap = new HashMap<>();
                objectTypeMap.put(object.getText(), typeMap);
            }

            Counter typeCounter = typeMap.get(ot);
            if (typeCounter == null) {
                typeCounter = new Counter();
                typeMap.put(ot, typeCounter);
            }
            typeCounter.inc();

            Map<String, Counter> sourceMap = objectSourceMap.get(object.getText());
            if (sourceMap == null) {
                sourceMap = new HashMap<>();
                objectSourceMap.put(object.getText(), sourceMap);
            }

            Counter sourceCounter = sourceMap.get(getModule().getFullName());
            if (sourceCounter == null) {
                sourceCounter = new Counter();
                sourceMap.put(getModule().getFullName(), sourceCounter);
            }
            sourceCounter.inc();
        }

        @Override
        public void postprocess() {
            final List<String> sorted = new ArrayList<>(objectTypeMap.keySet());
            Collections.sort(sorted, (o1, o2) -> {
                return objectTypeMap.get(o1).values().stream().mapToInt(c -> c.get()).sum() - objectTypeMap.get(o2).values().stream().mapToInt(c -> c.get()).sum();
            });

            for (final String s : sorted) {
                // if (objects.get(s).size() == 1) {
                if (foo(s) < 0.15) {
                    System.out.println(objectSourceMap.get(s).size() + " " + s + " " + objectTypeMap.get(s));
                }
                // }
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body")).buildAndRun();
    }

}
