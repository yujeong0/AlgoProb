package Baekjoon.bfs_dfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_보물섬 {
	static class Position{
		int x, y, count;
		Position(int x, int y, int count){
			this.x = x;
			this.y = y;
			this.count = count;
		}
		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
		
	}
	static int R, C;
	static char[][] map;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	
	public static int execute() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// 세로크기
		C = Integer.parseInt(st.nextToken());	// 가로크기
		
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();	
		}
		
		visited = new boolean[R][C];
		int max = -1;
		for (int i = 0; i < R; i++) {
			Arrays.fill(visited[i], false);
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'L')
					max = Math.max(max, bfs(i, j));	// 모든 L에서 bfs를 시작하여 가장 먼 L까지의 최단거리를 구한다.
			}
		}
		
		return max; // 리턴값을 수정하세요
	} // end of execute

	private static int bfs(int startx, int starty) {
		Queue<Position> q = new LinkedList<>();
		q.offer(new Position(startx, starty, 1));
		
		Position p = null;
		int x, y;
		while(!q.isEmpty()) {
			p = q.poll();
			visited[p.x][p.y] = true;
			
			for (int d = 0; d < 4; d++) {
				x = p.x + dir[d][0];
				y = p.y + dir[d][1];
				
				if(x < R && x >= 0 && y >= 0 && y < C && !visited[x][y] && map[x][y] == 'L') {
					q.offer(new Position(x, y, p.count+1));
				}
			}
			
		}
		
		return p.count;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(execute());
	}

}	// end of class
