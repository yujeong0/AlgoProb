package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2559_수열 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) 
			nums[i] = sc.nextInt();
		
		int sum, MAX = Integer.MIN_VALUE;
		for (int i = 0; i <= N-K; i++) {
			sum = 0;
			for (int j = i; j < i+K; j++) {
				sum += nums[j];
			}
			if(MAX < sum) MAX = sum;
		}
		
		System.out.println(MAX);
		
		sc.close();
	}	// end of main
}
