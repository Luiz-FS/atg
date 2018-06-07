import java.io.IOException;


import graph.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {
	
	private String file = "src/sample_graph.txt";
	private String file2 = "src/sample_weighted_graph.txt";
	private String file3 = "src/sample_weighted_graph2.txt";
	private Graph g, weightedG, weightedG2;

    @BeforeEach
	public void setUp() throws IOException {
		g = GraphCreator.createGraph(file);
		weightedG = GraphCreator.createGraph(file2);
		weightedG2 = GraphCreator.createGraph(file3);
	}

	@Test
	public void testAdjacencyList() {
		String expectedOutput = "1 - 2 5\n2 - 1 5\n3 - 5\n4 - 5\n5 - 1 2 3 4";
		assertEquals(expectedOutput, GraphFormatter.getAdjacencyList(g));
	}

	@Test
	public void testAdjacencyMatrix() {
		String expectedOutput = "  1 2 3 4 5\n1 0 1 0 0 1\n2 1 0 0 0 1\n3 0 0 0 0 1\n4 0 0 0 0 1\n5 1 1 1 1 0";

		assertEquals(expectedOutput, GraphFormatter.getAdjacencyMatrix(g));
	}

	@Test
	public void testWeightedAdjacencyList() {
		String expectedOutput = "1 - 2(0,1) 5(1)\n2 - 1(0,1) 5(0,2)\n3 - 4(-9,5) 5(5)\n4 - 3(-9,5) 5(2,3)\n5 - 1(1) 2(0,2) 3(5) 4(2,3)";

		assertEquals(expectedOutput, GraphFormatter.getAdjacencyList(weightedG));
	}

	@Test
	public void testWeightedAdjacencyMatrix() {
		String expectedOutput = "  1 2 3 4 5\n1 0 0,1 0 0 1\n2 0,1 0 0 0 0,2\n3 0 0 0 -9,5 5\n4 0 0 -9,5 0 2,3\n5 1 0,2 5 2,3 0";

		assertEquals(expectedOutput, GraphFormatter.getAdjacencyMatrix(weightedG));
	}

	@Test
	public void testWeightedShortestPathWithNegativeCicle() {
		String expectedOutput = "O grafo contém um ciclo de pesos negativos.";

		assertEquals(expectedOutput, GraphConnectivity.getShortestPathWeighted(weightedG, 1, 5));
	}

	@Test
	public void testWeightedShortestPath() {
		String expectedOutput = "1 2 5";

		assertEquals(expectedOutput, GraphConnectivity.getShortestPathWeighted(weightedG2, 1, 5));
	}

	@Test
	public void testUnweightedShortestPath() {
		String expectedOutput = "1 5";

		assertEquals(expectedOutput, GraphConnectivity.getShortestPathUnweighted(g, 1, 5));
	}

	@Test
	public void testGetVertexNumber() {
		assertEquals(5, g.getVertexNumber());
	}

	@Test
	public void testGetEdgeNumber() {
		assertEquals(5, g.getEdgeNumber());
	}
	
	@Test
	public void testBFS() {
		GraphSearcher.bfs(g, 1);
		GraphSearcher.bfs(weightedG, 1);
	}
	
	@Test
	public void testDFS() {
		String expectedOutput = "1 - - 02 - 1 15 - 2 23 - 5 34 - 5 3";
		
		Integer firstVertex = 1;
		String dfsOutput = GraphSearcher.dfs(g, firstVertex);
		
		StringBuilder output = new StringBuilder();
    	for (String string : dfsOutput.split("\n")) {
			output.append(string.trim());
		}
		assertEquals(expectedOutput, output.toString());
	}

	@Test
	public void testConnectivity(){
		assertEquals(true, GraphConnectivity.isConnected(g));
		assertEquals(true, GraphConnectivity.isConnected(weightedG));
		assertEquals(true, GraphConnectivity.isConnected(weightedG2));
	}
	
	@Test
	public void testMST() {
		assertEquals(GraphTree.mst(g), "1 - 0 -\n" +
				"2 - 1 1\n" + 
				"3 - 2 5\n" + 
				"4 - 2 5\n" +
				"5 - 1 1");
		assertEquals(GraphTree.mst(weightedG), "1 - 4 2\n" +
				"2 - 3 5\n" +
				"3 - 0 -\n" +
				"4 - 1 3\n" + 
				"5 - 2 4");
		assertEquals(GraphTree.mst(weightedG2), "1 - 3 2\n" +
				"2 - 2 5\n" + 
				"3 - 2 5\n" + 
				"4 - 0 -\n" +
				"5 - 1 4");
	}
	
}
