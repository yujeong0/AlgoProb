package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_10163_색종이 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] grid = new int[101][101];
		StringTokenizer st;
		int x, y, w, h;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());	// 열
			x = Integer.parseInt(st.nextToken());	// 행
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < x+h; j++) {
				for (int j2 = y; j2 < y+w; j2++) {
					grid[j][j2] = i+1;	// 색종이 번호 부여
				}
			}
		}
		
		int[] cnt = new int[N+1];
		for (int r = 0; r < 101; r++) {
			for (int c = 0; c < 101; c++) {
				if(grid[r][c] != 0)
					cnt[grid[r][c]]++;
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.println(cnt[i]);
		}
		
	}	// end of main
	
}
