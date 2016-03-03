package com.adrien.games.pathfinding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class purpose is to find the shortest way between to cells of a grid.
 * Each cell has a weight which represents the cost of moving on that case.
 * The cells whose weight is equal to constant value UNREACHABLE are avoided and cannot
 * be part of the resulting path.
 */
public class PathFinder {

    public Path findPath(Node start, Node goal) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(goal);
        Path path = new Path();
        NavigableSet<Node> openedList = new TreeSet<>((node1, node2) -> node1.getScore() - node2.getScore() <= 0 ? -1 : 1);
        openedList.add(start);
        Map<String, Node> closedList = new HashMap<>();
        while (!openedList.isEmpty()) {
            Node currentNode = openedList.pollFirst();
            if(currentNode.equals(goal)){
                path = reconstructPath(currentNode);
                break;
            }
            Node currentNodeFromClosedList = closedList.get(currentNode.getId());
            if(Objects.isNull(currentNodeFromClosedList) || currentNode.getScore() < currentNodeFromClosedList.getScore()) {
                closedList.put(currentNode.getId(), currentNode);
                openedList.addAll(currentNode.getNeighbors().entrySet().stream()
                    .map(entry ->
                            new Node(entry.getKey().getId(), entry.getKey().getX(), entry.getKey().getY(), entry.getValue() + currentNode.getCost(),
                                    entry.getKey().getDistance(goal), new HashMap<>(entry.getKey().getNeighbors()), currentNode))
                    .collect(Collectors.toSet()));
            }
        }
        return Objects.isNull(path) ? new Path() : path;
    }

    /**
     * Reconstruct a path from the goal node
     * @param goal
     * @return
     */
    private Path reconstructPath(Node goal) {
        Path path = new Path();
        List<Node> invertedReconstructedPath = new ArrayList<>();
        Node previous = goal;
        while (Objects.nonNull(previous.getPreviousNode())) {
            invertedReconstructedPath.add(previous);
            previous = previous.getPreviousNode();
        }
        invertedReconstructedPath.add(previous);
        Collections.reverse(invertedReconstructedPath);
        invertedReconstructedPath.stream().forEach(path::addNode);
        return path;
    }


}
