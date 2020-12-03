package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_8382_방향전환_bfs_XOR {
	
	static int x1, x2, y1, y2;
	static final int HOR = 0;
	static final int VER = 1;
	
	static int[][][] dir = {
			{ {-1, 0},{1, 0} },	// hor : 0
			{ {0, -1},{0, 1} }	// ver : 1
	};
	
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
			
			System.out.println("#" + testcase + " " + bfs() + "\n");
		} // tc
		
	} // main
	
	private static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[2][201][201];
		
		visited[HOR][x1][y1] = true;
		queue.offer(new Point(x1, y1, HOR, 0));
		visited[VER][x1][y1] = false;
		queue.offer(new Point(x1, y1, VER, 0));
		
		Point cur;
		int nx, ny;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			if(cur.x == x2 && cur.y == y2) return cur.cnt;
			
			int[][] d = dir[cur.d^1];	// xor 하면 hor일ㄸ ㅐ ver 되고 ver 일 떄 hor 됨
			for (int i = 0; i < d.length; i++) {
				nx = cur.x + d[i][0];
				ny = cur.y + d[i][1];
			
				if(nx >= 0 && nx <= 200 && ny >= 0 && ny <= 200 && !visited[cur.d^1][nx][ny]) {
					visited[cur.d^1][nx][ny] = true;
					queue.offer(new Point(nx, ny, cur.d^1, cur.cnt+1));
				}
			}
			
		} // while
 		
		return 0;
	}
}
