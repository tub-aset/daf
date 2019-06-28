/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.filter;

import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public class SimpleFilter implements Predicate<DoorsTreeNode> {

    private final ValueMatcher valueMatcher;

    SimpleFilter(String textFilter, boolean caseSensitive) {
        this.valueMatcher = new ValueMatcher(textFilter, false, caseSensitive, -1);
    }

    @Override
    public boolean test(DoorsTreeNode t) {
        return t != null && valueMatcher.test((t instanceof DoorsObject) ? ((DoorsObject) t).getText() : t.getFullName());
    }

}
