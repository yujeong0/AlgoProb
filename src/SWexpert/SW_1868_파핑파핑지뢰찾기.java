package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SW_1868_파핑파핑지뢰찾기 {
	
	static int N;
	static char[][] grid;
	static boolean[][] visited;
	static int[][] dir = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
	static int clickCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			grid = new char[N][];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				grid[i] = br.readLine().toCharArray();
			}
			
			clickCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(grid[i][j] == '.' && cntMine(i, j) == 0) {
						clickCnt++;
						bfs(i, j);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(grid[i][j] == '.') {
						clickCnt++;
					}
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(clickCnt).append("\n");
		}
		System.out.println(sb.toString());
		
	} // main
	
	private static void bfs(int startx, int starty) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {startx, starty});
		visited[startx][starty] = true;
		
		int[] p;
		while(!q.isEmpty()) {
			p = q.poll();
			
			grid[p[0]][p[1]] = '0';
			int x, y;
			for (int d = 0; d < 8; d++) {
				x = p[0] + dir[d][0];
				y = p[1] + dir[d][1];
				
				int cnt = cntMine(x, y);
				if(x >= 0 && x < N && y >= 0 && y < N && !visited[x][y] && grid[x][y] == '.') {
					if(cnt == 0) {
						q.offer(new int[] {x, y});
						visited[x][y] = true;
					}
					else {
						grid[x][y] = (char) (cnt + '0');
						visited[x][y] = true;
					}
				}
			}
			
		} // while
		
		
	} //solve
	
	private static int cntMine(int startx, int starty) {
		int cnt = 0;
		int x, y;
		for (int d = 0; d < 8; d++) {
			x = startx + dir[d][0];
			y = starty + dir[d][1];
			
			if(x >= 0 && x < N && y >= 0 && y < N && grid[x][y] == '*')
				cnt++;
		}
		return cnt;
	}
	
} // class
