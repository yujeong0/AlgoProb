package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			solve(A, B);
		} // t
	
	} // main
	
	static class Info {
		int A;
		String orders;
		public Info(int a, String orders) {
			A = a;
			this.orders = orders;
		}
	};
	
	static void solve(int A, int B) {
		boolean[] visited = new boolean[10000];
		Queue<Info> queue = new LinkedList<>();
		queue.offer(new Info(A, ""));
		visited[A] = true;
		
		while(!queue.isEmpty()) {
			Info p = queue.poll();

			// D
			if(p.A != 0) {
				int num = (p.A*2) % 10000;
				if(!visited[num]) {
					if(num == B) {
						System.out.println(p.orders+"D");
						return;
					}
					visited[num] = true;
					queue.offer(new Info(num, p.orders+"D"));
				}
			}
			
			// S
			{
				int num = p.A == 0? 9999 : p.A-1;
				if(!visited[num]) {
					if(num == B) {
						System.out.println(p.orders+"S");
						return;
					}
					visited[num] = true;
					queue.offer(new Info(num, p.orders+"S"));
				}
			}
			
			// L
			{
				int num = (p.A % 1000) * 10 + (p.A / 1000);
				if(!visited[num]) {
					if(num == B) {
						System.out.println(p.orders+"L");
						return;
					}
					visited[num] = true;
					queue.offer(new Info(num, p.orders+"L"));
				}
			}
			
			// R
			{
				int num = (p.A % 10) * 1000 + p.A / 10;
				if(!visited[num]) {
					if(num == B) {
						System.out.println(p.orders+"R");
						return;
					}
					visited[num] = true;
					queue.offer(new Info(num, p.orders+"R"));
				}
			}
			
		}
		
	} // solve
	
}
