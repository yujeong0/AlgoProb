package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_2117_홈방범서비스 {
	static int N, M, MAX;
	static int[][] map;
	static List<int[]> houses;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			houses = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) houses.add(new int[] {i, j});
				} 
			}
			
			int K;
			if(N % 2 == 0) K = N+1;
			else K = N;
			
			for (int k = K; k >= 1; k--) {
				if(MAX >= (k*k+(k-1)*(k-1))) break;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						solve(i, j, k);
					}
				}
			} 
			
			System.out.println("#" + testcase + " " + MAX);
		} // tc
	} // main

	private static void solve(int x, int y, int K) {
		int houseCnt = 0;
		for(int[] house : houses) {
			int distance = Math.abs(x-house[0]) + Math.abs(y-house[1]);
			if(distance <= K-1) houseCnt++;
		}
		
		if(houseCnt * M - (K*K+(K-1)*(K-1)) >= 0) {
			if(MAX < houseCnt) MAX = houseCnt;
		}
	} //solve
}
