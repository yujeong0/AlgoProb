package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2941_크로아티아알파벳 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] alphabets = {"c=","c-","dz=","d-","lj","nj","s=","z="};
		String s = br.readLine();
		for (int i = 0; i < alphabets.length; i++) {
			s = s.replaceAll(alphabets[i], "*");
		}
		System.out.println(s.length());
	}
}
