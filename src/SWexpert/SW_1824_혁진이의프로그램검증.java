package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1824_혁진이의프로그램검증 {
	
	static char[][] grid;
	static int R,C;
	static boolean[][][][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			grid = new char[R][];
			
			boolean golbang = false;
			for (int i = 0; i < R; i++) {
				grid[i] = br.readLine().toCharArray();
				
				if(!golbang) {
					for (int j = 0; j < C; j++) {
						if(grid[i][j] == '@') {
							golbang = true;
							break;
						}
					}
				}
			}
			
			if(golbang) {
				if(grid[0][0] != '@') {
					end = false;
					visited = new boolean[R][C][16][4];
					visited[0][0][0][1] = true;
					solve(1, 0, 0, 0);
				}
				else end = true;
			} 
			else end = false;
			
			if(end) sb.append("#").append(testcase).append(" ").append("YES").append("\n");
			else sb.append("#").append(testcase).append(" ").append("NO").append("\n");
			
		} //tc
		
		System.out.println(sb.toString());
	} // main
	
	static int[][] dir = { {-1, 0},{0, 1},{1, 0},{0, -1} };
	static boolean end;
	private static void solve(int curDir, int curX, int curY, int memory) {
		
 		int x = curX, y = curY;
 		char ch;
 		while(true) {
 			if(end) return;
 			ch = grid[x][y];
			switch(ch) {
			case '<':
				curDir = 3;
				break;
			case '>':
				curDir = 1;
				break;
			case '^':
				curDir = 0;
				break;
			case 'v':
				curDir = 2;
				break;
			case '_':
				if(memory == 0) {
					curDir = 1;
				} else {
					curDir = 3;
				}
				break;
			case '|':
				if(memory == 0) {
					curDir = 2;
				} else {
					curDir = 0;
				}
				break;
			case '?':
				int nx, ny;
				for (int d = 0; d < 4; d++) {
					nx = x+dir[d][0];
					ny = y+dir[d][1];
					if(nx >= R) nx = 0;
					else if(ny >= C) ny = 0;
					else if(nx < 0) nx = R-1;
					else if(ny < 0) ny = C-1;
					
					if(!visited[nx][ny][memory][d]) {
						visited[nx][ny][memory][d] = true;
						solve(d, nx, ny, memory);
						if(end) return;
					}
				}
				return;
			case '.':
				break;
			case '@':
				end = true;
				break;
			case '+':
				if(memory == 15) memory = 0;
				else memory++;
				break;
			case '-':
				if(memory == 0) memory = 15;
				else memory--;
				break;
			default:
				memory = ch - '0';
				break;
			}
			
			if(end) return;
			
			if(ch != '?') {
				x += dir[curDir][0];
				y += dir[curDir][1];
				if(x >= R) x = 0;
				else if(y >= C) y = 0;
				else if(x < 0) x = R-1;
				else if(y < 0) y = C-1;
				if(!visited[x][y][memory][curDir])
					visited[x][y][memory][curDir] = true;
				else break;
			}
 		} // while
		
	} //solve
	
}
