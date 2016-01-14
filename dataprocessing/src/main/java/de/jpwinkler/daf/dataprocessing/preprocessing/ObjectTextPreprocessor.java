package de.jpwinkler.daf.dataprocessing.preprocessing;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import de.jpwinkler.libs.stringprocessing.tokens.Token;
import de.jpwinkler.libs.stringprocessing.tokens.Tokenizer;

public class ObjectTextPreprocessor {

    private final PatternProgram program;

    public ObjectTextPreprocessor(final PatternProgram program) {
        super();
        this.program = program;
    }

    public List<Token> preprocessText(final String text) {
        final List<Token> tokens = Tokenizer.tokenizeString(text);
        return program.execute(tokens);
    }

    public String preprocessTextToString(final String text) {
        final List<Token> tokens = Tokenizer.tokenizeString(text);
        return StringUtils.join(program.execute(tokens), " ");
    }

}
