package swTest210423;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 하강모의실험 {
	static int N;
	static int[][] grid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int testcase = 1; testcase <= 10; testcase++) {
			N = Integer.parseInt(br.readLine().trim());
			grid = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
			solve();
		
			int CNT1 = 0;
			for (int j = 0; j < N; j++) {
				if(grid[N-1][j] == 1) CNT1++;
			}
			int CNT2 = 0;
			for (int i = 0; i < N; i++) {
				if(grid[i][N-1] == 1) CNT2++;
			}
			
			System.out.println("#" + testcase + " " + CNT1 + " " + CNT2);
		} // tc
		
	} // main

	private static void solve() {
		
		// 아래하강
		for (int j = 0; j < N; j++) {
			if(grid[0][j] == 0) continue;
			
			double myPower = 1;
			int r = 1, power = 0, myLength = 1;
			boolean canGo = true;
			for (int i = r; i < N; i++) {
				if(grid[i][j] == 0) {
					myPower *= 1.9;
					grid[i][j] = 1;
					grid[i-myLength][j] = 0;
				}
				else {
					power = 0;
					for (int k = i; k < N; k++) {
						if(grid[k][j] == 0) break;
						power++;
						if(power >= myPower) {
							canGo = false;
							break;
						}
					}
					
					if(canGo) {
						i += power-1;
						myPower += power;
						myLength += power;
					}
					else break;
				}
			} // i
			
		} // j
		
		// 우측하강
		for (int i = 0; i < N; i++) {
			if(grid[i][0] == 0) continue;
			
			double myPower = 1;
			int r = 1, power = 0, myLength = 1;
			boolean canGo = true;
			for (int j = r; j < N; j++) {
				if(grid[i][j] == 0) {
					myPower *= 1.9;
					grid[i][j] = 1;
					grid[i][j-myLength] = 0;
				}
				else {
					power = 0;
					for (int k = j; k < N; k++) {
						if(grid[i][k] == 0) break;
						power++;
						if(power >= myPower) {
							canGo = false;
							break;
						}
					}
					
					if(canGo) {
						j += power-1;
						myPower += power;
						myLength += power;
					}
					else break;
				}
			} // j
			
		} // i
		
	} // solve
	
	static void printGrid() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
