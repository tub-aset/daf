package de.jpwinkler.daf.csveditor.filter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class DoorsObjectFilter {

    public abstract boolean checkObject(DoorsObject object);

    public static DoorsObjectFilter compile(final String filter) {

        final DoorsObjectFilterLexer lexer = new DoorsObjectFilterLexer(new ANTLRInputStream(filter));
        final DoorsObjectFilterParser parser = new DoorsObjectFilterParser(new CommonTokenStream(lexer));
        final DoorsObjectFilterListenerImpl listener = new DoorsObjectFilterListenerImpl();

        parser.addParseListener(listener);
        parser.filterExpression();
        return listener.getFilter();

    }
}
