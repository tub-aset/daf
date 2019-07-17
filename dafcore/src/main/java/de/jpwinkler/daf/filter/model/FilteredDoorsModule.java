/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.filter.model;

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
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
class FilteredDoorsModule extends FilteredDoorsTreeNode<DoorsModule> implements DoorsModule {

    FilteredDoorsModule(DoorsModule self, Predicate<DoorsTreeNode> filter, WeakHashMap<DoorsTreeNode, FilteredDoorsTreeNode<?>> nodeMap) {
        super(self, filter, nodeMap);
    }

    @Override
    public String getView() {
        return self.getView();
    }

    @Override
    public List<String> getObjectAttributes() {
        return self.getObjectAttributes();
    }

    @Override
    public CompletableFuture<List<String>> getObjectAttributesAsync(BackgroundTaskExecutor executor) {
        return self.getObjectAttributesAsync(executor);
    }

    @Override
    public void setObjectAttributes(List<String> attrs) {
        self.setObjectAttributes(attrs);
    }

}
