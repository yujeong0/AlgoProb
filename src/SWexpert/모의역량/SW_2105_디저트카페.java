package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2105_디저트카페 {

	static int N;
	static int[][] grid = new int[22][22];
	static boolean[][] visited = new boolean[22][22];
	static int[][] dir = { {1, 1}, {1, -1}, {-1, -1}, {-1, 1} };
	static int MAX, count;
	static int startx, starty;
	
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
					
					startx = i;
					starty = j;
					
					dfs(startx, starty, 0, list, 0);
					
				} //j
			} //i
			
			sb.append("#" + testcase + " " + MAX + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	private static void dfs(int posx, int posy, int d, List<Integer> list, int count) {
		count++;
		
		int x, y;
		for(int direction = d; direction <= d+1; direction++) {	// 같은 방향으로도 가보고 다음 방향으로도 꺾어보고!
			if(direction == 4) return;
			
			x = posx + dir[direction][0];
			y = posy + dir[direction][1];
			
			if(isInBound(x, y)) {
				if(direction == 3 && startx == x && starty == y) {
					if(MAX < count) MAX = count;
					return;
				}
				
				if(!visited[x][y] && !list.contains(grid[x][y])) {
					visited[x][y] = true;
					
					List<Integer> newList = new ArrayList<>();
					for(int num : list) {
						newList.add(num);
					}
					newList.add(grid[x][y]);
					dfs(x, y, direction, newList, count);

					visited[x][y] = false;
				}
			}
		} // for
		
	}
	
	private static boolean isInBound(int x, int y) {
		if(x >= N || x < 0 || y < 0 || y >= N)
			return false;
		
		return true;
	}
} // end of class
