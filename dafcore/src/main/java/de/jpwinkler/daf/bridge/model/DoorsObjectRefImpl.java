package de.jpwinkler.daf.bridge.model;

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
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.Link;
import de.jpwinkler.daf.model.ResolvedLink;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author fwiesweg
 */
class DoorsObjectRefImpl extends DoorsTreeNodeRefImpl implements DoorsObject {

    public DoorsObjectRefImpl(DoorsApplication DoorsApplication, DoorsTreeNode parent) {
        super(DoorsApplication, DoorsItemType.OBJECT, parent, null);
    }

    private final Map<String, String> attributes = Collections.synchronizedMap(new HashMap<>());
    private final List<DoorsTreeNode> dumbChildrenHolder = new ArrayList<>();

    @Override
    public CompletableFuture<DoorsTreeNode> getChildAsync(BackgroundTaskExecutor executor, String name) {
        return super.getChildAsync(executor, name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompletableFuture<List<DoorsTreeNode>> getChildrenAsync(BackgroundTaskExecutor executor) {
        return CompletableFuture.completedFuture(this.dumbChildrenHolder);
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public CompletableFuture<Map<String, String>> getAttributesAsync(BackgroundTaskExecutor executor) {
        return CompletableFuture.completedFuture(attributes);
    }

    @Override
    public String getObjectIdentifier() {
        return DoorsAttributes.OBJECT_IDENTIFIER.getValue(String.class, this);
    }

    @Override
    public void setObjectIdentifier(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public int getObjectLevel() {
        return this.getParent() instanceof DoorsObject ? ((DoorsObject) this.getParent()).getObjectLevel() + 1 : 1;
    }

    @Override
    public String getObjectNumber() {
        return DoorsAttributes.OBJECT_NUMBER.getValue(String.class, this);
    }

    @Override
    public void setObjectNumber(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public int getAbsoluteNumber() {
        return DoorsAttributes.ABSOLUTE_NUMBER.getValue(Integer.class, this);
    }

    @Override
    public void setAbsoluteNumber(int value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getObjectText() {
        return DoorsAttributes.OBJECT_TEXT.getValue(String.class, this);
    }

    @Override
    public void setObjectText(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getObjectShortText() {
        return DoorsAttributes.OBJECT_SHORT_TEXT.getValue(String.class, this);
    }

    @Override
    public void setObjectShortText(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getObjectHeading() {
        return DoorsAttributes.OBJECT_HEADING.getValue(String.class, this);
    }

    @Override
    public void setObjectHeading(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getText() {
        return isHeading() ? getObjectHeading() : getObjectText();
    }

    @Override
    public void setText(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<Link> getOutgoingLinks() {
        return Collections.emptyList();
    }

    @Override
    public List<ResolvedLink> getIncomingLinks() {
        return Collections.emptyList();
    }

    @Override
    public boolean isHeading() {
        return getObjectHeading() != null && !getObjectHeading().isEmpty();
    }

    @Override
    public boolean isChildrenLoaded() {
        return true;
    }

}
