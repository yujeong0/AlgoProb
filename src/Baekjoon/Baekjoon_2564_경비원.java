package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_2564_경비원 {

	static int[] targetPos;
	static int sum  = 0, N, M;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 가로길이
		M = sc.nextInt();	// 세로길이
		int[][] grid = new int[N][M];
		visited = new boolean[N][M];
		
		List<int[]> list = new ArrayList<>();
		int numStore = sc.nextInt();	// 상점 개수
		int d, p;
		// 동근이의 위치는 numStore로 표시됨
		for (int i = 0; i <= numStore; i++) {
			d = sc.nextInt();	// 방향
			p = sc.nextInt();	// 거리
			
			switch(d) {
			case 1:
				grid[0][p] = i;
				list.add(new int[] {0, p});
				break;
			case 2:
				grid[N-1][p] = i;
				list.add(new int[] {N-1, p});
				break;
			case 3:
				grid[p][0] = i;
				list.add(new int[] {p, 0});
				break;
			case 4:
				grid[p][N-1] = i;
				list.add(new int[] {p, N-1});
				break;
			}
		}
		
		sc.close();
		
		int[] dongpos = list.remove(numStore);	// 리스트의 마지막 위치가 동근이 위치
		int targetNum = 1;
		for(int[] pos: list) {
			targetPos = pos;
			
			
			
			targetNum++;
		}
		
		
		
	}
	
	private void bfs(int startx, int starty) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { startx, starty, 0 });
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			if(pos[0] == targetPos[0] && pos[1] == targetPos[1]) {
				sum += pos[2];	// 최단 거리 찾았으니 이동거리 합해주고 끝냄
				return;
			}
			
		}
	}
	
	private boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y < 0 || y >= M)
			return false;
		
		return true;
	}
	
}
