package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1074_Z {
	static int N,r,c;
	static int count = 0;
	static boolean success = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		N = exp(2, N);	// (int)Math.pow(2, N) 과 같음
		
		if(r == 0 && c == 0) count = 0;
		else solve(N, 0, 0);
		
		System.out.println(count);
		
		sc.close();
	}
	
	private static void solve(int n, int x, int y) {
		if(n == 2) {
			for (int i = x; i < x+n; i++) {
				for (int j = y; j < y+n; j++) {
					if(i == r && j == c) {
						success = true;
						return;
					}
					count++;
				}
			}
			return;
		}
		
		if(!success)
			solve(n/2, x, y);
		if(!success)
			solve(n/2, x, y+(n/2));
		if(!success)
			solve(n/2, x+(n/2), y);
		if(!success)
			solve(n/2, x+(n/2), y+(n/2));
		
	}
	
	private static int exp(int x, int y) {
		if(y == 0) return 1;
		if(y == 1) return x;
		int result = exp(x, y/2);
		result *= result;
		if(y % 2 != 0) result *= x;
		
		return result;
	}
}
