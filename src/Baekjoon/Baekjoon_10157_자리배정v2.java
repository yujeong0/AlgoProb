package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10157_자리배정v2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();	// 가로크기
		int R = sc.nextInt();	// 세로크기
		int K = sc.nextInt();	// 대기번호
		
		sc.close();

		int[][] dir = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
		if(K > R*C) System.out.println("0");
		else {
			int[][] grid = new int[C+1][R+1];
			int d = 0, count = 0;
			int px, py;
			int x = 1, y = 0;	// 시작 위치
			while(true) {
				px = x + dir[d][0];
				py = y + dir[d][1];
				
				if(px > C || px < 1 || py < 1 || py > R || grid[px][py] != 0) {
					d++;
					if(d == 4) d = 0;
					px = x + dir[d][0];
					py = y + dir[d][1];
				}
				
				x = px;
				y = py;
				
				grid[x][y] = ++count;
				if(count == K) {
					System.out.println(x + " " + y);
//					printGrid(R,C,grid);
					break;
				}
			}
		}
		
	}	// end of main
	static void printGrid(int R, int C, int[][] grid) {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
