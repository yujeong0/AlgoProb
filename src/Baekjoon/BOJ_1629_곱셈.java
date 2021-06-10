package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_곱셈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
	
		System.out.println(power(A % C, B, C));
	}
	
	static long power(long a, long b, long c) {
		if(b == 1) return a % c;
		
		long half = power(a, b/2, c) % c;
		
		if(b % 2 == 0) {
			return (half * half) % c;
		}
		return (((half * half) % c) * a) % c;
	}
}
