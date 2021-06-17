package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9251_LCS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		
		arr = new int[s1.length()+1][s2.length()+1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				arr[i][j] = -1;
			}
		}
		
		solve();

	} // main
	
	static String s1, s2;
	static int[][] arr;
	static void solve() {
		
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i-1) != s2.charAt(j-1)) 
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				else 
					arr[i][j] = arr[i-1][j-1] + 1;
			}
		}
		
		System.out.println(arr[s1.length()][s2.length()]);
 		
	} // solve
}
