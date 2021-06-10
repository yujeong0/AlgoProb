package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {
	public static void main(String[] args) throws Exception {
		new BOJ_1520_내리막길().solve();
	} // main
	
	int M, N;
	int[][] map, answer;
	boolean[][] visited;
	void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		answer = new int[M][N];
		visited = new boolean[M][N];
		isEnd = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	void solve() throws Exception {
		input();
		visited[0][0] = true;
		dfs(0, 0);
		
		System.out.println(answer[0][0]);
	} // solve
	
	int[][] dir = new int[][] { {-1,0},{0,1},{1,0},{0,-1} };
	boolean[][] isEnd;
	int dfs(int x, int y) {
		if(isEnd[x][y] || answer[x][y] > 0) {
			return answer[x][y];
		}
		if(x == M-1 && y == N-1) {
			return 1;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
		
			if(!isInBound(nx, ny) || visited[nx][ny] || map[nx][ny] >= map[x][y]) continue;
			visited[nx][ny] = true;
			answer[x][y] += dfs(nx, ny);
			visited[nx][ny] = false;
		}
		
		isEnd[x][y] = true;
		return answer[x][y];
	} // dfs
	
	boolean isInBound(int x, int y) {
		if(x < 0 || y < 0 || x >= M || y >= N) return false;
		return true;
	}
}
