package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_4949_균형잡힌세상 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine();
			if(s.charAt(0) == '.') break;
			
			List<Integer> brac = new ArrayList<>();
			int bigFront = 0, smallFront = 0;
			boolean isPass = true;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(c == '[') {
					brac.add(1);
					bigFront++;
				}
				else if(c == '(') {
					brac.add(2);
					smallFront++;
				}
				else if(c == ']') {
					if(bigFront == 0 || brac.get(brac.size()-1) != 1) {
						isPass = false;
						break;
					}
					brac.remove(brac.size()-1);
					bigFront--;
				}
				else if(c == ')') {
					if(smallFront == 0 || brac.get(brac.size()-1) != 2) {
						isPass = false;
						break;
					}
					brac.remove(brac.size()-1);
					smallFront--;
				}
			}
			if(bigFront > 0 || smallFront > 0) isPass = false;
			if(isPass) System.out.println("yes");
			else System.out.println("no");
			
		} //while
	
	}
}
