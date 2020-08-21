package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_1463_1로만들기 {
	static int N;
	static int answer = 999999999;
	
	static class Number {
		int num;
		int count;
		
		Number(int num, int count){
			this.num = num;
			this.count = count;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
//		dfs(N, 0);
		if(N != 1)
			bfs(N, 0);
		
		else answer = 0;
		
		System.out.println(answer);
		sc.close();
	}
	
	private static void bfs(int number, int count) {
		Queue<Number> q = new LinkedList<>();
		
		q.offer(new Number(number, count));
		
		while(!q.isEmpty()) {
			Number n = q.poll();
			if(n.num == 1) {
				answer = Math.min(answer, n.count);
			}
			else {
				if(n.num % 3 == 0) {
					q.offer(new Number(n.num/3, n.count+1));
				}
				if(n.num % 2 == 0) {
					q.offer(new Number(n.num/2, n.count+1));
				}
				q.offer(new Number(n.num-1, n.count+1));
			}
		}
	}
	
	private static void dfs(int number, int count) {
		if(number == 1) {
			answer = Math.min(answer, count);
			return;
		}
		
		if(number % 3 == 0)
			dfs(number/3, count+1);
		
		if(number % 2 == 0)
			dfs(number/2, count+1);
		
		dfs(number-1, count+1);
		
	}
}
