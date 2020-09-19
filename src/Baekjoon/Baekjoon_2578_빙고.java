package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2578_빙고 {
	
	
	static boolean[][] selected = new boolean[5][5];
	static int[][] arr = new int[5][5];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int[] pos;
		for (int i = 0; i < 25; i++) {
			pos = findPosition(sc.nextInt());
			selected[pos[0]][pos[1]] = true;
			if(checkBingo() == 3) {
				System.out.println(i+1);
				break;
			}
		}
		
		sc.close();
	}
	
	private static int checkBingo() {
		int bingoCnt = 0;
		boolean bingo;
		for (int i = 0; i < 5; i++) {	// 행마다 검사
			bingo = true;
			for (int j = 0; j < 5; j++) {
				if(!selected[i][j]) {
					bingo = false;
					break;
				}
			}
			if(bingo) {
				bingoCnt++;
				if(bingoCnt == 3) return 3;
			}
		}
		for (int j = 0; j < 5; j++) {	// 열마다 검사
			bingo = true;
			for (int i = 0; i < 5; i++) {	
				if(!selected[i][j]) {
					bingo = false;
					break;
				}
			}
			if(bingo) {
				bingoCnt++;
				if(bingoCnt == 3) return 3;
			}
		}
		
		bingo = true;
		for (int i = 0, j = 0; i < 5 && j < 5; i++, j++) {	// 대각선 1 검사
			if(!selected[i][j]) {
				bingo = false;
				break;
			}
		}
		if(bingo) {
			bingoCnt++;
			if(bingoCnt == 3) return 3;
		}
		
		bingo = true;
		for (int i = 4, j = 0; i >= 0 && j < 5; i--, j++) {	// 대각선 2 검사
			if(!selected[i][j]) {
				bingo = false;
				break;
			}
		}
		if(bingo) {
			bingoCnt++;
		}
		
		return bingoCnt;
	}
	
	private static int[] findPosition(int num) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(arr[i][j] == num) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	
}
