package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		String s = br.readLine();
		String bomb = br.readLine();
		int bombLen = bomb.length();
		
		char[] ans = new char[s.length()];
		int ansIdx = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			ans[ansIdx++] = c;
			if(c != bomb.charAt(bombLen-1)) continue;
			boolean isSame = true;
			int idx = ansIdx-2;
			for (int j = bombLen-2; j >= 0; j--) {
				if(idx < 0) {
					isSame = false;
					break;
				}
				if(ans[idx--] != bomb.charAt(j)) {
					isSame = false;
					break;
				}
			}
			if(isSame) {
				ansIdx -= bombLen;
			}
		}
		
		if(ansIdx == 0)	System.out.println("FRULA");
		else System.out.println(String.valueOf(ans, 0, ansIdx));
		
	} // main
}
