package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2112_보호필름 {

	static int D, W, K, MIN;
	static int[][] grid;
	static int[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());	// 두께
			W = Integer.parseInt(st.nextToken());	// 가로
			K = Integer.parseInt(st.nextToken());	// 기준
			numbers = new int[D];
			grid = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(K==1) MIN = 0;
			else combi(0, 0);
			
			sb.append("#").append(testcase).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb.toString());
		
	} // end of main
	
	private static void combi(int cnt, int injectionCount) {
		if(injectionCount >= MIN) return;
		if(cnt == D) {
			int[][] copy = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					copy[i][j] = grid[i][j];
				}
			}
			int mCnt = 0;
			for (int i = 0; i < D; i++) {
				if(numbers[i] == -1) continue;
				
				mCnt++;
				for (int j = 0; j < W; j++) {
					copy[i][j] = numbers[i];
				}
			}
			
			if(passK(copy)) {
				if(MIN > mCnt) MIN = mCnt;
			}
			return;
		}
		
		// -1이면 약품x, 0이면 A, 1이면 B
		numbers[cnt] = -1;
		combi(cnt+1, injectionCount);
		
		numbers[cnt] = 0;
		combi(cnt+1, injectionCount+1);
		
		numbers[cnt] = 1;
		combi(cnt+1, injectionCount+1);
		
	} // combi
	
	private static boolean passK(int[][] copy) {
		int cur, cnt;
		boolean pass;
		for (int j = 0; j < W; j++) {
			cur = copy[0][j];
			cnt = 1;
			pass = false;
			for (int i = 1; i < D; i++) {
				if(cur == copy[i][j]) {
					cnt++;
					if(cnt == K) {
						pass = true;
						break;
					}
				}
				else {
					cur = copy[i][j];
					cnt = 1;
				}
			}
			
			if(!pass) return false;
		}
		
		return true;
	}
	
} // end of class
