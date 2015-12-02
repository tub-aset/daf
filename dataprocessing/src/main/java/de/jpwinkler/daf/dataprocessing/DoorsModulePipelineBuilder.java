package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dataprocessing.preprocessing.CompoundSplitterPreprocessor;
import de.jpwinkler.daf.dataprocessing.preprocessing.IgnoreCasePreprocessor;
import de.jpwinkler.daf.dataprocessing.preprocessing.SpecialCharacterRemovalPreprocessor;
import de.jpwinkler.daf.dataprocessing.preprocessing.StopwordRemovalPreprocessor;
import de.jpwinkler.daf.dataprocessing.preprocessing.WordStemmerPreprocessor;

public class DoorsModulePipelineBuilder {

    private final List<DoorsModuleSource> sources = new ArrayList<>();
    private final List<DoorsModuleOperation> operations = new ArrayList<>();

    public DoorsModulePipelineBuilder withSource(final DoorsModuleSource source) {
        sources.add(source);
        return this;
    }

    public DoorsModulePipelineBuilder addOperation(final DoorsModuleOperation operation) {
        operations.add(operation);
        return this;
    }

    public DoorsModulePipelineBuilder addDefaultPreprocessors() throws IOException {
        operations.add(new IgnoreCasePreprocessor());
        operations.add(new SpecialCharacterRemovalPreprocessor());
        operations.add(new CompoundSplitterPreprocessor());
        operations.add(new StopwordRemovalPreprocessor(DoorsModulePipelineBuilder.class.getResourceAsStream("stopwords.txt")));
        operations.add(new WordStemmerPreprocessor());
        return this;
    }

    public DoorsModuleSource build() {
        DoorsModuleSource source;
        if (sources.size() > 1) {
            source = new CombinedSource(sources);
        } else if (sources.size() == 1) {
            source = sources.get(0);
        } else {
            throw new RuntimeException("No source defined.");
        }

        DoorsModuleSource lastSource = source;

        for (final DoorsModuleOperation operation : operations) {
            operation.setSource(lastSource);
            lastSource = operation;
        }

        return lastSource;
    }

    public <T extends DoorsModuleSink> T buildWithSink(final T moduleSink) {
        moduleSink.setSource(build());
        return moduleSink;
    }
}
