package de.jpwinkler.daf.csveditor.massedit;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public interface MassEditOperation {

    void apply(DoorsObject object);

}
