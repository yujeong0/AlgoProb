package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class 모의SW_4012_요리사 {
	static int N, MIN;
	static int[][] grid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			numbers = new int[N/2];
			selected = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()); 
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve(0, 0);
			
			System.out.println("#" + testcase + " " + MIN);
			
		} // tc
		
	} // main

	
	static int[] numbers;
	static boolean[] selected;
	private static void solve(int start, int cnt) {
		if(cnt == N/2) {
			int[] num2 = new int[N/2];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if(!selected[i]) num2[idx++] = i;
			}
			int sum1 = 0, sum2 = 0;
			for (int i = 0; i < N/2-1; i++) {
				for (int j = i+1; j < N/2; j++) {
					sum1 += grid[numbers[i]][numbers[j]];
					sum1 += grid[numbers[j]][numbers[i]];
				}
			}
			for (int i = 0; i < N/2-1; i++) {
				for (int j = i+1; j < N/2; j++) {
					sum2 += grid[num2[i]][num2[j]];
					sum2 += grid[num2[j]][num2[i]];
				}
			}
			
			int result = Math.abs(sum1-sum2);
			if(MIN > result) MIN = result;
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			selected[i] = true;
			solve(i+1, cnt+1);
			selected[i] = false;
		}
	}
	
}
