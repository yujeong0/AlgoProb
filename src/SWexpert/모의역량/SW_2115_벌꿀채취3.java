package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2115_벌꿀채취3 {
	static class Position {
		int x, y;
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, C;
	static int[][] arr = new int[11][11];
	static Position[] honey = new Position[6];
	static int[][] sumValue = new int[11][6];	// 배열 연속 M 에서 더한 값 최대되는 제곱합 저장하는 배열
	static boolean[] selected = new boolean[6];
	static int honeyMax;	// 각 연속 M 배열에서 sum 최대
	static int valMax;
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
		// 미리 값 다 계산해놓기~
		int idx;
		List<Integer> newList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				
				idx = 0;
				for (int j2 = j; j2 < j+M; j2++) {
					honey[idx++] = new Position(i, j2);
				}
				
				honeyMax = 0;
				Arrays.fill(selected, false);
				subset(0);
				sumValue[i][j] = valMax;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				System.out.print(sumValue[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int MAX = 0;
		int sum1, sum2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
			
				sum1 = sumValue[i][j];	// 첫번쨰 일꾼
				
				// 두번쨰 일꾼 벌통 위치 고르기
				for (int i2 = 0; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						if(i2 == i && (j2 == j || (j < j2 && j2 <= j+M-1) || (j2 < j && j <= j2+M-1 ))) continue;	// 첫번째 일꾼이랑 위치 겹치면 안 됨
						
						sum2 = sumValue[i][j];	// 두번쨰 일꾼

//						System.out.println("(" + sum1 + ", " + sum2+")" + " "+ ((sum1*sum1)+(sum2*sum2)));
						MAX = Math.max(MAX, sum1+sum2);
						
					} // j2
				} // i2
				
			} // j
		} // i
		
		return MAX;
		
	} // solve
	

	private static void subset(int cnt) {
		if(cnt == M) {
			int val;
			int sum = 0, valSum = 0;
			for (int i = 0; i < M; i++) {
				if(selected[i]) {
					val = arr[honey[i].x][honey[i].y];
					valSum += val * val;
					sum += val;
				}
			}
			
			if(sum <= C && honeyMax < sum) {
				honeyMax = sum;
				valMax = valSum;
			}
			
			return;
		}
		
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}
	
} // end of class
