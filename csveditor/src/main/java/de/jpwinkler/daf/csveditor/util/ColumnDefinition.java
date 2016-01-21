package de.jpwinkler.daf.csveditor.util;

import javafx.beans.property.DoubleProperty;

public class ColumnDefinition {

    private ColumnType columnType;

    private String attributeName;

    private String columnTitle;

    private double width;

    private boolean visible;

    public ColumnDefinition() {
    }

    public ColumnDefinition(final ColumnType columnType, final String attributeName, final String columnTitle, final double width, final boolean visible) {
        super();
        this.columnType = columnType;
        this.attributeName = attributeName;
        this.columnTitle = columnTitle;
        this.width = width;
        this.visible = visible;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(final String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(final double width) {
        this.width = width;
    }

    public DoubleProperty widthProperty() {
        return widthProperty();
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(final ColumnType columnType) {
        this.columnType = columnType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

}
