package graph;

// an AdjancenyNode represents an edge in the graph.  It is a node in 
// the adjacency list of a vertex

public class AdjacencyNode {
	int weight;		// weight of this edge
	int vertexNum;  // index in vertices array of the vertex
	//                 at the other end of this edge

	AdjacencyNode next;  // next node in this adjacency list

	public AdjacencyNode(int vertexNum, AdjacencyNode next, int weight) {
		this.vertexNum = vertexNum;
		this.next = next;
		this.weight = weight;     
		}

}		
