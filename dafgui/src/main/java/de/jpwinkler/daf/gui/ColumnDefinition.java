package de.jpwinkler.daf.gui;

import java.io.Serializable;

public class ColumnDefinition implements Serializable{

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
