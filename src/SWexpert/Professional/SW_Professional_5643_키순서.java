package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 으악 어려웡
 */
public class SW_Professional_5643_키순서 {
	
	static int[][] talladj, shortadj;
	static int N, M;
	static int tallCnt, shortCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			talladj = new int[N+1][N+1];
			shortadj = new int[N+1][N+1];
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				talladj[a][b] = 1;
				shortadj[b][a] = 1;
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				tallCnt = shortCnt = 0;
				tallDfs(i, new boolean[N+1]);
				shortDfs(i, new boolean[N+1]);
				
				if(tallCnt + shortCnt == N-1) answer++;
			}
			
			sb.append("#").append(testcase).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void tallDfs(int start, boolean[] visited) {
		visited[start] = true;
		for (int j = 1; j <= N; j++) {
			if(start == j) continue;
			
			if(talladj[start][j] == 1 && !visited[j]) {
				tallCnt++;
				tallDfs(j, visited);
			}
		}
	}
	
	private static void shortDfs(int start, boolean[] visited) {
		visited[start] = true;
		for (int j = 1; j <= N; j++) {
			if(start == j) continue;
			
			if(shortadj[start][j] == 1 && !visited[j]) {
				shortCnt++;
				shortDfs(j, visited);
			}
		}
	}
	
} // class
