package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_3190_뱀 {

	static int N, K, L;
	static int[][] moves;
	static int[][] grid;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	static List<int[]> snake = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// grid 크기
		K = Integer.parseInt(br.readLine());	// 사과 개수
		grid = new int[N+1][N+1];
		
		StringTokenizer st;
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			grid[x][y] = 1;	// 사과 있는 곳은 1로 설정
		}
		
		L = Integer.parseInt(br.readLine());	// 방향 변환 횟수
		moves = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			moves[i][0] = Integer.parseInt(st.nextToken());
			moves[i][1] = st.nextToken().charAt(0) == 'L'? 0 : 1;	// L(왼쪽)이면 0, R(오른쪽)이면, 1
		}
		
		System.out.println(solve()+1);
		
	} //main
	
	private static int solve() {
		snake.add(new int[] {1, 1});
		
		grid[1][1] = 2;	// 뱀은 2
		
		int headX = 1, headY = 1;
		int time = 0, curDir = 1, moveCnt = 0;
		while(true) { 
			for (int t = moveCnt; t < L; t++) {
				if(moves[t][0] == time) {
					moveCnt++;
					if(moves[t][1] == 0) curDir = (curDir-1 < 0? curDir+3 : curDir-1) % 4;
					else curDir = (curDir+1) % 4;
				}
			}
			headX += dir[curDir][0];
			headY += dir[curDir][1];
			
			if(headX < 1 || headY < 1 || headX > N || headY > N || grid[headX][headY] == 2) {
				return time;
			}
			
			if(grid[headX][headY] == 1) {	// 사과면 몸길이+1
				snake.add(new int[] {headX, headY});
				grid[headX][headY] = 2;	// 뱀은 2
			}
			else {
				int[] tmp = snake.remove(0);
				grid[tmp[0]][tmp[1]] = 0;	// 뱀 없어진 곳 0으로 돌려놓기
				
				snake.add(new int[] {headX, headY});
				grid[headX][headY] = 2;	// 뱀은 2
			}
			time++;
		} // while
		
	} // solve
} // class
