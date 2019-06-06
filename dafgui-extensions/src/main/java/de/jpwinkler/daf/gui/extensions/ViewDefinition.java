package de.jpwinkler.daf.gui.extensions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewDefinition implements Serializable {

    public ViewDefinition(String name) {
        this.name = name;
    }

    private String name;
    private final List<ColumnDefinition> columns = new ArrayList<>();
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

    public static class ColumnDefinition implements Serializable {

        public ColumnDefinition(String title) {
            this.title = title;
            this.width = 150;
            this.visible = true;
        }

        private String attributeName;

        private String title;

        private double width;

        private boolean visible;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(final String attributeName) {
            this.attributeName = attributeName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(final double width) {
            this.width = width;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(final boolean visible) {
            this.visible = visible;
        }

        @Override
        public String toString() {
            return title;
        }

    }

}
