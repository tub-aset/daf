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
    private final List<ColumnDefinition> columns = new ArrayList<>();
    private final Set<DoorsObject> filteredObjects = new HashSet<>();
    private boolean displayRemainingColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnDefinition> getColumns() {
        return columns;
    }

    public Set<DoorsObject> getFilteredObjects() {
        return filteredObjects;
    }

    public boolean isDisplayRemainingColumns() {
        return displayRemainingColumns;
    }

    public void setDisplayRemainingColumns(boolean displayRemainingColumns) {
        this.displayRemainingColumns = displayRemainingColumns;
    }

    @Override
    public String toString() {
        return name;
    }

}
