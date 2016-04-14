package de.jpwinkler.daf.doorsdb.search;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import de.jpwinkler.daf.doorsdb.DBSearchLexer;
import de.jpwinkler.daf.doorsdb.DBSearchParser;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public abstract class DBSearchExpression {

    public abstract boolean matches(DBModule module);

    public static DBSearchExpression compile(final String filter) {

        final DBSearchLexer lexer = new DBSearchLexer(new ANTLRInputStream(filter));
        final DBSearchParser parser = new DBSearchParser(new CommonTokenStream(lexer));
        final DBSearchListenerImpl listener = new DBSearchListenerImpl();

        parser.addParseListener(listener);
        parser.searchExpression();
        return listener.getSearchExpression();

    }

}
