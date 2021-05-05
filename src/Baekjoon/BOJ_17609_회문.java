package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17609_회문 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			MIN = 2;
			checkPal(false, s, 0, s.length()-1);
			System.out.println(MIN);
		}
	} // main

	static int MIN;
	static void checkPal(boolean isUse, String s, int start, int idx) {
		for (int i = start; i < s.length(); i++) {
			if (i >= idx) {
				break;
			}
			if (s.charAt(i) != s.charAt(idx)) {
				if (isUse) return;
				if (s.charAt(i) == s.charAt(idx - 1)) {
					checkPal(true, s, i+1, idx-2);
				}
				if (MIN == 2 && s.charAt(i + 1) == s.charAt(idx)) {
					checkPal(true, s, i+2, idx-1);
				}
				return;
			}
			idx--;
		}
		if (isUse) MIN = 1;
		else MIN = 0;
		return;
	}
}
