package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프 {
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
			
			isEnd = false;
			visited = new int[V+1];
			boolean endTest = false;
			for (int i = 1; i <= V; i++) {
				if(isEnd) {
					sb.append("NO\n");
					endTest = true;
					break;
				}
				if(visited[i] == 0) {
					solve(i, 1);
				}
			}
			if(!endTest) {
				sb.append("YES\n");
			}
			
		} // testcase
		
		System.out.println(sb.toString());
	} // main

	// visited에 0 이면 방문 x, 1팀, 2팀
	static boolean isEnd;
	static int[] visited;
	private static void solve(int head, int team) { 
		if(isEnd) {
			return;
		}
		
		visited[head] = team;
		for(int num : adjList[head]) {
			if (visited[head] == visited[num]) {
				isEnd = true;
				return;
			} else if(visited[num] == 0) {
				if(visited[head] == 1) {
					solve(num, 2);
				} else {
					solve(num, 1);
				}
			} 
		}
	} // solve
	
}
