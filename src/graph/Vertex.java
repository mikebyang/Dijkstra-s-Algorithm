package graph;

// represents a vertex in the graph

class Vertex {
	String name;  			//name of the vertex
	AdjacencyNode adjList;	// adjacency list giving neighbors of the vertex

	Vertex(String name, AdjacencyNode neighbors) {
		this.name = name;
		this.adjList = neighbors;
	}

}
