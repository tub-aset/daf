package de.jpwinkler.daf.doorsdb.search;

import java.util.Deque;
import java.util.LinkedList;

import org.apache.commons.lang3.StringEscapeUtils;

import de.jpwinkler.daf.doorsdb.DBSearchBaseListener;
import de.jpwinkler.daf.doorsdb.DBSearchParser.AndSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.AttributeSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.BracketSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.NameSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.NotSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.OrSearchExpressionContext;
import de.jpwinkler.daf.doorsdb.DBSearchParser.TagSearchExpressionContext;

public class DBSearchListenerImpl extends DBSearchBaseListener {

    private final Deque<DBSearchExpression> stack = new LinkedList<>();

    private String unescapeString(final String string) {
        return StringEscapeUtils.unescapeJava(string.substring(1, string.length() - 1));
    }

    @Override
    public void exitAndSearchExpression(final AndSearchExpressionContext ctx) {
        final DBSearchExpression e1 = stack.pop();
        final DBSearchExpression e2 = stack.pop();
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
        final DBSearchExpression e1 = stack.pop();
        final DBSearchExpression e2 = stack.pop();
        stack.push(new OrSearchExpression(e2, e1));
    }

    public DBSearchExpression getSearchExpression() {
        return stack.peek();
    }

}
