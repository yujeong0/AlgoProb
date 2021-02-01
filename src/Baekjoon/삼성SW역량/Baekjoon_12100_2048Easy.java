package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_12100_2048Easy {	// 1시간 5분 풀었는데 틀렸다....
	static int N;
	static int curx, cury;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, grid);
		
		System.out.println(MAX);
		
	} // main
	
	static int MAX = 0;
	static void dfs(int movCnt, int[][] grid) {
		if(movCnt > 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(MAX < grid[i][j]) MAX = grid[i][j];
				}
			}
			return;
		}
		
		int[][] copy = new int[N][N];
		
		// 북
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = grid[i][j];	
			}
		}
		for (int j = 0; j < N; j++) {
			int[] tmp = new int[N];
			for (int k = 0; k < N; k++) {
				tmp[k] = copy[k][j];
			}
			tmp = moveArr(tmp);
			for (int i = 0; i < N; i++) {
				copy[i][j] = tmp[i];
			}
		}
		dfs(movCnt+1, copy);
		
		// 남
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = grid[i][j];	
			}
		}
		for (int j = 0; j < N; j++) {
			int[] tmp = new int[N];
			for (int k = N-1; k >= 0; k--) {
				tmp[k] = copy[k][j];
			}
			tmp = moveArr(tmp);
			for (int i = 0; i < N; i++) {
				copy[i][j] = tmp[i];
			}
		}
		dfs(movCnt+1, copy);

		// 동
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = grid[i][j];	
			}
		}
		for (int i = 0; i < N; i++) {
			int[] tmp = new int[N];
			for (int k = 0; k < N; k++) {
				tmp[k] = copy[i][k];
			}
			tmp = moveArr(tmp);
			for (int j = 0; j < N; j++) {
				copy[i][j] = tmp[j];
			}
		}
		dfs(movCnt+1, copy);
		
		// 서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = grid[i][j];	
			}
		}
		for (int i = 0; i < N; i++) {
			int[] tmp = new int[N];
			for (int k = N-1; k >= 0; k--) {
				tmp[k] = copy[i][k];
			}
			tmp = moveArr(tmp);
			for (int j = 0; j < N; j++) {
				copy[i][j] = tmp[j];
			}
		}
		dfs(movCnt+1, copy);
	}
	
	static void printArr(int[][] grid) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int[] removeZero(int[] arr) {
		int x = 0;
		while(x < N-1) {
			if(arr[x] == 0) {
				int cnt = 1;
				for (int i = x+1; i < N; i++) {
					if(arr[i] == 0) cnt++;
					else break;
				}
				
				for (int i = x; i < N; i++) {
					if(i+cnt < N) {
						arr[i] = arr[i+cnt];
						arr[i+cnt] = 0;
					}
					else break;
				}
			}
			x++;
		}
		return arr;
	}
	static int[] moveArr(int[] arr) {
		arr = removeZero(arr);
		int x = 0;
		while(x < N) {
			if(x+1 < N && arr[x] == arr[x+1]) {
				arr[x] += arr[x];
				arr[x+1] = 0;
			}
			x++;
		}
		arr = removeZero(arr);
		return arr;
	}
	
}
