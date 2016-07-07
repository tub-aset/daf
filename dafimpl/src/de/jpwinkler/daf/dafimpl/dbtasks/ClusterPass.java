package de.jpwinkler.daf.dafimpl.dbtasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.aliasi.cluster.HierarchicalClusterer;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import info.debatty.java.stringsimilarity.interfaces.NormalizedStringDistance;

public class ClusterPass extends ObjectCSVPass {

    private double maxTextDistance;
    private NormalizedStringDistance distance;
    private double acceptRate;
    private int minClusterSize;

    private HierarchicalClusterer<String> clusterer;
    private final Map<String, Map<String, Counter>> objectTypeMap = new HashMap<>();
    private final Map<String, Map<String, Counter>> objectSourceMap = new HashMap<>();
    private final Random r = new Random(5565);

    @Override
    protected void processObject(final DoorsObject object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
