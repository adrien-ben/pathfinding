package com.adrien.games.pathfinding;

import com.adrien.games.pathfinding.commons.Position;
import com.adrien.games.pathfinding.graph.Edge;
import com.adrien.games.pathfinding.graph.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test class for {@link PathFinder}.
 */
public class PathFinderTest {

    @Test
    public void itShouldFindShortestPath() {
        Graph<Position> graph = new Graph<>();

        Position start = new Position(0, 0);
        Position goal = new Position(2, 0);
        Position a = new Position(0, 2);
        Position b = new Position(1, 2);
        Position c = new Position(2, 2);
        Position d = new Position(0, 1);
        Position e = new Position(1, 1);
        Position f = new Position(2, 1);

        graph.addVertex(start);
        graph.addVertex(goal);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        graph.addEdge(a, b, new Edge(1.0));
        graph.addEdge(a, d, new Edge(1.0));
        graph.addEdge(b, a, new Edge(1.0));
        graph.addEdge(b, c, new Edge(1.0));
        graph.addEdge(b, e, new Edge(1.0));
        graph.addEdge(c ,b , new Edge(1.0));
        graph.addEdge(c ,f , new Edge(1.0));
        graph.addEdge(d ,a , new Edge(1.0));
        graph.addEdge(d ,e , new Edge(1.0));
        graph.addEdge(d ,start , new Edge(1.0));
        graph.addEdge(e ,b , new Edge(1.0));
        graph.addEdge(e ,d , new Edge(1.0));
        graph.addEdge(e ,f , new Edge(1.0));
        graph.addEdge(f ,c , new Edge(1.0));
        graph.addEdge(f ,e , new Edge(1.0));
        graph.addEdge(f ,goal , new Edge(1.0));
        graph.addEdge(start, d, new Edge(1.0));
        graph.addEdge(goal, f, new Edge(1.0));

        List<Position> path = PathFinder.findPath(
                graph,
                start,
                goal,
                Position::computeManhattanDistance);

        Assert.assertNotNull(path);
        Assert.assertEquals(5, path.size());
        Assert.assertEquals(start, path.get(0));
        Assert.assertEquals(d, path.get(1));
        Assert.assertEquals(e, path.get(2));
        Assert.assertEquals(f, path.get(3));
        Assert.assertEquals(goal, path.get(4));
    }

    @Test
    public void itShouldReturnEmptyListWhenThereIsNoPath() {
        Graph<Position> graph = new Graph<>();
        Position vertex0 = new Position(0, 0);
        Position vertex1 = new Position(0, 1);
        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        List<Position> path = PathFinder.findPath(
                graph,
                vertex0,
                vertex1,
                Position::computeManhattanDistance);
        Assert.assertNotNull(path);
        Assert.assertTrue(path.isEmpty());
    }

}