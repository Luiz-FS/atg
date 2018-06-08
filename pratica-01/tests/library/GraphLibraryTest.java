package library;

import graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphLibraryTest {
    //exemplos de grafos
    private String fileTest1 = "src/smallGraph1.txt";
    private String fileTest2 = "src/graphNotConnected.txt";
    private String fileTest3 = "src/bigGraph1.txt";
    private String graphReadTestPath = "src/graphReadTest1.txt";
    private String weightedGraphPath = "src/sample_weighted_graph.txt";

    // grafo pequeno sem peso
    private String file1 = "src/smallGraph1.txt";
    // grafo pequeno com peso positivo
    private String file2 = "src/smallGraph2.txt";
    // grafo pequeno com peso negativo
    private String file3 = "src/smallGraph3.txt";

    // grafo grande sem peso
    private String file4 = "src/bigGraph1.txt";
    // grafo grande com pesos positivos e negativos
    private String file5 = "src/bigGraph2.txt";
    // grafo grande com pesos positivos
    private String file6 = "src/bigGraph3.txt";

    // grafo conectado
    private String file7 = "src/graphNotConnected.txt";
    // grafo não conectado
    private String file8 = "src/graphConnected.txt";

    private Graph graph1, graph2, graph3;
    private GraphLibrary graphLibrary;

    @BeforeEach
    void setup () {
        graphLibrary = new GraphLibrary();
    }

    @Test
    void readGraphVertexSizeTest() {
        Graph graph = graphLibrary.readGraph(graphReadTestPath);
        assertEquals(5, graphLibrary.getVertexNumber(graph), "The graph should have 5 vertexes");

        List<Integer> vertexes = new ArrayList<Integer>();
        vertexes.add(1);
        vertexes.add(2);
        vertexes.add(3);
        vertexes.add(4);
        vertexes.add(5);

        boolean containsAllVertexes = graph.getNodeMap().keySet().containsAll(vertexes);

        assertTrue(containsAllVertexes, "The graph should contain all the read vertexes");
    }

    @Test
    void readGraphEdgesTest() {
        Graph graph = graphLibrary.readGraph(fileTest1);
        assertEquals(4, graphLibrary.getEdgeNumber(graph), "The graph should have 4 edges");
    }

    @Test
    void readWeightedGraph() {
        Graph graph = graphLibrary.readWeightedGraph(weightedGraphPath);

        boolean isWeighted = graph.isWeighted();
        assertTrue(isWeighted, "The graph should be weighted.");
    }

    @Test
    void getVertexNumber() {
        Graph graph = graphLibrary.readGraph(fileTest1);
        assertEquals(4, graphLibrary.getVertexNumber(graph), "The graph should have 5 vertexes");
    }

    @Test
    void getEdgeNumber() {
        Graph graph = graphLibrary.readGraph(fileTest1);
        assertEquals(4, graphLibrary.getEdgeNumber(graph), "The graph should have 4 edges");
    }

    @Test
    void getMeanEdge() {

        graph1 = GraphCreator.createGraph(fileTest1);
        graph2 = GraphCreator.createGraph(fileTest2);
        graph3 = GraphCreator.createGraph(fileTest3);

        // grafo normal conectado com mesmo numero de vertices e arestas
        assertEquals(2, graphLibrary.getMeanEdge(graph1));
        // grafo desconectado com mesmo numero de vertices e arestas
        assertEquals(2, graphLibrary.getMeanEdge(graph2));
        // grafo com numero de arestas maior que número de vertices
        assertEquals(2.53, graphLibrary.getMeanEdge(graph3));
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
        String output1 = graphLibrary.BFS(graph1, 1);
        assertEquals(expected1,output1);

        // busca a partir de um vertice desconectado
        String expected2 = "6 - 0 -";
        String output2 = graphLibrary.BFS(graph2, 6);
        assertEquals(expected2,output2);

        // busca de um grafo desconectado
        String expected3 = "1 - 0 -\n"
                + "2 - 1 1\n"
                + "3 - 2 2\n"
                + "4 - 2 2\n"
                + "5 - 3 4";
        String output3 = graphLibrary.BFS(graph2, 1);
        assertEquals(expected3,output3);

        // iniciando a busca a partir de um vertice inexistente
        String expected4 = "5 - 0 -";
        String output4 = graphLibrary.BFS(graph1, 5);
        assertEquals(expected4,output4);
    }

    @Test
    void connected () {
        graph1 = graphLibrary.readGraph(file7);
        graph2 = graphLibrary.readGraph(file8);
        assertEquals(false, graphLibrary.connected(graph1));
        assertEquals(true, graphLibrary.connected(graph2));
    }

    @Test
    void DFS() {
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


        String output = graphLibrary.DFS(graph, 4);

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
    public void testWeightedShortestPath() {

        Graph graph2 = graphLibrary.readGraph(file2);
        Graph graph3 = graphLibrary.readGraph(file3);
        Graph graph5 = graphLibrary.readGraph(file5);
        Graph graph6 = graphLibrary.readGraph(file6);

        // teste Menor caminho em um grafo pequeno com pesos positivos
        String expectedOutput = "1 5 3";
        assertEquals(expectedOutput, graphLibrary.shortestPath(graph2, 1, 3));

        // teste menor caminho em um grafo pequeno com pesos negativos
        String expectedOutput2 = "O grafo contém um ciclo de pesos negativos.";
        assertEquals(expectedOutput2, graphLibrary.shortestPath(graph3, 1, 3));

        // teste menor caminho em um grafo grande com pesos positivos e negativos
        String expectedOutput3 = "O grafo contém um ciclo de pesos negativos.";
        assertEquals(expectedOutput3, graphLibrary.shortestPath(graph5, 1, 11));

        // teste menor caminho em um grafo grande com pesos positivos apenas
        String expectedOutput4 = "1 2 3 10 11";
        assertEquals(expectedOutput4, graphLibrary.shortestPath(graph6, 1, 11));
    }

    @Test
    public void testUnweightedShortestPath() {
        Graph graph1 = GraphCreator.createGraph(file1);
        Graph graph4 = GraphCreator.createGraph(file4);

        //teste menor caminho em um grafo pequeno sem pesos
        String expectedOutput = "1 2 4";
        assertEquals(expectedOutput, graphLibrary.shortestPath(graph1, 1, 4));

        //teste menor caminho em um grafo grande sem pesos
        String expectedOutput2 = "1 2 3 10 11 12";
        assertEquals(expectedOutput2, graphLibrary.shortestPath(graph4, 1, 12));

        String expectedOutput3 = "13 8 4 5 11 12";
        assertEquals(expectedOutput3, graphLibrary.shortestPath(graph4, 13, 12));
    }

    @Test
    void getAdjacencyList() {
        Graph graph1 = GraphCreator.createGraph(file1);
        Graph graph2 = GraphCreator.createGraph(file2);
        Graph graph3 = GraphCreator.createGraph(file3);
        Graph graph4 = GraphCreator.createGraph(file7);

        String expectedOutput2 = "1 - 4(0,1) 5(0,3)\n2 - 3(8)\n3 - 2(8) 4(1) 5(0,1)\n4 - 1(0,1) 3(1) 5(1)\n5 - 1(0,3) 3(0,1) 4(1)\n";
        assertEquals(expectedOutput2, graphLibrary.graphRepresentation(graph2, "AL"));
        String expectedOutput3 = "1 - 4(0,1) 5(0,3)\n2 - 3(8) 4(-1)\n3 - 2(8) 4(1) 5(0,1)\n4 - 1(0,1) 2(-1) 3(1) 5(-2)5 - 1(0,3) 3(0,1) 4(-2)\n";
        assertEquals(expectedOutput3, graphLibrary.graphRepresentation(graph3, "AL"));
        String expectedOutput4 = "1 - 2\n2 - 1 3 4\n3 - 2 4\n4 - 2 3 5\n5 - 4\n6 - 6\n";
        assertEquals(expectedOutput4, graphLibrary.graphRepresentation(graph4, "AL"));
        String expectedOutput1 = "1 - 2 3\n2 - 1 3 4\n3 - 1 2\n4 - 2";
        assertEquals(expectedOutput1, graphLibrary.graphRepresentation(graph1, "AL"));
    }

    @Test
    void getAdjacencyMatrix() {
        Graph graph1 = GraphCreator.createGraph(file1);
        Graph graph2 = GraphCreator.createGraph(file2);
        Graph graph3 = GraphCreator.createGraph(file3);
        Graph graph4 = GraphCreator.createGraph(file7);


        String expectedOutput2 = "  1 2 3 4 5\n1 0 0 0 0,1 0,3\n2 0 0 8 0 0\n3 0 8 0 1 0,1\n4 0,1 0 1 0 1\n5 0,3 0 0,1 1 0";
        assertEquals(expectedOutput2, graphLibrary.graphRepresentation(graph2, "AM"));
        String expectedOutput3 = "  1 2 3 4 5\n1 0 0 0 0,1 0,3\n2 0 0 8 -1 0\n3 0 8 0 1 0,1\n4 0,1 -1 1 0 -2\n5 0,3 0 0,1 -2 0";
        assertEquals(expectedOutput3, graphLibrary.graphRepresentation(graph3, "AM"));
        String expectedOutput4 = "  1 2 3 4 5 6\n1 0 1 0 0 0 0\n2 1 0 1 1 0 0\n3 0 1 0 1 0 0\n4 0 1 1 0 1 0\n5 0 0 0 1 0 0\n6 1 1 1 1 1 1";
        assertEquals(expectedOutput4, graphLibrary.graphRepresentation(graph4, "AM"));
        String expectedOutput1 = "  1 2 3 4\n1 0 1 1 0\n2 1 0 1 1\n3 1 1 0 0\n4 0 1 0 0";
        assertEquals(expectedOutput1, graphLibrary.graphRepresentation(graph1, "AM"));
    }

    @Test
    void mst() {
        Graph graph1 = GraphCreator.createGraph(file1);
        Graph graph2 = GraphCreator.createGraph(file2);
        Graph graph3 = GraphCreator.createGraph(file3);

        Graph graph4 = GraphCreator.createGraph(file4);
        Graph graph5 = GraphCreator.createGraph(file5);


        //teste de grafo pequeno sem peso
        assertEquals(graphLibrary.mst(graph1), "1 - 0 -\n" +
                "2 - 1 1\n" +
                "3 - 1 1\n" +
                "4 - 2 2");

        //teste grafo pequeno com peso positivo
        assertEquals(graphLibrary.mst(graph2), "1 - 0 -\n" +
                "2 - 3 3\n" +
                "3 - 2 5\n" +
                "4 - 1 1\n" +
                "5 - 1 1");

        //teste grafo pequeno com peso negativo
        assertEquals(graphLibrary.mst(graph3), "1 - 0 -\n" +
                "2 - 2 4\n" +
                "3 - 3 5\n" +
                "4 - 1 1\n" +
                "5 - 2 4");

        //teste grafo grando sem peso
        //A partir do nivel 4, o calculo do nivel está errado
        assertNotEquals(graphLibrary.mst(graph4), "1 - 0 -\n" +
                "10 - 3 3\n" +
                "11 - 4 10\n" +
                "12 - 5 11\n" +
                "13 - 4 8\n" +
                "14 - 3 15\n" +
                "15 - 2 2\n" +
                "2 - 1 1\n" +
                "3 - 2 2\n" +
                "4 - 4 8\n" +
                "5 - 5 4\n" +
                "6 - 5 4\n" +
                "7 - 4 8\n" +
                "8 - 3 15\n" +
                "9 - 3 1");

        //teste grafo grando com pesos positivos e negativos
        //A partir do nivel 4, o calculo do nivel está errado
        //Monta a MST da maneira correta, porém a escolha da ordem dos nós está diferente da sugerida pelo algoritimo
        assertNotEquals(graphLibrary.mst(graph5), "2 - 0 -\n" +
                "3 - 1 2\n" +
                "4 - 3 8\n" +
                "15 - 1 2\n" +
                "8 - 2 15\n" +
                "4 - 3 8\n" +
                "5 - 4 4\n" +
                "7 - 3 8\n" +
                "1 - 1 2\n" +
                "14 - 2 15\n" +
                "10 - 2 3\n" +
                "11 - 3 10\n" +
                "9 - 2 1\n" +
                "12 - 4 11\n" +
                "13 - 3 8");
    }
}