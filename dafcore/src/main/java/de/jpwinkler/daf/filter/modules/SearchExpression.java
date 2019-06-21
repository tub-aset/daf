package de.jpwinkler.daf.filter.modules;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
