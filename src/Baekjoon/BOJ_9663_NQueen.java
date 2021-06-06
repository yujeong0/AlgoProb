package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_NQueen {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		System.out.println(new BOJ_9663_NQueen().solution());
	} // main
	
	static int ANS, N;
	static boolean [][] board;
	
	public int solution() {
		solve(0);
		return ANS;
	} // sol
	
	public void solve(int curR) {
		if(curR == N) {
			ANS++;
			return;
		}

		for (int j = 0; j < N; j++) {
			if(isPossible(curR, j)) {
				board[curR][j] = true;
				solve(curR+1);
				board[curR][j] = false;
			}
		}
	} // solve
	
	int[][] dir = new int[][] { {-1, -1},{-1, 1} };
	public boolean isPossible(int r, int c) {
		for (int d = 0; d < 2; d++) {
			int nr = r;
			int nc = c;
			while(true) {
				nr += dir[d][0];
				nc += dir[d][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;
				if(board[nr][nc]) return false;
			}
		}
		for (int i = 0; i < r; i++) {
			if(board[i][c]) return false;
		}
		return true;
	} // isPossible
}
