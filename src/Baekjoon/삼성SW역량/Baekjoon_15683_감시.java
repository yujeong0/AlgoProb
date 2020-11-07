package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_15683_감시 {
	
	static int N, M, MIN;
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static int[][] numbers = {
			{0, 1, 3},	
			{0, 1, 2},	
			{1, 2, 3},	
			{0, 2, 3}	
	};
	static List<int[]> CCTVs = new ArrayList<>();
	static int[][] CCTVdir = { 
			{0},
			{0},
			{0,2},
			{0,1},
			{0,1,2},
			{0,1,2,3}
	};
	static int[] rotLimit = {0, 4, 2, 4, 4, 1}; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		MIN = Integer.MAX_VALUE;
		
		int a, b;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5)
					CCTVs.add(new int[] {i, j});
			}
		}
		if(CCTVs.size() != 0)
			dfs(0, map);
		else MIN = checkZero(map);
		
		System.out.println(MIN);
		
	} // main
	
	
	private static int[][] cctv(int[][] arr, int d, int startx, int starty) {
		int x = startx;
		int y = starty;
		while(true) {
			x += dir[d][0];
			y += dir[d][1];
			if(x < 0 || y < 0 || x >= N || y >= M || arr[x][y] == 6) break;
			if(arr[x][y] == 0) {
				arr[x][y] = 10;
			}
		}
		
		return arr;
	} // solve
	
	private static void dfs(int idx, int[][] arr) {
		if(CCTVs.size() == idx) {
			int cnt = checkZero(arr);
			if(MIN > cnt) MIN = cnt;
			return;
		}
		
		int i = CCTVs.get(idx)[0];
		int j = CCTVs.get(idx)[1];
		int cctvType = arr[i][j];
		for(int r = 0; r < rotLimit[cctvType]; r++) {
			int[][] copy = new int[N][M];
			for (int i2 = 0; i2 < N; i2++) {
				for (int j2 = 0; j2 < M; j2++) {
					copy[i2][j2] = arr[i2][j2];
				}
			}
			for(int d = 0; d < CCTVdir[cctvType].length; d++) {
				copy = cctv(copy, (CCTVdir[cctvType][d]+r)%4, i, j);
			}
			dfs(idx+1, copy);
		}
		
	} // dfs
	
	private static int checkZero(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
}
