package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1003_피보나치함수 {
	private static int[][] arr;
	private static int[] arrN = new int[41];
	private static int N;
	
	private static int fibo(int n) {
		if(n == 0) {
			arr[n][0]++;
			return 0;
		}
		if(n == 1) {
			arr[n][1]++;
			return arrN[n] = 1;
		}

		arr[n][0] += arr[n-1][0];
		arr[n][1] += arr[n-1][1];

		if(arrN[n] > 0) return arrN[n];
		
		
		return arrN[n] = fibo(n-1) + fibo(n-2);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			arr = new int[41][2];
			N = sc.nextInt();
			fibo(N);

//			sb.append(arrN[0] + " " + arrN[1] + "\n");
			sb.append(arr[N][0] + " " + arr[N][1] + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}
