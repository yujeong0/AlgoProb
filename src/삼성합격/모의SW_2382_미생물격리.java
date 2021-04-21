package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_2382_미생물격리 {
	static class Microbe {
		int x, y, d, qty;

		public Microbe(int x, int y, int qty, int d) {
			this.x = x;
			this.y = y;
			this.qty = qty;
			this.d = d;
		}
	}
	static int N, M, K;
	static List<Integer>[][] grid;
	static Microbe[] micros;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N  = Integer.parseInt(st.nextToken());
			M  = Integer.parseInt(st.nextToken());
			K  = Integer.parseInt(st.nextToken());
			
			micros = new Microbe[K];
			grid = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = new ArrayList<>();
				}
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int qty = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				switch(d) {
				case 1:
					d = 0;
					break;
				case 4:
					d = 1;
					break;
				}
				micros[i] = new Microbe(x, y, qty, d);
			}
			
			System.out.println("#" + testcase + " " + solve());
		} // tc
	
	} //main

	static int[][] dir = new int[][] { {-1, 0},{0, 1},{1, 0},{0, -1} };
	private static int solve() {
		for (int t = 0; t < M; t++) {
			// 미생물 순회
			for (int i = 0; i < K; i++) {
				if(micros[i].qty == 0) continue;
				micros[i].x += dir[micros[i].d][0];
				micros[i].y += dir[micros[i].d][1];
				if(micros[i].x <= 0 || micros[i].x >= N-1 || micros[i].y <= 0 || micros[i].y >= N-1) {
					micros[i].qty /= 2;
					if(micros[i].qty > 0)
						micros[i].d = (micros[i].d + 2) % 4;
				}
				else {
					grid[micros[i].x][micros[i].y].add(i);
				}
			}
			
			// grid 순회
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(grid[i][j].size() > 1) {
						int maxIdx = 0, max = 0, sum = 0;
						for(int num : grid[i][j]) {
							if(max < micros[num].qty) {
								max = micros[num].qty;
								maxIdx = num;
							}
							sum += micros[num].qty;
						}
						
						micros[maxIdx].qty = sum;
						for(int num : grid[i][j]) {
							if(num != maxIdx) {
								micros[num].qty = 0;
							}
						}
					} // if
					grid[i][j].clear();
				} // j
			} // i
			
		} // t
		 
		int cnt = 0;
		for (int i = 0; i < K; i++) {
			if(micros[i].qty > 0) cnt += micros[i].qty;
		}
		
		return cnt;
	} // solve
}
