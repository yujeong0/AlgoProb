package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1953_탈주범검거 {
	static class Position {
		int x, y, depth;
		Position(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 세로
			M = Integer.parseInt(st.nextToken());	// 가로
			R = Integer.parseInt(st.nextToken());	// 맨홀 세로 위치
			C  = Integer.parseInt(st.nextToken());	// 맨홀 가로 위치
			L  = Integer.parseInt(st.nextToken());	// 탈출 후 소요된 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(L == 1) sb.append("#").append(testcase).append(" 1\n");
			else sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
		} 
		
		System.out.println(sb.toString());
		
	} // end of main
	
	private static int solve() {
		int totalCnt = 0;
		Queue<Position> q = new LinkedList<>();
		
		q.offer(new Position(R, C, 1));
		visited[R][C] = true;
		
		Position p;
		while(!q.isEmpty()) {
			p = q.poll();
			totalCnt++;
			if(p.depth >= L) continue;
			
			int x,y;
			for (int d = 0; d < 4; d++) {
				switch(map[p.x][p.y]) {
				case 2:
					if(d == 1 || d == 3) continue;
					break;
				case 3:
					if(d == 0 || d == 2) continue;
					break;
				case 4:
					if(d == 2 || d == 3) continue;
					break;
				case 5:
					if(d == 0 || d == 3) continue;
					break;
				case 6:
					if(d == 0 || d == 1) continue;
					break;
				case 7:
					if(d == 1 || d == 2) continue;
					break;
				}
				
				x = p.x + dir[d][0];
				y = p.y + dir[d][1];
				
				if(isInBound(x, y) && !visited[x][y] && map[x][y] != 0) {
					switch(d) {
                    case 0: // 현재위치에서 상으로 갔다면
                       if(map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 7) continue;
                       break;
                    case 1: // 우
                       if(map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 5) continue;
                       break;
                    case 2: // 하
                       if(map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 6) continue;
                       break;
                    case 3: // 좌
                       if(map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 7) continue;
                       break;
					}
					
					
//					switch(map[p.x][p.y]) {
//					case 1:
//						if(d == 0 && (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 7)) continue;
//						if(d == 1 && (map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 5)) continue;
//						if(d == 2 && (map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 6)) continue;
//						if(d == 3 && (map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 7)) continue;
//						break;
//					case 2:
//						if(d == 0 && (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 7)) continue;
//						if(d == 2 && (map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 6)) continue;
//						break;
//					case 3:
//						if(d == 1 && (map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 5)) continue;
//						if(d == 3 && (map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 7)) continue;
//						break;
//					case 4:
//						if(d == 0 && (map[x][y] == 3 || map[x][y] == 4 ||map[x][y] == 7)) continue;
//						if(d == 1 && (map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 5)) continue;
//						break;
//					case 5:
//						if(d == 1 && (map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 4)) continue;
//						if(d == 2 && (map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 6)) continue;
//						break;
//					case 6:
//						if(d == 2 && (map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 5)) continue;
//						if(d == 3 && (map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 7)) continue;
//						break;
//					case 7:
//						if(d == 0 && (map[x][y] == 3 || map[x][y] == 7 || map[x][y] == 4)) continue;
//						if(d == 3 && (map[x][y] == 2 || map[x][y] == 7 || map[x][y] == 6)) continue;
//						break;
//					}
					
					q.offer(new Position(x, y, p.depth+1));
					visited[x][y] = true;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		return totalCnt;
		
	} // solve
	
	private static boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y < 0 || y >= M) 
			return false;
			
		return true;
	}
	
	
} // end of class
