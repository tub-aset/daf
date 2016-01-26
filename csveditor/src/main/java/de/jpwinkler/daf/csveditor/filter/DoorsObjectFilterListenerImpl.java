package de.jpwinkler.daf.csveditor.filter;

import java.util.Deque;
import java.util.LinkedList;

import org.apache.commons.lang.StringEscapeUtils;

import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.AndFilterExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.AttributeExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.AttributeMissingExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.AttributeRegexpExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.BracketFilterExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.NotFilterExpressionContext;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilterParser.OrFilterExpressionContext;

public class DoorsObjectFilterListenerImpl extends DoorsObjectFilterBaseListener {

    private final Deque<DoorsObjectFilter> filterStack = new LinkedList<>();

    private String unescapeString(final String string) {
        return StringEscapeUtils.unescapeJava(string.substring(1, string.length() - 1));
    }

    @Override
    public void exitAndFilterExpression(final AndFilterExpressionContext ctx) {
        final DoorsObjectFilter f2 = filterStack.pop();
        final DoorsObjectFilter f1 = filterStack.pop();

        filterStack.push(new CompositeAndFilter(f1, f2));
    }

    @Override
    public void exitAttributeExpression(final AttributeExpressionContext ctx) {
        if (ctx.attributeName != null) {
            filterStack.push(new AttributeFilter(unescapeString(ctx.attributeName.getText()), unescapeString(ctx.attributeValue.getText()), false, false));
        } else {
            filterStack.push(new ObjectTextAndHeadingFilter(unescapeString(ctx.attributeValue.getText()), false, false));
        }
    }

    @Override
    public void exitAttributeMissingExpression(final AttributeMissingExpressionContext ctx) {
        filterStack.push(new AttributeMissingFilter(unescapeString(ctx.attributeName.getText())));
    }

    @Override
    public void exitAttributeRegexpExpression(final AttributeRegexpExpressionContext ctx) {
        if (ctx.attributeName != null) {
            filterStack.push(new AttributeFilter(unescapeString(ctx.attributeName.getText()), unescapeString(ctx.regexp.getText()), false, true));
        } else {
            filterStack.push(new ObjectTextAndHeadingFilter(unescapeString(ctx.regexp.getText()), false, true));
        }

    }

    @Override
    public void exitBracketFilterExpression(final BracketFilterExpressionContext ctx) {
        // TODO: nothing to be done!!!!! i guess?
    }

    @Override
    public void exitNotFilterExpression(final NotFilterExpressionContext ctx) {
        filterStack.push(new NotFilter(filterStack.pop()));
    }

    @Override
    public void exitOrFilterExpression(final OrFilterExpressionContext ctx) {
        final DoorsObjectFilter f2 = filterStack.pop();
        final DoorsObjectFilter f1 = filterStack.pop();

        filterStack.push(new CompositeOrFilter(f1, f2));
    }

    public DoorsObjectFilter getFilter() {
        return filterStack.peek();
    }
}
