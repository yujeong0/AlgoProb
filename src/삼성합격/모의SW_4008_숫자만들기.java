package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의SW_4008_숫자만들기 {
	static int N;
	static int[] operators = new int[4], numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = Integer.MIN_VALUE;
			MIN = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			} // input end
			
			solve(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);
			solve(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);
			solve(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);
			solve(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);
			
			System.out.println("#" + testcase + " " + (MAX-MIN));
		} //tc
		
	} //main
	
	static int MAX, MIN;
	private static void solve(int idx, int sum, int plus, int minus, int mul, int div) {
		if(idx == N) {
			if(MAX < sum) 
				MAX = sum;
			if(MIN > sum) 
				MIN = sum;
			
			return;
		}
		
		if(plus > 0) solve(idx+1, sum+numbers[idx], plus-1, minus, mul, div);
		if(minus > 0) solve(idx+1, sum-numbers[idx], plus, minus-1, mul, div);
		if(mul > 0) solve(idx+1, sum*numbers[idx], plus, minus, mul-1, div);
		if(div > 0) solve(idx+1, sum/numbers[idx], plus, minus, mul, div-1);
		
	} //solve
}
