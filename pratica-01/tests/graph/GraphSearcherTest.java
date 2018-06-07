package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphSearcherTest {
    
    @Test
    void dfs() {
        Edge e1 = new Edge(1,2);
        Edge e2 = new Edge(2,3);
        Edge e3 = new Edge(2,6);
        Edge e4 = new Edge(4,2);
        Edge e5 = new Edge(4,6);
        Edge e6 = new Edge(6,5);
        Edge e7 = new Edge(6,7);

        Graph graph = new Graph();

        graph.addEdge(1, e1);
        graph.addEdge(3, e2);
        graph.addEdge(5, e6);
        graph.addEdge(7, e7);
        graph.addEdge(4, e4);
        graph.addEdge(4, e5);
        graph.addEdge(2, e1);
        graph.addEdge(2, e2);
        graph.addEdge(2, e3);
        graph.addEdge(6, e6);
        graph.addEdge(6, e7);


        String output = GraphSearcher.dfs(graph, 4);

        String expected = "4 - - 0" + System.getProperty("line.separator")
                + "2 - 4 1" + System.getProperty("line.separator")
                + "1 - 2 2" + System.getProperty("line.separator")
                + "3 - 2 2" + System.getProperty("line.separator")
                + "6 - 4 1" + System.getProperty("line.separator")
                + "5 - 6 2" + System.getProperty("line.separator")
                + "7 - 6 2" + System.getProperty("line.separator");

        assertEquals(expected, output);
    }

    @Test
    void bfs() {
    }
}