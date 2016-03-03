package com.adrien.games.pathfinding;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a node of a graph.
 */
public class Node {

    private final String id;
    private Integer x;
    private Integer y;
    private Integer cost;
    private Integer heuristic;
    private final Map<Node, Integer> neighbors;
    private Node previousNode;

    public Node(String id, Integer x, Integer y) {
        this.id = Objects.requireNonNull(id);
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
        this.cost = 0;
        this.heuristic = 0;
        this.neighbors = new HashMap<>();
    }

    public Node(String id, Integer x, Integer y, Integer cost, Integer heuristic, Map<Node, Integer> neighbors, Node previousNode) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.heuristic = heuristic;
        this.neighbors = new HashMap<>(neighbors);
        this.previousNode = previousNode;
    }

    /**
     * Gets the score of the node.
     * @return The score of the node.
     */
    public Integer getScore() {
        return cost + heuristic;
    }

    /**
     * Get the distance (absolute value) between this node and another node.
     * @param other The other node.
     * @return The distance between the two nodes.
     */
    public Integer getDistance(Node other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
    }

    /**
     * Add a neighbor node and associate a cost to reach that neighbor node.
     * A node cannot be its own neighbor and you cannot add a neighbors that is already present.
     * @param neighbor The neighbor node.
     * @param cost The access cost to that neighbor.
     */
    public void addNeighbor(Node neighbor, Integer cost) {
        Objects.requireNonNull(neighbor);
        Objects.requireNonNull(cost);
        if(neighbors.containsKey(neighbor)) {
            throw new IllegalArgumentException("Failed to add node \'" + neighbor + "\' as neighbor to node \'" + this + "\'. They already are neighbors.");
        }
        if(this.equals(neighbor)) {
            throw new IllegalArgumentException("Failed to add node \'" + this + "\' as neighbor to node \'" + this + "\'. A node cannot be its own neighbor.");
        }
        neighbors.put(neighbor, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;

        return new EqualsBuilder().append(id, node.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                ", heuristic=" + heuristic +
                ", neighbors=" + neighbors +
                '}';
    }

    public String getId() {
        return id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = Objects.requireNonNull(x);
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = Objects.requireNonNull(y);
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = Objects.requireNonNull(cost);
    }

    public Integer getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Integer heuristic) {
        this.heuristic = Objects.requireNonNull(heuristic);
    }

    public Map<Node, Integer> getNeighbors() {
        return neighbors;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
