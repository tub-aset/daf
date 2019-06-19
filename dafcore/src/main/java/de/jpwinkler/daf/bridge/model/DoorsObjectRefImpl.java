/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.Link;
import de.jpwinkler.daf.model.ResolvedLink;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fwiesweg
 */
class DoorsObjectRefImpl extends DoorsTreeNodeRefImpl implements DoorsObject {

    public DoorsObjectRefImpl(DoorsApplication doorsApplicationImpl, DoorsTreeNode parent) {
        super(doorsApplicationImpl, DoorsItemType.OBJECT, parent, null);
    }

    private final Map<String, String> attributes = new HashMap<>();

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
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
        return DoorsAttributes.OBJECT_LEVEL.getValue(Integer.class, this);
    }

    @Override
    public void setObjectLevel(int value) {
        throw new UnsupportedOperationException("Not supported");
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

}
