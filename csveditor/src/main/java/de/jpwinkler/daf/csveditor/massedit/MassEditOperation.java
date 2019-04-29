package de.jpwinkler.daf.csveditor.massedit;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public interface MassEditOperation {

    void apply(DoorsObject object);

}
