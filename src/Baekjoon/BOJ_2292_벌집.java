package Baekjoon;

import java.util.Scanner;

public class BOJ_2292_벌집 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num = 1, count = 1, add = 0;
		while(true) {
			num += add;
			if(num >= N) break;
			
			count++;
			add += 6;
		}
		
		System.out.println(count);
		
		sc.close();
	}

}

