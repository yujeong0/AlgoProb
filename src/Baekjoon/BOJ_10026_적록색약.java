package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10026_적록색약 {
	static char[][] grid;
	static int N;
	public static void main(String[] args) throws Exception {
		new BOJ_10026_적록색약().solve();
	} // main
	
	void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
	}
	
	void solve() throws Exception {
		input();
		
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				CNT1++;
				visited[i][j] = true;
				dfs(i, j, grid[i][j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(grid[i][j] == 'G') grid[i][j] = 'R';
			}
		}
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				CNT2++;
				visited[i][j] = true;
				dfs(i, j, grid[i][j]);
			}
		}
		
		System.out.println(CNT1 + " " + CNT2);
		
	} //solve
	
	static boolean[][] visited;
	static int CNT1, CNT2;
	static int[][] dir = new int[][] { {-1,0},{0,1},{1,0},{0,-1} };
	void dfs(int x, int y, char c) {
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if(!isInBound(nx, ny) || visited[nx][ny]) continue;
			
			if(c != grid[nx][ny]) continue;
			visited[nx][ny] = true;
			dfs(nx, ny, grid[nx][ny]);
		}
		
	} // dfs
	
	boolean isInBound(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		return true;
	}
}
