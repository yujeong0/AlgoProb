//package Baekjoon;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class Baekjoon_10282_해킹 {
//
//	static class Node {
//		int n, time;
//		Node(int n, int time){
//			this.n = n;
//			this.time = time;
//		}
//	}
//	
//	static int n, d, c, totalTime, count;
//	static List<Node>[] adjList;
//	static boolean[] visited = new boolean[10001];
//	static int[] minCost = new int[10001];
//	
//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		int a, b, s;
//		adjList = new ArrayList[10001];
//		
//		for (int i = 1; i <= 10000; i++) {
//			adjList[i] = new ArrayList<>();
//		}
//		for (int testcase = 0; testcase < T; testcase++) {
//			totalTime = -1;
//			count = 0;
//			
//			st = new StringTokenizer(br.readLine(), " ");
//			n = Integer.parseInt(st.nextToken());	// 컴퓨터 개수
//			d = Integer.parseInt(st.nextToken());	// 의존성 개수
//			c = Integer.parseInt(st.nextToken());	// 해킹당한 컴퓨터의 번호 -> 출발지
//			
//			for (int i = 1; i <= n; i++) {
//				adjList[i].clear();
//				Arrays.fill(visited, false);
//				Arrays.fill(minCost, Integer.MAX_VALUE);
//			}
//
//			for (int i = 0; i < d; i++) {
//				st = new StringTokenizer(br.readLine(), " ");
//				a = Integer.parseInt(st.nextToken());	
//				b = Integer.parseInt(st.nextToken());	
//				s = Integer.parseInt(st.nextToken());	// 감염시간
//				
//				adjList[b].add(new Node(a, s));
//			}	// end of input
//			
//			dijkstra();
//			
//			sb.append(count + " " + totalTime +"\n");
//		}	// end of tc
//		
//		System.out.println(sb.toString());
//	}	// end of main
//	
//	private static void dijkstra() {
//		minCost[c] = 0;	// 출발지 비용 0
//		
//		int min, current;
//		for (int v = 1; v <= n; v++) {
//			current = -1;
//			min = Integer.MAX_VALUE;
//			for (int i = 1; i <= n; i++) {
//				if(!visited[i] && min > minCost[i]) {
//					min = minCost[i];
//					current = i;
//				}
//			}
//			if(current == -1) break;
//			
//			count++;
//			visited[current] = true;
//			
//			for (Node to: adjList[current]) {	// 비용 update
//				if(!visited[to.n] && to.time != 0 && minCost[to.n] > minCost[current] + to.time)
//					minCost[to.n] = minCost[current] + to.time;
//			}
//			
//		}
//		
//		for (int i = 1; i <= n; i++) {	// 몇 초 걸렸는지 찾는다
//			if(minCost[i] != Integer.MAX_VALUE && totalTime < minCost[i])
//				totalTime = minCost[i];
//		}
//		
//	}
//}
