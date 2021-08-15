package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작_dfs {
	static int N, M, H;
	static int[][] ori_ledder;
	
	static class Ledder implements Comparable<Ledder>{
		int cnt; // 사다리 추가로 놓은 개수
		int[][] ledder;	// 사다리 상태
		int[] last; // 마지막으로 사다리 놓은 위치
		public Ledder(int cnt, int[][] ledder, int[] last) {
			this.cnt = cnt;
			this.last = last;
			this.ledder = ledder;
		}
		@Override
		public int compareTo(Ledder o) {
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ori_ledder = new int[H+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			ori_ledder[a][b] = 1;
		}
		
		if(check()) {	// 자기 번호로 가는 사다리가 완성되면
			System.out.println(0);
		} else {
			if(N == 0) System.out.println(-1);
			else {
				solve(1, 1, 0);
				System.out.println(MIN > 3? -1 : MIN);
			}
		}
		
	} // main
	
	static int MIN = 4;
	private static void solve(int r, int c, int cnt) {
		if(cnt >= MIN-1) return;
		if(r >= H+1) {
			return;
		}
		
		if(c == N-1) {
			solve(r+1, 0, cnt);
		} else solve(r, c+1, cnt);
		
		if(ori_ledder[r][c] == 0) {
			if(c > 1 && ori_ledder[r][c-1] == 1) return;
			if(c < N && ori_ledder[r][c+1] == 1) return;
			
			ori_ledder[r][c] = 1;
			if(check()) {
				if(MIN > cnt+1) MIN = cnt+1;
			}
			if(c == N-1) {
				solve(r+1, 0, cnt+1);
			} else solve(r, c+1, cnt+1);
			ori_ledder[r][c] = 0;
		}
	} // solve

	private static boolean check() { // 자기 번호로 가는 지 체크하는 함수
		for (int num = 1; num <= N; num++) {
			
			int i = 1, j = num;
			while(true) {
				if(i == H+1) break;
				
				if(j > 1 && ori_ledder[i][j-1] == 1) { // 왼쪽에 있는 상황
					j--;
				} else if(ori_ledder[i][j] == 1){	// 오른쪽에 있는 상황
					j++;
				}
				i++;
			} // while
			
			if(j != num) return false;
		}
		
		return true;
	} // check
	
}
