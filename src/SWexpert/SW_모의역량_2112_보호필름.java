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

 */

public class SW_모의역량_2112_보호필름 {

	static int ANSWER, D, W, K;
	static int[][] arr = new int[14][21];
	static int[] numbers = new int[14];	//  A or B 순열
	static boolean[] selected = new boolean[14];
	static int[][] copy;	// 원래 arr 보존 위해 copy
	static int NumOfMedicine;	// 약품투여횟수
	static int[] ABSelected = new int[14];
	
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
			
			subset(0);
			
			sb.append("#" + t + " " + ANSWER + "\n");
		}
		
		System.out.println(sb.toString());
		
	}	// end of main
	
	private static void subset(int count) {	// 약물투입할 행번호 부분집합들 만들기 ( 0~D개만큼이 아니고 0~K임)
		if(count == D) {
			NumOfMedicine = 0;
			for (int i = 0; i < D; i++) {
				if(selected[i] == true) {
					NumOfMedicine++;
					if(NumOfMedicine >= ANSWER) return;	// 이미 최소보다 커지면 구할 필요가 없다
					if(NumOfMedicine > K) return;	// 최대 K개만 투입하면 되므로 큰 거는 제외
				}
			}
			subsetAB(0);
			
			return;
		}
		
		selected[count] = false;
		subset(count+1);
		selected[count] = true;
		subset(count+1);
	}
	
	private static void subsetAB(int count) {	// A와 B 채우기를 위한 부분집합 만들기
		if(count == NumOfMedicine) {	// 0 에서 약물투입할 행개수까지 각 행에 a 혹은 b (0/1) 가 들어간다
			
			if(medicine()) {	// 뽑은 층들에 A/B 뭐 넣을 지 결정했으니 실제로 넣어보기
				ANSWER = Math.min(NumOfMedicine, ANSWER);
			}
			
			return;
		}
		
		ABSelected[count] = 1;
		subsetAB(count+1);
		ABSelected[count] = 0;
		subsetAB(count+1);
		
	}
	
	private static boolean medicine() {
		copy = new int[D][];	// 원래 arr 보존 위해 copy
		for (int i = 0; i < D; i++) 
			copy[i] = Arrays.copyOf(arr[i], W);
		
		int count = 0;
		int num;
		for (int i = 0; i < D; i++) {
			if(selected[i] == true){	// 약물투여할 행
				num = ABSelected[count++];
				for (int j = 0; j < W; j++) 	// 해당 행의 모든 열을 A or B로 변경
					copy[i][j] = num;
			}
		}
		
		if(!isPass()) return false;
		return true;
	}
	
	private static boolean isPass() {
		int start, count;
		for (int j = 0; j < W; j++) {	// 열마다 검사
			count = 1;
			start = copy[0][j];
			for (int i = 1; i < D; i++) {	
				if(start != copy[i][j]) {
					count = 1;
					start = copy[i][j];
				}
				else {
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
