package de.jpwinkler.daf.reqinfclassifier;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import de.jpwinkler.daf.reqinfclassifier.utils.Counter;
import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;
import de.jpwinkler.libs.stringprocessing.tokens.Tokenizer;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;

public class ClassifierContext {

    private static ClassifierContext instance = null;

    public static ClassifierContext getInstance() {
        if (instance == null) {
            instance = new ClassifierContext();
        }
        return instance;
    }

    private static final Logger LOGGER = Logger.getLogger(ClassifierContext.class.getName());

    private final LexicalizedParser parser;
    private PatternProgram preprocessor = null;
    private PatternProgram convNetPreprocessor = null;

    private final Counter i = new Counter();

    private final DB db;
    private final Map<String, List<Tree>> trees;

    private ClassifierContext() {
        parser = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/germanPCFG.ser.gz");
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
        db = DBMaker.newFileDB(new File("C:/WORK/trees.mapdb"))
                .compressionEnable()
                .mmapFileEnable()
                .make();
        trees = db.getHashMap("pcfg");
    }

    public String preprocess(final String input) {
        return StringUtils.join(preprocessor.execute(Tokenizer.tokenizeString(input)), "");
    }

    public String preprocess(final String input, final String separator) {
        return StringUtils.join(preprocessor.execute(Tokenizer.tokenizeString(input)), separator);
    }

    public String convNetPreprocess(final String input) {
        return StringUtils.join(convNetPreprocessor.execute(preprocessor.execute(Tokenizer.tokenizeString(input))), " ");
    }

    public List<Tree> parseTrees(final String text) {
        if (trees.containsKey(text)) {
            return trees.get(text);
        } else {
            final DocumentPreprocessor preprocessor = new DocumentPreprocessor(new StringReader(text));
            final List<Tree> l = StreamSupport.stream(preprocessor.spliterator(), false).map(s -> parser.parse(s)).collect(Collectors.toList());
            trees.put(text, l);
            synchronized (i) {
                i.inc();
                if (i.get() > 1000) {
                    i.set(0);
                    LOGGER.info("committing...");
                    db.commit();
                    LOGGER.info("committed.");
                }
            }
            return l;
        }

    }
}
