package de.jpwinkler.daf.documenttagging.doors.preprocessing;

import java.util.Arrays;
import java.util.List;

public class SpecialCharacterRemovalPreprocessor extends ObjectTextPreprocessor {

    private final List<Character> allowedCharacters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', 'ä', 'ö', 'ü', 'ß');

    @Override
    protected String preprocessString(final String string) {
        String result = "";
        for (final char c : string.toCharArray()) {
            if (allowedCharacters.contains(c)) {
                result += c;
            } else {
                result += ' ';
            }
        }
        result = result.replaceAll("  ", " ");
        return result;
    }

}
