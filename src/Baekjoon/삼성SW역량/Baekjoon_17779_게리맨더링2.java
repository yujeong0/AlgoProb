package Baekjoon.삼성SW역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_17779_게리맨더링2 {
	static int N;
	static int[][] map;
	static int[][] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		input = new int[N+1][N+1];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setVal();
		
		System.out.println(MIN);
	} // main
	
	private static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int MIN = Integer.MAX_VALUE;
	private static void setVal() {	// x ,y ,D1,D2 정하기
		int d1, d2, x = 1, y = 0;	
		
		// x, y 값 변경
		while(true) {
			y++;
			if(y > N) {
				x++;
				if(x > N) {
					return;
				}
				y = 1;
			}
//			System.out.println("x=" + x + " y=" + y);
			// x, y 에 따른 d1, d2 설정
			d1 = 1;
			while(true) {
				d2 = 1;
				if(x+d1+d2 > N || y+d2 > N || y-d1 < 1) break;
				while(true) {
					if(x+d1+d2 > N || y+d2 > N || y-d1 < 1) break;
					
					setArea(d1, d2, x, y);	//1 ~ 5구역 배열에 값 넣기
					
//					System.out.println("x=" + x + " y=" + y + " d1=" + d1 + " d2=" + d2);
//					printMap();

					MIN = Math.min(MIN, calculate(x, y, d1, d2));

					d2++;
				}
				d1++;
			}
		}
		
		
	}
	
	private static int calculate(int x, int y, int d1, int d2) {
		int[] arr = new int[5];
		// 1구역
		for (int r = 1; r < x+d1; r++) {
			for (int c = 1; c <= y; c++) {
				if(map[r][c] == 1) {
					arr[0] += input[r][c];
				}
			}
		}
		// 2구역
		for (int r = 1; r <= x+d2; r++) {
			for (int c = y+1; c <= N; c++) {
				if(map[r][c] == 2) {
					arr[1] += input[r][c];
				}
			}
		}
		// 3구역
		for (int r = x+d1; r <= N; r++) {
			for (int c = 1; c < y-d1+d2; c++) {
				if(map[r][c] == 3) {
					arr[2] += input[r][c];
				}
			}
		}
		// 4구역
		for (int r = x+d2+1; r <= N; r++) {
			for (int c = y-d1+d2; c <= N; c++) {
				if(map[r][c] == 4) {
					arr[3] += input[r][c];
				}
			}
		}
		// 5구역
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(map[r][c] == 5) {
					arr[4] += input[r][c];
				}
			}
		}
		
		Arrays.sort(arr);
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println();
		
		return arr[4]-arr[0];
		
	}

	private static void setArea(int d1, int d2, int x, int y) {
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = 0;	
			}
		}
		
		// 5구역 경계선 설정
		int rr = x, cc = y;	
		while(true) {	
			map[rr][cc] = 5;
			if(rr == x+d1 && cc == y-d1) break;
			rr++;
			cc--;
		}
		while(true) {	
			map[rr][cc] = 5;
			if(rr == x+d1+d2 && cc == y-d1+d2) break;
			rr++;
			cc++;
		}
		rr = x; cc = y;
		while(true) {	
			map[rr][cc] = 5;
			if(rr == x+d2 && cc == y+d2) break;
			rr++;
			cc++;
		}
		while(true) {	
			map[rr][cc] = 5;
			if(rr == x+d1+d2 && cc == y+d2-d1) break;
			rr++;
			cc--;
		}
		
		// 경계선 내 5구역 채우기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == x && j == y) continue;
				if(i == x+d1+d2 && j == y-d1+d2) break;
				
				if(map[i][j] == 5) {
					rr = i;
					cc = j+1;
					while(map[rr][cc] != 5) {
						map[rr][cc++] = 5;
					}
					break;
				}
			}
		}
		
		// 1구역 설정
		for (int r = 1; r < x+d1; r++) {
			for (int c = 1; c <= y; c++) {
				if(map[r][c] != 5)
					map[r][c] = 1;
			}
		}
		// 2구역 설정
		for (int r = 1; r <= x+d2; r++) {
			for (int c = y+1; c <= N; c++) {
				if(map[r][c] != 5)
					map[r][c] = 2;
			}
		}
		// 3구역 설정
		for (int r = x+d1; r <= N; r++) {
			for (int c = 1; c < y-d1+d2; c++) {
				if(map[r][c] != 5)
					map[r][c] = 3;
			}
		}
		// 4구역 설정
		for (int r = x+d2+1; r <= N; r++) {
			for (int c = y-d1+d2; c <= N; c++) {
				if(map[r][c] != 5)
					map[r][c] = 4;
			}
		}
		
		
	} // solve
}
