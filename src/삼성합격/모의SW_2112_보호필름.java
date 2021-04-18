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
			
			if(isSuccess(film)) MIN = 0;
			else {
				for (int i = 1; i < D; i++) {
					if(i >= MIN) break;
					R = i;
					combi(0, 0);
				}
			}
			
			System.out.println("#" + testcase + " " + MIN);
		} //tc
	}
	
	static boolean[] selected;
	static int MIN;
	static int[] list;
	static int R;
	private static void combi(int start, int cnt) {
		if(cnt >= MIN) return;
		if(cnt == R) {
			int[] arr = new int[D];
			solve2(0, arr);
			return;
		}
		
		for(int i = start; i < D; i++) {
			selected[i] = true;
			combi(i+1, cnt+1);
			selected[i] = false;
		}
		
	} // combi
	
	
	private static void solve2(int row, int[] arr) {
		if(row == D) {
			int[][] copy = copyGrid(film);
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] > -1) {
					for (int j = 0; j < W; j++) {
						copy[i][j] = arr[i];
					}
				}
			}
			
			if(isSuccess(copy)) {
				if(MIN > R) MIN = R;
			}
			
			return;
		}
		
		if(selected[row]) {
			arr[row] = 0;
			solve2(row+1, arr);
			arr[row] = 1;
			solve2(row+1, arr);
		}
		else {
			arr[row] = -1;
			solve2(row+1, arr);
		}
		
	} // solve2

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
