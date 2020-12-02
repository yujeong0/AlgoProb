package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2814_최장경로 {
	
	static int N, M;
	static List<Integer>[] adjList;
	static int MAX;
	static int len;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			visited = new boolean[N+1];
			
			int x, y;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				adjList[x].add(y);
				adjList[y].add(x);
			}
			
			for (int i = 1; i <= N; i++) {
				visited[i] = true;
				solve(i, 1);
				visited[i] = false;
			}
			
			sb.append("#").append(testcase).append(" ").append(MAX).append(" \n");
		}	// tc
		
		System.out.println(sb.toString());
	} // main
	
	private static void solve(int start, int len) {
		boolean go = false;
		for(int n: adjList[start]) {
			if(!visited[n]) {
				go = true;
				visited[n] = true;
				solve(n, len+1);
				visited[n] = false;
			}
		}
		if(!go) {
			if(MAX < len) MAX = len;
		}
		
	} // solve
}
