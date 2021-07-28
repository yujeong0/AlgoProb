package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[N];
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
		}
		
		for (int i = 0; i < N; i++) {
			while(!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
				numbers[stack.pop()] = numbers[i];
			}
			
			stack.push(i);
		} // for
		
		while(!stack.isEmpty()) {
			numbers[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(numbers[i] + " ");
		}
		System.out.println(sb.toString());
		
	} // main
}
