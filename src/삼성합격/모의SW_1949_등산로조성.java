package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의SW_1949_등산로조성 {
	static int N, K, MAX;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (max < map[i][j])
						max = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						visited[i][j] = true;
						solve(false, i, j, 1);
						visited[i][j] = false;
					}
				}
			}

			System.out.println("#" + testcase + " " + MAX);
		} // tc

	} // main

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;

	private static void solve(boolean isUse, int x, int y, int cnt) {
		if (MAX < cnt)
			MAX = cnt;

		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];

			if (isInBound(nx, ny) && !visited[nx][ny]) {
				int val = map[nx][ny];
				if (map[x][y] > val) {
					visited[nx][ny] = true;
					solve(isUse, nx, ny, cnt + 1);
					visited[nx][ny] = false;
				} else {
					if (!isUse) {
						int i = 0;
						while (i < K) {
							i++;
							map[nx][ny]--;
							if (map[nx][ny] < 0)
								break;
							if (map[x][y] > map[nx][ny]) {
								visited[nx][ny] = true;
								solve(true, nx, ny, cnt + 1);
								visited[nx][ny] = false;
							}
						} // while
						map[nx][ny] += i;
					}
				}
			}
		} // d

	} // solve

	static boolean isInBound(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
}
