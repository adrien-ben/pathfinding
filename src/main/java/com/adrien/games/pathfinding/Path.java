package com.adrien.games.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a path.
 */
public class Path {

    private final List<Position> positions = new ArrayList<>();

    public void addPosition(Position position) {
        positions.add(position);
    }

    public Integer getSize() {
        return positions.size();
    }

    public List<Position> getPositions() {
        return positions;
    }
}
