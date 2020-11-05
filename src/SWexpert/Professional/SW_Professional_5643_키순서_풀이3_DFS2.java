package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_Professional_5643_키순서_풀이3_DFS2 {
	
	static int N, M, adj[][], radj[][];
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());	// 학생 수 : 정점 수
			M = Integer.parseInt(br.readLine());	// 관계 수 : 간선 수
			adj = new int[N+1][N+1];	// 자신보다 큰 관계 저장
			radj = new int[N+1][N+1];	// 자신보다 작은 관계 저장
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				radj[b][a] = adj[a][b] = 1;	// radj에는 b보다 a가 작으면 1로 채우기~ adj에는 a보다 b가 크면 1로 채우기~
			}
			
			int answer = 0;
			for (int k = 1; k <= N; k++) {
				cnt = 0;
				dfs(k, adj, new boolean[N+1]);
				dfs(k, radj,new boolean[N+1]);
				
				if(cnt == N-1) answer++;
			}

			sb.append("#").append(testcase).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void dfs(int start, int[][] adj, boolean[] visited) {
		visited[start] = true;
		
		for (int i = 1; i <= N; i++) {
			if(adj[start][i] == 1 && !visited[i]) {
				cnt++;
				dfs(i, adj, visited);
			}
		}
	}
	
} // class
