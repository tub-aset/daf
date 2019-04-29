package de.jpwinkler.daf.csveditor.massedit;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class CopyAttributeOperation implements MassEditOperation {

    private final String fromAttribute;
    private final String toAttribute;

    public CopyAttributeOperation(final String fromAttribute, final String toAttribute) {
        super();
        this.fromAttribute = fromAttribute;
        this.toAttribute = toAttribute;
    }

    @Override
    public void apply(final DoorsObject object) {
        if (object.getAttributes().containsKey(fromAttribute)) {
            object.getAttributes().put(toAttribute, object.getAttributes().get(fromAttribute));
        }
    }

}
