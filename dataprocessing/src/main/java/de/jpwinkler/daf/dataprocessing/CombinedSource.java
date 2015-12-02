package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class CombinedSource implements DoorsModuleSource {

    private final List<DoorsModuleSource> sources;

    public CombinedSource(final List<DoorsModuleSource> sources) {
        super();
        this.sources = sources;
    }

    @Override
    public DoorsModule next() throws IOException {
        final DoorsModuleSource firstAvailableSource = getFirstAvailableSource();
        if (firstAvailableSource != null) {
            return firstAvailableSource.next();
        } else {
            return null;
        }
    }

    private DoorsModuleSource getFirstAvailableSource() {
        for (final DoorsModuleSource source : sources) {
            if (source.available() > 0) {
                return source;
            }
        }
        return null;
    }

    @Override
    public int available() {
        return sources.stream().mapToInt(source -> source.available()).sum();
    }

    @Override
    public void reset() {
        sources.stream().forEach(source -> source.reset());
    }

}
