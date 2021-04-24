package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	static class Fish {
		int n, d;

		public Fish(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
	static class Shark {
		int x, y, d;
		
		public Shark(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static Fish[][] grid = new Fish[4][4];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				grid[i][j] = new Fish(n, d);
			}
		}
		
		int eat = grid[0][0].n;
		grid[0][0].n = -1;
		solve(grid, eat, new Shark(0, 0, grid[0][0].d));
		
		System.out.println(MAX);
	} // main

	static int[][] dir = { {0, 0}, {-1, 0},{-1, -1},{0, -1},{1, -1},{1, 0},{1, 1},{0, 1},{-1, 1} };
	static int MAX = 0;
	private static void solve(Fish[][] grid, int SUM, Shark shark) {
		// 상어 이동 끝
		if(MAX < SUM) MAX = SUM;
				
		// 물고기 이동
		int num = 1;
		while(true) {
			if(num == 17) break;
			
		out:for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(grid[i][j].n == num) {
						int cnt = 1;
						int nx = -1, ny = -1;
						while(true) {
							if(cnt == 9) break;

							nx = i + dir[grid[i][j].d][0];
							ny = j + dir[grid[i][j].d][1];
							
							if(!isInBound(nx, ny) || (shark.x == nx && shark.y == ny)) 
								grid[i][j].d = grid[i][j].d+1 == 9? 1 : grid[i][j].d+1;
							else {
								Fish tmp = grid[nx][ny];
								grid[nx][ny] = grid[i][j];
								grid[i][j] = tmp;
								break;
							}
							cnt++;
						}
						break out;
					}
				}
			}
			num++;
		} //while
		
		// 상어 이동
		int n = 1;
		while(true) {
			int nx = shark.x + (dir[shark.d][0] * n);
			int ny = shark.y + (dir[shark.d][1] * n);
			
			if(!isInBound(nx, ny)) break;
			if(grid[nx][ny].n != -1) {
				Fish[][] copy = copyGrid(grid);
				copy[nx][ny].n = -1;
				Shark newShark = new Shark(nx, ny, grid[nx][ny].d);
				solve(copy, SUM+grid[nx][ny].n, newShark);
			}
			n++;
		}
			
	} // solve
	
	static Fish[][] copyGrid(Fish[][] grid) {
		Fish[][] copy = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Fish f = grid[i][j];
				copy[i][j] = new Fish(f.n, f.d);
			}
		}
		
		return copy;
	}
	static boolean isInBound(int x, int y) {
		if(x < 0 || x >= 4 || y < 0 || y >= 4) return false;
		
		return true;
	}
}
