package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_8382_방향전환 {
	
	static int x1, x2, y1, y2;
	static int MIN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
		} // tc
		
	} // main
	
	private static void solve() {
 		
	} // solve
}
