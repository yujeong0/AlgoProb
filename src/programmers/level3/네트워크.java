package programmers.level3;

public class 네트워크 {
	
    public static int solution(int n, int[][] computers) {
    	size = n;
    	visited = new boolean[n];
    	for (int net = 0; net < n; net++) {
    		if(!visited[net]) {
    			visited[net] = true;
    	    	network++;
    			dfs(computers, net, 0);
    		}
		}
        return network;
    }
    
    static int network = 0;
    static int size;
    static boolean[] visited;
    public static void dfs(int[][] computers, int start, int cur) {
    	if(cur == size) return;
    	
    	for (int i = 0; i < size; i++) {
			if(!visited[i] && computers[start][i] == 1) {
				visited[i] = true;
				dfs(computers, i, 0);
			}
		}
    }
    
	public static void main(String[] args) {
		System.out.println(solution(3, new int[][] { {1,1,0},{1,1,0},{0,0,1} }));
		
	} // main

}
