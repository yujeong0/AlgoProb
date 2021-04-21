package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모의SW_1953_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] map;

	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
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

			CNT = 0;
			solve();

			System.out.println("#" + testcase + " " + CNT);
		} // tc

	} // main

	static int CNT;
	static boolean[][] visited;
	static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void solve() {
		Queue<Pos> q = new LinkedList<>();

		visited[R][C] = true;
		q.add(new Pos(R, C, 1));

		while (!q.isEmpty()) {
			Pos p = q.poll();
			CNT++;
			if (p.cnt == L) continue;

			int curPipe = map[p.x][p.y];
			for (int d = 0; d < 4; d++) {
				if(!canGo(curPipe, d)) continue;
				int nx = p.x + dir[d][0];
				int ny = p.y + dir[d][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0
						|| !canGoNextPipe(map[nx][ny], d))
					continue;
				visited[nx][ny] = true;
				q.add(new Pos(nx, ny, p.cnt+1));
			}

		} // while

	} // solve

	static boolean canGo(int curPipe, int d) { // 현재 curPipe에서 d방향으로 갈 수 있는가?
		switch (curPipe) {
		case 1: return true;
		case 2:
			if(d == 0 || d == 2) return true;
			break;
		case 3:
			if(d == 1 || d == 3) return true;
			break;
		case 4:
			if(d == 0 || d == 1) return true;
			break;
		case 5:
			if(d == 1 || d == 2) return true;
			break;
		case 6:
			if(d == 2 || d == 3) return true;
			break;
		case 7:
			if(d == 0 || d == 3) return true;
			break;
		}
		
		return false;
	} // canGo
	
	static boolean canGoNextPipe(int pipeNum, int d) {
		switch (d) {
		case 0:
			if(pipeNum == 1 || pipeNum == 2 || pipeNum == 5 || pipeNum == 6) {
				return true;
			}
			break;
		case 1:
			if(pipeNum == 1 || pipeNum == 3 || pipeNum == 6 || pipeNum == 7) {
				return true;
			}
			break;
		case 2:
			if(pipeNum == 1 || pipeNum == 2 || pipeNum == 4 || pipeNum == 7) {
				return true;
			}
			break;
		case 3:
			if(pipeNum == 1 || pipeNum == 3 || pipeNum == 4 || pipeNum == 5) {
				return true;
			}
			break;
		}

		return false;
	} // getDirs
	
}
