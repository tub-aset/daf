package de.jpwinkler.daf.doorsdb.search;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DBSearchLexer;
import de.jpwinkler.daf.doorsdb.DBSearchParser;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public abstract class DBSearchExpression {

    private static class MyErrorListener extends BaseErrorListener {

        private final List<String> errors = new ArrayList<>();

        @Override
        public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
            errors.add("line " + line + ":" + charPositionInLine + " " + msg);
        }

        public List<String> getErrors() {
            return errors;
        }
    }

    public abstract boolean matches(DoorsModule module);

    public static DBSearchExpression compile(final String filter) {

        final DBSearchLexer lexer = new DBSearchLexer(new ANTLRInputStream(filter));
        final DBSearchParser parser = new DBSearchParser(new CommonTokenStream(lexer));
        final DBSearchListenerImpl listener = new DBSearchListenerImpl();

        parser.addParseListener(listener);
        final MyErrorListener errorListener = new MyErrorListener();
        parser.addErrorListener(errorListener);
        parser.searchExpression();
        if (errorListener.getErrors().isEmpty()) {
            return listener.getSearchExpression();
        } else {
            return null;
        }

    }

}
