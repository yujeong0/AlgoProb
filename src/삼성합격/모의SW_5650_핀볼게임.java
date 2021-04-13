package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 모의SW_5650_핀볼게임 {
	static int N, MAX, score;
	static int[][] grid;
	static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Map<Integer, int[]> wormholes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			N = Integer.parseInt(br.readLine().trim());
			wormholes = new HashMap<>();
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] >= 6 && grid[i][j] <= 10) {
						if (wormholes.containsKey(grid[i][j])) {
							wormholes.put(grid[i][j] - 5, new int[] { i, j });
						} else {
							wormholes.put(grid[i][j], new int[] { i, j });
						}
					}
				}
			} // input

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							score = 0;
							solve2(i, j, d);
							if(MAX < score) MAX = score;
						}
					}
				}
			}

			sb.append("#").append(testcase).append(" ").append(MAX).append("\n");
		} // t
		System.out.println(sb.toString());

	} // main

	static void solve2(int startx, int starty, int direction) {
		int curx = startx + dir[direction][0];
		int cury = starty + dir[direction][1];
		
		while (true) {
			if (curx >= N || curx < 0 || cury >= N || cury < 0) { // 벽에 부딪히면
				direction = (direction + 2) % 4;
				curx += dir[direction][0];
				cury += dir[direction][1];
				score++;
			}
			if (curx == startx && cury == starty) return;
			
			int now = grid[curx][cury];
			if (now == -1) return;
			
			if (now >= 1 && now <= 5) { // 삼각형 & 사각형
				if (now == 1 && (direction == 2 || direction == 3)) {
					switch(direction) {
					case 2:
						direction = 1;
						break;
					case 3:
						direction = 0;
						break;
					}
					curx += dir[direction][0];
					cury += dir[direction][1];
					score++;
				} else if (now == 2 && (direction == 0 || direction == 3)) {
					switch(direction) {
					case 0:
						direction = 1;
						break;
					case 3:
						direction = 2;
						break;
					}
					curx += dir[direction][0];
					cury += dir[direction][1];
					score++;
				} else if (now == 3 && (direction == 1 || direction == 0)) {
					switch(direction) {
					case 1:
						direction = 2;
						break;
					case 0:
						direction = 3;
						break;
					}
					curx += dir[direction][0];
					cury += dir[direction][1];
					score++;
				} else if (now == 4 && (direction == 2 || direction == 1)) {
					switch(direction) {
					case 1:
						direction = 0;
						break;
					case 2:
						direction = 3;
						break;
					}
					curx += dir[direction][0];
					cury += dir[direction][1];
					score++;
				} else {
					direction = (direction + 2) % 4;
					curx += dir[direction][0];
					cury += dir[direction][1];
					score++;
				}
			} else if (now >= 6 && now <= 10) { // 웜홀
				int[] pos = wormholes.get(now);
				if (pos[0] == curx && pos[1] == cury) {
					pos = wormholes.get(now - 5);
				}
				curx = pos[0] + dir[direction][0];
				cury = pos[1] + dir[direction][1];
			} else if (now == 0) { // 빈칸이면 그냥 전진
				curx += dir[direction][0];
				cury += dir[direction][1];
			}
			
		}


	} // solve2

//	static void solve(int startx, int starty, int direction, int curx, int cury, int score) {
//		if (curx >= N || curx < 0 || cury >= N || cury < 0) { // 벽에 부딪히면
//			direction = (direction + 2) % 4;
//			solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score + 1);
//			return;
//		}
//		if (curx == startx && cury == starty) {
//			if (MAX < score)
//				MAX = score;
//			return;
//		}
//
//		int now = grid[curx][cury];
//		if (now == -1) {
//			if (MAX < score)
//				MAX = score;
//			return;
//		} else if (now >= 1 && now <= 5) { // 삼각형 & 사각형
//			if (now == 1 && (direction == 2 || direction == 3)) {
//				if (direction == 2)
//					direction = 1;
//				if (direction == 3)
//					direction = 0;
//				solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score + 1);
//			} else if (now == 2 && (direction == 0 || direction == 3)) {
//				if (direction == 0)
//					direction = 1;
//				if (direction == 3)
//					direction = 2;
//				solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score + 1);
//			} else if (now == 3 && (direction == 1 || direction == 0)) {
//				if (direction == 1)
//					direction = 2;
//				if (direction == 0)
//					direction = 3;
//				solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score + 1);
//			} else if (now == 4 && (direction == 2 || direction == 1)) {
//				if (direction == 1)
//					direction = 0;
//				if (direction == 2)
//					direction = 3;
//				solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score + 1);
//			} else {
//				solve(startx, starty, (direction + 2) % 4, curx - dir[direction][0], cury - dir[direction][1],
//						score + 1);
//			}
//		} else if (now >= 6 && now <= 10) { // 웜홀
//			int[] pos = wormholes.get(now);
//			if (pos[0] == curx && pos[1] == cury) {
//				pos = wormholes.get(now - 5);
//			}
//			solve(startx, starty, direction, pos[0] + dir[direction][0], pos[1] + dir[direction][1], score);
//		} else if (now == 0) { // 빈칸이면 그냥 전진
//			solve(startx, starty, direction, curx + dir[direction][0], cury + dir[direction][1], score);
//		}
//
//	} // solve
}
