package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1949_등산로조성 {

	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int MAX;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int max;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			max = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max < map[i][j]) {
						max = map[i][j];
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(max == map[i][j]) {
						visited[i][j] = true;
						dfs(i, j, 1, false, map[i][j]);
						visited[i][j] = false;
					}
				}
			}
			
			sb.append("#" + testcase + " " + MAX + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	private static void dfs(int startx, int starty, int cnt, boolean cut, int val) {
		
		int x, y;
		for (int d = 0; d < 4; d++) {
			x = startx + dir[d][0];
			y = starty + dir[d][1];
			
			if(x >= 0 && x < N && y >= 0 && y < N && !visited[x][y]) {
				if(val > map[x][y]) {
					visited[x][y] =  true;
					dfs(x,y, cnt+1, cut, map[x][y]);	// 이미 깎은 경우 + 안 깎은 경우지만 일부러 안 깍음
					visited[x][y] =  false;
				}
				else if(!cut && val <= map[x][y]) {	// 안 깎았고 val보다 큰 값이라면 1~K 까지 다 깎아봄
					for (int i = 1; i <= K; i++) {
						if(map[x][y]-i < 0) break;
						if(map[x][y]-i >= val) continue;
						
						visited[x][y] =  true;
						dfs(x,y, cnt+1, true, map[x][y]-i);
						visited[x][y] =  false;
					}
				}
				
			}
		}
		
		if(MAX < cnt) MAX = cnt;
	}
	
} // end of class
