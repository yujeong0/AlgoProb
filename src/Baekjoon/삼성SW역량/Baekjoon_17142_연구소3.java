package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17142_연구소3 {
	static class Virus {
		int x, y, time;

		public Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Virus [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
		
	}
	static int N, M, TIME = Integer.MAX_VALUE;
	static int[][] grid;
	static boolean[][] visited;
	static List<Virus> viruses = new ArrayList<>();
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static int zeroCnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		visited = new boolean[N][N];
		selected = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == 2) {
					viruses.add(new Virus(i, j, 0));
				}
				else if(grid[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		if(zeroCnt == 0) System.out.println(0);
		else {
			combi(0, 0);
			if(TIME == Integer.MAX_VALUE) TIME = -1;
			System.out.println(TIME);
		}
		
	} // main
	
	static int[] selected;
	private static void combi(int start, int cnt) {
		if(cnt == M) {
//			System.out.println(Arrays.toString(selected));
			
//			TIME = Math.min(TIME, bfs());
//			if(selected[0] == 9) {
//				System.out.println();
//			}
			int tmp = bfs();
			if(TIME > tmp) 
				TIME = tmp;
			return ;
		}
		for(int i = start; i < viruses.size(); i++) {
			selected[cnt] = i;
			combi(i+1, cnt+1);
		}
	}
	
	private static int bfs() {
		int cnt = zeroCnt;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		Queue<Virus> q = new LinkedList<>();
		int x, y;
		for (int i = 0; i < M; i++) {
			x = viruses.get(selected[i]).x;
			y = viruses.get(selected[i]).y;
			q.offer(new Virus(x, y, 0));
			visited[x][y] = true;
		}
		
		Virus p;
		int nx, ny;
		
		int time = 0;
		while(!q.isEmpty()) {
			p = q.poll();
			if(time < p.time) time = p.time;
			
			for (int d = 0; d < 4; d++) {
				nx = p.x + dir[d][0];
				ny = p.y + dir[d][1];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && grid[nx][ny] != 1) {
					if(grid[nx][ny] == 0)
						cnt--;
					if(cnt <= 0) {
						return p.time+1;
					}
					
					if(isZero(nx, ny)) {
						q.offer(new Virus(nx, ny, p.time+1));
					}
					else {
						if(grid[nx][ny] == 0) q.offer(new Virus(nx, ny, p.time+1));
					}
					visited[nx][ny] = true;
				}
			}
			
		}
		if(cnt <= 0) return time;
		else return Integer.MAX_VALUE;
	}
	
	private static boolean isZero(int x, int y) {
		int nx, ny;
		for (int d = 0; d < 4; d++) {
			nx = x + dir[d][0];
			ny = y + dir[d][1];
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if(grid[nx][ny] == 0 && !visited[nx][ny]) {
					return true;
				}
				else if(grid[nx][ny] == 2 && !visited[nx][ny]) {
					return true;
				}
			}
		}
		
		return false;
	}
	
} // class
