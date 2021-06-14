package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N == K) System.out.println(0);
		else {
			new BOJ_13549_숨바꼭질().solve();
		}
		
	} // main
	
	static int N, K;
	void solve() {
		boolean[] visited = new boolean[100001];
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) return o1[2]-o2[2];
				return o1[1]-o2[1];
			}
		});
		
		queue.add(new int[] {N, 0, 0});
		visited[N] = true;
		
		int order = 0;
		int[] dir = new int[] {-1, +1};
		while(!queue.isEmpty()) {
			order++;
			int[] p = queue.poll();
			if(p[0] == K){
				System.out.println(p[1]); 
				return;
			}
			int np = p[0] * 2;
			if(isInBound(np) && !visited[np]) {
				queue.add(new int[] {np, p[1], order});
				visited[np] = true;
			}
			for(int d = 0; d < 2; d++) {
				np = p[0]+dir[d];
				if(isInBound(np) && !visited[np]) {
					queue.add(new int[] {np, p[1]+1, order});
					visited[np] = true;
				}
			}
		}
	}
	
	boolean isInBound(int x) {
		if(x < 0 || x > 100000) return false;
		return true;
	}
}
