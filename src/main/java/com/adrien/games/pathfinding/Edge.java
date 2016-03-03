package com.adrien.games.pathfinding;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

/**
 * Class representing an edge of a graph.
 */
public class Edge {

    private final String id;
    private Integer cost;

    public Edge(String id, Integer cost) {
        this.id = Objects.requireNonNull(id);
        this.cost = Objects.requireNonNull(cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return new EqualsBuilder().append(id, edge.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = Objects.requireNonNull(cost);
    }
}
