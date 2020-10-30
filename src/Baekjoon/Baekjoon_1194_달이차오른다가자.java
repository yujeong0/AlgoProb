package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_1194_달이차오른다가자 {
	static class Position {
		int x, y;
		int keys;
		int moveCnt;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position(int x, int y, int moveCnt) {
			this.x = x;
			this.y = y;
			this.keys = 0;
			this.moveCnt = moveCnt;
		}
		
		public Position(int x, int y, int moveCnt, int keys) {
			this.x = x;
			this.y = y;
			this.keys = keys;
			this.moveCnt = moveCnt;
		}
		
	}
	
	static int N, M;
	static char[][] map;
	static Position minsik;	// 민식이 위치
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		visited = new boolean[N][M][64];
		boolean m = false;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			if(!m) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == '0') {
						minsik = new Position(i, j);
						m = true;
						map[i][j] = '.';
						break;
					}
				}
			}
		}
		
		System.out.println(bfs());
		
	} //main
	
	private static int bfs() {
		Queue<Position> q = new LinkedList<>();
		visited[minsik.x][minsik.y][0] = true;
		q.offer(new Position(minsik.x, minsik.y, 0));
		
		Position p;
		int x, y;
		while(!q.isEmpty()) {
			p = q.poll();
			
			for (int d = 0; d < 4; d++) {		
				int key = p.keys;
				x = p.x + dir[d][0];
				y = p.y + dir[d][1];
				
				if(x >= 0 && x < N && y < M && y >= 0) {
					if(map[x][y] == '#') continue;
					if(map[x][y] == '1') return p.moveCnt+1;
					
					if(map[x][y] >= 'A' && map[x][y] <= 'F') {	// A~F (문)
						if((p.keys & (1 << map[x][y] -'A')) == 0) {	// 문에 해당하는 키가 있는지!!
							continue;
						}
					}
					if(map[x][y] >= 'a' && map[x][y] <= 'f') {	// 열쇠!
						key = p.keys | (1 << map[x][y] -'a');
					}
					
					if(visited[x][y][key]) continue;

					visited[x][y][key] = true;
					q.offer(new Position(x, y, p.moveCnt+1, key));
				}
			}
			
		} // while
		
		return -1;
	} // bfs
} //class
