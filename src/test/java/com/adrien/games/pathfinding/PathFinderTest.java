package com.adrien.games.pathfinding;

import com.adrien.games.pathfinding.graph.Edge;
import com.adrien.games.pathfinding.graph.Graph;
import com.adrien.games.pathfinding.graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link PathFinder}.
 */
public class PathFinderTest {

    private PathFinder pathFinder = new PathFinder();

    @Test
    public void itShouldFindShortestPath() {
        Graph graph = new Graph();

        Vertex start = new Vertex("start", 0, 0);
        Vertex goal = new Vertex("end", 2, 0);
        Vertex a = new Vertex("a", 0, 2);
        Vertex b = new Vertex("b", 1, 2);
        Vertex c = new Vertex("c", 2, 2);
        Vertex d = new Vertex("d", 0, 1);
        Vertex e = new Vertex("e", 1, 1);
        Vertex f = new Vertex("f", 2, 1);

        graph.addVertex(start);
        graph.addVertex(goal);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        graph.addEdge(a, b, new Edge(1));
        graph.addEdge(a, d, new Edge(1));
        graph.addEdge(b, a, new Edge(1));
        graph.addEdge(b, c, new Edge(1));
        graph.addEdge(b, e, new Edge(1));
        graph.addEdge(c ,b , new Edge(1));
        graph.addEdge(c ,f , new Edge(1));
        graph.addEdge(d ,a , new Edge(1));
        graph.addEdge(d ,e , new Edge(1));
        graph.addEdge(d ,start , new Edge(1));
        graph.addEdge(e ,b , new Edge(1));
        graph.addEdge(e ,d , new Edge(1));
        graph.addEdge(e ,f , new Edge(1));
        graph.addEdge(f ,c , new Edge(1));
        graph.addEdge(f ,e , new Edge(1));
        graph.addEdge(f ,goal , new Edge(1));
        graph.addEdge(start, d, new Edge(1));
        graph.addEdge(goal, f, new Edge(1));

        Path path = pathFinder.findPath(graph, start, goal);

        Assert.assertNotNull(path);
        Assert.assertEquals(Integer.valueOf(5), path.getSize());
        Assert.assertEquals(Integer.valueOf(0), path.getPositions().get(0).getX());
        Assert.assertEquals(Integer.valueOf(0), path.getPositions().get(0).getY());
        Assert.assertEquals(Integer.valueOf(0), path.getPositions().get(1).getX());
        Assert.assertEquals(Integer.valueOf(1), path.getPositions().get(1).getY());
        Assert.assertEquals(Integer.valueOf(1), path.getPositions().get(2).getX());
        Assert.assertEquals(Integer.valueOf(1), path.getPositions().get(2).getY());
        Assert.assertEquals(Integer.valueOf(2), path.getPositions().get(3).getX());
        Assert.assertEquals(Integer.valueOf(1), path.getPositions().get(3).getY());
        Assert.assertEquals(Integer.valueOf(2), path.getPositions().get(4).getX());
        Assert.assertEquals(Integer.valueOf(0), path.getPositions().get(4).getY());
    }


}