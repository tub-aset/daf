package de.jpwinkler.daf.datasetutil;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;
import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.interfaces.NormalizedStringSimilarity;

public class TaskQueueFillThread extends Thread {

    private final DataSetStorage storage;
    private final SynchronizedQueue<DoorsObject> objectQueue;
    private final SynchronizedQueue<Task> taskQueue;

    private final ConvNetClassifier convNetClassifier;

    private final ClusterClassifier clusterClassifier;

    private final StructuralClassifier structuralClassifier;

    private final NormalizedStringSimilarity similarityFunction = new Cosine();

    private String getId(final DoorsObject object) {
        final String srcId = object.getAttributes().get("SourceID");
        if (srcId != null && !srcId.isEmpty() && !"new".equals(srcId)) {
            return srcId;
        } else {
            return object.getObjectIdentifier();
        }
    }

    private Task createTask(final DoorsObject object) {
        final Task t = new Task();
        t.setBody(object.getText());
        if (object.getParent() instanceof DoorsObject) {
            t.setParentBody(((DoorsObject) object.getParent()).getText());
            t.setParentId(getId((DoorsObject) object.getParent()));
        }
        for (final DoorsTreeNode n : object.getChildren()) {
            if (n instanceof DoorsObject) {
                t.getChildBodies().add(((DoorsObject) n).getText());
            }
        }
        final DoorsObject prev = DoorsModuleUtil.getPreviousObject(object);
        final DoorsObject next = DoorsModuleUtil.getNextObject(object);
        if (prev != null) {
            t.setPreviousBody(prev.getText());
            t.setPreviousId(getId(prev));
        }
        if (next != null) {
            t.setNextBody(next.getText());
            t.setNextId(getId(next));
        }
        t.setId(getId(object));
        t.setOriginalObjectType(object.getAttributes().get("Object Type"));

        final DataSetRecord record = new DataSetRecord();
        record.setBody(t.getBody());
        record.setParentBody(t.getParentBody());
        record.setPreviousBody(t.getPreviousBody());
        record.setNextBody(t.getNextBody());
        if (storage.exists(record)) {
            return null;
        }

        final DoorsObjectContext ctx = new DoorsObjectContext(object, ClassifierContext.getInstance());

        t.setStructuralType(structuralClassifier.classify(ctx));

        if (t.getStructuralType() == null || !t.getStructuralType().contains("sentence")) {
            return null;
        }

        t.setClusterAnalysisObjectType(clusterClassifier.classify(ctx));

        t.setConvNetClassification(convNetClassifier.classify(ctx));
        t.setConvNetInf((Double) ctx.getProperties().get(DoorsObjectContext.PROPERTY_CONVNET_CLASSIFIER_INFORMATION));
        t.setConvNetReq((Double) ctx.getProperties().get(DoorsObjectContext.PROPERTY_CONVNET_CLASSIFIER_REQUIREMENT));

        // if (t.getOnlineConvNetClassification().equals(t.getOriginalObjectType())) {
        // return null;
        // }

        t.setGreatestSimiliarity(0);

        // similiarity analysis
        storage.forEach(r -> {
            final double s1 = similarityFunction.similarity(r.getBody(), t.getBody());
            if (s1 > t.getGreatestSimiliarity()) {
                t.setGreatestSimiliarity(s1);
                t.setSimiliarityAnalysisObjectType(r.getCorrectedObjectType());
                t.setSimiliarityAnalysisStructuralType(r.getStructualType());
            }
        });

        return t;
    }

    public TaskQueueFillThread(final SynchronizedQueue<DoorsObject> objectQueue, final SynchronizedQueue<Task> taskQueue, final DataSetStorage storage) {
        this.objectQueue = objectQueue;
        this.taskQueue = taskQueue;
        this.storage = storage;

        structuralClassifier = new StructuralClassifier(ClassifierContext.getInstance());
        clusterClassifier = new ClusterClassifier(ClassifierContext.getInstance());
        convNetClassifier = new ConvNetClassifier(ClassifierContext.getInstance());
    }

    @Override
    public void run() {
        while (true) {
            final DoorsObject o = objectQueue.dequeue();
            final Task t = createTask(o);
            if (t != null) {
                taskQueue.enqueue(t);
            }
        }
    }

}
