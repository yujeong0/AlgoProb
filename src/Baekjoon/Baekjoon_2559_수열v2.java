package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2559_수열v2 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) 
			nums[i] = sc.nextInt();
		
		int sum = 0;
		for (int i = 0; i < K; i++) 
			sum += nums[i];
		
		int MAX = sum;
		for (int i = K; i < N; i++) {
			sum -= nums[i-K];
			sum += nums[i];
			if(MAX < sum) MAX = sum;
		}
		
		System.out.println(MAX);
		
		sc.close();
	}	// end of main
}
