package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_Professional_5643_키순서_풀이5_floydWarshall {
	
	static int N, M, adj[][];
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
				adj[a][b] = 1;	// a 보다 b 가 크면 1로 채우기~
			}
			
			for (int k = 1; k <= N; k++) { // 경유지
				for (int i = 1; i <= N; i++) {	// 출발지
					if(i == k) continue;	// 나로 가는건 xx
					for (int j = 1; j <= N; j++) { // 도착지
						if(j == k || i == j || adj[i][j] == 1) continue;
						// i < k && k < j  =>   i < j
						if(adj[i][k] == 1 && adj[k][j] == 1) adj[i][j] = 1;
					}
				}
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[i][0] += adj[i][j];	// 남는 공간 활용 : 각 행 0열에 그 행에서 1인 애들 개수 세기(나보다 키 큰 애들)
					adj[0][j] += adj[i][j];	// 남는 공간 활용 : 각 열 0행에 그 열에서 1인 애들 개수 세기(나보다 키 작은 애들)
				}
			}
			
			for (int k = 1; k <= N; k++) {
				if(adj[0][k] + adj[k][0] == N-1) answer++;
			}
			
			sb.append("#").append(testcase).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	
} // class
