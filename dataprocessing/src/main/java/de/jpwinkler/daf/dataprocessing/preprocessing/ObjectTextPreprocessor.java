package de.jpwinkler.daf.dataprocessing.preprocessing;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import de.jpwinkler.libs.stringprocessing.tokens.Token;
import de.jpwinkler.libs.stringprocessing.tokens.Tokenizer;

public class ObjectTextPreprocessor {

    private PatternProgram program;
    private boolean enabled;

    public ObjectTextPreprocessor(final PatternProgram program) {
        super();
        this.program = program;
        enabled = true;
    }


    public List<Token> preprocessText(final String text) {
        if (enabled) {
            final List<Token> tokens = Tokenizer.tokenizeString(text);
            return program.execute(tokens);
        } else {
            return Tokenizer.tokenizeString(text);
        }
    }

    public String preprocessTextToString(final String text) {
        if (enabled) {
            return StringUtils.join(preprocessText(text), "");
        } else {
            return text;
        }
    }

    public static ObjectTextPreprocessor getDefaultPreprocessor() {
        try {
            final InputStream stream = ObjectTextPreprocessor.class.getClassLoader().getResourceAsStream("preprocess.pp");
            return new ObjectTextPreprocessor(PatternProgram.compile(IOUtils.toString(stream)));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectTextPreprocessor getEmptyDisabledPreprocessor() {
        final ObjectTextPreprocessor objectTextPreprocessor = new ObjectTextPreprocessor(PatternProgram.compile(""));
        objectTextPreprocessor.setEnabled(false);
        return objectTextPreprocessor;
    }

    public PatternProgram getProgram() {
        return program;
    }

    public void setProgram(final PatternProgram program) {
        this.program = program;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
