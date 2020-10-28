package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_6109_추억의2048게임ver2 {

	static int N;
	static int[][] grid;
	static String D;
	static Queue<Integer> q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = st.nextToken();
			
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			
			sb.append("#").append(testcase).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(j != N-1) sb.append(grid[i][j]).append(" ");
					else sb.append(grid[i][j]).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	} // main
	
	private static void solve() {
		
		switch(D) {
		case "left":
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(grid[i][j] == 0) continue;

					q.offer(grid[i][j]);
				}
				List<Integer> list = func();
				int j = 0;
				for (int num : list) {
					grid[i][j++] = num;
				}
				for (; j < N; j++) {
					grid[i][j] = 0;
				}
			}
			break;
		case "right":
			for (int i = 0; i < N; i++) {
				for (int j = N-1; j >= 0; j--) {
					if(grid[i][j] == 0) continue;

					q.offer(grid[i][j]);
				}
				List<Integer> list = func();
				int j = N-1;
				for (int num : list) {
					grid[i][j--] = num;
				}
				for (; j >= 0; j--) {
					grid[i][j] = 0;
				}
			}
			break;
		case "up":
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if(grid[i][j] == 0) continue;

					q.offer(grid[i][j]);
				}
				List<Integer> list = func();
				int i = 0;
				for (int num : list) {
					grid[i++][j] = num;
				}
				for (; i < N; i++) {
					grid[i][j] = 0;
				}
			}
			break;
		case "down":
			for (int j = 0; j < N; j++) {
				for (int i = N-1; i >= 0; i--) {
					if(grid[i][j] == 0) continue;

					q.offer(grid[i][j]);
				}
				List<Integer> list = func();
				int i = N-1;
				for (int num : list) {
					grid[i--][j] = num;
				}
				for (; i >= 0; i--) {
					grid[i][j] = 0;
				}
			}
			break;
		}
		
	} // solve
	
	private static List<Integer> func() {
		List<Integer> list = new ArrayList<>();
		int cur;
		int next;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(q.size() == 0) {
				list.add(cur);
				break;
			}
			next = q.peek();
			if(cur == next) {
				list.add(cur*2);
				q.poll();
			}
			else {
				list.add(cur);
			}
		}
			
		return list;
	}
} //class
 