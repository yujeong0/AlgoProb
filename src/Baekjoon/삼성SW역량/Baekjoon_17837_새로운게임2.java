package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_17837_새로운게임2 {
	static class Box {
		int color;
		List<Marker> markers = new ArrayList<>();
		
		public Box(int color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return "Box [color=" + color + ", markers=" + markers.toString() + "]";
		}
		
	}
	static class Marker {
		int num, x, y, d;

		public Marker(int num, int x, int y, int d) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Marker [num=" + num + ", x=" + x + ", y=" + y + ", d=" + d + "]";
		}
	}
	
	static int N, K;
	static Box[][] grid;
	static int[][] dir = { {0, 0},{0, 1},{0, -1},{-1, 0},{1, 0} }; // 0인덱스는 의미 x
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		grid = new Box[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				grid[i][j] = new Box(Integer.parseInt(st.nextToken()));
			}
		}
		
		int x, y, d;
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			grid[x][y].markers.add(new Marker(i, x, y, d));
		}
		
		System.out.println(solve());
		
	} // main

	private static int solve() {
		
		int count = 0;
		while(true) {
			if(count > 1000) {
				count = -1;
				break;
			}
			
			count++;
			
			for (int cur = 1; cur <= K; cur++) {
			out:for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						int order = 0;
						for(Marker m : grid[i][j].markers) {
							if(m.num == cur) {
								List<Marker> list = new ArrayList<>();
								Marker tmp;
								for (int k = order; k < grid[i][j].markers.size(); k++) {
									tmp = grid[i][j].markers.get(k);
									list.add(new Marker(tmp.num, tmp.x, tmp.y, tmp.d));
								}
								move(i, j, m.d, order, list);
								if(endGame) {
									return count;
								}
								break out;
							}
							order++;
						}
					}
				}
			} //cur
		}
		
		return count;
	} // solve

	static boolean endGame = false;
	private static void move(int x, int y, int d, int order, List<Marker> markers) {
		boolean changeDir = false;
		
		int nx = x+dir[d][0];
		int ny = y+dir[d][1];
		if(nx > N || ny > N || nx <= 0 || ny <= 0) {
			changeDir = true;

			nx -= dir[d][0];
			ny -= dir[d][1];
			if(d == 1) d = 2;
			else if(d == 2) d = 1;
			else if(d == 3) d = 4;
			else if(d == 4) d = 3;
			nx = x+dir[d][0];
			ny = y+dir[d][1];
			markers.get(0).d = d;
		}
		switch(grid[nx][ny].color) {
		case 0:	// 흰
			for (int i = grid[x][y].markers.size()-1; i >= order; i--) {
				grid[x][y].markers.remove(i);
			}
			
			for(Marker m : markers) {
				m.x = nx;
				m.y = ny;
				grid[nx][ny].markers.add(m);
			}
			
			break;
		case 1: // 빨
			for (int i = grid[x][y].markers.size()-1; i >= order; i--) {
				grid[x][y].markers.remove(i);
			}
			
			Marker m;
			for(int i = grid[x][y].markers.size()-1; i >= order; i--) {
				m = markers.get(i);
				m.x = nx;
				m.y = ny;
				grid[nx][ny].markers.add(m);
			}
			break;
		case 2: // 파
			if(changeDir) {
//				grid[x][y].markers.get(order).d = d;	// 이렇게 하면 안될 것 같은디?
				break;
			}
			
			if(d == 1) d = 2;
			else if(d == 2) d = 1;
			else if(d == 3) d = 4;
			else if(d == 4) d = 3;
			int nx2 = nx+dir[d][0];
			int ny2 = ny+dir[d][1];
			
			if(nx2 > N || ny2 > N || nx2 <= 0 || ny2 <= 0) {
				grid[x][y].markers.get(order).d = d;
				break;
//				nx2 -= dir[d][0];
//				ny2 -= dir[d][1];
//				if(d == 1) d = 2;
//				else if(d == 2) d = 1;
//				else if(d == 3) d = 4;
//				else if(d == 4) d = 3;
//				nx2 = nx+dir[d][0];
//				ny2 = ny+dir[d][1];
			}
			
			if(grid[nx2][ny2].color != 2) {	// 이동하려는 칸이 파란색 아니면 원래 있던 칸 지우고 nx ny 에 add
				for (int i = grid[x][y].markers.size()-1; i >= order; i--) {
					grid[x][y].markers.remove(i);
				}
				markers.get(0).d = d;	// 방향 바꿔줘야해!!!
				
				if(grid[nx2][ny2].color == 0) {	// 흰
					for(Marker mar : markers) {
						mar.x = nx;
						mar.y = ny;
						grid[nx2][ny2].markers.add(mar);
					}
				}
				else {	// 빨
					Marker mar;
					for(int i = grid[x][y].markers.size()-1; i >= order; i--) {
						mar = markers.get(i);
						mar.x = nx;
						mar.y = ny;
						grid[nx][ny].markers.add(mar);
					}
				}
			}
			else { // 이동하려는 칸이 파란색인 경우에는 그냥 가만히 있는다. 대신 방향 바꿔줘야함
				grid[x][y].markers.get(order).d = d;
			}
			break;
		}
		
		if(grid[nx][ny].markers.size() == K) {
			endGame = true;
		}
	} //move
	
}
