package SWexpert;

import java.util.Arrays;
import java.util.Scanner;

/*
	시간복잡도를 따져보면,
	상하좌우 4가지 탐색을 거치므로, 20x20 그리드라고 했을 때 2^400 가까이 가지만,
	알파벳이 26개니까 아무리 최대로 가도
	재귀 depth가 26 벗어나지 않는다.
	즉 이 문제는 dfs가 더 유리하다.
	
3
2 4
CAAB
ADCB
3 6
HFDFFB
AJHGDH
DGAGEH
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH


#1 3
#2 6
#3 10
 */

public class SW_7699_수지의수지맞는여행_비트마스킹 {
	static int R, C;
	static char[][] grid;
	static int MAX;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[] alpha = new boolean[26];	// 알파벳 나왔는지 체크
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int testcase = 1; testcase <= T; testcase++) {
			R = sc.nextInt();
			C = sc.nextInt();
			grid = new char[R + 1][C + 1];
			MAX = 0;

			Arrays.fill(alpha, false);
			for (int i = 1; i <= R; i++) {
				String str = sc.next();
				for (int j = 1; j <= C; j++) {
					grid[i][j] = str.charAt(j-1);
				}
			}
			
			alpha[grid[1][1] - 'A'] = true;
			dfs(1, 1, 1);
			
			sb.append("#" + testcase + " " + MAX + "\n");
		}

		System.out.println(sb.toString());
		
		sc.close();
	}
	
	private static void dfs(int posX, int posY, int count) {
		if(MAX == 26) return;	// 어차피 26 초과할 수 없으니까
		MAX = Math.max(MAX, count);
		
		for (int d = 0; d < 4; d++) {
			int x = posX + dir[d][0];
			int y = posY + dir[d][1];
			if(isInBound(x, y) && !alpha[grid[x][y] - 'A']) {	
				alpha[grid[x][y] - 'A'] = true;		//비트마스킹...............?
				dfs(x, y, count+1);
				alpha[grid[x][y] - 'A'] = false;
			}
		}
		
	}

	static boolean isInBound(int x, int y) {
		if (x > R || x < 1 || y < 1 || y > C) {
			return false;
		}
		return true;
	}
}
