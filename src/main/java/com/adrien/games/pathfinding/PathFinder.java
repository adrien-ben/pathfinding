package com.adrien.games.pathfinding;

import com.adrien.games.pathfinding.graph.Graph;
import com.adrien.games.pathfinding.graph.Vertex;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class purpose is to find the shortest way between to vertices of a graph.
 */
public class PathFinder {

    public Path findPath(Graph graph, Vertex start, Vertex goal) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(start);
        Objects.requireNonNull(goal);
        Path path = new Path();
        NavigableSet<Node> openedList = new TreeSet<>((node1, node2) -> node1.getScore() - node2.getScore() <= 0 ? -1 : 1);
        openedList.add(new Node(start.getId(), 0, 0, null, start));
        Map<String, Node> closedList = new HashMap<>();
        while (!openedList.isEmpty()) {
            Node currentNode = openedList.pollFirst();
            if(currentNode.getVertex().equals(goal)){
                reconstructPath(currentNode).stream().forEach(path::addPosition);
                break;
            }
            Node currentNodeFromClosedList = closedList.get(currentNode.getId());
            if(Objects.isNull(currentNodeFromClosedList) || currentNode.getScore() < currentNodeFromClosedList.getScore()) {
                closedList.put(currentNode.getId(), currentNode);
                openedList.addAll(graph.getNeighbors(currentNode.getVertex()).entrySet().stream()
                        .map(entry ->
                                new Node(
                                        entry.getKey().getId(),
                                        currentNode.getCost() + entry.getValue().getCost(),
                                        computeDistance(entry.getKey(), goal),
                                        currentNode,
                                        entry.getKey()))
                        .collect(Collectors.toSet()));
            }
        }
        return path;
    }

    private List<Position> reconstructPath(Node last) {
        List<Position> positions = new ArrayList<>();
        Node next = last;
        while (!Objects.isNull(next)) {
            positions.add(new Position(next.getVertex().getX(), next.getVertex().getY()));
            next = next.getPrevious();
        }
        Collections.reverse(positions);
        return positions;
     }

    /**
     * Compute the distance between two vertices.
     * @param vertex0 A vertex.
     * @param vertex1 Another vertex.
     * @return The distance between the two vertices.
     */
    private Integer computeDistance(Vertex vertex0, Vertex vertex1) {
        return Math.abs(vertex1.getX() - vertex0.getX()) + Math.abs(vertex1.getY() - vertex0.getY());
    }

    private static class Node {
        private final String id;
        private Integer cost;
        private Integer heuristic;
        private Node previous;
        private final Vertex vertex;

        public Node(String id, Integer cost, Integer heuristic, Node previous, Vertex vertex) {
            this.id = Objects.requireNonNull(id);
            this.cost = Objects.requireNonNull(cost);
            this.heuristic = Objects.requireNonNull(heuristic);
            this.previous = previous;
            this.vertex = Objects.requireNonNull(vertex);
        }

        public Integer getScore() {
            return cost + heuristic;
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

        public Integer getHeuristic() {
            return heuristic;
        }

        public void setHeuristic(Integer heuristic) {
            this.heuristic = Objects.requireNonNull(heuristic);
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = Objects.requireNonNull(previous);
        }

        public Vertex getVertex() {
            return vertex;
        }
    }


}
