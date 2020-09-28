package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1922_네트워크연결 {

	static int N, M, ANSWER = 0;
	static boolean[] visited;
	static int[] minEdge;
	static int[][] adjMatrix;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjMatrix = new int[N+1][N+1];	// 인접행렬
		visited = new boolean[N+1];
		minEdge = new int[N+1];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		StringTokenizer st;
		int s, e, cost;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			adjMatrix[s][e] = cost;
			adjMatrix[e][s] = cost;
		}	// end of input
		
		Prim();
		
		System.out.println(ANSWER);
	}	// end of main
	
	private static void Prim() {
		minEdge[1] = 0;	// 시작점은 1
		
		int min, minVertex = -1;
		for (int i = 1; i <= N; i++) {
			min = Integer.MAX_VALUE;
			
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			visited[minVertex] = true;
			ANSWER += min;	// 비용에 추가
			
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && adjMatrix[minVertex][j] != 0 && minEdge[j] > adjMatrix[minVertex][j]) {
					minEdge[j] = adjMatrix[minVertex][j];
				}
			}
			
		}
		
		
	}
}
