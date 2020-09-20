package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2563_색종이 {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	// 색종이 수
		
		int[][] grid = new int[100][100];	// 색종이 검은 영역 표시할 arr
		int[][] pos = new int[N][2];	// 색종이 왼쪽 아래 x,y 좌표
		int posx, posy;
		for (int i = 0; i < N; i++) {
			posx = sc.nextInt();
			posy = sc.nextInt();
			pos[i][0] = posx;
			pos[i][1] = posy;
			
			// 색종이 영역에 +1 해준다.
			for (int x = posx; x < posx+10; x++) {
				for (int y = posy; y < posy+10; y++) {
					grid[x][y] = 1;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(grid[i][j] == 1)
					sum += 1;
			}
		}
		
		System.out.println(sum);
		
		sc.close();
	}
	
	
}
