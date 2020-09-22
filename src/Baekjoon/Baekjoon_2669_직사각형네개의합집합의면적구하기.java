package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2669_직사각형네개의합집합의면적구하기 {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = 4;	// 색종이 수
		
		int[][] grid = new int[101][101];	// 색종이 검은 영역 표시할 arr
		int[][] pos = new int[N][2];	// 색종이 왼쪽 아래 x,y 좌표
		int posx, posy;
		int a,b;
		for (int i = 0; i < N; i++) {
			posx = sc.nextInt();
			posy = sc.nextInt();
			pos[i][0] = posx;
			pos[i][1] = posy;
			
			a = sc.nextInt();	// 오른쪽 x
			b = sc.nextInt();	// 오른쪽 y
			
			// 색종이 영역에 +1 해준다.
			for (int x = posx; x < a; x++) {
				for (int y = posy; y < b; y++) {
					grid[x][y] = 1;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if(grid[i][j] == 1)
					sum += 1;
			}
		}
		
		System.out.println(sum);
		
		sc.close();
	}
	
	
}
