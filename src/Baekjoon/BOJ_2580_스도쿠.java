package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠 {
	static int[][] grid = new int[9][9];
	static List<int[]> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		if(list.size() > 0)
			solve(0);
		
	} // main
	
	static void solve(int idx) {
		if(idx == list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		int i = list.get(idx)[0], j = list.get(idx)[1];
				
		for (int num = 1; num <= 9; num++) {
			if(!isSuccess(i, j, num)) continue;
			
			grid[i][j] = num;
			solve(idx+1);
			grid[i][j] = 0;
		}
		
	} // solve
	
	static boolean isSuccess(int r, int c, int num) {
		// 가로 세로
		for (int i = 0; i < 9; i++) {
			if(grid[i][c] == num || grid[r][i] == num) return false;
		}
		
		// 대각선
		r = (r/3) * 3;
		c = (c/3) * 3;
		for (int i = r; i < r+3; i++) {
			for (int j = c; j < c+3; j++) {
				if(grid[i][j] == num) return false;
			}
		}
		return true;
	}
}
