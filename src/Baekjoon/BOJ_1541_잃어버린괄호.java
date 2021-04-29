package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1541_잃어버린괄호 {
	static String str;
	static List<String> list;

	public static void main(String[] args) throws Exception {
		input();
		
		int SUM = Integer.parseInt(list.get(0));
		int i = 1;
	out:while(true) {
			if(i >= list.size()) break;
			char oper = list.get(i).charAt(0);
			if (oper == '-') {
				int sum = Integer.parseInt(list.get(i + 1));
				int idx = i+2;
				while(true) {
					if(idx >= list.size()) {
						SUM -= sum;
						break out;
					}
					if(list.get(idx).charAt(0) != '+') break;
					if(list.get(idx).charAt(0) == '+') {
						sum += Integer.parseInt(list.get(idx+1));
						idx+=2;
					}
				}
				SUM -= sum;
				i = idx;
			} else {
				SUM += Integer.parseInt(list.get(i + 1));
				i+=2;
			}
		}

		System.out.println(SUM);

	} // main

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		list = new ArrayList<>();
		String s = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				s += str.charAt(i);
			} else {
				list.add(s);
				s = "";
				list.add(Character.toString(str.charAt(i)));
			}
		}
		if (s.length() > 0)
			list.add(s);
	}
}
