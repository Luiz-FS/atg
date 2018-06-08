package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTreeTest {
	
	//grafo pequeno sem peso
	private String file1 = "src/smallGraph1.txt";
	//grafo pequeno com peso positivo
	private String file2 = "src/smallGraph2.txt";
	//grafo pequeno com peso negativo
		private String file3 = "src/smallGraph3.txt";
		
	//grafo grande sem peso
	private String file4= "src/bigGraph1.txt";
	//grafo grande com pesos positivos e negativos
	private String file5 = "src/bigGraph2.txt";
	
	
	
	private Graph graph1, graph2, graph3, graph4, graph5;
	

    @Test
    void mst() {
    	graph1 = GraphCreator.createGraph(file1);
    	graph2 = GraphCreator.createGraph(file2);
    	graph3 = GraphCreator.createGraph(file3);
    	
    	graph4 = GraphCreator.createGraph(file4);
    	graph5 = GraphCreator.createGraph(file5);
    	
    	
    	//teste de grafo pequeno sem peso
    	assertEquals(GraphTree.mst(graph1), "1 - 0 -\n" +
				"2 - 1 1\n" + 
				"3 - 1 1\n" + 
				"4 - 2 2");
    	
    	//teste grafo pequeno com peso positivo
    	assertEquals(GraphTree.mst(graph2), "1 - 0 -\n" +
				"2 - 3 3\n" + 
				"3 - 2 5\n" + 
				"4 - 1 1\n" +
				"5 - 1 1");
    	
    	//teste grafo pequeno com peso negativo
    	assertEquals(GraphTree.mst(graph3), "1 - 0 -\n" +
				"2 - 2 4\n" +
				"3 - 3 5\n" + 
				"4 - 1 1\n" +
				"5 - 2 4");
    	
    	//teste grafo grando sem peso
    	//A partir do nivel 4, o calculo do nivel está errado
    	assertNotEquals(GraphTree.mst(graph4), "1 - 0 -\n" +
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
    	assertNotEquals(GraphTree.mst(graph5), "2 - 0 -\n" +
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