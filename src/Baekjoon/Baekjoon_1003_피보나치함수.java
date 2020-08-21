package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1003_피보나치함수 {
<<<<<<< HEAD
	private static int[][] arr;
	private static int[] arrN = new int[41];
=======
	private static int[][] arr = new int[41][2];
	private static int[] f = new int[41];
>>>>>>> 2880e545594187e116fed46e122658e3056ce43f
	private static int N;
	
	private static int fibo(int n) {
		if(n == 0) {
<<<<<<< HEAD
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
=======
			return 0;
		}
		if(n == 1) {
			return f[n] = 1;
		}
>>>>>>> 2880e545594187e116fed46e122658e3056ce43f
		
		if(f[n] > 0) return f[n];
		
		f[n] = fibo(n-1) + fibo(n-2);

		arr[n][0] = arr[n-1][0] + arr[n-2][0];
		arr[n][1] = arr[n-1][1] + arr[n-2][1];
		
<<<<<<< HEAD
		return arrN[n] = fibo(n-1) + fibo(n-2);
=======
		return f[n];
>>>>>>> 2880e545594187e116fed46e122658e3056ce43f
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
<<<<<<< HEAD
			arr = new int[41][2];
			N = sc.nextInt();
			fibo(N);

//			sb.append(arrN[0] + " " + arrN[1] + "\n");
=======
			arr[0][0] = 1;
			arr[1][1] = 1;
			N = sc.nextInt();
			fibo(N);

>>>>>>> 2880e545594187e116fed46e122658e3056ce43f
			sb.append(arr[N][0] + " " + arr[N][1] + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}
