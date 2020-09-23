package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_2477_참외밭 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		
		int[][] side = new int[9][2];	// 방향 1-동, 2-서, 3-남, 4-북
		
		int maxHeight = 0, maxWidth = 0;
		for (int i = 0; i < 6; i++) {
			side[i][0] = sc.nextInt();
			side[i][1] = sc.nextInt();
			if((side[i][0] == 3 || side[i][0] == 4) && maxWidth < side[i][1]) maxWidth = side[i][1];
			if((side[i][0] == 1 || side[i][0] == 2) && maxHeight < side[i][1]) maxHeight = side[i][1];
		}
		
		side[6][0] = side[0][0];
		side[6][1] = side[0][1];
		side[7][0] = side[1][0];
		side[7][1] = side[1][1];
		side[8][0] = side[2][0];
		side[8][1] = side[2][1];
		
		int smallArea = 0;
		for (int i = 0; i <= 5; i++) {
			if(side[i][0] == 4 && side[i+1][0] == 2 && side[i+2][0] == 4 && side[i+3][0] == 2) {
				smallArea = side[i+1][1] * side[i+2][1];
				break;
			}
			if(side[i][0] == 2 && side[i+1][0] == 3 && side[i+2][0] == 2 && side[i+3][0] == 3) {
				smallArea = side[i+1][1] * side[i+2][1];
				break;
			}
			if(side[i][0] == 3 && side[i+1][0] == 1 && side[i+2][0] == 3 && side[i+3][0] == 1) {
				smallArea = side[i+1][1] * side[i+2][1];
				break;
			}
			if(side[i][0] == 1 && side[i+1][0] == 4 && side[i+2][0] == 1 && side[i+3][0] == 4) {
				smallArea = side[i+1][1] * side[i+2][1];
				break;
			}
		}
		
		System.out.println(((maxHeight * maxWidth) - smallArea) * K);
		
		sc.close();
	}	// end of main
	
}
