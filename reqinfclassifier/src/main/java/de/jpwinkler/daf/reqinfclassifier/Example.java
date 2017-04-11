package de.jpwinkler.daf.reqinfclassifier;

import java.util.List;

public interface Example {

    String getText();

    boolean isHeading();

    List<String> getAttributeNames();

    String getAttributeValue(String attributeName);

    String getKey();
}
