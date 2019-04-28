
import java.util.*;

class GNode{
	int num;
	List<GEdge> edges = new ArrayList<GEdge>();
	int dist = Integer.MAX_VALUE;
	int ownWeight;
}

class GEdge{
	GNode dest;
	int weight;
}

class GNodeDistComp implements Comparator<GNode>{

	@Override
	public int compare(GNode o1, GNode o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.dist, o2.dist);
	}
	
}

public class MST {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
	        Scanner in = new Scanner(System.in);
	        //Scanner out = new Scanner(new File("/Users/jenish_shah/Downloads/rank_output.txt"));
	        long tc = 1;
	        for(int i = 0;i<tc;i++){
	        	int n = in.nextInt();
	        	List<GNode> nodes = new ArrayList<GNode>();
	        	Map<Integer,GNode> idToNode = new HashMap<Integer,GNode>();
	        	for(int j = 0;j<n;j++){
	        		GNode t = new GNode();
	        		t.num = j+1;
	        		nodes.add(t);
	        		idToNode.put(t.num, t);
	        	}
	        	int edges = in.nextInt();
	        	for(int j = 0;j<edges;j++){
	        		int s = in.nextInt();
	        		int d = in.nextInt();
	        		int w = in.nextInt();
	        		GNode src = idToNode.get(s);
	        		GNode dest = idToNode.get(d);
	        		GEdge edge = new GEdge();
	        		edge.dest = dest;
	        		edge.weight = w;
	        		src.edges.add(edge);
	        		edge = new GEdge();
	        		edge.dest = src;
	        		edge.weight = w;
	        		dest.edges.add(edge);
	        	}
	        	int start = in.nextInt();
	        	idToNode.get(start).dist = 0;
	        	
	        	PriorityQueue<GNode> q = new PriorityQueue<GNode>(new GNodeDistComp());
	        	q.add(idToNode.get(start));
	        	List<GEdge> srcEdges = idToNode.get(start).edges;
	        	Set<GNode> srcTarges = new HashSet<GNode>();
	        	for(GEdge e: srcEdges){
	        		srcTarges.add(e.dest);
	        	}
	        	q.addAll(srcTarges);
	        	BitSet visited = new BitSet(n);
	        	while(!q.isEmpty()){
	        		GNode t = q.poll();
	        		visited.set(t.num);
	        		List<GEdge> ns = t.edges;
	        		for(GEdge gn : ns){
	        			GNode n1 = gn.dest;
	        			if(visited.get(n1.num)){
	        				continue;
	        			}
	        			q.remove(n1);
	        			if(n1.dist > gn.weight){
	        				n1.dist =  gn.weight;
	        			}
	        			q.add(n1);
	        		}
	        	}
	        	long result = 0;
	        	for(int k = 0;k<n;k++){
	        		if(k+1 == start)
	        			continue;
	        		int dist = idToNode.get(k+1).dist;
	        		result = result + ((dist!=Integer.MAX_VALUE? dist:0));
	        	}
	        	/*String op = out.nextLine();
	        	if(!result.toString().equals(op)){
	        		System.out.println("Not Match");
	        	}
	        	System.out.println(op);*/
	        	System.out.println(result);
	        }
	        in.close();
    }
}