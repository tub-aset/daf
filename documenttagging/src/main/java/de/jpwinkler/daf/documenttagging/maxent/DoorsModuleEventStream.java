package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import opennlp.model.Event;
import opennlp.model.EventStream;

public class DoorsModuleEventStream implements EventStream {

    private MaxentDataGenerator generator;
    private final List<Event> events = new ArrayList<>();
    private Iterator<Event> iterator;

    private Event buildEventFromObject(final DoorsObject object) {
        final MaxentDataElement e = generator.run(object);
        if (e != null) {
            return new Event(e.getOutcome(), e.getFeatures());
        } else {
            return null;
        }
    }

    public DoorsModuleEventStream(final MaxentDataGenerator generator, final DoorsModule... modules) {
        super();
        this.generator = generator;

        for (final DoorsModule m : modules) {
            m.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    final Event event = buildEventFromObject(object);
                    if (event != null) {
                        events.add(event);
                    }
                    return true;
                }
            });
        }
        iterator = events.iterator();
    }

    @Override
    public Event next() throws IOException {
        return iterator.next();
    }

    @Override
    public boolean hasNext() throws IOException {
        return iterator.hasNext();
    }

}
