package de.jpwinkler.daf.csveditor.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class ViewModel {

    public ViewModel(String name) {
        this.name = name;
    }

    private String name;
    private final List<ColumnDefinition> displayedColumns = new ArrayList<>();
    private final Set<DoorsObject> visibleObjects = new HashSet<>();
    private boolean displayRemainingColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnDefinition> getDisplayedColumns() {
        return displayedColumns;
    }

    public Set<DoorsObject> getFilteredObjects() {
        return visibleObjects;
    }

    public boolean isDisplayRemainingColumns() {
        return displayRemainingColumns;
    }

    public void setDisplayRemainingColumns(boolean displayRemainingColumns) {
        this.displayRemainingColumns = displayRemainingColumns;
    }

}
