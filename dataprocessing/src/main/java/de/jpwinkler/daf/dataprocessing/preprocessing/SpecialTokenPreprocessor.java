package de.jpwinkler.daf.dataprocessing.preprocessing;

import java.util.regex.Pattern;

public class SpecialTokenPreprocessor extends ObjectTextPreprocessor {

    private static final Pattern TELEPHONE_NUMBER_PATTERN = Pattern.compile("\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("([a-zA-Z0-9.\\-_]+)@([a-zA-Z0-9]+).([a-z]+)");
    private static final Pattern SIGNAL_PATTERN = Pattern.compile("([a-zA-Z0-9]+[_])+([a-zA-Z0-9]+)");
    private static final Pattern DOORS_ID_PATTERN = Pattern.compile("([A-Za-z_-]+)-([0-9]+)");
    private static final Pattern NUMBER_WITH_UNIT_PATTERN = Pattern.compile("([0-9]+)([.,][0-9]+)?([°a-zA-Z]+)");
    private static final Pattern NUMBER_WITH_PREFIX_PATTERN = Pattern.compile("([a-zA-Z]+)([0-9]+)([.,][0-9]+)?");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("([0-9]+)([.,][0-9]+)?");
    private static final Pattern CHAPTER_PATTERN = Pattern.compile("([0-9]+)([.][0-9]+){2,}");

    @Override
    protected String preprocessString(final String string) {
        String temp = string;
        temp = TELEPHONE_NUMBER_PATTERN.matcher(temp).replaceAll("\\$\\$TELEPHONENUMBER\\$\\$");
        temp = DOORS_ID_PATTERN.matcher(temp).replaceAll("\\$\\$DOORSID\\$\\$");
        temp = SIGNAL_PATTERN.matcher(temp).replaceAll("\\$\\$SIGNAL\\$\\$");
        temp = EMAIL_PATTERN.matcher(temp).replaceAll("\\$\\$EMAIL\\$\\$");
        temp = NUMBER_WITH_PREFIX_PATTERN.matcher(temp).replaceAll("\\$\\$NUMBERwithprefix\\$\\$");
        temp = NUMBER_WITH_UNIT_PATTERN.matcher(temp).replaceAll("\\$\\$NUMBERwithunit\\$\\$");
        temp = CHAPTER_PATTERN.matcher(temp).replaceAll("\\$\\$CHAPTER\\$\\$");
        temp = NUMBER_PATTERN.matcher(temp).replaceAll("\\$\\$NUMBER\\$\\$");
        return temp;
    }

}
