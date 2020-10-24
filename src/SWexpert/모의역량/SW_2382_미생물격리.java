package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2382_미생물격리 {
	static class Microbe {
		int x, y, d, size;

		public Microbe(int x, int y, int size, int d) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Microbe [x=" + x + ", y=" + y + ", d=" + d + ", size=" + size + "]";
		}
		
	}
	static int N, M, K,totalSum;
	static int[][] dir = { {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};	// x, 상 1, 하2, 좌3, 우4
	static List<Integer>[][] grid;
	static int[][] visited;
	static boolean[][] collided;
	static Microbe[] mis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			totalSum = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 그리드 크기
			M = Integer.parseInt(st.nextToken());	// 몇 초
			K = Integer.parseInt(st.nextToken());	// 미생물수
			
			grid = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = new ArrayList<>();
				}
			}
			
			visited = new int[N][N];
			collided = new boolean[N][N];
			
			mis = new Microbe[K];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				mis[i] = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} // end of input
			
			sb.append("#" + testcase + " " + solve() + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
	private static int solve() {
		for (int m = 0; m < K; m++) {	// 모든 미생물 위치에 넣기
			grid[mis[m].x][mis[m].y].add(m);
		}
		
		for (int time = 0; time < M; time++) {	// M초 동안
			
			for (int m = 0; m < K; m++) {	// 모든 미생물 다 이동
				if(mis[m].size == -1) continue;	// 죽은 애들은 pass
				int tmp = 0;
				for (int n : grid[mis[m].x][mis[m].y]) {
					if(n == m) {
						grid[mis[m].x][mis[m].y].remove(tmp);
						break;
					}
					tmp++;
				}
				
				mis[m].x += dir[mis[m].d][0];
				mis[m].y += dir[mis[m].d][1];
				
				if(isInMedicine(mis[m].x, mis[m].y)) {	// 이동했는데 약품에 갔다
					mis[m].size /= 2;
					if(mis[m].size < 1) {
						mis[m].size = -1;
						continue;
					}
					switch(mis[m].d) {
					case 1: mis[m].d = 2; break;
					case 2: mis[m].d = 1; break;
					case 3: mis[m].d = 4; break;
					case 4: mis[m].d = 3; break;
					}
				}
				
				grid[mis[m].x][mis[m].y].add(m);
			} // m
			
			int max, maxNum, sum;
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < N-1; j++) {
					if(grid[i][j].size() > 1) {	// 미생물이 만나면
						max = -1;
						maxNum = -1;
						sum = 0;
						for(int n : grid[i][j]) {
							sum += mis[n].size;
							if(max < mis[n].size) {
								if(maxNum != -1) mis[maxNum].size = -1;
								max = mis[n].size;
								maxNum = n;
							}
							else {	// 같은 위치에 있는 미생물들 중에 미생물수가 최대가 아닌 애들은 -1로 죽이기
								mis[n].size = -1;
							}
						}
						
						grid[i][j].clear();
						grid[i][j].add(maxNum);
						mis[maxNum].size = sum;
					}
				}
			}
			
		} // time
		
		int count = 0;
		for (int m = 0; m < K; m++) {	// 남아있는 미생물 count
			if(mis[m].size == -1) continue;
			count += mis[m].size;
		}
		
		return count;
		
	} // solve
	
	private static boolean isInMedicine(int x, int y) {
		if(x == 0 || y == 0 || x == N-1 || y == N-1)
			return true;
		
		return false;
	}
	
} // end of class
