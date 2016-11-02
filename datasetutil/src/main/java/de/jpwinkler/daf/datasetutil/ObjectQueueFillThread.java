package de.jpwinkler.daf.datasetutil;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.search.NotSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class ObjectQueueFillThread extends Thread {

    private final SynchronizedQueue<DoorsObject> queue;

    private class Pass extends ObjectCSVPass {

        @Override
        protected void processObject(final DoorsObject object) {
            if (getId(object) != null && (getId(object).startsWith("STLH-") || getId(object).startsWith("SB-"))) {
                return;
            }

            queue.enqueue(object);
        }

        private String getId(final DoorsObject object) {
            final String srcId = object.getAttributes().get("SourceID");
            if (srcId != null && !srcId.isEmpty() && !"new".equals(srcId)) {
                return srcId;
            } else {
                return object.getObjectIdentifier();
            }
        }

    }

    public ObjectQueueFillThread(final SynchronizedQueue<DoorsObject> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            new ModuleTaskBuilder().withFilter(new NotSearchExpression(new HasTagsSearchExpression("quality:no_objecttype"))).withFilter(new HasTagsSearchExpression("lang:de", "doctype:KLH")).withPass(new Pass()).buildAndRun();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
