package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_6109_추억의2048게임 {

	static int N;
	static int[][] grid;
	static String D;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = st.nextToken();
			
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			
			
			sb.append("#").append(testcase).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(j != N-1) sb.append(grid[i][j]).append(" ");
					else sb.append(grid[i][j]).append("\n");
				}
			}
			sb.append("\n");
		} // 
		
		System.out.println(sb.toString());
	} // main
	
	private static void solve() {
		
		switch(D) {
		case "left":
			for (int i = 0; i < N; i++) {
				int[] arr = new int[N];
				for (int j = 0; j < N; j++) {
					arr[i] = grid[i][j];
				}
				int[] tmp = func(arr);
				for (int j = 0; j < N; j++) {
					grid[i][j] = tmp[i];
				}
			}
			break;
		case "right":
			
			break;
		case "up":
			for (int j = 0; j < N; j++) {
				int[] arr = new int[N];
				for (int i = 0; i < N; i++) {
					arr[i] = grid[i][j];
				}
				int[] tmp = func(arr);
				for (int i = 0; i < N; i++) {
					grid[j][i] = tmp[i];
				}
			}
			break;
		case "down":
			
			break;
		}
		
	} // solve
	
	private static int[] func(int[] arr) {
		for (int i = 1; i < N; i++) {
			if(arr[i] == 0) continue;
			
			if(arr[i-1] == arr[i]) {
				arr[i-1] *= 2;
				arr[i] = 0;
			}
				
		} // for

		// 배열 0 없을 때까지 땡기기
		for (int j = 0; j < N-1; j++) {
			if(arr[j] == 0) {
				arr[j] = arr[j+1];
				arr[j+1] = 0;
			}
		}
		
		return arr;
	}
} //class
 