package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_2117_홈방범서비스 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static int houseCnt, MAX;
	static int curX, curY, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			sb.append("#").append(testcase).append(" ").append(MAX).append("\n");
		}
		System.out.println(sb.toString());
		
	} // main
	
	private static void solve() {
		int cost;
		MAX = 1;
		for (int k = 2; k <= N+1; k++) {	// 서비스영역을 N 까지 다 해보기~
			cost = k*k + (k-1)*(k-1);
			K = k;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					houseCnt = 0;
					if(map[i][j] == 1) houseCnt++;
					visited[i][j] = true;
					curX = i;
					curY = j;
					dfs(i, j);

					for (int r = 0; r < N; r++) 
						Arrays.fill(visited[r], false);

					if(houseCnt*M >= cost && MAX < houseCnt) {
						MAX = houseCnt;
					}
				} // j
				
			}// i
		} // k
		
	} // solve()

	private static void dfs(int startx, int starty) {
		int x, y;
		for (int d = 0; d < 4; d++) {
			x = startx + dir[d][0];
			y = starty + dir[d][1];
			
			if(x >= 0 && x < N && y < N && y >= 0 && !visited[x][y] && Math.abs(curX-x)+Math.abs(curY-y) <= K-1) {
				if(map[x][y] == 1) houseCnt++;
				visited[x][y] = true;
				dfs(x, y);
			}
		}
	}
	
} // class
