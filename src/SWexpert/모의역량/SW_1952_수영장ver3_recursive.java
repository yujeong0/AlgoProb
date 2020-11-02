package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1952_수영장ver3_recursive {
	static int[] ticketCost = new int[4];	// 이용권별 요금
	static int[] swimDays = new int[13];	// 1~12 월 이용계획
	static int MinCost;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MinCost = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) 
				ticketCost[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 12; i++) 
				swimDays[i] = Integer.parseInt(st.nextToken());
			
			
			
			
			sb.append("#" + testcase + " " + MinCost + "\n");
		}
		
		System.out.println(sb.toString());
	} // end of main
}
