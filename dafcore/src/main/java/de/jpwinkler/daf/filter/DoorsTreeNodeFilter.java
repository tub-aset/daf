package de.jpwinkler.daf.filter;

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
import de.jpwinkler.daf.filter.objects.DoorsTreeNodeFilterBaseListener;
import de.jpwinkler.daf.filter.objects.DoorsTreeNodeFilterLexer;
import de.jpwinkler.daf.filter.objects.DoorsTreeNodeFilterParser;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Predicate;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public abstract class DoorsTreeNodeFilter implements Predicate<DoorsTreeNode> {

    /**
     * Create a DoorsTreeNodeFilter
     *
     * @param filter Filter expression as described in the ANTLR grammar
     * @param patternFlags Flags passed to java.util.regex.Pattern::compile.
     * @return
     */
    public static DoorsTreeNodeFilter compile(final String filter, int patternFlags) {
        final DoorsTreeNodeFilterLexer lexer = new DoorsTreeNodeFilterLexer(CharStreams.fromString(filter));
        final DoorsTreeNodeFilterParser parser = new DoorsTreeNodeFilterParser(new CommonTokenStream(lexer));
        final DoorsTreeNodeFilterListener listener = new DoorsTreeNodeFilterListener(patternFlags);

        parser.addParseListener(listener);
        parser.filterExpression();
        return listener.getFilter();

    }

    private static class DoorsTreeNodeFilterListener extends DoorsTreeNodeFilterBaseListener {

        private final Deque<DoorsTreeNodeFilter> filterStack = new LinkedList<>();
        private final int patternFlags;

        public DoorsTreeNodeFilterListener(int patternFlags) {
            this.patternFlags = patternFlags;
        }

        @Override
        public void exitBracketFilterExpression(final DoorsTreeNodeFilterParser.BracketFilterExpressionContext ctx) {
        }

        @Override
        public void exitNotFilterExpression(final DoorsTreeNodeFilterParser.NotFilterExpressionContext ctx) {
            filterStack.push(new NotFilter(filterStack.pop()));
        }

        @Override
        public void exitOrFilterExpression(final DoorsTreeNodeFilterParser.OrFilterExpressionContext ctx) {
            final DoorsTreeNodeFilter f2 = filterStack.pop();
            final DoorsTreeNodeFilter f1 = filterStack.pop();

            filterStack.push(new OrFilter(f1, f2));
        }

        @Override
        public void exitAndFilterExpression(final DoorsTreeNodeFilterParser.AndFilterExpressionContext ctx) {
            final DoorsTreeNodeFilter f2 = filterStack.pop();
            final DoorsTreeNodeFilter f1 = filterStack.pop();

            filterStack.push(new AndFilter(f1, f2));
        }

        @Override
        public void exitEquivalenceExpression(DoorsTreeNodeFilterParser.EquivalenceExpressionContext ctx) {
            final DoorsTreeNodeFilter f2 = filterStack.pop();
            final DoorsTreeNodeFilter f1 = filterStack.pop();

            filterStack.push(new EquivalenceFilter(f1, f2));
        }

        @Override
        public void exitImplicationExpression(DoorsTreeNodeFilterParser.ImplicationExpressionContext ctx) {
            final DoorsTreeNodeFilter f2 = filterStack.pop();
            final DoorsTreeNodeFilter f1 = filterStack.pop();

            filterStack.push(new ImplicationFilter(f1, f2));
        }

        @Override
        public void exitExclusiveOrFilterExpression(DoorsTreeNodeFilterParser.ExclusiveOrFilterExpressionContext ctx) {
            final DoorsTreeNodeFilter f2 = filterStack.pop();
            final DoorsTreeNodeFilter f1 = filterStack.pop();

            filterStack.push(new ExclusiveOrFilter(f1, f2));
        }

        @Override
        public void exitAttributeLikeExpression(final DoorsTreeNodeFilterParser.AttributeLikeExpressionContext ctx) {
            filterStack.push(new AttributeFilter(ctx.attributeName.getText(), new ValueMatcher(ctx.attributeValue.getText(), false, -1)));
        }

        @Override
        public void exitAttributeIsExpression(final DoorsTreeNodeFilterParser.AttributeIsExpressionContext ctx) {
            filterStack.push(new AttributeFilter(ctx.attributeName.getText(), new ValueMatcher(ctx.attributeValue.getText(), true, -1)));
        }

        @Override
        public void exitAttributeMissingExpression(final DoorsTreeNodeFilterParser.AttributeMissingExpressionContext ctx) {
            filterStack.push(new AttributeFilter(ctx.attributeName.getText(), null));
        }

        @Override
        public void exitAttributeRegexpExpression(final DoorsTreeNodeFilterParser.AttributeRegexpExpressionContext ctx) {
            filterStack.push(new AttributeFilter(ctx.attributeName.getText(), new ValueMatcher(ctx.regexp.getText(), false, patternFlags)));

        }

        public DoorsTreeNodeFilter getFilter() {
            return filterStack.peek();
        }
    }
}
