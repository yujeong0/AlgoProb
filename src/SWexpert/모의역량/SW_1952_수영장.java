package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1952_수영장 {
	
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
			
//			solve();
			
			int cost;
			int[][] myTicket = new int[13][13];	// 달 별로 무슨 이용권 쓰는 지 저장, [month][이용권남은개월수]
			for (int t = 0; t < 4; t++) {
				cost = 0;
				myTicket[1][0] = t;	// 1월달 이용권을 다 바꿔보기~
				switch(t) {
				case 0 :
					myTicket[1][1] = 0;
					cost += ticketCost[t] * swimDays[1];
					break;
				case 1 :
					myTicket[1][1] = 0;
					cost += ticketCost[t];
					break;
				case 2 :
					myTicket[1][1] = 3;
					cost += ticketCost[t];
					break;
				case 3 :
					myTicket[1][1] = 12;
					cost += ticketCost[t];
					break;
				}
				
				int[][] copy = new int[13][13];
				copy[1][0] = myTicket[1][0];
				copy[1][1] = myTicket[1][1];
				solve2(copy, cost, 2);
			}
			
			
			sb.append("#" + testcase + " " + MinCost + "\n");
		}
		
		System.out.println(sb.toString());
	} // end of main

	private static void solve2(int[][] myTicket, int cost, int month) {
//		if(month == 13) {
//			if(MinCost > cost) MinCost = cost;
//			return;
//		}
		
		for (int j = 0; j < 4; j++) {	// 모든 티켓 다 해봄
			if(myTicket[month-1][1] <= 1) {	// 이용권 끝난 경우
				myTicket[month][0] = j;
				switch(j) {
				case 0 :	// 1일권
					myTicket[month][1] = 0;
					cost += ticketCost[j] * swimDays[month];
					break;
				case 1 :	// 1달
					myTicket[month][1] = 0;
					cost += ticketCost[j];
					break;
				case 2 :	// 3달
					myTicket[month][1] = 3;
					cost += ticketCost[j];
					break;
				case 3 :	// 1년
					myTicket[month][1] = 12;
					cost += ticketCost[j];
					break;
				}
			}
			else { // 이용권이 아직 남은 경우
				myTicket[month][0] = myTicket[month-1][0];
				myTicket[month][1] = myTicket[month-1][1]-1;
			}

			if(month == 12) {
				if(MinCost > cost) MinCost = cost;
				if(j == 3) return;
				
				continue;
			}
			
			int[][] copy = new int[13][13];
			for (int i = 1; i <= month; i++) {
				copy[i][0] = myTicket[i][0];
				copy[i][1] = myTicket[i][1];
			}
			solve2(copy, cost, month+1);
			
		} // j ( ticket)

		
	} // solve2
	
} // end of class
