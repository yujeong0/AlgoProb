package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 으악 어려웡
 */
public class SW_Professional_5643_키순서 {
	
	static int[][] adj;
	static int N, M;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adj = new int[N+1][N+1];
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}

			cnt = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					boolean[] visited = new boolean[N+1];
					dfs(1, 1, visited);
				}
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void dfs(int start, int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if(start == cur || start == i || cur == i) continue;
			
			if(adj[start][cur] == 1 && adj[cur][i] == 1 && !visited[cur]) {
				cnt++;
				adj[cur][i] = 1;
				adj[i][start] = 1;
				dfs(start, i, visited);
			}
			
		}
		
	}
	
} // class
