package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_8382_방향전환_bfs {
	
	static int x1, x2, y1, y2;
	static final int HOR = 0;
	static final int VER = 1;
	
	static class Point {
		int x, y, d, cnt;

		public Point(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;
			
			if(x1 == x2 && y1 == y2) 
				System.out.println("#" + testcase + " 0\n");
			else {
				for (int d = 0; d < 4; d++) {
					solve(x1+dir[d][0], y1+dir[d][1], d, 1);
				}
				
				System.out.println("#" + testcase + " " + bfs() + "\n");
			}
		} // tc
		
	} // main
	
	private static int bfs(int x, int y, int curDir, int cnt) {
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[2][201][201];
		
		visited[HOR][x1][y1] = true;
		queue.offer(new Point(x1, y1, HOR, 0));
		visited[VER][x1][y1] = false;
		queue.offer(new Point(x1, y1, VER, 0));
		
		for (int d = 0; d < 4; d++) {
			if( (curDir == 0 || curDir == 1) && (d == 0 || d == 1)) continue;
			if( (curDir == 2 || curDir == 3) && (d == 2 || d == 3)) continue;
			
			solve(x+dir[d][0], y+dir[d][1], d, cnt+1);
		}
 		
		return 0;
	}
}
