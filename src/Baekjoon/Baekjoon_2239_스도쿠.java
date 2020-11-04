package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon_2239_스도쿠 {

	static int[][] grid = new int[9][9];
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static boolean isEnd = false;
	static boolean[][] visited = new boolean[9][9];
	static int zeroCnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			str = br.readLine();
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
				if(grid[i][j] == 0) zeroCnt++;
			} // j
		} // i
		
		sdoku(0, 0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(grid[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void sdoku(int i, int j) {
		if(zeroCnt == 0) return;
		
		int ni = j+1 == 9 ? i+1 : i;
		int nj = (j+1) % 9;

		if(grid[i][j] == 0) {
			for (int n = 1; n <= 9; n++) {
				zeroCnt--;
				grid[i][j] = n;
				if(garoseroCheck(i, j)) {	// 가로 체크
					if(threeby3Check(i, j)) {	// 3x3 체크
						sdoku(ni, nj);
						
						if(zeroCnt == 0) return;
					}
				}
				grid[i][j] = 0;
				zeroCnt++;
			}
		}
		else {
			sdoku(ni, nj);
		}
		
	} //sdoku
	
	private static boolean garoseroCheck(int r, int c) {
		for (int i = 0; i < 9; i++) {
			if(c == i) continue;
			if(grid[r][c] == grid[r][i]) return false;
		}
		
		for(int i = 0; i < 9; i++) {
			if(r == i) continue;
			if(grid[r][c] == grid[i][c]) return false;
		}
		return true;
	}
	
	private static boolean threeby3Check(int x, int y) {
		int r = (x/3) * 3, c = (y/3) * 3;
		for (int i = r; i < r+3; i++) {
			for (int j = c; j < c+3; j++) {
				if(x == i && y == j) continue;
				if(grid[i][j] == grid[x][y]) return false;
			}
		}
		return true;
	}
	
} //class
