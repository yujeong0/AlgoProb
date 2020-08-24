package SWexpert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
	시간복잡도를 따져보면,
	상하좌우 4가지 탐색을 거치므로, 20x20 그리드라고 했을 때 2^400 가까이 가지만,
	알파벳이 26개니까 아무리 최대로 가도
	재귀 depth가 26 벗어나지 않는다.
	즉 이 문제는 dfs가 더 유리하다.
	
	비트마스킹으로도 가능하다.
	내 풀이처럼 list 자꾸 넘기지말고 boolean[] visited 26개짜리 만들어서 -'A' 해가지고 0~25 이걸로도 할 수 있다 
	그러니까 두가지방법 더 짜보자
 */

public class SW_7699_수지의수지맞는여행 {
	static int R, C;
	static char[][] grid;
	static int MAX;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		List<Character> alphalist = new ArrayList<>();
		
		int T = sc.nextInt();
		for (int testcase = 1; testcase <= T; testcase++) {
			R = sc.nextInt();
			C = sc.nextInt();
			grid = new char[R + 1][C + 1];
			MAX = 0;

			for (int i = 1; i <= R; i++) {
				String str = sc.next();
				for (int j = 1; j <= C; j++) {
					grid[i][j] = str.charAt(j-1);
					
				}
			}
			
			alphalist.add(grid[1][1]);
			dfs(1, 1, 1, alphalist);
			alphalist.clear();
			
			sb.append("#" + testcase + " " + MAX + "\n");
		}

		System.out.println(sb.toString());
		
		sc.close();
	}
	
	private static void dfs(int posX, int posY, int count, List<Character> list) {
		if(MAX == 26) return;	// 어차피 26 초과할 수 없으니까
		
		for (int d = 0; d < 4; d++) {
			int x = posX + dir[d][0];
			int y = posY + dir[d][1];
			if(isInBound(x, y) && !list.contains(grid[x][y])) {
				list.add(grid[x][y]);
				dfs(x, y, count+1, list);
				list.remove(list.size()-1);
			}
		}
		
		MAX = Math.max(MAX, count);
	}

	static boolean isInBound(int x, int y) {
		if (x > R || x < 1 || y < 1 || y > C) {
			return false;
		}
		return true;
	}
}
