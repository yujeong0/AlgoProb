package SWexpert.Professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_Professional_5607_조합 {

	static long[] fac = new long[1000001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N, R;
		StringTokenizer st;
		long result;
		int p = 1234567891;
		fac[0] = 1;
		
		for (int i = 1; i <= 1000000; i++) {
			fac[i] = (fac[i-1] * i) % p;
		}
		
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			result = nCr(N, R, p);
			sb.append("#" + testcase + " " + result + "\n");
		}	// end of tc
		
		System.out.println(sb.toString());
	}	// end of main
	
	private static long nCr(int n, int r, int p) {
		if(r == 0) return 1L;
		
		return (fac[n] * power(fac[r], p-2, p) % p * power(fac[n-r], p-2, p) %p) % p;
	}
	
	private static long power(long a, long b, long p) {
		long ans = 1L;
		a = a % p;
		while(b > 0) {
			if(b % 2 == 1)
				ans = (ans * a) % p;
			
			b = b >> 1;
			a = (a*a) % p;
		}
		return ans;
	}
	
}
