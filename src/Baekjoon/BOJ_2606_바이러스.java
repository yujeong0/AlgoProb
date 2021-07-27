package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {
	static List<Integer>[] adjustList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
		int N = Integer.parseInt(br.readLine());
		adjustList = new ArrayList[N+1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			adjustList[i] = new ArrayList<>();
		}
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			adjustList[a].add(b);
			adjustList[b].add(a);
		}
		
		visited[1] = true;
		solve(1);
		
		System.out.println(ANS);
		
	} // main
	
	static int ANS = 0;
	static boolean[] visited;
	private static void solve(int num) {
		for (int i = 0; i < adjustList[num].size(); i++) {
			int next = adjustList[num].get(i);
			if(visited[next]) continue;
			visited[next] = true;
			ANS++;
			solve(next);
		}
		
	} // solve
}
