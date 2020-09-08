package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
10
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
6 8 3
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
6 8 4
1 1 0 0 0 1 1 0
1 0 1 0 0 1 1 1
0 1 0 0 1 1 0 0
1 0 1 0 0 0 0 0
1 1 0 0 0 0 0 0
1 0 0 0 1 1 1 1
6 4 4
1 1 0 0
0 1 0 1
0 0 0 1
1 1 1 1
1 1 0 1
1 0 1 0
6 10 3
0 1 0 0 0 1 0 0 1 1
0 1 1 0 0 1 0 0 1 0
0 1 0 0 1 0 1 1 1 1
0 0 0 0 0 1 1 1 1 0
0 1 0 0 1 1 1 1 1 1
1 0 0 0 1 1 0 0 1 1
6 6 5
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
6 6 4
1 1 1 1 1 1
0 0 0 0 0 1
0 1 1 1 0 1
0 1 0 1 0 1
0 1 0 0 0 1
0 1 1 1 1 1
8 15 3
0 1 1 0 0 1 1 0 1 1 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0 0 1 0 1 1
1 1 0 1 0 1 0 1 0 1 0 1 0 0 0
0 1 1 1 0 0 1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 1 0 0 0 1 1 0 0 1
1 0 1 0 0 1 0 1 1 1 1 0 1 1 1
0 0 0 0 0 1 1 1 0 0 0 0 0 1 0
0 0 1 0 1 1 0 1 1 0 0 0 1 0 0
10 20 4
1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1
1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0
1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0
0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0
0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1
1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0
0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1
1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0
0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1
0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0
13 20 5
1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0
1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0
1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0
0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1
0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1
0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1
0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0
1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1
0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1
0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1
0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0
0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0
0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0

#1 2
#2 0
#3 4
#4 2
#5 2
#6 0
#7 3
#8 2
#9 3
#10 4
 */

public class SW_모의역량_2112_보호필름_v2 {

	static int ANSWER, D, W, K;
	static int[][] arr = new int[14][21];
	static final int NOT = -1, A = 0, B = 1;
	static int[] list = new int[14];	// 투여 약품 행별로 관리
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ANSWER = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " "); 
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Arrays.fill(list, NOT);	// 모든 행을 비투여상태로
			if(isPass()) {
				ANSWER = 0;
			} else {
				process(0, 0);
			}
			
			
			sb.append("#" + t + " " + ANSWER + "\n");
		}
		
		System.out.println(sb.toString());
		
	}	// end of main
	
	/*
	D = 4 일 때
	A A A A
	A A A B
	A A A N
	A A B A
	A A B N
	A A N A
	A A N B
	A A N N 
	.
	.
	.
	
	이런 식으로 배치하여 연속 K 만족하는 지 확인
	 */
	
	private static void process(int row, int cnt) {	
		if(cnt >= ANSWER) return;
		if(row == D) {	// 마지막행까지 시도해봤다면
			if(isPass()) {	// 약품을 투여했거나 안 한 상태로 조건 만족하는지 확인
				if(ANSWER > cnt) ANSWER = cnt;
			}
			return;
		}
		
		// A 약품 투여
		list[row] = A;
		process(row+1, cnt+1);
		// B 약품 투여
		list[row] = B;
		process(row+1, cnt+1);
		// 약품 비투여
		list[row] = NOT;
		process(row+1, cnt);
	}
	
	private static boolean isPass() {
		int before, current, count;
		for (int j = 0; j < W; j++) {	// 열마다 검사
			before = list[0] == NOT? arr[0][j] : list[0];	// 비투여 상태면 원래 arr 값, 아니면 약물(A/B) 값
			count = 1;
			for (int i = 1; i < D; i++) {
				current = list[i] == NOT? arr[i][j] : list[i];
				if(before != current) {
					count = 1;
					before = current;
				}
				else {	// 같으면
					if(++count >= K) {
						break;
					}
				}
			}
			if(count < K) return false;
		}
		
		return true;
	}
	
}
