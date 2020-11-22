package programmers.level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 가장먼노드 {
	static class Node {
		int n;
		int len;
		public Node(int n, int len) {
			this.n = n;
			this.len = len;
		}
	}
	
	static boolean[] visited;
	static List<Integer>[] adjList;
	static int N;
	static int[] lenList;
	static int maxLen = 0;
	public static int solution(int n, int[][] edge) {
		N = n;
		adjList = new ArrayList[n+1];
		visited = new boolean[n+1];
		lenList = new int[n+1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
			lenList[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < edge.length; i++) {
			adjList[edge[i][0]].add(edge[i][1]);
			adjList[edge[i][1]].add(edge[i][0]);
		}
		
		solve();
		
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(maxLen == lenList[i]) cnt++;
		}
        return cnt;
    }
	
	public static void solve() {
		Queue<Node> q = new LinkedList<>();
		visited[1] = true;
		for (int n : adjList[1]) {
			visited[n] = true;
			q.offer(new Node(n, 2));
		}
		
		Node p = null;
		while(!q.isEmpty()) {
			p = q.poll();
			
			boolean end = true;
			for (int n : adjList[p.n]) {
				if(!visited[n]) {
					end = false;
					visited[n] = true;
					
					q.offer(new Node(n, p.len+1));
				}
			}
			if(end) {
				lenList[p.n] = p.len;
				if(maxLen < p.len) maxLen = p.len;
			}
			
		} // while
	}
	
	public static void main(String[] args) {
		System.out.println(solution(6, new int[][] {{3, 6},{4, 3},{3, 2},{1, 3},{1, 2},{2, 4},{5, 2}}));
		
	}
}
