package com.adrien.games.pathfinding.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Class representing a position in a path.
 */
public class Position {

    private Integer x;
    private Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Compute the manhattan distance between this and another position.
     * @param other An other position.
     * @return The distance between the positions.
     */
    public Double computeManhattanDistance(Position other) {
        return (double)Math.abs(other.getY() - getY()) + Math.abs(other.getX() - getX());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return new EqualsBuilder()
                .append(x, position.x)
                .append(y, position.y)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(x)
                .append(y)
                .toHashCode();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
