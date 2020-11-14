package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon_17140_이차원배열과연산 {
	
	static int R, C, k;
	static int N = 100;
	static int[][] grid = new int[N][N];
	static int TIME = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()) -1;
		C = Integer.parseInt(st.nextToken()) -1;
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 3; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
		
		System.out.println(TIME);
	} // main
	
	private static void solve() {
		while(true) {
			if(grid[R][C] == k) break;
			
			int cal = chooseCal();	// 0이면 R연산, 1이면 C연산
			
			if(cal == 0) {	// R 연산
				for (int r = 0; r < N; r++) {
					int[][] countNums = new int[101][2];	// 1~100 의 각 등장 횟수
					for (int i = 1; i <= 100; i++) {
						countNums[i][0] = i;	// [0]에는 자기번호
					}
					for (int j = 0; j < N; j++) {
						if(grid[r][j] == 0) continue;
						countNums[grid[r][j]][1]++;	// [1]에는 등장횟수
					}
					Arrays.sort(countNums, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return o1[1] - o2[1];
						}
					});
					
					int idx = 0;
					for (int j = 0; j <= 100; j++) {
						if(countNums[j][0] == 0 || countNums[j][1] == 0) continue;
						grid[r][idx++] = countNums[j][0];
						grid[r][idx++] = countNums[j][1];
						if(idx > 100) break;
					}
					for (int j = idx; j < N; j++) {
						grid[r][j] = 0;
					}
				}
			}
			else {	// C 연산
				for (int c = 0; c < N; c++) {
					int[][] countNums = new int[101][2];	// 1~100 의 각 등장 횟수
					for (int i = 1; i <= 100; i++) {
						countNums[i][0] = i;	// [0]에는 자기번호
					}
					for (int i = 0; i < N; i++) {
						if(grid[i][c] == 0) continue;
						countNums[grid[i][c]][1]++;	// [1]에는 등장횟수
					}
					Arrays.sort(countNums, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return o1[1] - o2[1];
						}
					});
					
					int idx = 0;
					for (int i = 0; i <= 100; i++) {
						if(countNums[i][0] == 0 || countNums[i][1] == 0) continue;
						grid[idx++][c] = countNums[i][0];
						grid[idx++][c] = countNums[i][1];
						if(idx > 100) break;
					}
					for (int i = idx; i < N; i++) {
						grid[i][c] = 0;
					}
				}
			}
			TIME++;
			if(TIME> 100) {
				TIME = -1;
				break;
			}
		} // while
	
	} // solve
	
	private static int chooseCal() {	// 0이면 R연산, 1이면 C연산
		int cnt1 = 0;
		for (int i = 0; i < N; i++) {	// 행 수
			boolean isAllZero = true;
			for(int j = 0; j < N; j++) {
				if(grid[i][j] != 0) {
					isAllZero = false;
					break;
				}
			}
			if(!isAllZero) cnt1++;
		}
		int cnt2 = 0;
		for (int j = 0; j < N; j++) { // 열 수
			boolean isAllZero = true;
			for(int i = 0; i < N; i++) {
				if(grid[i][j] != 0) {
					isAllZero = false;
					break;
				}
			}
			if(!isAllZero) cnt2++;
		}
		
		if(cnt1>= cnt2) return 0;
		else return 1;
	}
	
} // class

