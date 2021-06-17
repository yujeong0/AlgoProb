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
			new BOJ_2580_스도쿠().solve(0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(j < 8) System.out.print(grid[i][j] + " ");
				else  System.out.print(grid[i][j]);
			}
			if(i < 8) System.out.println();
		}
		
	} // main
	
	static boolean isEnd = false;
	void solve(int idx) {
		if(idx == list.size()) {
			isEnd = true;
			return;
		}
		int i = list.get(idx)[0], j = list.get(idx)[1];
				
		for (int num = 1; num <= 9; num++) {
			// 1. 가로
			if(!isSuccess(grid[i], num)) continue;
			// 2. 세로
			int[] arr = new int[9];
			for (int k = 0; k < 9; k++) {
				arr[k] = grid[k][j];
			}
			if(!isSuccess(arr, num)) continue;
			// 3. 사각형
			if(!isSuccessSquare(i, j, num)) continue;
			
			grid[i][j] = num;
			
			solve(idx+1);
			
			if(isEnd) break;
			grid[i][j] = 0;
		}
		
	} // solve
	
	boolean isSuccess(int[] arr, int num) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == num) return false;
		}
		return true;
	}
	
	boolean isSuccessSquare(int r, int c, int num) {
		if(r < 3) r = 0;
		else if(r < 6) r = 3;
		else r = 6;
		
		if(c < 3) c = 0;
		else if(c < 6) c = 3;
		else c = 6;
		
		for (int i = r; i < r+3; i++) {
			for (int j = c; j < c+3; j++) {
				if(grid[i][j] == num) return false;
			}
		}
		return true;
	}
}
