package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2105_디저트카페 {

	static int N;
	static int[][] grid = new int[22][22];
	static boolean[][] visited = new boolean[22][22];
	static int[][] dir = { {-1, 1}, {1, 1}, {1, -1}, {-1, -1} };
	static int MAX, count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input
			
			
			MAX = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {	// visited 초기화
						for (int k2 = 0; k2 < N; k2++) 
						visited[k][k2] = false;
					}
					List<Integer> list = new ArrayList<>();
					list.add(grid[i][j]);
					visited[i][j] = true;
					count = 0;
					dfs(i, j, 0, list);
					
					if(MAX < count) MAX = count;
				} //j
			} //i
			
			sb.append("#" + testcase + " " + MAX + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	private static void dfs(int startx, int starty, int d, List<Integer> list) {
		count++;
		if(d == 3 && list.get(0) == grid[startx][starty]) {
			if(MAX < count) MAX = count;
			return;
		}
		
		int x = startx;
		int y = starty;
		
		while(true) {
			x += dir[d][0];
			y += dir[d][1];
			
			if(isInBound(x, y) && !visited[x][y] && !list.contains(grid[x][y])) {
				visited[x][y] = true;
				List<Integer> newList = new ArrayList<>();
				for(int num : list) {
					newList.add(num);
				}
				newList.add(grid[x][y]);
				dfs(x, y, d+1, newList);
				visited[x][y] = false;
			}
			else break;
		}
		
	}
	
	private static boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y <0 || y >= N)
			return false;
		
		return true;
	}
} // end of class
