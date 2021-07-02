package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	static int N, M;
	static int[][] grid;
	static int[][] dir = { 
			{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {	
				visited[i][j] = true;
				solve(i, j, 1, grid[i][j]);
				visited[i][j] = false;
				solve2(i, j);
			}
		}
		
		System.out.println(MAX);

	} // main

	static int MAX = 0;
	static boolean[][] visited;
	private static void solve(int x, int y, int cnt, int sum) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			if(cnt == 3) {
				MAX = Math.max(MAX, sum+grid[nx][ny]);
				visited[nx][ny] = false;
				continue;
			}
			solve(nx, ny, cnt+1, sum+grid[nx][ny]);
			visited[nx][ny] = false;
		}
		
	} // solve
	
	static int[][][] blocks = {
			{{-1, 0},{0, -1},{0, 1}},	
			{{-1, 0},{0, 1},{1, 0}},	
			{{0, -1},{0, 1},{1, 0}},	
			{{-1, 0},{0, -1},{1, 0}},	
	};
	private static void solve2(int x, int y) {
		for (int b = 0; b < 4; b++) {
			int sum = grid[x][y];
			boolean pass = true;
			for (int d = 0; d < 3; d++) {
				int nx = x + blocks[b][d][0];
				int ny = y + blocks[b][d][1];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					pass = false;
					break;
				}
				sum += grid[nx][ny];
			}
			if(pass) {
				MAX = Math.max(MAX, sum);
			}
		}
		
	} // solve2

}
