package library;

import graph.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphLibraryTest {
    //exemplos de grafos
    private String fileTest1 = "src/smallGraph1.txt";
    private String fileTest2 = "src/graphNotConnected.txt";
    private String fileTest3 = "src/bigGraph1.txt";

    private Graph graph1, graph2, graph3;

    @Test
    void readGraph() {
    }

    @Test
    void readWeightedGraph() {
    }

    @Test
    void getVertexNumber() {
    }

    @Test
    void getEdgeNumber() {
    }

    @Test
    void getMeanEdge() {

        graph1 = GraphCreator.createGraph(fileTest1);
        graph2 = GraphCreator.createGraph(fileTest2);
        graph3 = GraphCreator.createGraph(fileTest3);

        // grafo normal conectado com mesmo numero de vertices e arestas
        assertEquals(2, graph1.getMeanEdge());
        // grafo desconectado com mesmo numero de vertices e arestas
        assertEquals(2, graph2.getMeanEdge());
        // grafo com numero de arestas maior que número de vertices
        assertEquals(2.53, graph3.getMeanEdge());
    }

    @Test
    void graphRepresentation() {
    }

    @Test
    void BFS() {
        graph1 = GraphCreator.createGraph(fileTest1);
        graph2 = GraphCreator.createGraph(fileTest2);

        // busca de um grafo normal a partir do primeiro vertice
        String expected1 = "1 - 0 -\n"
                + "2 - 1 1\n"
                + "3 - 1 1\n"
                + "4 - 2 2";
        String output1 = GraphSearcher.bfs(graph1, 1);
        assertEquals(expected1,output1);

        // busca a partir de um vertice desconectado
        String expected2 = "6 - 0 -";
        String output2 = GraphSearcher.bfs(graph2, 6);
        assertEquals(expected2,output2);

        // busca de um grafo desconectado
        String expected3 = "1 - 0 -\n"
                + "2 - 1 1\n"
                + "3 - 2 2\n"
                + "4 - 2 2\n"
                + "5 - 3 4";
        String output3 = GraphSearcher.bfs(graph2, 1);
        assertEquals(expected3,output3);

        // iniciando a busca a partir de um vertice inexistente
        String expected4 = "5 - 0 -";
        String output4 = GraphSearcher.bfs(graph1, 5);
        assertEquals(expected4,output4);
    }

    @Test
    void DFS() {
    }

    @Test
    void connected() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void mst() {
    }
}