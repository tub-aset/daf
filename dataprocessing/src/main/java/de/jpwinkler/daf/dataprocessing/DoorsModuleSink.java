package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public abstract class DoorsModuleSink {

    private DoorsModuleSource source;

    public final void runOnce() throws IOException {
        if (source.available() > 0) {
            run(source.next());
        }
    }

    public final void runAll() throws IOException {
        while (source.available() > 0) {
            run(source.next());
        }
    }

    protected abstract void run(DoorsModule module);

    public DoorsModuleSource getSource() {
        return source;
    }

    public void setSource(final DoorsModuleSource source) {
        this.source = source;
    }

}
