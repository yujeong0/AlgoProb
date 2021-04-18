package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_4014_활주로건설 {
	static int N, X;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + testcase + " " + solve());
		} // tc

	} // main

	private static int solve() {
		int COUNT = 0;
		// 가로
		for (int i = 0; i < N; i++) {
			if(canMakeRoad(grid[i])) {
				COUNT++;
			}
		} // i
		
		
		// 세로
		for (int j = 0; j < N; j++) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = grid[i][j];
			}
			if(canMakeRoad(arr)) {
				COUNT++;
			}
		}
		
		return COUNT;
	} // solve

	static boolean canMakeRoad(int[] arr) {
		int now = arr[0];
		int cnt = 1;
		List<Integer> list = new ArrayList<>();	// 설치한 인덱스 추가
		
		for (int i = 1; i < N; i++) {
			if (now == arr[i])
				cnt++;
			else if (now + 1 == arr[i]) {
				if (cnt < X) return false;
				
				int idx = i-1;
				int runCnt = X;
				while(true) {
					if(runCnt <= 0) break;
					if(list.contains(idx)) return false;
					list.add(idx--);
					runCnt--;
				}
				now = arr[i];
				cnt = 1;
			} else if (now - 1 == arr[i]) {
				int cnt2 = 1;
				list.add(i);
				for (int j = i + 1; j < N; j++) {
					if (now - 1 == arr[j]) {
						list.add(j);
						cnt2++;
					}
					else break;
					if (cnt2 >= X) {
						now = arr[j];
						i = j;
						break;
					}
				}
				if (cnt2 < X) return false;
				cnt=1;
			} else
				return false;
		}

		return true;
	} // road
}
