package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1953_수영장ver2 {
	
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
			
			for (int i = 0; i < 3; i++) {
				numbers[1][0] = i;
				switch(i) {
				case 0 :	// 1일
					numbers[1][1] = 0;
					break;
				case 1 :	// 1달
					numbers[1][1] = 0;
					break;
				case 2 :	// 3달
					numbers[1][1] = 3;
					break;
				}
				solve(2);
			}
			
			if(MinCost > ticketCost[3]) MinCost = ticketCost[3];
			
			sb.append("#" + testcase + " " + MinCost + "\n");
		}
		
		System.out.println(sb.toString());
	} // end of main


	static int[][] numbers = new int[13][13];
	private static void solve(int cnt) {
		if(cnt == 13) {
//			System.out.println(Arrays.toString(numbers));
			int totalCost = 0;
			for (int i = 1; i < 13; i++) {
//				if(swimDays[i] == 0) continue;
				switch(numbers[i][0]) {
				case -1:
					break;
				case 0:
					totalCost += swimDays[i] * ticketCost[0];
					break;
				case 1:
					totalCost += ticketCost[numbers[i][0]];
					break;
				case 2:
					if(numbers[i][1] == 3)
						totalCost += ticketCost[numbers[i][0]];
					break;
				}
			}
			
			if(MinCost > totalCost) MinCost = totalCost;
			
			return;
		}
		
		if(swimDays[cnt] == 0) {
			numbers[cnt][0] = -1;
			solve(cnt+1);
		}
		else {
			for (int j = 0; j < 3; j++) {
				if(numbers[cnt-1][0] == -1 || numbers[cnt-1][1] <= 1) {	// 이용권 끝난 경우
					numbers[cnt][0] = j;
					switch(j) {
					case 0 :	// 1일권
						numbers[cnt][1] = 0;
						break;
					case 1 :	// 1달
						numbers[cnt][1] = 0;
						break;
					case 2 :	// 3달
						numbers[cnt][1] = 3;
						break;
					}
				}
				else { // 이용권이 아직 남은 경우
					numbers[cnt][0] = numbers[cnt-1][0];
					numbers[cnt][1] = numbers[cnt-1][1]-1;
				}
				
				solve(cnt+1);
			}
		}
		
	} // solve
	
	
	
} // end of class
