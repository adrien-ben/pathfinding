package com.adrien.games.pathfinding;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link PathFinder}.
 */
public class PathFinderTest {

    private PathFinder pathFinder = new PathFinder();

    @Test
    public void itShouldFindShortestPath() {
        Node start = new Node("start", 0, 0);
        Node goal = new Node("end", 2, 0);
        Node a = new Node("a", 0, 2);
        Node b = new Node("b", 1, 2);
        Node c = new Node("c", 2, 2);
        Node d = new Node("d", 0, 1);
        Node e = new Node("e", 1, 1);
        Node f = new Node("f", 2, 1);

        a.addNeighbor(b, 1);
        a.addNeighbor(d, 1);
        b.addNeighbor(a, 1);
        b.addNeighbor(c, 1);
        b.addNeighbor(e, 1);
        c.addNeighbor(b, 1);
        c.addNeighbor(f, 1);
        d.addNeighbor(a, 1);
        d.addNeighbor(e, 1);
        d.addNeighbor(start, 1);
        e.addNeighbor(b, 1);
        e.addNeighbor(d, 1);
        e.addNeighbor(f, 1);
        f.addNeighbor(c, 1);
        f.addNeighbor(e, 1);
        f.addNeighbor(goal, 1);
        start.addNeighbor(d, 1);
        goal.addNeighbor(f, 1);

        Path path = pathFinder.findPath(start, goal);

        Assert.assertNotNull(path);
        Assert.assertEquals(Integer.valueOf(5), path.getSize());
        Assert.assertEquals("start", path.getNodes().get(0).getId());
        Assert.assertEquals("d", path.getNodes().get(1).getId());
        Assert.assertEquals("e", path.getNodes().get(2).getId());
        Assert.assertEquals("f", path.getNodes().get(3).getId());
        Assert.assertEquals("end", path.getNodes().get(4).getId());

    }

    @Test
    public void itShouldFindShortestPath2() {
        Node start = new Node("start", 0, 0);
        Node goal = new Node("end", 2, 0);
        Node a = new Node("a", 0, 2);
        Node b = new Node("b", 1, 2);
        Node c = new Node("c", 2, 2);
        Node d = new Node("d", 0, 1);
        Node e = new Node("e", 1, 1);
        Node f = new Node("f", 2, 1);

        a.addNeighbor(b, 1);
        a.addNeighbor(d, 1);
        b.addNeighbor(a, 1);
        b.addNeighbor(c, 1);
        b.addNeighbor(e, 1);
        c.addNeighbor(b, 1);
        c.addNeighbor(f, 1);
        d.addNeighbor(a, 1);
        d.addNeighbor(e, 1);
        d.addNeighbor(start, 1);
        e.addNeighbor(b, 1);
        e.addNeighbor(d, 1);
        f.addNeighbor(c, 1);
        f.addNeighbor(e, 1);
        f.addNeighbor(goal, 1);
        start.addNeighbor(d, 1);
        goal.addNeighbor(f, 1);

        Path path = pathFinder.findPath(start, goal);

        Assert.assertNotNull(path);
        Assert.assertEquals(Integer.valueOf(7), path.getSize());
        Assert.assertEquals("start", path.getNodes().get(0).getId());
        Assert.assertEquals("d", path.getNodes().get(1).getId());
        Assert.assertEquals("e", path.getNodes().get(2).getId());
        Assert.assertEquals("b", path.getNodes().get(3).getId());
        Assert.assertEquals("c", path.getNodes().get(4).getId());
        Assert.assertEquals("f", path.getNodes().get(5).getId());
        Assert.assertEquals("end", path.getNodes().get(6).getId());
    }

    @Test
    public void itShouldNotFindAnyPath() {
        Path path = pathFinder.findPath(new Node("start", 0, 0), new Node("goal", 0, 1));
        Assert.assertNotNull(path);
        Assert.assertEquals(Integer.valueOf(0), path.getSize());
    }

}