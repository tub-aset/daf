package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public abstract class DoorsModuleOperation implements DoorsModuleSource {

    private DoorsModuleSource source;

    protected abstract DoorsModule apply(DoorsModule module);

    @Override
    public final DoorsModule next() throws IOException {
        if (available() > 0) {
            return apply(source.next());
        } else {
            return null;
        }
    }

    @Override
    public final int available() {
        return source.available();
    }

    public final DoorsModuleSource getSource() {
        return source;
    }

    public final void setSource(final DoorsModuleSource source) {
        this.source = source;
    }

    @Override
    public void reset() {
        source.reset();
    }

}
