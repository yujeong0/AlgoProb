package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼 {
	static class FireBall {
		int r,c,m,s,d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static int N, M, K;
	static List<FireBall> list = new ArrayList<>();
	static List<Integer>[][] grid;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		grid = new ArrayList[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				grid[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r,c,m,s,d));
		}
		
		System.out.println(solve());
	} //main
	
	static int[][] dir = { {-1, 0},{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1},{-1, -1} };
	private static int solve() {
		// 총 K번
		for (int k = 0; k < K; k++) {
			// 파이어볼 순회
			for (int i = 0; i < list.size(); i++) {
				FireBall fb = list.get(i);
				if(fb.m == 0) continue;
				int nr = fb.r + dir[fb.d][0] * fb.s;
				int nc = fb.c + dir[fb.d][1] * fb.s;
				
				if(!isInBound(nr, nc)) {
					if(nr > N) 	nr = nr % N;
					if(nr < 1) nr = N- (Math.abs(nr) % N);
					if(nc > N) 	nc = nc % N;
					if(nc < 1) nc = N - (Math.abs(nc) % N);
				}
				
				fb.r = nr;
				fb.c = nc;
				grid[nr][nc].add(i);
			} // m
			
			// grid 순회
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(grid[i][j].size() > 1) {
						FireBall fb = list.get(grid[i][j].get(0));
						int totalM = fb.m, totalS = fb.s;
						fb.m = 0;
						
						boolean isAllSame = true;
						// 첫번째 d가 짝수면 1, 홀수면 0
						int number = fb.d % 2 == 0 ? 1 : 0;
						for(int n = 1; n < grid[i][j].size(); n++) {
							fb = list.get(grid[i][j].get(n));
							totalM += fb.m;
							totalS += fb.s;
							if(number == 1 && fb.d % 2 != 0) isAllSame = false;
							else if(number == 0 && fb.d % 2 == 0) isAllSame = false;
							
							fb.m = 0;	// 계산 끝난 파이어볼 없애고 새로운 파이어볼로!
						}
						
						int m = totalM / 5, s = totalS / grid[i][j].size();
						int[] d = new int[4];
						if(isAllSame) {
							int n = 0;
							for (int l = 0; l < 4; l++) {
								d[l] = n;
								n += 2;
							}
						}
						else {
							int n = 1;
							for (int l = 0; l < 4; l++) {
								d[l] = n;
								n += 2;
							}
						}
						
						// 새로운 파이어볼 4개 list에 추가
						for (int l = 0; l < 4; l++) {
							list.add(new FireBall(i, j, m, s, d[l]));
						}
					}
					
					// 계산 끝난 칸은 비워줌
					grid[i][j].clear();
					
				} // j
			} // i
			
		} // k
		
		int sum = 0;
		for(FireBall fb : list) {
			sum += fb.m;
		}
		
		return sum;
	} //solve
	
	static boolean isInBound(int x, int y) {
		if(x < 1 || x > N || y < 1 || y > N)
			return false;
		
		return true;
	}
}
