package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 모의SW_1952_수영장 {
	
	static int[] prices = new int[4];
	static int[] myPlan = new int[12];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				myPlan[i] = Integer.parseInt(st.nextToken());
			}
			
			MIN = prices[3];
			solve(0, 0);
			
			System.out.println("#" + testcase + " " + MIN);
		} // tc
		
		
	} // main

	static int MIN;
	private static void solve(int curMonth, int cost) {
		if(cost >= MIN) return;
		if(curMonth >= 12) {
			if(MIN > cost) MIN = cost;
			return;
		}
		
		solve(curMonth+1, cost + (myPlan[curMonth] * prices[0]));
		solve(curMonth+1, myPlan[curMonth] == 0? cost : cost+prices[1]);
		solve(myPlan[curMonth] == 0? curMonth+1 : curMonth+3, myPlan[curMonth] == 0? cost :cost + prices[2]);
		
	} // solve
}
