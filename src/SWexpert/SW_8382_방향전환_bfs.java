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
			
			if(cur.d == HOR) { // 세로 이동
				nx = cur.x;
				ny = cur.y-1;
				if(ny >= 0 && !visited[VER][nx][ny]) {
					visited[VER][nx][ny] = true;
					queue.offer(new Point(nx, ny, VER, cur.cnt+1));
				}
				ny = cur.y+1;
				if(ny <= 200 && !visited[VER][nx][ny]) {
					visited[VER][nx][ny] = true;
					queue.offer(new Point(nx, ny, VER, cur.cnt+1));
				}
			} else {  // 가로 이동 
				nx = cur.x-1;
				ny = cur.y;
				if(nx >= 0 && !visited[HOR][nx][ny]) {
					visited[HOR][nx][ny] = true;
					queue.offer(new Point(nx, ny, HOR, cur.cnt+1));
				}
				nx = cur.x+1;
				if(nx <= 200 && !visited[HOR][nx][ny]) {
					visited[HOR][nx][ny] = true;
					queue.offer(new Point(nx, ny, HOR, cur.cnt+1));
				}
			}
			
		}
 		
		return 0;
	}
}
