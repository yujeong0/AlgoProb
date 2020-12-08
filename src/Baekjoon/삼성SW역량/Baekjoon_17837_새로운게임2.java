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
	}
	
	static class Marker {
		int num, d;

		public Marker(int num, int d) {
			this.num = num;
			this.d = d;
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
			
			grid[x][y].markers.add(new Marker(i, d));
		}
		
		System.out.println(solve());
		
	} // main

	private static int solve() {
		int count = 0;
		while(true) {
			count++;
			if(count > 1000) {
				count = -1;
				break;
			}
			
			for (int cur = 1; cur <= K; cur++) {
			out:for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						int order = 0;
						for(Marker m : grid[i][j].markers) {
							if(m.num == cur) {
								move(i, j, m.d, order);
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
	private static void move(int x, int y, int d, int order) {
		int nx = x+dir[d][0];
		int ny = y+dir[d][1];
		
		if(nx > N || ny > N || nx <= 0 || ny <= 0 || grid[nx][ny].color == 2) {
			if(d == 1) d = 2;
			else if(d == 2) d = 1;
			else if(d == 3) d = 4;
			else if(d == 4) d = 3;
			nx = x+dir[d][0];
			ny = y+dir[d][1];
			
			grid[x][y].markers.get(order).d = d;
			if(nx > N || ny > N || nx <= 0 || ny <= 0 || grid[nx][ny].color == 2) {	// 한 번 더 체크
				return;
			}
		}
		
		switch(grid[nx][ny].color) {
		case 0:	// 흰
			for(int i = order; i < grid[x][y].markers.size(); i++) {
				grid[nx][ny].markers.add(grid[x][y].markers.get(i));
			}
			for (int i = grid[x][y].markers.size()-1; i >= order; i--) {
				grid[x][y].markers.remove(i);
			}
			
			break;
		case 1: // 빨
			for(int i = grid[x][y].markers.size()-1; i >= order; i--) {
				grid[nx][ny].markers.add(grid[x][y].markers.get(i));
			}
			for (int i = grid[x][y].markers.size()-1; i >= order; i--) {
				grid[x][y].markers.remove(i);
			}
			break;
		}
		
		if(grid[nx][ny].markers.size() >= 4) {
			endGame = true;
		}
	} //move
	
}
