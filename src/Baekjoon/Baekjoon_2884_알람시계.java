package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2884_알람시계 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int H = sc.nextInt();
		int M = sc.nextInt();
		
		if(M >= 45) {
			System.out.println(H + " " + (M-45));
		}
		else {
			if(H == 0) H = 24;
			int totalmin = H * 60 + M - 45;
			int h = totalmin / 60;
			int m = totalmin % 60;
			System.out.println(h + " " + m);
		}
		
		sc.close();
	}
}
