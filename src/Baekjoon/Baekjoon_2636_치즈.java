package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2636_치즈 {
	static class Position{
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, time, REMAIN;
	static int[][] grid;
	static boolean[][] visited;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		grid = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(time + "\n" + REMAIN);
		
	} // main
	
	private static void bfs() {
		Queue<Position> q = new LinkedList<>();
		
		time = 1;
		while(true) {
			
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			q.clear();
			
			q.offer(new Position(0,0));
			visited[0][0] = true;
			
			Position p;
			int x, y;
			while(!q.isEmpty()) {
				p = q.poll();
				
				for (int d = 0; d < 4; d++) {
					x = p.x + dir[d][0];
					y = p.y + dir[d][1];
				
					if(x >= 0 && x < N && y < M && y >= 0 && !visited[x][y]) {
						if(grid[x][y] >= 1) 
							grid[x][y] = 2;
						else {	// 공기 == 0 인 경우
							q.offer(new Position(x, y));
							visited[x][y] = true;
						}
					}
				}
				
			}
			
			REMAIN = checkAllMelt();
			if(REMAIN != -1) break;
			
			for (int i = 0; i < N; i++) {	// 녹을 치즈들 모두 녹여라~
				for (int j = 0; j < M; j++) {
					if(grid[i][j] == 2) grid[i][j] = 0;
				}
			}
			
			time++;
		} // while
	} // solve
	
	private static int checkAllMelt() {
		int count = 0;
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				if(grid[i][j] == 1) return -1;	// -1이면 모두 녹기 직전 상태가 아님!!
				if(grid[i][j] == 2) count++;
			}
		}
		
		return count;
	}
	
} // class
