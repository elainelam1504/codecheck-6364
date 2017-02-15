package codecheck;
import java.util.NoSuchElementException;
import java.util.*;

public class Digraph {
   
    private final int V;         // number of vertices
    private int E;               // number of edges
    private Vector<Vector<Integer>> adj;  // adj[v] = adjacency list for vertex v
    private int[] indegree;      // indegree[v] = indegree of vertex v
    private Vector<String> listWord = new Vector<String>();
	private String startWord;
	private boolean[] marked;  // marked[v] = true if v is reachable
	private double[] probability; //probability[v] = win probability of vertex v
	private int winPath;        //number of win path( length of path is 2n)
	private int losePath;       //num ber of lose path( length of path is 2n+1)
	
    
    

   // init graph from list of words.
    public Digraph(String[] parameters) {
		if(parameters.length == 0){
			throw new IllegalArgumentException("empty parameters");
		}else{
			
			startWord = parameters[0];
			V = parameters.length - 1;
			E = 0;
			indegree = new int[V];
			for (int i = 0; i < V; i++) {
				listWord.add(parameters[i + 1]);
				indegree[i] =  0;
			}
	        adj = new Vector<Vector<Integer>>();
			// create graph from list of word
			for( int i = 0; i< V; i++){
				//System.out.print(listWord.get(i));
				Vector<Integer> tmp = new Vector<Integer>();
				for(int j = 0; j< V; j++){
					if(i == j){
						continue;
					}
					String si = listWord.get(i);
					String sj = listWord.get(j);
					char lastChar = si.charAt(si.length() - 1);
					char firstChar = sj.charAt(0);
          
					if(lastChar == firstChar){
						indegree[j]++;
						tmp.add(j);
						//System.out.print(listWord.get(j));
					}
				}
				//System.out.println(" ");
				adj.add(tmp);
			}
        }
	}
    
        
    //Returns the number of vertices in this digraph.
    public int getV() {
        return V;
    }

    //Returns the number of edges in this digraph.
    public int getE() {
        return E;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

	//return the word which out degree is 0. Probability of Win is 100%
	private int printZeroOutDegree(){
		char lastChar = startWord.charAt(startWord.length() - 1);
		for(int i = 0; i< V; i++){
			String si = listWord.get(i);
			char firstChar = si.charAt(0);
			if(lastChar == firstChar && outdegree(i) == 0){
				System.out.print(si);
				return 1;
			}
		}
		return 0;
	}
	
	private void dfs( int v, int len) { 
		marked[v] = true;
		boolean ok = true;
        for (int w : this.adj(v)) {
            if (!marked[w]) {
				dfs(w, len + 1);
				ok = false;
			}
        }
		marked[v] = false;
		if(ok && len % 2 == 0){
			//win path
			//last node of path and length of path is 2n
			winPath++;
		}else if(ok && len % 2 == 1){
			//lose path
			//last node of path and length of path is 2n + 1
			losePath++;
		}
    }
	
	private double CalculateWinProb(int s) {
		winPath = losePath = 0;
		for(int i = 0; i< V; i++){
			marked[i] = false;
		}
        dfs(s, 0);
		if(winPath + losePath == 0){
			return 1.0;
		}else{
			return winPath * 1.0/(winPath + losePath);
		}		
    }
	
	
	public void Play(){
		if( printZeroOutDegree() == 0){
			// calculate probability of win in each word.
			char lastChar = startWord.charAt(startWord.length() - 1);
			marked = new boolean[V];
			double maxPro = -1.0;
			int maxIndex = -1;
			for(int i = 0; i< V; i++){
				String si = listWord.get(i);
				char firstChar = si.charAt(0);
				if(lastChar == firstChar){
					double winPro = CalculateWinProb(i);
					if(winPro > maxPro){
						maxPro = winPro;
						maxIndex = i;
					}
				}
			}
			if(maxIndex >= 0){
				System.out.print(listWord.get(maxIndex));
			}else{
				//lose
				System.out.print("Lose");
			}
		}
	}
    

    //Returns the vertices adjacent from vertex {@code v} in this digraph.
    public Vector<Integer> adj(int v) {
        return adj.get(v);
    }

    //Returns the outdegree of vertex v
    public int outdegree(int v) {
        return adj.get(v).size();
    }

    //Returns the indegree  vertex v.
     public int indegree(int v) {
        return indegree[v];
    }
}