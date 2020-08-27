package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_7793_오나의여신님 {
	static class Point {
		int x, y, count;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	static int N, M;
	static char[][] map;
	static Queue<Point> sQueue, devQueue;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
//	static List<Point> stone = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sQueue = new LinkedList<>();
			devQueue = new LinkedList<>();
			
			map = new char[N][];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				map[i] = st.nextToken().toCharArray();
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 'S') {
						sQueue.offer(new Point(i, j));
						map[i][j] = '.';	// 수연이 위치 넣었으니까 .으로 바꿔야함 
					}
					
					else if(map[i][j] == '*')
						devQueue.offer(new Point(i, j));
				}
			}
			
			sb.append("#" + testcase + " " + solve() + "\n");
		}
		
		System.out.println(sb.toString());
	}

	private static String solve() {
		int count = 0, result = -1;
		int dSize, sSize;
		L:while(true) {
			++count;
			// 악마
			dSize = devQueue.size();
			while(dSize-- > 0) {
				Point p = devQueue.poll();
				for (int d = 0; d < 4; d++) {
					int x = p.x + dir[d][0];
					int y = p.y + dir[d][1];
					
					if(isInBound(x, y) && map[x][y] == '.') {
						devQueue.offer(new Point(x, y));
						map[x][y] = '*';
					}
				}
			}
			
			// 수연
			sSize = sQueue.size();
			if(sSize == 0) break;
			while(sSize-- > 0) {
				Point p = sQueue.poll();
				for (int d = 0; d < 4; d++) {
					int x = p.x + dir[d][0];
					int y = p.y + dir[d][1];
					
					if(isInBound(x, y)) {
						if(map[x][y] == 'D') {
							result = count;
							break L;
						}
						if(map[x][y] == '.') {
							sQueue.offer(new Point(x, y));
							map[x][y] = 'S';
						}
					}
				}
			}
		}
		return result>-1? Integer.toString(result) : "GAME OVER";
	}
	
	private static boolean isInBound(int x, int y) {
	if(x >= N || x < 0 || y < 0 || y >= M)
		return false;
	
	return true;
}
}
