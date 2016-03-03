package com.adrien.games.pathfinding.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Class test for {@link Graph}.
 */
public class GraphTest {

    private static final Vertex VERTEX0 = new Vertex("V0", 0, 0);
    private static final Vertex VERTEX1 = new Vertex("V1", 0, 1);
    private static final Vertex VERTEX2 = new Vertex("V2", 0, 2);
    private static final Edge EDGE0 = new Edge(0);
    private static final Edge EDGE1 = new Edge(1);

    @Test
    public void itShouldAddVertex() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);

        Assert.assertTrue(graph.containsVertex(VERTEX0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddVertexAlreadyInGraph() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX0);
    }

    @Test
    public void itShouldAddUnidirectionalEdge() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);

        Assert.assertTrue(graph.hasEdge(VERTEX0, VERTEX1));
        Assert.assertFalse(graph.hasEdge(VERTEX1, VERTEX0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddEdgeIfSourceVertexIsNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddEdgeIfTargetVertexIsNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddMultipleEdgesBetweenTwoVertices() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
        graph.addEdge(VERTEX0, VERTEX1, EDGE1);
    }

    @Test
    public void itShouldGetNeighbors() {
        Graph graph = new Graph();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addVertex(VERTEX2);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
        graph.addEdge(VERTEX0, VERTEX2, EDGE1);

        Map<Vertex, Edge> neighbors = graph.getNeighbors(VERTEX0);

        Assert.assertNotNull(neighbors);
        Assert.assertEquals(2, neighbors.size());
        Assert.assertEquals(EDGE0, neighbors.get(VERTEX1));
        Assert.assertEquals(EDGE1, neighbors.get(VERTEX2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToGetNeighborsOfAVertexThatIsNotInTheGraph() {
        Graph graph = new Graph();
        graph.getNeighbors(VERTEX0);
    }

}