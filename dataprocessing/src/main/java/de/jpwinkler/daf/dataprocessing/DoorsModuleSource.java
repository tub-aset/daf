package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public interface DoorsModuleSource {

    DoorsModule next() throws IOException;

    int available();

    void reset();
}
