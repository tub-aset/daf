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

import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.AndSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.AttributeSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.BracketSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.NameSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.NotSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.OrSearchExpressionContext;
import de.jpwinkler.daf.filter.modules.DoorsModuleFilterParser.TagSearchExpressionContext;
import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.lang3.StringEscapeUtils;

public class SearchListenerImpl extends DoorsModuleFilterBaseListener {

    private final Deque<SearchExpression> stack = new LinkedList<>();

    private String unescapeString(final String string) {
        return StringEscapeUtils.unescapeJava(string.substring(1, string.length() - 1));
    }

    @Override
    public void exitAndSearchExpression(final AndSearchExpressionContext ctx) {
        final SearchExpression e1 = stack.pop();
        final SearchExpression e2 = stack.pop();
        stack.push(new AndSearchExpression(e2, e1));
    }

    @Override
    public void exitAttributeSearchExpression(final AttributeSearchExpressionContext ctx) {
        stack.push(new AttributeSearchExpression(unescapeString(ctx.attName.getText()), unescapeString(ctx.attValue.getText())));
    }

    @Override
    public void exitBracketSearchExpression(final BracketSearchExpressionContext ctx) {
    }

    @Override
    public void exitNameSearchExpression(final NameSearchExpressionContext ctx) {
        stack.push(new NameSearchExpression(unescapeString(ctx.name.getText())));
    }

    @Override
    public void exitNotSearchExpression(final NotSearchExpressionContext ctx) {
        stack.push(new NotSearchExpression(stack.pop()));
    }

    @Override
    public void exitTagSearchExpression(final TagSearchExpressionContext ctx) {
        stack.push(new RegexTagSearchExpression(unescapeString(ctx.tag.getText())));
    }

    @Override
    public void exitOrSearchExpression(final OrSearchExpressionContext ctx) {
        final SearchExpression e1 = stack.pop();
        final SearchExpression e2 = stack.pop();
        stack.push(new OrSearchExpression(e2, e1));
    }

    public SearchExpression getSearchExpression() {
        return stack.peek();
    }

}
