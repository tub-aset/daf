package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edge<T> {

    private final List<Node<T>> sourceNodes = new ArrayList<>();

    private final Map<Node<T>, Double> weightedTargetNodes = new HashMap<>();

}
