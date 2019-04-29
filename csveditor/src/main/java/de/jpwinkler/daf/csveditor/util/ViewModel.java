package de.jpwinkler.daf.csveditor.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class ViewModel {

    private final List<ColumnDefinition> displayedColumns = new ArrayList<>();

    private final Set<DoorsObject> visibleObjects = new HashSet<>();

    public Set<DoorsObject> getFilteredObjects() {
        return visibleObjects;
    }

    public List<ColumnDefinition> getDisplayedColumns() {
        return displayedColumns;
    }

}
