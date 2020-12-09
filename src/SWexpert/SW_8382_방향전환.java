package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_8382_방향전환 {
	static class Position {
		int x, y, d, cnt;

		public Position(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", d=" + d + ", cnt=" + cnt + "]";
		}
		
	}
	static int x1, x2, y1, y2;
	static int MIN;
	static int[][] dir = { {-1, 0},{1, 0},{0, 1},{0, -1} };	// 0, 1: 수평  / 2, 3: 수직
	static final int HOR = 0, VER = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken())+100;
			y1 = Integer.parseInt(st.nextToken())+100;
			x2 = Integer.parseInt(st.nextToken())+100;
			y2 = Integer.parseInt(st.nextToken())+100;
			
			if(x1 == x2 && y1 == y2) {
				sb.append("#").append(testcase).append(" 0\n");
			}
			else sb.append("#").append(testcase).append(" ").append(solve()).append("\n");
		} // tc
		
		System.out.println(sb.toString());
	} // main
	
	private static int solve() {
 		Queue<Position> q = new LinkedList<>();
 		boolean[][][] visited = new boolean[201][201][2];
 		
 		visited[x1][y1][HOR] = true;
 		visited[x1][y1][VER] = true;
 		
		q.offer(new Position(x1, y1, HOR, 0));
		q.offer(new Position(x1, y1, VER, 0));
 		
 		Position p;
 		int nx, ny;
 		while(!q.isEmpty()) {
			p = q.poll();
			
			if(p.d == HOR) {	// 수평으로 온 경우 다음 이동은 수직이동
				for (int i = 2; i <= 3; i++) {
					nx = p.x;
					ny = p.y + dir[i][1];
					if(ny >= 0 && ny <= 200 && !visited[nx][ny][VER]) {
						if(nx == x2 && ny == y2) {
							return p.cnt+1;
						}
						visited[nx][ny][VER] = true;
						q.offer(new Position(nx, ny, VER, p.cnt+1));
					}
				}
			}
			else {	// 수직으로 온 경우 수평이동만가능
				for (int i = 0; i <= 1; i++) {
					nx = p.x + dir[i][0];
					ny = p.y;
					if(nx >= 0 && nx <= 200 && !visited[nx][ny][HOR]) {
						if(nx == x2 && ny == y2) {
							return p.cnt+1;
						}
						visited[nx][ny][HOR] = true;
						q.offer(new Position(nx, ny, HOR, p.cnt+1));
					}
				}
			}
			
		} // while
		
 		return 0;
		
	} // solve
}
