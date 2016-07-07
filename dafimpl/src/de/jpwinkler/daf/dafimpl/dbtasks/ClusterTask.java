package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.aliasi.cluster.CompleteLinkClusterer;
import com.aliasi.cluster.HierarchicalClusterer;
import com.aliasi.util.Distance;
import com.google.gson.GsonBuilder;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.Cluster;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.interfaces.NormalizedStringDistance;

public class ClusterTask {

    private static final String PATH = "/Body/B2.1 - Components";

    private static class Pass extends ObjectCSVPass {
        private static final String CLUSTERS_JSON = "clusters.json";
        private static final double CLUSTER_MAX_TEXT_DISTANCE = 0.85;
        private static final double OBJECT_ACCEPT_RATE = 0.2;
        private static final int CLUSTER_MIN_SIZE = 2;

        private HierarchicalClusterer<String> clusterer;
        private final Map<String, Map<String, Counter>> objectTypeMap = new HashMap<>();
        private final Map<String, Map<String, Counter>> objectSourceMap = new HashMap<>();
        private final Random r = new Random(5565);

        public Pass() {
            final NormalizedStringDistance dist = new Cosine();

            clusterer = new CompleteLinkClusterer<>(CLUSTER_MAX_TEXT_DISTANCE, new Distance<String>() {

                @Override
                public double distance(final String e1, final String e2) {
                    return Math.max(dist.distance(e1, e2), 0);
                }
            });
        }

        private Map<String, Counter> reduceCounterMaps(final List<Map<String, Counter>> l) {
            final Map<String, Counter> result = new HashMap<>();
            for (final Map<String, Counter> m : l) {
                for (final Entry<String, Counter> e : m.entrySet()) {
                    if (!result.containsKey(e.getKey())) {
                        result.put(e.getKey(), new Counter(e.getValue().get()));
                    } else {
                        result.get(e.getKey()).add(e.getValue().get());
                    }
                }
            }
            return result;
        }

        private double getSourceDistribution(final Set<String> cluster) {
            final Map<String, Counter> sourceMap = reduceCounterMaps(cluster.stream().map(c -> objectSourceMap.get(c)).collect(Collectors.toList()));

            final int max = sourceMap.values().stream().mapToInt(c -> c.get()).max().getAsInt();
            final int sum = sourceMap.values().stream().mapToInt(c -> c.get()).sum();
            return (double) sum / max;
        }

        private double getTypeDistribution(final Set<String> cluster) {
            final Map<String, Counter> typeMap = getClusterTypesMap(cluster);

            final int max = typeMap.values().stream().mapToInt(c -> c.get()).max().getAsInt();
            final int sum = typeMap.values().stream().mapToInt(c -> c.get()).sum();
            return (double) sum / max;
        }

        private String getClusterLabel(final Set<String> cluster) {
            final Map<String, Counter> typeMap = getClusterTypesMap(cluster);

            String label = null;
            for (final Entry<String, Counter> e : typeMap.entrySet()) {
                if (e.getKey() == null || e.getKey().isEmpty()) {
                    continue;
                }
                if (label == null || e.getValue().get() > typeMap.get(label).get()) {
                    label = e.getKey();
                }
            }
            return label;
        }

        private Map<String, Counter> getClusterTypesMap(final Set<String> cluster) {
            return reduceCounterMaps(cluster.stream().map(c -> objectTypeMap.get(c)).collect(Collectors.toList()));
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
            if (!object.getText().contains("Mercedes-Benz Teile-Ident-Nr") && !object.getText().contains("Name, Vorname") && !object.getText().contains("muss")) {
                return;
            }
            if (r.nextDouble() > OBJECT_ACCEPT_RATE) {
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
            System.out.println("Clustering " + objectTypeMap.size() + " objects...");
            final long t1 = System.currentTimeMillis();
            final Set<Set<String>> result = clusterer.cluster(objectTypeMap.keySet());
            final long td = System.currentTimeMillis() - t1;

            final List<Cluster> labeledClusters = new ArrayList<>();

            System.out.println("found " + result.size() + " clusters.");

            for (final Set<String> cluster : result) {
                if (cluster.size() < CLUSTER_MIN_SIZE) {
                    continue;
                }
                final Cluster e = new Cluster(getClusterLabel(cluster), cluster, getSourceDistribution(cluster), getTypeDistribution(cluster));
                e.setTypeMap(getClusterTypesMap(cluster));
                labeledClusters.add(e);
            }

            Collections.sort(labeledClusters, (o1, o2) -> (int) Math.signum(o2.getSourceDistribution() - o1.getSourceDistribution()));

            System.out.println("Discarded " + (result.size() - labeledClusters.size()) + " clusters.");

            try {
                IOUtils.write(new GsonBuilder().setPrettyPrinting().create().toJson(labeledClusters), new FileOutputStream(CLUSTERS_JSON));
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("clustering took " + td + " ms");
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource(PATH)).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
