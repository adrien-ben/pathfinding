package com.adrien.games.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a path.
 */
public class Path {

    private final List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Integer getSize() {
        return nodes.size();
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
