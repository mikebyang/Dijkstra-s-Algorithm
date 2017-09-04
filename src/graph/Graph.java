package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//  represents a weighted undirected graph

public class Graph {
	Vertex[ ] adjLists;   // array of all vertices in the graph

	public Graph(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));

		// read number of vertices
		adjLists = new Vertex[sc.nextInt()];

		// read vertex names & create vertices
		for (int v=0; v < adjLists.length; v++) {
			adjLists[v] = new Vertex(sc.next(), null);
		}

		// read edges
		while (sc.hasNext()) {
			// read vertex names and translate to vertex numbers
			int v1 = indexForName(sc.next());
			int v2 = indexForName(sc.next());
			int weight = sc.nextInt( );

			// add v2 to front of v1's adjacency list and
			// add v1 to front of v2's adjacency list
			adjLists[v1].adjList = new AdjacencyNode(v2, adjLists[v1].adjList, weight);
			adjLists[v2].adjList = new AdjacencyNode(v1, adjLists[v2].adjList, weight);
		}
		sc.close( );
	}

	int indexForName(String name) {
		for (int v=0; v < adjLists.length; v++) {
			if (adjLists[v].name.equals(name)) {
				return v;
			}
		}
		return -1;
	}	
	
	// returns the number of vertices in the graph 
	public int numberOfVertices( ){
		return adjLists.length;
	}

	// print a summary of the graph
	public void summarize( ){
		for(int j = 0; j<numberOfVertices( ); j++){
			Vertex vj = adjLists[j];
			System.out.print(vj.name+": ");
			for(AdjacencyNode e = vj.adjList; e != null; e = e.next){
				System.out.print(adjLists[e.vertexNum].name+" "+e.weight+",  ");
			}
			System.out.println();
		}
	}
	
	public int shortestPath(String nameFrom, String nameTo){
		SPRecord [] inornot = new SPRecord[this.adjLists.length];
		for (int v=0; v < adjLists.length; v++) {
			inornot[v] = new SPRecord(false, 0, 0); //creates record that characterizes tree
		}
		
		int [] neighborhood = new int[this.adjLists.length]; //array containing index of nodes in neighborhood
		neighborhood[0] = indexForName(nameFrom); //adds starting node index to neighborhood
		inornot[neighborhood[0]].inTree = true;
		
		int in = 0; //number of nodes in neighborhood -1
		
		AdjacencyNode temp;
		
		int lweight; //lowest weight
		
		int lwloc = -1; //location of lowest weight
		
		Vertex temp2 = null; //temp variable used to find index in initial array that contains lweight
		
		do{
			lweight = -1;
			for(int i = 0; i <= in; i++){
				temp = this.adjLists[neighborhood[i]].adjList;
				while(temp != null){
					if(inornot[temp.vertexNum].inTree == true){
						temp = temp.next;
					}
					else{
						break;
					}
				} //find value that is not already in tree
				if(temp == null){
					continue;
				}
				else if(i == 0){
					lweight = temp.weight;
					lwloc = temp.vertexNum;
					temp2 = this.adjLists[neighborhood[i]];
				}
				
				while(temp != null){
					if(inornot[temp.vertexNum].inTree == true){
						temp = temp.next;
						continue;
					}
					else if(temp.weight < lweight || lweight ==-1){
						lweight = temp.weight;
						temp2 = this.adjLists[neighborhood[i]];
						lwloc = temp.vertexNum;
						temp = temp.next;
					}
					else{
						temp = temp.next;
					}
				}
			} //finds lweight and lwloc in current neighborhood and 
			  //finds index in initial array that contains the linked 
			  //list that contains lweight
			
			inornot[lwloc].inTree = true;
			inornot[lwloc].distance = lweight + inornot[indexForName(temp2.name)].distance;	//adds distance from previous node in order to 
																							//get distance from start to current node
			inornot[lwloc].link = indexForName(temp2.name);
			in++;	//updates number of nodes in neighborhood
			neighborhood[in] = lwloc;	//adds node to neighborhood
		}
		while(neighborhood[in] != indexForName(nameTo));
		
//		System.out.println(this.adjLists[inornot[indexForName(nameTo)].link].name);
		
		return inornot[indexForName(nameTo)].distance;
		
		
//		return 0;  // replace this line with your code.
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {		
		Graph g =  new Graph("graph.txt");
		g.summarize( );
		String from = "Alex";
		String to = "Jackie";
		System.out.println("Shortest Path "+ "(" + from + ") " + "To"+ " (" + to + ")" + ": " + g.shortestPath(from, to));
	}
}