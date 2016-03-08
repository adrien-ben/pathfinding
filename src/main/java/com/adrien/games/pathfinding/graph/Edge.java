package com.adrien.games.pathfinding.graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

/**
 * Class representing an edge of a graph.
 */
public class Edge {

    private Double cost;

    public Edge(Double cost) {
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

        return new EqualsBuilder().append(cost, edge.cost).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(cost).toHashCode();
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = Objects.requireNonNull(cost);
    }
}
