package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiWordMatcher<T> {

    private final Map<String, Map<String, T>> patternGroups = new HashMap<>();
    private final Map<String, String> commonSuffixes = new HashMap<>();

    public String getCommonSuffix(String group) {
        final Map<String, T> patterns = patternGroups.get(group);
        if (patterns == null) {
            return null;
        }
        String result = null;
        for (final String word : patterns.keySet()) {
            if (result == null) {
                result = word;
            } else {

                for (int i = Math.min(word.length(), result.length()); i >= 0; i--) {
                    if (word.substring(word.length() - i).equals(result.substring(result.length() - i))) {
                        result = result.substring(result.length() - i);
                        break;
                    }
                }

            }
        }

        return result;
    }

    public void addMatch(String group, String word, T marker) {
        if (!patternGroups.containsKey(group)) {
            patternGroups.put(group, new HashMap<>());
        }

        patternGroups.get(group).put(word, marker);

        commonSuffixes.put(group, getCommonSuffix(group));

    }

    public List<T> matchWord(String word) {
        final List<T> result = new ArrayList<>();

        for (final Entry<String, Map<String, T>> patternGroupEntry : patternGroups.entrySet()) {

            final Map<String, T> patternGroup = patternGroupEntry.getValue();
            final String commonSuffix = commonSuffixes.get(patternGroupEntry.getKey());

            for (final Entry<String, T> patternEntry : patternGroup.entrySet()) {
                final String prefix = patternEntry.getKey().substring(0, patternEntry.getKey().length() - commonSuffix.length());

                final Matcher matcher = Pattern.compile(String.format("%s.*%s", prefix.toLowerCase(), commonSuffix.toLowerCase())).matcher(word.toLowerCase());

                if (matcher.find()) {
                    result.add(patternEntry.getValue());
                }

            }

        }
        return result;
    }
}
