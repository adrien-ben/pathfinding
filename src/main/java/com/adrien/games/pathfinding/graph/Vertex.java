package com.adrien.games.pathfinding.graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

/**
 * Class representing a vertex of a graph.
 */
public class Vertex {

    private final String id;
    private Integer x;
    private Integer y;

    public Vertex(String id, Integer x, Integer y) {
        this.id = Objects.requireNonNull(id);
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return new EqualsBuilder().append(id, vertex.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id='" + id + '\'' +
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
}
