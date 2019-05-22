package de.jpwinkler.daf.filter.modules;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public abstract class SearchExpression {

    public abstract boolean matches(DoorsModule module);

    public static SearchExpression compile(final String filter) {

        final DoorsModuleFilterLexer lexer = new DoorsModuleFilterLexer(CharStreams.fromString(filter));
        final DoorsModuleFilterParser parser = new DoorsModuleFilterParser(new CommonTokenStream(lexer));
        final SearchListenerImpl listener = new SearchListenerImpl();

        parser.addParseListener(listener);

        final List<String> errors = new ArrayList<>();
        final BaseErrorListener errorListener = new BaseErrorListener() {
            public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
                errors.add("line " + line + ":" + charPositionInLine + " " + msg);
            }
        };
        parser.addErrorListener(errorListener);
        parser.searchExpression();
        return errors.isEmpty() ? listener.getSearchExpression() : null;
    }

}
