package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의SW_2112_보호필름 {
	static int D, W, K;
	static int[][] film;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new int[W];
			MIN = Integer.MAX_VALUE;
			selected = new boolean[D];
			
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				} 
			}

			solve(0, -1, 0);
			solve(0, 0, 1);
			solve(0, 1, 1);
			
			System.out.println("#" + testcase + " " + MIN);
		} //tc
	}
	
	static boolean[] selected;
	static int MIN;
	static int[] list;
	private static void solve(int row, int feature, int cnt) {
		if(MIN == 0 || cnt >= MIN) return;
		if(row < D) {
			if(feature > -1) {
				for (int j = 0; j < W; j++) {
					arr[row][j] = feature;
				}
			}
			if(isSuccess(arr)) {
				if(MIN > cnt) MIN = cnt;
				return;
			}
		}
		else if(row == D) return;
		
		list[row] = 0;
		solve(row+1, -1, arr, cnt);
		list[row] = 1;
		solve(row+1, 0, copyGrid(arr), cnt+1);
		list[row] = -1;
		solve(row+1, 1, copyGrid(arr), cnt+1);
		
	} //solve
	
	static boolean isSuccess(int[][] arr) {
		for (int j = 0; j < W; j++) {
			boolean suc = false;
		out:for (int i1 = 0; i1 <= D-K; i1++) {
				int val = arr[i1][j];
				int cnt = 1;
				if(cnt >= K) {
					suc = true;
					break out;
				}
				for (int i2 = i1+1; i2 < D; i2++) {
					if(arr[i2][j] != val) {
						i1 += cnt-1;
						break;
					}
					cnt++;
					if(cnt >= K) {
						suc = true;
						break out;
					}
				}
			}
			if(!suc) return false;
		}
		
		return true;
	}// isSuccess
	
	static int[][] copyGrid(int[][] arr) {
		int[][] copy = new int[D][W];
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = arr[i][j];
			} 
		}
		return copy;
	}
} //main
