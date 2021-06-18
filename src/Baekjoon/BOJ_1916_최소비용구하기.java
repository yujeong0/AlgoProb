package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기 {
	static class Node implements Comparable<Node> {
		int e, w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return w - o.w;
		}
	}
	
	static int N, M, S, E;
	static List<Node>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dijkstra();
		
	} // main

	static void dijkstra() {
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[S] = 0;
		pq.offer(new Node(S, 0));
		
		Node cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(visited[cur.e]) continue;
			
			visited[cur.e] = true;
		
			for(Node n : list[cur.e]) {
				if(!visited[n.e] && distance[n.e] > distance[cur.e] + n.w) {
					distance[n.e] = distance[cur.e] + n.w;
					pq.offer(new Node(n.e, distance[n.e]));
				}
			}
		} //while
		
		System.out.println(distance[E]);
	} // solve

}
