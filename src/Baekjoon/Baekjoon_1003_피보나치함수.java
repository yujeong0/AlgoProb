package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1003_피보나치함수 {
	private static int[] arr = { 0, 0 };
	private static int[] arrN = new int[41];
	private static int N;
	
	private static int fibo(int n) {
		if(n == 0) {
			arr[0]++;
			return 0;
		}
		if(n == 1) {
			arr[1]++;
			return arrN[N] = 1;
		}

		if(arrN[N] > 0) return arrN[N];
		
		
		return arrN[N] = fibo(n-1) + fibo(n-2);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			arr[0] = 0;
			arr[1] = 0;
			N = sc.nextInt();
			fibo(N);

			sb.append(arr[0] + " " + arr[1] + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}
