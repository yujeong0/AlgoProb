package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로_dijkstraPQ {
	static class Edge {
		int to, w;
		Edge(int to, int w){
			this.to = to;
			this.w = w;
		}
	}
	static class Vertex implements Comparable<Vertex>{
		int no, cost;
		Vertex(int no, int cost){
			this.no = no;
			this.cost = cost;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E, K, u, v, w;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());	// 정점 개수
		E = Integer.parseInt(st.nextToken());	// 간선 개수
		
		K = Integer.parseInt(br.readLine());	// 시작점

		List<Edge> adjList[] = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[V+1];
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new Edge(v, w));
		}	// end of input
		
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		distance[K] = 0;
		pq.offer(new Vertex(K, 0));
		
		Vertex current;
		while(!pq.isEmpty()) {
			current = pq.poll();
			if(visited[current.no]) continue;
			
			visited[current.no] = true;
			
			// distance 업데이트
			for (Edge edge : adjList[current.no]) {
				if(!visited[edge.to] && distance[edge.to] > distance[current.no] + edge.w) {
					distance[edge.to] = distance[current.no] + edge.w;
					pq.offer(new Vertex(edge.to, distance[edge.to]));
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE)
				sb.append("INF\n");
			else sb.append(distance[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}	// end of main
}
