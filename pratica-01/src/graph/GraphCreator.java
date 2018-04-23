package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphCreator {
	
	private static List<Edge> edges = new ArrayList<Edge>();
	
	public static Graph createGraph(final RawGraph rawGraph) {			
		setUpEdges(rawGraph);
		
		Graph g = new Graph(rawGraph);
		g.setEdges(edges);
		
		return g;
	}
	
	private static void setUpEdges(final RawGraph rawGraph) {
		int size = rawGraph.getNumEdges();
		for (int i = 1; i <= size; i++) {

			String[] splitted = rawGraph.getEdgeSplittedBy(" ", i);
			
			int v1 = Integer.parseInt(splitted[0]);
			int v2 = Integer.parseInt(splitted[1]);
			
			edges.add(new Edge(v1, v2));
		}
	}

}