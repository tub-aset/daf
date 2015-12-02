package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class SingleSource implements DoorsModuleSource {

    private final DoorsModule module;

    private boolean available;

    public SingleSource(final DoorsModule module) {
        super();
        this.module = module;
        available = true;
    }

    @Override
    public DoorsModule next() throws IOException {
        if (available) {
            available = false;
            return module;
        } else {
            return null;
        }
    }

    @Override
    public int available() {
        if (available) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void reset() {
        available = true;
    }

}
