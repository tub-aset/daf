package de.jpwinkler.daf.csveditor.views;

public class ColumnDefinition {

    private String attributeName;

    private String columnTitle;

    private double width;

    private boolean visible;

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return columnTitle;
    }

}
