package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의SW_2115_벌꿀채취 {
	static int N, M, C, MAX;
	static int[][] map;
	static int[][] money;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MAX = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			money = new int[N][N-M+1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N-M+1; y++) {
					int sum = money[x][y];
					for (int i = x; i < N; i++) {
						for (int j = 0; j < N-M+1; j++) {
							if(x == i && Math.abs(y-j) < M) continue;
							
							if(MAX < sum + money[i][j]) MAX = sum + money[i][j];
						} //j
					}//i
					
				}// y
			}//x
			
			System.out.println("#" + testcase  + " " + MAX);
		} //tc
		
	} //main

	private static void solve() {
		for (int i = 0; i < N; i++) {
			int order = 0;
			for (int j = 0; j <= N-M; j++) {
				int[] arr = new int[M];
				int idx = 0;
				for (int k = j; k < j+M; k++) {
					arr[idx++] = map[i][k];
				} // k

				moneyMAX = 0;
				getMAX(arr, 0, 0, 0);
				money[i][order] = moneyMAX;
				order++;
			} // j
		} // i
	} //solve
	
	static int moneyMAX;
	private static void getMAX(int[] arr, int cur, int sum, int zegopSum) {
		if(cur == arr.length) {
			if(moneyMAX < zegopSum) moneyMAX = zegopSum; 
			return;
		}
		
		if(sum + arr[cur] <= C)
			getMAX(arr, cur+1, sum+arr[cur], zegopSum+arr[cur]*arr[cur]);
		
		getMAX(arr, cur+1, sum, zegopSum);
	}
}
