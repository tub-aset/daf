package de.jpwinkler.daf.reqinfclassifier;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import de.jpwinkler.daf.reqinfclassifier.utils.Counter;
import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import de.jpwinkler.libs.stringprocessing.tokens.Token;
import de.jpwinkler.libs.stringprocessing.tokens.Tokenizer;

public class ClassifierContext {

    private static ClassifierContext instance = null;

    public static ClassifierContext getInstance() {
        if (instance == null) {
            instance = new ClassifierContext();
        }
        return instance;
    }

    private static final Logger LOGGER = Logger.getLogger(ClassifierContext.class.getName());

    private PatternProgram preprocessor = null;
    private PatternProgram convNetPreprocessor = null;

    private final Counter i = new Counter();

    private ClassifierContext() {
        try {
            preprocessor = PatternProgram.compile(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/preprocessor.pp")));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            preprocessor = PatternProgram.identity();
        }
        try {
            convNetPreprocessor = PatternProgram.compile(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("de/jpwinkler/daf/reqinfclassifier/convnetclassifier/preprocessor.pp")));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            convNetPreprocessor = PatternProgram.identity();
        }
    }

    public String preprocess(final String input) {
        return StringUtils.join(preprocessor.execute(Tokenizer.tokenizeString(input)), "");
    }

    public String preprocess(final String input, final String separator) {
        return StringUtils.join(preprocessor.execute(Tokenizer.tokenizeString(input)), separator);
    }

    public List<Token> convNetPreprocess(final String input) {
        return convNetPreprocessor.execute(preprocessor.execute(Tokenizer.tokenizeString(input)));
    }

}
