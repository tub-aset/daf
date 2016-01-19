package de.jpwinkler.daf.fap5.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;

public class DoorsModuleMap {

    private final Map<List<String>, CodeBeamerModel> models = new HashMap<>();

    public void put(final CodeBeamerModel model) {
        final List<String> key = buildKey(model.getPath() + "/" + model.getName());

        models.put(key, model);
    }

    private List<String> buildKey(final String fullName) {
        final List<String> key = new ArrayList<>();
        key.addAll(Arrays.asList(fullName.split("/")));
        return key.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    public CodeBeamerModel get(final String path, final String name) {
        return get(path + "/" + name);
    }

    public CodeBeamerModel get(final String fullName) {
        final List<String> key = buildKey(fullName);
        while (models.get(key) == null && key.size() > 1) {
            key.remove(0);
        }
        return models.get(key);
    }
}
