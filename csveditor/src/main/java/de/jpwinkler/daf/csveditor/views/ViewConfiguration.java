/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.csveditor.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public class ViewConfiguration {

    public ViewConfiguration(String name, String... columns) {
        this.name = name;
        this.columns.addAll(Arrays.asList(columns));
    }

    public List<String> getColumns() {
        return columns;
    }
    
    public String getName() {
        return name;
    }
    

    private final String name;
    private final List<String> columns = new ArrayList<>();
}
