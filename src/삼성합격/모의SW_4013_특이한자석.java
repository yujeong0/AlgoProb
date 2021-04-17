package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_4013_특이한자석 {
	static int K;
	static int[][] magnet = new int[5][8];
	static int[][] rotation;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			K = Integer.parseInt(br.readLine());
			rotation = new int[K][2];

			for (int i = 1; i <= 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					rotation[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" + testcase + " " + solve());
		} // tc

	} // main

	private static int solve() {
		
		for (int i = 0; i < K; i++) {
			List<int[]> rotateMag = new ArrayList<>();
			int me = rotation[i][0];	// 현재회전되는자석번호
			int dir = rotation[i][1];	// 1이면 시계, -1이면 반시계
			
			rotateMag.add(new int[] {me, dir});
			int idx = me;
			int newDir = dir;
			while(true) {
				if(idx-1 < 1) break;
				if(magnet[idx][6] != magnet[idx-1][2]) {
					newDir *= -1;
					rotateMag.add(new int[] {idx-1, newDir});
				}
				else break;
				idx--;
			}
			
			idx = me;
			newDir = dir;
			while(true) {
				if(idx+1 == 5) break;
				if(magnet[idx][2] != magnet[idx+1][6]) {
					newDir *= -1;
					rotateMag.add(new int[] {idx+1, newDir});
				}
				else break;
				idx++;
			}
			
			// 회전정보 모았고 이제 실제로 회전
			for(int[] arr : rotateMag) {
				int num = arr[0];
				
				if(arr[1] == 1) {	// 회전방향이 시계면 오른쪽으로 회전
					int tmp = magnet[num][7];
					for (int k = 7; k >= 1; k--) {
						magnet[num][k] = magnet[num][k-1];
					}
					magnet[num][0] = tmp;
				}
				else {
					int tmp = magnet[num][0];
					for (int k = 0; k < 7; k++) {
						magnet[num][k] = magnet[num][k+1];
					}
					magnet[num][7] = tmp;
				}
			}
			
		} // K
		
		int sum = 0;
		int score = 1;
		for (int i = 1; i <= 4; i++) {
			if(magnet[i][0] == 1) {
				sum += score;
			}
			score *= 2;
		}
		
		return sum;
	} // solve
}
