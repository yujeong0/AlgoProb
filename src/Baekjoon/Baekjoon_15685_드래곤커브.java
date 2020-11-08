package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_15685_드래곤커브 {

	static int N;
	static int[][] dragon;
	static int[][] grid = new int[101][101];
	static int[][] dir = { {1, 0}, {0, -1},{-1, 0} ,{0, 1} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dragon = new int[N][4];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			dragon[i][0] = Integer.parseInt(st.nextToken());
			dragon[i][1] = Integer.parseInt(st.nextToken());
			dragon[i][2] = Integer.parseInt(st.nextToken());
			dragon[i][3] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		
		System.out.println(getRect());
	} //main
	
	private static void solve() {
		for (int i = 0; i < N; i++) { // 커브 수만큼
			
			List<Integer> list = new ArrayList<>();
			list.add(dragon[i][2]);
			for (int gen = 1; gen <= dragon[i][3]; gen++) {
				for(int d = list.size()-1; d >= 0; d--) {
					list.add((list.get(d)+1) % 4);
				}
			} // gen
			
			int x = dragon[i][0];
			int y = dragon[i][1];
			grid[y][x] = 1;
			
			for (int d : list) {
				x += dir[d][0];
				y += dir[d][1];
				
				grid[y][x] = 1;
			}
			
		}
		
	} // solve
	
	private static int getRect() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(grid[i][j] == 1 && grid[i+1][j] == 1 && grid[i][j+1] == 1 && grid[i+1][j+1] == 1) {
					cnt++;
				}
			
			}
		}
		
		return cnt;
	}
	
} // class
