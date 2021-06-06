package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1022_소용돌이예쁘게출력하기 {
	int[][] grid;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		new BOJ_1022_소용돌이예쁘게출력하기().solution(r1, c1, r2, c2);
		
	} // main
	
	void makeArr(int r1, int c1, int r2, int c2) {
		int[][] dir = new int[][] { {0, 1},{-1, 0},{0, -1},{1, 0} };
		int r = 0, c = 0, n = 1, cnt = 2, number = 1, d = 0;
		if(r1 <= r && r <= r2 && c1 <= c && c <= c2)
			grid[r-r1][c-c1] = number;
		
		while(true) {
			if(r < -5000 || c < -5000 || r > 5000 || c > 5000) break;
			
			if(cnt == 0) {
				cnt = 2;
				n++;
			}
			
			for (int k = 0; k < n; k++) {
				if(r1 <= r && r <= r2 && c1 <= c && c <= c2)
					grid[r-r1][c-c1] = number;
				number++;
				r += dir[d][0];
				c += dir[d][1];
				if(r < -5000 || c < -5000 || r > 5000 || c > 5000) break;
			}
			d = (d+1)%4;
			cnt--;
		}
		
	}
	
	void solution(int r1, int c1, int r2, int c2) {
		grid = new int[r2-r1+1][c2-c1+1];
		makeArr(r1, c1, r2, c2);
		
		int maxLen = 0;
		for (int i = 0; i <= r2-r1; i++) {
			for (int j = 0; j <= c2-c1; j++) {
				int len = Integer.toString(grid[i][j]).length();
				if(maxLen < len) {
					maxLen = len;
				}
			}
		}
		
		for (int i = 0; i <= r2-r1; i++) {
			for (int j = 0; j <= c2-c1; j++) {
				System.out.format("%" + maxLen + "d ", grid[i][j]);
			}
			System.out.println();
		}
		
	}
}
