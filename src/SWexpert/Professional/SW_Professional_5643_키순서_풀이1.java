package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_Professional_5643_키순서_풀이1 {
	
	static int N, M, adj[][];
	static int gtCnt, ltCnt;
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
			
			int answer = 0;
			for (int k = 1; k <= N; k++) {
				gtCnt = ltCnt = 0;
				gtBFS(k);
				ltBFS(k);
				if(gtCnt + ltCnt == N-1) answer++;
			}

			sb.append("#").append(testcase).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void gtBFS(int start) {	// 인접행렬을 행증가 시키면서 나보다 큰 애들 찾기
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		queue.offer(start);
		visited[start] =  true;
		
		while(!queue.isEmpty()) {
			int k = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(adj[k][i] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
					gtCnt++;	// 나보다 큰 애들 수 세기
				}
			}
		}
	}
	
	private static void ltBFS(int start) {	// 인접행렬을 열로 검사하면 나보다 작은애들 찾을 수 있다~!
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		queue.offer(start);
		visited[start] =  true;
		
		while(!queue.isEmpty()) {
			int k = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(adj[i][k] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
					ltCnt++;	// 나보다 큰 애들 수 세기
				}
			}
		}
	}
	
} // class
