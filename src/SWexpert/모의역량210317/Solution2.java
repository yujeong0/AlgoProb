package SWexpert.모의역량210317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
	static int N, M;
	static int[][] map = new int[310][310];
	static List<int[]> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int testcase = 1; testcase <= T; testcase++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				list.add(new int[] {Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2)});
			}
			
			sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int solve() {
		for (int k = 1; k <= 300; k++) {
			
			for (int i = 0; i < 300 ; i++) {
				if(i+k > 300) break;
				for (int j = 0; j < 300; j++) {
					if(j+k > 300) break;
					
					int cnt = 0;
					for (int idx = 0; idx < N; idx++) {
						int[] arr = list.get(idx);
						int x1 = arr[0], y1 = arr[1], x2 = arr[2], y2 = arr[3];
						if(x1 >= i && y1 >= j && x2 <= i+k && y2 <= j+k) {
							cnt++;
							if(N-M <= cnt) return k;
						}
					}
					
				} // j
			} // i
		} // k
		
		return 300;
	}
	
}
