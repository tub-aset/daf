package de.jpwinkler.daf.csveditor.otclassification;

import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class IgnoreList {

    private final Set<String> ignoreOnceSet = new HashSet<>();
    private final Set<String> ignoreAlwaysSet = new HashSet<>();

    // TODO: save load

    private String getLocalKey(final DoorsObject object) {
        return object.getModule() + ":" + object.getObjectIdentifier();
    }

    private String getGlobalKey(final DoorsObject object) {
        return object.getText();
    }

    public synchronized boolean isIgnoredOnce(final DoorsObject object) {
        return ignoreOnceSet.contains(getLocalKey(object));
    }

    public synchronized boolean isAlwaysIgnored(final DoorsObject object) {
        return ignoreAlwaysSet.contains(getGlobalKey(object));
    }

    public synchronized void ignoreOnce(final DoorsObject object) {
        ignoreOnceSet.add(getLocalKey(object));
    }

    public synchronized void ignoreAlways(final DoorsObject object) {
        ignoreAlwaysSet.add(getGlobalKey(object));
    }

    public synchronized void unIgnore(final DoorsObject object) {
        ignoreOnceSet.remove(getLocalKey(object));
        ignoreAlwaysSet.remove(getGlobalKey(object));
    }

    public synchronized boolean isIgnored(final DoorsObject o) {
        return isIgnoredOnce(o) || isAlwaysIgnored(o);
    }

}
