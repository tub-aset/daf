package de.jpwinkler.daf.fap5.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;

public class DoorsModuleMap {

    private final Map<List<String>, Map<String, CodeBeamerModel>> models = new HashMap<>();

    public void put(final CodeBeamerModel model) {
        final List<String> key1 = buildKey(model.getPath(), model.getName());

        if (models.containsKey(key1)) {
            models.get(key1).put(model.getView(), model);
        } else {
            final Map<String, CodeBeamerModel> modelMap = new HashMap<>();
            modelMap.put(model.getView(), model);
            models.put(key1, modelMap);
        }
    }

    private List<String> buildKey(final String path, final String name) {
        final List<String> key = new ArrayList<>();
        key.addAll(Arrays.asList(path.split("/")));
        key.add(name);
        return key.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    public CodeBeamerModel get(final String path, final String name, final String view) {
        final Map<String, CodeBeamerModel> modelMap = get(path, name);
        if (modelMap != null) {
            return modelMap.get(view);
        } else {
            return null;
        }
    }

    public Map<String, CodeBeamerModel> get(final String path, final String name) {
        final List<String> key = buildKey(path, name);
        while (models.get(key) == null && key.size() > 1) {
            key.remove(0);
        }
        return models.get(key);
    }

}
