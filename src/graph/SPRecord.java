package graph;

public class SPRecord {
	boolean inTree;
	int link;
	int distance;
	
	SPRecord(boolean inTree, int link, int distance){
		this.inTree = inTree;
		this.link = link;
		this.distance = distance;
	}

	public String toString( ){
		return inTree+ " " +link +" "+distance;
	}
	
	public static void printRecords(SPRecord [ ] spRecords, Graph g){
		for (int j = 0; j<spRecords.length; j++){
			System.out.println(j+" "+g.adjLists[j].name+ ": "+ spRecords[j]);
		}
	}
}
