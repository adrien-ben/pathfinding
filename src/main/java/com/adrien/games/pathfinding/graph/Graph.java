package com.adrien.games.pathfinding.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a graph.
 */
public class Graph<T> {

    private final Map<T, Map<T, Edge>> graph = new HashMap<>();

    /**
     * Adds a vertex to the graph.
     * The vertex must not be already present in graph.
     * @param vertex The vertex to add.
     */
    public void addVertex(T vertex) {
        Objects.requireNonNull(vertex);
        if(containsVertex(vertex)) {
            throw new IllegalArgumentException("Impossible to add vertex '" + vertex + "'. Vertex already in graph.");
        }
        graph.put(vertex, new HashMap<>());
    }

    /**
     * Adds an edge between two vertices of the graph. An edge is unidirectional.
     * Both vertices must be present in graph.
     * Multiple edges between two vertices are not supported.
     * @param source The source vertex.
     * @param target The target vertex
     * @param edge The edge to add.
     */
    public void addEdge(T source, T target, Edge edge) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Objects.requireNonNull(edge);
        if(!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Impossible to add edge '" + edge + "' between vertices '" + source + "' and '" + target + "'. Both vertices must be in the graph.");
        }
        if(hasEdge(source, target)) {
            throw new IllegalArgumentException("Impossible to add edge '" + edge + "' between vertices '" + source + "' and '" + target + "''. Multiple edges are not supported.");
        }
        graph.get(source).put(target, edge);
    }

    /**
     * Removes a vertex from the graph.
     * The vertex must be in graph.
     * @param vertex The vertex to remove.
     */
    public void removeVertex(T vertex) {
        Objects.requireNonNull(vertex);
        if(!containsVertex(vertex)) {
            throw new IllegalArgumentException("Impossible to remove vertex '" + vertex + "'. Vertex is not in graph.");
        }
        graph.remove(vertex);
        graph.values().stream().forEach(map -> map.remove(vertex));
    }

    /**
     * Removes an edge between two nodes of a graph.
     * The two vertex must be in the graph.
     * @param source The source vertex.
     * @param target The target vertex.
     */
    public void removeEdge(T source, T target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        if(!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Impossible to remove edge between vertices '" + source + "' and '" + target + "'. Both vertices must be in the graph.");
        }
        if(!hasEdge(source, target)) {
            throw new IllegalArgumentException("Impossible to remove edge between vertices '" + source + "' and '" + target + "'. The edge does not exist.");
        }
        graph.get(source).remove(target);
    }

    /**
     * Retrieves all the vertices connected to a vertex with the associated edges.
     * The vertex must be in graph.
     * @param vertex The vertex from which to get connected vertices.
     * @return A map associating the connected vertices and the edge.
     */
    public Map<T, Edge> getNeighbors(T vertex) {
        if(!containsVertex(vertex)) {
            throw new IllegalArgumentException("Impossible to find neighbors for vertex '" + vertex + "'. Vertex must be in graph.");
        }
        return graph.get(vertex);
    }

    /**
     * Checks whether or not the graph contains a vertex.
     * @param vertex The vertex to check.
     * @return True if contained, false otherwise.
     */
    public Boolean containsVertex(T vertex) {
        Objects.requireNonNull(vertex);
        return graph.containsKey(vertex);
    }

    /**
     * Checks whether or not source vertex is connected to target vertex.
     * Both vertices must be in graph.
     * @param source The source vertex.
     * @param target The target vertex.
     * @return True if connected, false otherwise.
     */
    public Boolean hasEdge(T source, T target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        if(!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Impossible to find an edge between vertices '" + source + "' and '" + target + "'. Both vertices must be in the graph.");
        }
        return graph.get(source).containsKey(target);
    }
}
