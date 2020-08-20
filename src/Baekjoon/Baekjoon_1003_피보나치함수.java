package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1003_피보나치함수 {
	private static int[][] arr = new int[41][2];
	private static int[] f = new int[41];
	private static int N;
	
	private static int fibo(int n) {
		if(n == 0) {
			return 0;
		}
		if(n == 1) {
			return f[n] = 1;
		}
		
		if(f[n] > 0) return f[n];
		
		f[n] = fibo(n-1) + fibo(n-2);

		arr[n][0] = arr[n-1][0] + arr[n-2][0];
		arr[n][1] = arr[n-1][1] + arr[n-2][1];
		
		return f[n];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			arr[0][0] = 1;
			arr[1][1] = 1;
			N = sc.nextInt();
			fibo(N);

			sb.append(arr[N][0] + " " + arr[N][1] + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}
