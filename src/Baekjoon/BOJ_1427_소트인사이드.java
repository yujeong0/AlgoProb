package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1427_소트인사이드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Integer[] arr = new Integer[s.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.charAt(i)-'0';
		}
		Arrays.sort(arr);
		for (int i = arr.length-1; i >= 0; i--) {
			System.out.print(arr[i]);
		}
	}
}
