package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모의SW_1953_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] map;

	static class Ground {
		int x, y, cnt, d;

		public Ground(int x, int y, int cnt, int d) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve();

			System.out.println("#" + testcase + " " + CNT);
		} // tc

	} // main

	static int CNT;
	static boolean[][] visited;
	static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void solve() {
		Queue<Ground> q = new LinkedList<>();

		CNT++;
		visited[R][C] = true;
		switch (map[R][C]) {
		case 0:
			break;
		case 1: // 4방향 다 갈 수 있다
			for (int i = 0; i < 4; i++) {
				q.add(new Ground(R, C, L, i));
			}
			break;
		case 2:
			q.add(new Ground(R, C, L, 0));
			q.add(new Ground(R, C, L, 2));
			break;
		case 3:
			q.add(new Ground(R, C, L, 1));
			q.add(new Ground(R, C, L, 3));
			break;
		case 4:
			q.add(new Ground(R, C, L, 2));
			q.add(new Ground(R, C, L, 3));
			break;
		case 5:
			q.add(new Ground(R, C, L, 0));
			q.add(new Ground(R, C, L, 3));
			break;
		case 6:
			q.add(new Ground(R, C, L, 0));
			q.add(new Ground(R, C, L, 1));
			break;
		case 7:
			q.add(new Ground(R, C, L, 1));
			q.add(new Ground(R, C, L, 2));
			break;
		}
		while (!q.isEmpty()) {
			Ground g = q.poll();
			if (g.cnt == 1)
				continue;

			int curPipe = map[g.x][g.y];
			List<Integer> pipes = getPipes(curPipe, g.d);
			int nx = g.x + dir[n][0];
			int ny = g.y + dir[n][1];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0
					|| !pipes.contains(map[nx][ny]))
				continue;
			visited[nx][ny] = true;
			CNT++;
			q.add(new Ground(nx, ny, g.cnt - 1, n));
		}

	} // while

	} // solve

	static List<Integer> getPipes(int curPipe, int d) {
		List<Integer> dirs = new ArrayList<>();
		switch (curPipe) {
		case 1: // 4방향 다 갈 수 있다
			for (int i = 0; i < 4; i++) {
				dirs.add(i);
			}
			break;
		case 2:
			dirs.add(0);
			dirs.add(2);
			break;
		case 3:
			dirs.add(1);
			dirs.add(3);
			break;
		case 4:
			dirs.add(2);
			dirs.add(3);
			break;
		case 5:
			dirs.add(0);
			dirs.add(3);
			break;
		case 6:
			dirs.add(0);
			dirs.add(1);
			break;
		case 7:
			dirs.add(1);
			dirs.add(2);
			break;
		}

		List<Integer> list = new ArrayList<>();
		for (int dir : dirs) {
			switch (dir) {
			case 0:
				list.add(1);
				list.add(2);
				list.add(5);
				list.add(6);
				break;
			case 1:
				list.add(1);
				list.add(3);
				list.add(6);
				list.add(7);
				break;
			case 2:
				list.add(1);
				list.add(3);
				list.add(6);
				list.add(7);
				break;
			case 3:
				list.add(1);
				list.add(3);
				list.add(4);
				list.add(5);
				break;
			}
		}

		return list;
	} // getDirs
}
