package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {
	static int n, m;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			switch(order) {
			case 0:
				union(a, b);
				break;
			case 1:
				int p1 = find(a);
				int p2 = find(b);
				System.out.println(p1 == p2? "YES" : "NO");
				break;
			}
		}
		
	} // main
	
	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		int p1 = find(x);
		int p2 = find(y);
		
		parents[p2] = p1;
	}
}
