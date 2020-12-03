package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_8382_방향전환 {
	
	static int x1, x2, y1, y2;
	static int[][] dir = { {1, 0},{-1, 0},{0, 1},{0, -1} };	// dir 0/1 -> 가로이동, 2/3 -> 세로이동
	static int MIN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = 1000;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			if(x1 == x2 && y1 == y2) 
				System.out.println("#" + testcase + " 0\n");
			else {
				for (int d = 0; d < 4; d++) {
					solve(x1+dir[d][0], y1+dir[d][1], d, 1);
				}
				
				System.out.println("#" + testcase + " " + MIN + "\n");
			}
		} // tc
		
	} // main
	
	private static void solve(int x, int y, int curDir, int cnt) {
		if(x < -100 || y < -100 || x > 100 || y > 100) return;
		if(cnt >= MIN) return;
		
		if(x == x2 && y == y2) {
			if(MIN > cnt) MIN = cnt;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			if( (curDir == 0 || curDir == 1) && (d == 0 || d == 1)) continue;
			if( (curDir == 2 || curDir == 3) && (d == 2 || d == 3)) continue;
			
			solve(x+dir[d][0], y+dir[d][1], d, cnt+1);
		}
 		
	} // solve
}
