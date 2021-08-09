package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프_bfs {
	static List<Integer>[] adjList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[V+1];
			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList[s].add(e);
				adjList[e].add(s);
			}
			
			visited = new int[V+1];
			boolean endTest = false;
			for (int i = 1; i <= V; i++) {
				if(visited[i] == 0) {
					if(!bfs(i)) {
						endTest = true;
						sb.append("NO\n");
						break;
					}
				}
			}
			
			if(!endTest) {
				sb.append("YES\n");
			}
			
		} // testcase
		
		System.out.println(sb.toString());
	} // main

	// visited에 0 이면 방문 x, 1팀, 2팀
	static int[] visited;
	private static boolean bfs(int head) { 
		Queue<Integer> q = new LinkedList<>();
		q.offer(head);
		visited[head] = 1;
		
		int p;
		while(!q.isEmpty()) {
			p = q.poll();
			
			for(int num : adjList[p]) {
				if(visited[num] == 0) {
					q.offer(num);
					visited[num] = 3-visited[p];
				} else if(visited[num] == visited[p]) {
					return false;
				}
			}
			
		} // while
		
		return true;
	} // solve
	
}
