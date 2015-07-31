package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class ArrayVariable extends Variable {

    private final List<String> items = new ArrayList<>();

    public List<String> getItems() {
        return items;
    }

}
