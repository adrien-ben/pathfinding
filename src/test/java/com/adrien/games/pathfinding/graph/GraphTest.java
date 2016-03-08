package com.adrien.games.pathfinding.graph;

import com.adrien.games.pathfinding.commons.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Class test for {@link Graph}.
 */
public class GraphTest {

    private static final Position VERTEX0 = new Position(0, 0);
    private static final Position VERTEX1 = new Position(0, 1);
    private static final Position VERTEX2 = new Position(0, 2);
    private static final Edge EDGE0 = new Edge(0.0);
    private static final Edge EDGE1 = new Edge(1.0);

    @Test
    public void itShouldAddVertex() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);

        Assert.assertTrue(graph.containsVertex(VERTEX0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddVertexAlreadyInGraph() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX0);
    }

    @Test
    public void itShouldAddUnidirectionalEdge() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);

        Assert.assertTrue(graph.hasEdge(VERTEX0, VERTEX1));
        Assert.assertFalse(graph.hasEdge(VERTEX1, VERTEX0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddEdgeIfSourceVertexIsNotInGraph() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddEdgeIfTargetVertexIsNotInGraph() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToAddMultipleEdgesBetweenTwoVertices() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
        graph.addEdge(VERTEX0, VERTEX1, EDGE1);
    }

    @Test
    public void itShouldGetNeighbors() {
        Graph<Position> graph = new Graph<>();
        graph.addVertex(VERTEX0);
        graph.addVertex(VERTEX1);
        graph.addVertex(VERTEX2);
        graph.addEdge(VERTEX0, VERTEX1, EDGE0);
        graph.addEdge(VERTEX0, VERTEX2, EDGE1);

        Map<Position, Edge> neighbors = graph.getNeighbors(VERTEX0);

        Assert.assertNotNull(neighbors);
        Assert.assertEquals(2, neighbors.size());
        Assert.assertEquals(EDGE0, neighbors.get(VERTEX1));
        Assert.assertEquals(EDGE1, neighbors.get(VERTEX2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWhenTryingToGetNeighborsOfAVertexThatIsNotInTheGraph() {
        Graph<Position> graph = new Graph<>();
        graph.getNeighbors(VERTEX0);
    }

}