package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_4013_특이한자석 {

	static int K;
	static int[][] wheel = new int[5][8];
	static int[][] rotation = new int[21][2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			K = Integer.parseInt(br.readLine());	// 회전 수
			for (int i = 1; i < 5; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {	// 각 자석 자성 정보
					wheel[i][j] = Integer.parseInt(st.nextToken());
				}
			} 
			
			for (int i = 0; i < K; i++) {	// K 개의 회전정보
				st = new StringTokenizer(br.readLine(), " ");
				rotation[i][0] = Integer.parseInt(st.nextToken());	// 자석 번호
				rotation[i][1] = Integer.parseInt(st.nextToken());	// 회전 방향
			}	// end of input
			
			sb.append("#" + testcase + " " + solve() + "\n");
		}	// end of tc

		System.out.println(sb.toString());
	} // end of main
	
	private static int solve() {
		int sum = 0;
		int rotateDir;
		List<int[]> list = new ArrayList<>();	// 회전시켜야 하는 자석 번호
		for (int i = 0; i < K; i++) {
			list.clear();
			
			switch(rotation[i][0]) {
			case 1:
				rotateDir = rotation[i][1];
				list.add(new int[] {1, rotateDir});	// 자신은 일단 회전
				if(wheel[1][2] != wheel[2][6]) {	// 내자성이랑 다른 자성이면
					list.add(new int[] {2, rotateDir * -1});

					if(wheel[2][2] != wheel[3][6]) {
						list.add(new int[] {3, rotateDir});
					
						if(wheel[3][2] != wheel[4][6]) {
							list.add(new int[] {4, rotateDir * -1});
						}
					}
				}
				
				break;
			case 2:
				rotateDir = rotation[i][1];
				list.add(new int[] {2, rotateDir});	
				if(wheel[2][2] != wheel[3][6]) {	// 내자성이랑 다른 자성이면
					list.add(new int[] {3, rotateDir * -1});
					
					if(wheel[3][2] != wheel[4][6]) {
						list.add(new int[] {4, rotateDir});
					}
				}
				if(wheel[2][6] != wheel[1][2]) {
					list.add(new int[] {1, rotateDir * -1});
				}
				break;
			case 3:
				rotateDir = rotation[i][1];
				list.add(new int[] {3, rotateDir});	
				if(wheel[3][6] != wheel[2][2]) {	// 내자성이랑 다른 자성이면
					list.add(new int[] {2, rotateDir * -1});
					
					if(wheel[2][6] != wheel[1][2]) {
						list.add(new int[] {1, rotateDir});
					}
				}
				if(wheel[3][2] != wheel[4][6]) {
					list.add(new int[] {4, rotateDir * -1});
				}
				break;
			case 4:
				rotateDir = rotation[i][1];
				list.add(new int[] {4, rotateDir});	
				if(wheel[4][6] != wheel[3][2]) {	// 내자성이랑 다른 자성이면
					list.add(new int[] {3, rotateDir * -1});
					
					if(wheel[3][6] != wheel[2][2]) {
						list.add(new int[] {2, rotateDir});
					
						if(wheel[2][6] != wheel[1][2]) {
							list.add(new int[] {1, rotateDir * -1});
						}
					}
				}
				break;
			
			}	// 돌아야하는 자석 번호 add 끝
			
			// 다 돌려버리기~
			for(int[] rot : list) {
				rotate(rot[0], rot[1]); 
			}
			
		}	// K 번 회전 끝
		
		// 모든 자석을 돌면서 S 인지 N 인지 확인하고 점수더해줌
		for (int i = 1; i < 5; i++) {
			if(wheel[i][0] == 1) {	// S 이면 점수 플러스
				switch(i) {
				case 1: sum += 1; break;
				case 2: sum += 2; break;
				case 3: sum += 4; break;
				case 4: sum += 8; break;
				}
			}
		}
		
		return sum;
		
	}	// solve
	
	private static void rotate(int wheelnum, int rotateDir) { // 자석번호와 회전방향을 받아서 배열 회전시키는 함수
		if(rotateDir == 1) {	// 시계 : 배열끝이 0번 인덱스로 옴
			int end = wheel[wheelnum][7];
			for (int i = 7; i > 0; i--) {
				wheel[wheelnum][i] = wheel[wheelnum][i-1];
			}
			wheel[wheelnum][0] = end;
		}
		else {	// 반시계 : 0번이 끝으로 감
			int first = wheel[wheelnum][0];
			for (int i = 0; i < 7; i++) {
				wheel[wheelnum][i] = wheel[wheelnum][i+1];
			}
			wheel[wheelnum][7] = first;
		}
		
		
	} //rotate 
}
