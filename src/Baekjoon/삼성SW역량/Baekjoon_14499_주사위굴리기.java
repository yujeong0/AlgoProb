package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14499_주사위굴리기 {
	static int N, M, x, y, K;
	static int[][] map;
	static int[][] dir = { {0,0},{0,1},{0,-1},{-1, 0},{1, 0} };	// [0]은 의미없는 숫자임
	static int[][] dice = {
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0},
	};	// 밑 면 인덱스 : (3, 1)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int d;
		for (int i = 0; i < K; i++) {
			d = Integer.parseInt(st.nextToken());
			
			x += dir[d][0];
			y += dir[d][1];
			
			if(x < N && y < M && x >= 0 && y >= 0) {
				int tmp;
				switch(d) {
				case 1:
					tmp = dice[3][1];
					dice[3][1] = dice[1][2];
					dice[1][2] = dice[1][1];
					dice[1][1] = dice[1][0];
					dice[1][0] = tmp;
					break;
				case 2:
					tmp = dice[3][1];
					dice[3][1] = dice[1][0];
					dice[1][0] = dice[1][1];
					dice[1][1] = dice[1][2];
					dice[1][2] = tmp;
					break;
				case 3:
					tmp = dice[0][1];
					dice[0][1] = dice[1][1];
					dice[1][1] = dice[2][1];
					dice[2][1] = dice[3][1];
					dice[3][1] = tmp;
					break;
				case 4:
					tmp = dice[3][1];
					dice[3][1] = dice[2][1];
					dice[2][1] = dice[1][1];
					dice[1][1] = dice[0][1];
					dice[0][1] = tmp;
					break;
				}
				if(map[x][y] == 0) {
					map[x][y] = dice[3][1];
				}
				else {
					dice[3][1] = map[x][y];
					map[x][y] = 0;
				}
				System.out.println(dice[1][1]);
			}
			else {
				x -= dir[d][0];
				y -= dir[d][1];
			}
		}
	
	} // main
	
	
}
