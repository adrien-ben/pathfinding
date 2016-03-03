package com.adrien.games.pathfinding.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a graph.
 */
public class Graph {

    private final Map<String, Vertex> vertices = new HashMap<>();
    private final Map<Vertex, Map<Vertex, Edge>> connections = new HashMap<>();

    /**
     * Adds a vertex to the graph.
     * The vertex must not be already present in graph.
     * @param vertex The vertex to add.
     */
    public void addVertex(Vertex vertex) {
        Objects.requireNonNull(vertex);
        if(containsVertex(vertex)) {
            throw new IllegalArgumentException("Impossible to add vertex '" + vertex + "'. Vertex already in graph.");
        }
        vertices.put(vertex.getId(), vertex);
        connections.put(vertex, new HashMap<>());
    }

    /**
     * Adds an edge between two vertices of the graph. An edge is unidirectional.
     * Both vertices must be present in graph.
     * Multiple edges between two vertices are not supported.
     * @param source The source vertex.
     * @param target The target vertex
     * @param edge The edge to add.
     */
    public void addEdge(Vertex source, Vertex target, Edge edge) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Objects.requireNonNull(edge);
        if(!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Impossible to add edge '" + edge + "' between vertices '" + source + "' and '" + target + "'. Both vertices must be in the graph.");
        }
        if(hasEdge(source, target)) {
            throw new IllegalArgumentException("Impossible to add edge '" + edge + "' between vertices '" + source + "' and '" + target + "''. Multiple edges are not supported.");
        }
        connections.get(source).put(target, edge);
    }

    /**
     * Retrieves all the vertices connected to a vertex with the associated edges.
     * The vertex must be in graph.
     * @param vertex The vertex from which to get connected vertices.
     * @return A map associating the connected vertices and the edge.
     */
    public Map<Vertex, Edge> getNeighbors(Vertex vertex) {
        if(!containsVertex(vertex)) {
            throw new IllegalArgumentException("Impossible to find neighbors for vertex '" + vertex + "'. Vertex must be in graph.");
        }
        return connections.get(vertex);
    }

    /**
     * Checks whether or not the graph contains a vertex.
     * @param vertex The vertex to check.
     * @return True if contained, false otherwise.
     */
    public Boolean containsVertex(Vertex vertex) {
        Objects.requireNonNull(vertex);
        return vertices.containsKey(vertex.getId());
    }

    /**
     * Checks whether or not source vertex is connected to target vertex.
     * Both vertices must be in graph.
     * @param source The source vertex.
     * @param target The target vertex.
     * @return True if connected, false otherwise.
     */
    public Boolean hasEdge(Vertex source, Vertex target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        if(!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Impossible to find an edge between vertices '" + source + "' and '" + target + "'. Both vertices must be in the graph.");
        }
        return connections.get(source).containsKey(target);
    }
}
