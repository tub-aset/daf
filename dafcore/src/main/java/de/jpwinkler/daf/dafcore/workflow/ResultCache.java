package de.jpwinkler.daf.dafcore.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.workflowdsl.Step;

public class ResultCache {

    private final Map<ResultCacheKey, List<ModelObject>> cache = new HashMap<>();

    public void cacheResult(final Step step, final Map<String, Object> variables, final List<ModelObject> result) {
        cache.put(new ResultCacheKey(step, variables), result);
    }

    public List<ModelObject> findResult(final Step step, final Map<String, Object> variables) {
        return cache.get(new ResultCacheKey(step, variables));
    }

    public void clear() {
        cache.clear();
    }
}
