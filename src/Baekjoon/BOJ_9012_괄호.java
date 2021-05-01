package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9012_괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		char[] arr;
		for (int i = 0; i < T; i++) {
			arr = br.readLine().toCharArray();
			
			boolean isEnd = false;
			int front = 0;
			for (int j = 0; j < arr.length; j++) {
				if(arr[j] == '(') front++;
				else {	// ')'
					if(front == 0) {
						System.out.println("NO");
						isEnd = true;
						break;
					}
					else {
						front--;
					}
				}
				
			}
			if(!isEnd) {
				if(front > 0) System.out.println("NO");
				else System.out.println("YES");
			}
			front = 0;
		}
	} // main
}
