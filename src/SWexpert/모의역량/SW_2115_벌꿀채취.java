package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2115_벌꿀채취 {

	static int N, M, C;
	static int[][] arr = new int[11][11];
	static List<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// grid 크기
			M = Integer.parseInt(st.nextToken());	// 연속으로 선택해야하는 벌통
			C = Integer.parseInt(st.nextToken());	// 일꾼 당 최대 꿀 양
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#" + testcase + " " + solve() + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	private static int solve() {
		int MAX = -1;
		int x1, y1;	// 첫번째 일꾼이 선택한 벌통 시작 index
		int x2, y2;	// 두번째 일꾼이 선택한 벌통 시작 index
		int sum1, sum2;
		for (int i = 0; i < N-M; i++) {
			for (int j = 0; j < N; j++) {
				
				x1 = i;
				y1 = j;
				sum1 = getSum(x1, y1);	// 첫번쨰 일꾼
				
				// 두번쨰 일꾼 벌통 위치 고르기
				for (int i2 = 0; i2 < N-M; i2++) {
					for (int j2 = 0; j2 < N; j2++) {
						if(i2+M >= x1 || i2 <= x1+M) continue;	// 첫번째 일꾼이랑 위치 겹치면 안 됨
						
						x2 = i2;
						y2 = j2;
						sum2 = getSum(x2, y2);	// 첫번쨰 일꾼
						
						MAX = Math.max(MAX, (sum1*sum1)+(sum2*sum2));
						
					} // j2
				} // i2
				
				
			} // j
		} // i
		
		return MAX;
		
	} // solve
	
	private static int getSum(int x, int y) {	// 일꾼이 선택한 첫번째벌통 위치
		list.clear();
		int sum = 0;
		for (int r = x; r < x+M; r++) {
			sum += arr[r][y];
			list.add(new int[] {r, y, arr[r][y]});	// 채취 위치와 값 저장
			
			if(sum > C) {	// sum이 C 넘으면 제일 작은 거부터 빼서 계속 확인
				Collections.sort(list, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return o1[2] - o2[2];	// 해당 셀의 값 오름차순 정렬
					}
				});

				int n = 0;
				while(true) {	
					if(sum <= C) {	
						break;
					}
					sum -= list.get(n++)[2];
				}
			}
		}
		
		return sum;
	}
	
} // end of class
