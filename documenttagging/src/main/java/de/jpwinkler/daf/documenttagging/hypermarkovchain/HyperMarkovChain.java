package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.ArrayList;
import java.util.List;

public class HyperMarkovChain<T> {

    private final List<Node<T>> nodes = new ArrayList<>();

    public List<Node<T>> getNodes() {
        return nodes;
    }

}
