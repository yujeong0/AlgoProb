package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014_활주로건설 {
	
	static int N, X;
	static int[][] map = new int[22][22];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}	// end of input
			
			
			sb.append("#" + testcase + " " + solve() + "\n");
		}	// end of tc
		
		System.out.println(sb.toString());
	}	// end of main
	
	private static int solve() {
		
		int totalSuccess = 0;
		int start;
		int count;
		boolean success;
		
		// 가로줄 확인
		for (int i = 0; i < N; i++) {
			success = true;
			start = map[i][0];
			count = 1;
		out:for (int j = 1; j < N; j++) {
				if(map[i][j] == start) {
					count++;
					continue;
				}
				if(map[i][j] >= start+2 || map[i][j] <= start-2) {	// 높이차이2이상나면 바로 out
					success = false;
					break;
				}
				else {	// 나보다 1높거나 낮은 애가 나올 경우
					if(map[i][j] == start+1) {	// 나보다 1높은 애가 나오면
						if(count < X) {	// 경사로 놓을 수 없으면
							success = false;
							break;	// 다음 i로~
						}
						else {	// 경사로 놓기 성공했다면
//							j = j+X-1;	// 경사로 놓은 다음 위치부터 다시 보기
							if(j < N) {
								start = map[i][j];
								count = 1;
							}
						}
					}
					
					else if(map[i][j] == start-1) {	// 나보다 1 낮은 애가 나오면
						int j2 = j+1;
						if(j2 >= N) {
							success = false;
							break out;	// 다른 행으로~
						}
						for (; j2 < j+X; j2++) {
							if(start-1 != map[i][j2]) {
								success = false;
								break out;	// 다른 행으로~
							}
						}
						// 경사로 놓기 성공했다면
						j = j+X-1;	// 경사로 놓은 다음 위치부터 다시 보기
						if(j < N) {
							start = map[i][j];
							count = 0;
						}
					}
				}
			}
			
			if(success) totalSuccess++;
		}	// 가로
		
		
		// 세로줄 확인
		for (int j = 0; j < N; j++) {
			success = true;
			start = map[0][j];
			count = 1;
		out:for (int i = 1; i < N; i++) {
				if(map[i][j] == start) count++;
				if(map[i][j] >= start+2 || map[i][j] <= start-2) {	// 높이차이2이상나면 바로 out
					success = false;
					break;
				}
				else {	// 나보다 1높거나 낮은 애가 나올 경우
					if(map[i][j] == start+1) {	// 나보다 1높은 애가 나오면
						if(count < X) {	// 경사로 놓을 수 없으면
							success = false;
							break;	// 다음 i로~
						}
						else {	// 경사로 놓기 성공했다면
//							i = i+X-1;	// 경사로 놓은 다음 위치부터 다시 보기
							if(i < N) {
								start = map[i][j];
								count = 1;
							}
						}
					}
					
					else if(map[i][j] == start-1) {	// 나보다 1 낮은 애가 나오면
						int i2 = i+1;
						if(i2 >= N) {
							success = false;
							break out;	// 다른 행으로~
						}
						for (; i2 < i+X; i2++) {
							if(start-1 != map[i2][j]) {
								success = false;
								break out;	// 다른 행으로~
							}
						}
						// 경사로 놓기 성공했다면
						i = i+X-1;	// 경사로 놓은 다음 위치부터 다시 보기
						if(i < N) {
							start = map[i][j];
							count = 0;
						}
					}
				}
			}
			if(success) totalSuccess++;
		}	// 세로
		
		return totalSuccess;
	}	// solve
	
	
}	// end of class
