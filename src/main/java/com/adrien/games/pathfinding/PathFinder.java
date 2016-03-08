package com.adrien.games.pathfinding;

import com.adrien.games.pathfinding.graph.Graph;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * This class purpose is to find a way between to vertices of a graph.
 */
public class PathFinder {

    /**
     * Finds a path between the vertices of a graph.
     * This method implements the A* (a star) algorithm.
     * @param graph The graph in which to find a path.
     * @param start The starting vertex.
     * @param goal The goal vertex.
     * @param heuristicFunction The heuristic function used to estimate the distance between a vertex and the goal vertex.
     * @return The path between the two vertices represented by a list of vertices. An empty list is no path could be found.
     */
    public static <T> List<T> findPath(Graph<T> graph, T start, T goal, BiFunction<T, T, Double> heuristicFunction) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(start);
        Objects.requireNonNull(goal);
        Objects.requireNonNull(heuristicFunction);
        List<T> path = new ArrayList<>();
        NavigableSet<Node<T>> openedList = new TreeSet<>((node1, node2) -> node1.getScore() - node2.getScore() <= 0 ? -1 : 1);
        openedList.add(new Node<>(0.0, 0.0, null, start));
        Map<T, Node<T>> closedList = new HashMap<>();
        while (!openedList.isEmpty()) {
            Node<T> currentNode = openedList.pollFirst();
            if(currentNode.getVertex().equals(goal)){
                reconstructPath(currentNode).stream().forEach(path::add);
                break;
            }
            Node<T> currentNodeFromClosedList = closedList.get(currentNode.getVertex());
            if(Objects.isNull(currentNodeFromClosedList) || currentNode.getScore() < currentNodeFromClosedList.getScore()) {
                closedList.put(currentNode.getVertex(), currentNode);
                openedList.addAll(
                        graph
                                .getNeighbors(currentNode.getVertex())
                                .entrySet()
                                .stream()
                                .map(entry ->
                                        new Node<>(
                                                currentNode.getCost() + entry.getValue().getCost(),
                                                heuristicFunction.apply(entry.getKey(), goal),
                                                currentNode,
                                                entry.getKey()))
                                .collect(Collectors.toSet()));
            }
        }
        return path;
    }

    /**
     * Reconstructs the path once the algorithm found one.
     * @param last The last node.
     * @return The path.
     */
    private static <T> List<T> reconstructPath(Node<T> last) {
        List<T> positions = new ArrayList<>();
        Node<T> next = last;
        while (!Objects.isNull(next)) {
            positions.add(next.getVertex());
            next = next.getPrevious();
        }
        Collections.reverse(positions);
        return positions;
    }

    /**
     * Inner class used to store visited vertices, its score, and its predecessor.
     */
    private static class Node<T> {
        private Double cost;
        private Double heuristic;
        private Node<T> previous;
        private final T vertex;

        public Node(Double cost, Double heuristic, Node<T> previous, T vertex) {
            this.cost = Objects.requireNonNull(cost);
            this.heuristic = Objects.requireNonNull(heuristic);
            this.previous = previous;
            this.vertex = Objects.requireNonNull(vertex);
        }

        public Double getScore() {
            return cost + heuristic;
        }



        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = Objects.requireNonNull(cost);
        }

        public Double getHeuristic() {
            return heuristic;
        }

        public void setHeuristic(Double heuristic) {
            this.heuristic = Objects.requireNonNull(heuristic);
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = Objects.requireNonNull(previous);
        }

        public T getVertex() {
            return vertex;
        }
    }


}
