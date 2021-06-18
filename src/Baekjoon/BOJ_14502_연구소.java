package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N, M;
	static int[][] grid;
	static List<int[]> virus = new ArrayList<>();
	static List<int[]> empty = new ArrayList<>();

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
				if (grid[i][j] == 2)
					virus.add(new int[] { i, j });
				if (grid[i][j] == 0)
					empty.add(new int[] { i, j });
			}
		}

		combi(0, 0);
		
		System.out.println(MAX);
	} // main

	static int[] numbers = new int[3];

	static void combi(int s, int cnt) {
		if(cnt == 3) {
			int[][] copy = new int[N][];
			for (int i = 0; i < N; i++) {
				copy[i] = Arrays.copyOf(grid[i], M);
			}
			for (int i = 0; i < 3; i++) {
				int[] p = empty.get(numbers[i]);
				copy[p[0]][p[1]] = 1;
			}
			solve(copy);
			return;
		}
		
		for (int i = s; i < empty.size(); i++) {
			numbers[cnt] = i;
			combi(i+1, cnt+1);
		}
	} // combi

	static int MAX = 0;

	static void solve(int[][] grid) {
		visited = new boolean[N][M];
		for (int[] pos : virus) {
			visited[pos[0]][pos[1]] = true;
			spreadVirus(grid, pos[0], pos[1]);
		}
		int cntSafeArea = countSafe(grid);
		if (MAX < cntSafeArea) {
			MAX = cntSafeArea;
		}
	}
	
	static int countSafe(int[][] grid) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 0)
					count++;
			}
		}
		return count;
	}

	static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;
	static void spreadVirus(int[][] grid, int x, int y) {
		grid[x][y] = 2;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];

			if (!isInBound(nx, ny) || visited[nx][ny] || grid[nx][ny] != 0)
				continue;

			visited[nx][ny] = true;
			spreadVirus(grid, nx, ny);
		}
	}

	static boolean isInBound(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

}
