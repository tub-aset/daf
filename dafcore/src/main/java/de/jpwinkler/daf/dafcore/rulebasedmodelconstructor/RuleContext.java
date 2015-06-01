package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.TransformationException;

public class RuleContext {

    private RuleBasedModelConstructor modelConstructor;
    private DoorsModule module;
    private ModelObject rootModelObject;

    private final List<Rule> rulePool = new ArrayList<>();
    private final List<Rule> activeRules = new ArrayList<>();
    private final List<Rule> completedRules = new ArrayList<>();
    private int stage;

    private final Map<DoorsObject, Map<String, Marker>> markers = new HashMap<>();
    private final Map<String, Set<DoorsObject>> markedObjects = new HashMap<>();

    public RuleBasedModelConstructor getModelConstructor() {
        return modelConstructor;
    }

    public List<Rule> getActiveRules() {
        return activeRules;
    }

    public List<Rule> getRulePool() {
        return rulePool;
    }

    public List<Rule> getCompletedRules() {
        return completedRules;
    }

    public DoorsModule getModule() {
        return module;
    }

    public ModelObject getRootModelObject() {
        return rootModelObject;
    }

    public void setModule(final DoorsModule module) {
        this.module = module;
    }

    public void setRootModelObject(final ModelObject rootModelObject) {
        this.rootModelObject = rootModelObject;
    }

    public void addMarker(final DoorsObject object, final Marker marker) {
        if (!markers.containsKey(object)) {
            markers.put(object, new HashMap<>());
        }
        if (!markedObjects.containsKey(marker.getType())) {
            markedObjects.put(marker.getType(), new HashSet<>());
        }
        markers.get(object).put(marker.getType(), marker);
        markedObjects.get(marker.getType()).add(object);
    }

    public void removeMarker(final DoorsObject object, final Marker marker) {
        if (markers.containsKey(object)) {
            markers.get(object).remove(marker.getType());
        }
        if (markedObjects.containsKey(marker.getType())) {
            markedObjects.get(marker.getType()).remove(object);
        }
    }

    public Collection<Marker> getMarkers(final DoorsObject object) {
        if (markers.containsKey(object)) {
            return markers.get(object).values();
        } else {
            return Collections.emptySet();
        }
    }

    public Collection<Marker> getMarkers(final String markerType) {
        final List<Marker> result = new ArrayList<>();
        for (final DoorsObject object : getMarkedObjects(markerType)) {
            result.add(getMarker(object, markerType));
        }
        return result;
    }

    public Set<String> getUsedMarkerTypes() {
        return markedObjects.keySet();
    }

    public Marker getMarker(final DoorsObject object, final String type) {
        if (markers.containsKey(object)) {
            return markers.get(object).get(type);
        } else {
            return null;
        }
    }

    public Set<DoorsObject> getMarkedObjects(final String markerType) {
        if (markedObjects.containsKey(markerType)) {
            return markedObjects.get(markerType);
        } else {
            return Collections.emptySet();
        }
    }

    public int getMarkerCount(final String markerType) {
        return getMarkedObjects(markerType).size();
    }

    public int getMarkerCount(final DoorsObject object) {
        return getMarkers(object).size();
    }

    public void init(final RuleBasedModelConstructor modelConstructor, final DoorsModule module) throws TransformationException {
        this.modelConstructor = modelConstructor;
        this.module = module;

        rulePool.clear();
        completedRules.clear();
        activeRules.clear();

        rootModelObject = modelConstructor.createRootModelObject();

        for (final Class<? extends Rule> clazz : modelConstructor.getRules()) {
            try {
                rulePool.add(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new TransformationException(String.format("Could not create rule instance: %s.", clazz.getName()), e);
            }
        }

        stage = 0;
    }

    /**
     * Starts a new stage. The preconditions of all remaining rules are checked.
     * Rules whose preconditions are met are added to the set of rules of the
     * new stage.
     *
     * @return True, when the set of active rules is not empty. False otherwise.
     */
    public boolean beginStage() {
        stage++;
        for (final Rule rule : rulePool) {
            if (rule.getPrecondition().checkPrecondition(this)) {
                activeRules.add(rule);
                rule.prepare(this);
            }
        }
        rulePool.removeAll(activeRules);
        return !activeRules.isEmpty();
    }

    /**
     * Ends the current stage.
     */
    public void endStage() {
        for (final Rule rule : activeRules) {
            rule.finish(this);
        }
        completedRules.addAll(activeRules);
        activeRules.clear();
    }

    /**
     * Returns current stage number.
     *
     * @return
     */
    public int getStage() {
        return stage;
    }

}
